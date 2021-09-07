package org.edudev.arch.db;

import com.mongodb.ConnectionString;

public final class MongoConfig {

    private final ConnectionString url;
    private final String username;
    private final String password;

    public MongoConfig(final ConnectionString url, final String username, final String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ConnectionString getUrl() { return url; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }
}