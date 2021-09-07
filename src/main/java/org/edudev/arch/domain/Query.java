package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class Query {

    @QueryParam("q")
    @DefaultValue("")
    private String query;

    @QueryParam("min_price")
    @DefaultValue("")
    private String minPrice;

    @QueryParam("max_price")
    @DefaultValue("")
    private String maxPrice;

    public Query(final String query, final String minPrice, final String maxPrice) {
        this.query = query;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Boolean isEmpty() {
        return query.isEmpty() && minPrice.isEmpty() && maxPrice.isEmpty();
    }

    public String getQuery() { return query; }

    public void setQuery(final String query) { this.query = query; }

    public String getMinPrice() { return minPrice; }

    public void setMinPrice(final String minPrice) { this.minPrice = minPrice; }

    public String getMaxPrice() { return maxPrice; }

    public void setMaxPrice(final String maxPrice) { this.maxPrice = maxPrice; }
}
