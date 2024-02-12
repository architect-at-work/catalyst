package org.andino.catalyst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class YamlPropertySourceFactoryTest {

	private YamlPropertySourceFactory factory;

	@BeforeEach
	void setup() {
		factory = new YamlPropertySourceFactory();
	}

	@Test
	public void returnFileNameAsCustomPropertySourceWhenCustomNameIsPassed() throws IOException {
		String expectedPropertySourceName = "bootstrap.yml";
		EncodedResource res = new EncodedResource(new ClassPathResource("bootstrap-test.yml"), "UTF-8");

		PropertySource<?> propertiesPropertySource = factory.createPropertySource(expectedPropertySourceName, res);

		assertNotNull(propertiesPropertySource);
		assertEquals(expectedPropertySourceName, propertiesPropertySource.getName());
	}

	@Test
	public void returnFileNameAsPropertySourceWhenCustomNameIsNotPassed() throws IOException {
		EncodedResource res = new EncodedResource(new ClassPathResource("bootstrap-test.yml"), "UTF-8");

		PropertySource<?> propertiesPropertySource = factory.createPropertySource(null, res);

		assertNotNull(propertiesPropertySource);
		assertEquals("bootstrap-test.yml", propertiesPropertySource.getName());
	}

	@Test
	public void returnDefaultFileNameWhenEncodingDoesNotHaveAnything() throws IOException {
		EncodedResource res = new EncodedResource(new ClassPathResource(""), "UTF-8");

		PropertySource<?> propertiesPropertySource = factory.createPropertySource(null, res);

		assertNotNull(propertiesPropertySource);
		assertTrue(propertiesPropertySource.getName().contains("file-"));
	}

	@Test
	public void throwAnIOErrorWhenPassedFileDoesNotExist() {
		EncodedResource res = new EncodedResource(new ClassPathResource("some-invalid-file"), "UTF-8");

		assertThrows(IOException.class, () -> factory.createPropertySource(null, res));
	}

	@Test
	public void throwAnErrorWhenPassedInvalidFile() {
		try (var ignored = Mockito.mockConstruction(YamlPropertiesFactoryBean.class, (mock, context) -> {
			doThrow(new IllegalStateException()).when(mock).getObject();
		})) {
			EncodedResource res = new EncodedResource(new ClassPathResource(""), "UTF-8");

			assertThrows(IllegalStateException.class, () -> factory.createPropertySource(null, res));
		}
	}

	@Test
	public void returnNullWhenBeanReturnsNull() throws IOException {
		try (var ignored = Mockito.mockConstruction(YamlPropertiesFactoryBean.class, (mock, context) -> {
			doReturn(null).when(mock).getObject();
		})) {
			EncodedResource res = new EncodedResource(new ClassPathResource(""), "UTF-8");

			assertNull(factory.createPropertySource(null, res));
		}
	}

}
