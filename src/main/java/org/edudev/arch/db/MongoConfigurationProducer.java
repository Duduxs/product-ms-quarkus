package org.edudev.arch.db;

import com.mongodb.ConnectionString;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public final class MongoConfigurationProducer {

    private final String url;

    private final Optional<String> username;

    private final Optional<String> password;

    @Inject
    MongoConfigurationProducer(
            @ConfigProperty(name = "MONGODB_URL") final String url,
            @ConfigProperty(name = "MONGODB_USERNAME") final Optional<String> username,
            @ConfigProperty(name = "MONGODB_PASSWORD") final Optional<String> password
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Produces
    public MongoConfig produce() {
        return new MongoConfig(
                new ConnectionString(this.url),
                this.username.orElse(null),
                this.password.orElse(null)
        );
    }
}

