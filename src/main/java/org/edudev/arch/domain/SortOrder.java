package org.edudev.arch.domain;

public enum SortOrder {
    ASC("ASC"),
    DESC("DESC");

    String asc;

    SortOrder(String asc) {
        this.asc = asc;
    }
}
