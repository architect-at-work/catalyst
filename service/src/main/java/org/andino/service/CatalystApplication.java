/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.andino.service;

import org.andino.catalyst.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:config/datasource.yml", factory = YamlPropertySourceFactory.class)
public class CatalystApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalystApplication.class, args);
	}

}
