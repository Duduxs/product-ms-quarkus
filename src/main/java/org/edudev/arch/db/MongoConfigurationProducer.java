package org.edudev.arch.db;

import com.mongodb.ConnectionString;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.edudev.GreetingResource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class MongoConfigurationProducer {

    private String url;
    private Optional<String> username;
    private Optional<String> password;

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

    public String getUrl() { return url; }

    public void setUrl(final String url) { this.url = url; }

    public Optional<String> getUsername() { return username; }

    public void setUsername(final Optional<String> username) { this.username = username; }

    public Optional<String> getPassword() { return password; }

    public void setPassword(final Optional<String> password) { this.password = password; }

    @Produces
    public MongoConfig produce() {
        return new MongoConfig(
                new ConnectionString(this.url),
                this.username.orElse(null),
                this.password.orElse(null)
        );
    }
}

