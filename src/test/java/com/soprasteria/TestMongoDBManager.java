package com.soprasteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;

public class TestMongoDBManager implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static MongoDBContainer mongoDbContainer;
    private final Logger log = LoggerFactory.getLogger(TestMongoDBManager.class);

    @Override
    @SuppressWarnings("resource")
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        if (mongoDbContainer != null) {
            return;
        }

        mongoDbContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:7.0.2"))
                .withTmpFs(Collections.singletonMap("/testtmpfs", "rw"))
                .withLogConsumer(new Slf4jLogConsumer(log))
                .withReuse(true)
                // Utilisation de Podman en local -> nécessité de disable ryuk qui est spécifique à Docker
                .withEnv("TESTCONTAINERS_RYUK_DISABLED", "true");

        mongoDbContainer.start();

        System.setProperty("TEST_MONGODB_URI", mongoDbContainer.getReplicaSetUrl());
        Runtime.getRuntime().addShutdownHook(new Thread(stopContainer()));
    }

    private Runnable stopContainer() {
        return mongoDbContainer::stop;
    }
}
