package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class Page {

    @QueryParam("first")
    @DefaultValue("0")
    private Integer first;

    @QueryParam("last")
    @DefaultValue("10")
    private Integer last;

    public Page(final Integer first, final Integer last) {
        this.first = first;
        this.last = last;
    }

    public Integer getFirst() { return first; }

    public void setFirst(final Integer first) { this.first = first; }

    public Integer getLast() { return last; }

    public void setLast(final Integer last) { this.last = last; }
}


