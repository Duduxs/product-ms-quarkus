package org.edudev.arch.repositories;

import org.edudev.arch.domain.DomainEntity;
import org.edudev.arch.domain.Page;
import org.edudev.arch.domain.Query;
import org.edudev.arch.domain.Sort;

import java.util.Collection;
import java.util.Optional;

public interface ReadOnlyRepository<T extends DomainEntity> {

    Optional<T> findById(final String id);

    default Boolean exists(final String id) { return findById(id).isPresent(); }

    Collection<T> list(final Query query, final Sort sort, final Page page);
}
