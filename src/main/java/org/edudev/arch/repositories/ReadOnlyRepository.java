package org.edudev.arch.repositories;

import org.edudev.arch.domain.*;

import java.util.Collection;
import java.util.Optional;

public interface ReadOnlyRepository<T extends DomainEntity> {

    Optional<T> findById(final String id);

    Collection<T> list(final Query query, final Sort sort, final Page page);
}
