package org.edudev.arch.repositories;

import org.edudev.arch.domain.DomainEntity;

public interface InsertOnlyRepository<T extends DomainEntity> {

    void insert(final T entity);
}
