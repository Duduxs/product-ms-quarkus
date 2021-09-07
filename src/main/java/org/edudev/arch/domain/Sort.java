package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class Sort {

    @QueryParam("field")
    @DefaultValue("id")
    private String field;

    @QueryParam("order")
    @DefaultValue("DESC")
    private SortOrder type;

    public Sort(final String field, final SortOrder type) {
        this.field = field;
        this.type = type;
    }

    public String getField() { return field; }

    public void setField(final String field) { this.field = field; }

    public SortOrder getType() { return type; }

    public void setType(final SortOrder type) { this.type = type; }
}


