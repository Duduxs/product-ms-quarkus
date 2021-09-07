package org.edudev.arch.repositories;

import org.edudev.arch.domain.DomainEntity;

public interface GenericRepository<T extends DomainEntity> extends InsertOnlyRepository<T>, UpdateOnlyRepository<T>, DeleteOnlyRepository<T>, ReadOnlyRepository<T> { }
