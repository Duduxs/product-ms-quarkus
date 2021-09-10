package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class Sort {

    @QueryParam("field")
    @DefaultValue("id")
    private final String field;

    @QueryParam("order")
    @DefaultValue("DESC")
    private final SortOrder type;

    public Sort() {
        this.field = "id";
        this.type = SortOrder.DESC;
    }

    public Sort(final String field, final SortOrder type) {
        this.field = field;
        this.type = type;
    }

    public String getField() { return field; }

    public SortOrder getType() { return type; }

}


