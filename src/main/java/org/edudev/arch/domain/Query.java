package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class Query {

    @QueryParam("q")
    @DefaultValue("")
    private final String query;

    @QueryParam("min_price")
    @DefaultValue("")
    private final String minPrice;

    @QueryParam("max_price")
    @DefaultValue("")
    private final String maxPrice;

    public Query() {
        this.query = "";
        this.minPrice = "";
        this.maxPrice = "";
    }

    public Query(final String query, final String minPrice, final String maxPrice) {
        this.query = query;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Boolean isEmpty() { return query.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty(); }

    public String getQuery() { return query; }

    public String getMinPrice() { return minPrice; }

    public String getMaxPrice() { return maxPrice; }
}
