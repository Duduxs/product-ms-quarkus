package org.edudev.arch.domain;

import javax.validation.constraints.NotNull;

public interface DomainEntity {
    @NotNull
    String getId();
}
