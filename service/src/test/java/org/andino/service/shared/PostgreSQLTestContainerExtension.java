package org.andino.service.shared;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

public class PostgreSQLTestContainerExtension implements BeforeAllCallback, AfterAllCallback {

    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:16.1")
                    .asCompatibleSubstituteFor(PostgreSQLContainer.IMAGE));

    @Override
    public void beforeAll(ExtensionContext context) {
        System.out.println("PostgreSQL Container Running: " + postgreSQLContainer.isRunning());
        startContainerIfNeed();
    }

    @Override
    public void afterAll(ExtensionContext context) {

    }

    private void startContainerIfNeed() {
        if (!postgreSQLContainer.isRunning()) {
            postgreSQLContainer.withEnv(Collections.singletonMap("ACCEPT_EULA", "Y"))
                    .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"));
            postgreSQLContainer.start();
            System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
            System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
            System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        }
    }

}
