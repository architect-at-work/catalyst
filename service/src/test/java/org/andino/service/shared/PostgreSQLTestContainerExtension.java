package org.andino.service.shared;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static java.util.Collections.singletonMap;

public class PostgreSQLTestContainerExtension implements BeforeAllCallback, AfterAllCallback {

	private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(
			DockerImageName.parse("postgres:16.1").asCompatibleSubstituteFor(PostgreSQLContainer.IMAGE));

	@Override
	public void beforeAll(ExtensionContext context) {
		System.out.println("PostgreSQL Container is Running: " + POSTGRES_CONTAINER.isRunning());
		startContainerIfNeed();
	}

	@Override
	public void afterAll(ExtensionContext context) {

	}

	private void startContainerIfNeed() {
		if (!POSTGRES_CONTAINER.isRunning()) {
			POSTGRES_CONTAINER.withEnv(singletonMap("ACCEPT_EULA", "Y")).withTmpFs(singletonMap("/testtmpfs", "rw"));
			POSTGRES_CONTAINER.start();
			System.setProperty("spring.datasource.url", POSTGRES_CONTAINER.getJdbcUrl());
			System.setProperty("spring.datasource.username", POSTGRES_CONTAINER.getUsername());
			System.setProperty("spring.datasource.password", POSTGRES_CONTAINER.getPassword());
		}
	}

}
