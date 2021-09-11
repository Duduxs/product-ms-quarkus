package org.edudev.arch.repositories;

import org.edudev.arch.domain.DomainEntity;

public interface UpdateOnlyRepository<T extends DomainEntity> {

    void update(final T entity);
}
