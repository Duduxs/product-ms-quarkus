package org.edudev.arch.db;

import com.mongodb.ConnectionString;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public final class MongoConfigurationProducer {

    private final String url;

    @Inject
    MongoConfigurationProducer(@ConfigProperty(name = "MONGODB_URL") final String url) { this.url = url; }

    @Produces
    public MongoConfig produce() {
        return new MongoConfig(new ConnectionString(this.url));
    }
}

