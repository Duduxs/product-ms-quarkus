package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class Query {

    @QueryParam("q")
    @DefaultValue("")
    private final String query;

    @QueryParam("min_price")
    @DefaultValue("0.0")
    private final Double minPrice;

    @QueryParam("max_price")
    @DefaultValue("0.0")
    private final Double maxPrice;

    public Query() {
        this.query = "";
        this.minPrice = 0.0;
        this.maxPrice = 0.0;
    }

    public Query(final String query, final Double minPrice, final Double maxPrice) {
        this.query = query;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Boolean isEmpty() { return query.isEmpty() && minPrice == 0 && maxPrice == 0; }

    public String getQuery() { return query; }

    public Double getMinPrice() { return minPrice; }

    public Double getMaxPrice() { return maxPrice; }
}
