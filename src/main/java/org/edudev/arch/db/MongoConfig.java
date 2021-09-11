package org.edudev.arch.db;

import com.mongodb.ConnectionString;

public final class MongoConfig {

    private final ConnectionString url;

    public MongoConfig(final ConnectionString url) { this.url = url; }

    public ConnectionString getUrl() { return url; }

}