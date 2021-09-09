package org.edudev.arch.dtos;

import org.edudev.arch.domain.DomainEntity;

import java.util.function.Function;

public class EntityDTOMapper<T extends DomainEntity, DTO> {

    private final Function<T, DTO> mapper;

    private final Function<DTO, T> unmapper;

    public EntityDTOMapper(final Function<T, DTO> mapper, final Function<DTO, T> unmapper) {
        this.mapper = mapper;
        this.unmapper = unmapper;
    }

    public DTO map(final T entity) { return this.mapper.apply(entity); }

    public T unmap(final DTO dto) { return this.unmapper.apply(dto); }
}
