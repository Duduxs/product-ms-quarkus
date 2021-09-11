package org.edudev.arch.domain;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public final class Page {

    @QueryParam("first")
    @DefaultValue("0")
    private final Long first;

    @QueryParam("size")
    @DefaultValue("10")
    private final Long size;

    public Page() {
        this.first = 0L;
        this.size = 10L;
    }

    public Page(final Long first, final Long last) {
        this.first = first;
        this.size = last;
    }

    public Long getFirst() { return first; }

    public Long getSize() { return size; }

}


