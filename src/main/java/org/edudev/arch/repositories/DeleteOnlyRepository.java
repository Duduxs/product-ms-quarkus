package org.edudev.arch.repositories;

import org.edudev.arch.domain.DomainEntity;

public interface DeleteOnlyRepository<T extends DomainEntity> {

    void remove(final T entity);
}
