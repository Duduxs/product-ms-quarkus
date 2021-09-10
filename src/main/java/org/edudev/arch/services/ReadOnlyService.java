package org.edudev.arch.services;


import org.edudev.arch.domain.DomainEntity;
import org.edudev.arch.domain.Page;
import org.edudev.arch.domain.Query;
import org.edudev.arch.domain.Sort;
import org.edudev.arch.dtos.EntityDTOMapper;
import org.edudev.arch.exceptions.BadRequestHttpException;
import org.edudev.arch.exceptions.NotFoundHttpException;
import org.edudev.arch.repositories.GenericRepository;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class ReadOnlyService<T extends DomainEntity, DTO> {

    protected final GenericRepository<T> repository;

    protected final EntityDTOMapper<T, DTO> mapper;

    public ReadOnlyService() {
        this.repository = null;
        this.mapper = null;
    }

    public ReadOnlyService(final GenericRepository<T> repository, final EntityDTOMapper<T, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GET
    @Path("{id}")
    public DTO findById(@PathParam("id") final String id) {
        var entity = baseEntityFromPath(id);
        return mapper.map(entity);
    }

    @GET
    public Collection<DTO> list() {
        return repository.list(new Query(), new Sort(), new Page(0, Integer.MAX_VALUE))
                .stream()
                .map(mapper::map)
                .collect(toList());
    }

    @GET
    @Path("/search")
    public Collection<DTO> search(
            @BeanParam final Query query,
            @BeanParam final Page page,
            @BeanParam final Sort sort
    ) {
        if (page.getFirst() < 0 || page.getLast() < 0)
            throw new BadRequestHttpException("Query params [first] e [last] devem ser positivos.");
        else if (page.getFirst() > page.getLast())
            throw new BadRequestHttpException("Query params [first] não deve ser maior que [last].");

        return repository.list(query, sort, page)
                .stream()
                .map(mapper::map)
                .collect(toList());
    }

    protected T baseEntityFromPath(final String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundHttpException(String.format("Entidade com id %s não encontrada.", id)));
    }
}
