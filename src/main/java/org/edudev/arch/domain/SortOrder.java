package org.edudev.arch.domain;

public enum SortOrder {
    ASC("ASC"),
    DESC("DESC");

    String type;

    SortOrder(final String type) {
        this.type = type;
    }
}
