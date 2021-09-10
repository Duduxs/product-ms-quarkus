package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class Page {

    @QueryParam("first")
    @DefaultValue("0")
    private final Integer first;

    @QueryParam("last")
    @DefaultValue("10")
    private final Integer last;

    public Page() {
        this.first = 0;
        this.last = 10;
    }

    public Page(final Integer first, final Integer last) {
        this.first = first;
        this.last = last;
    }

    public Integer getFirst() { return first; }

    public Integer getLast() { return last; }

}


