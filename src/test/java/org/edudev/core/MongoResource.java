package org.edudev.core;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.logging.Logger;

public final class MongoResource implements QuarkusTestResourceLifecycleManager {

    private final MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    private final Logger log = Logger.getLogger(MongoResource.class.getName());

    @Override
    public Map<String, String> start() {
        this.container.start();
        this.log.info(
                String.format("* * * Inicializando mongo de testes em %s * * * ", this.container.getReplicaSetUrl())
        );

        return Map.of("MONGODB_URL", this.container.getReplicaSetUrl());
    }

    @Override
    public void stop() {
        this.container.stop();
    }
}
