package org.edudev.arch.services;

import com.mongodb.MongoWriteException;
import dev.morphia.query.UpdateException;
import org.edudev.arch.domain.DomainEntity;
import org.edudev.arch.dtos.EntityDTOMapper;
import org.edudev.arch.exceptions.ConflictHttpException;
import org.edudev.arch.exceptions.NotFoundHttpException;
import org.edudev.arch.repositories.GenericRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.logging.Logger;

public class CrudService<T extends DomainEntity, DTO> extends ReadOnlyService<T, DTO> {

    @Context
    private UriInfo uriInfo;

    private final Logger log = Logger.getLogger(CrudService.class.getName());

    public CrudService() {
        super();
    }

    public CrudService(final GenericRepository<T> repository, final EntityDTOMapper<T, DTO> mapper) {
        super(repository, mapper);
    }

    @POST
    public Response save(final DTO dto) {
        final T entity = mapper.unmap(dto);

        try {
            repository.insert(entity);
        } catch (final MongoWriteException e) {
            throw new ConflictHttpException("Entidade com dados já cadastrados no banco");
        }

        final URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", entity.getId())
                .build();

        return Response.created(uri).entity(mapper.map(entity)).build();
    }


    @PUT
    @Path("{id}")
    public DTO update(final @PathParam("id") String id, final DTO dto) {

        if (!repository.exists(id))
            throw new NotFoundHttpException(String.format("A entidade com id %s não existe!", id));

        final T entity = mapper.unmap(dto);

        try {
            repository.update(entity);
        } catch (final UpdateException e) {
            log.info("* * Como não foram passados novos dados para a entidade a mesma não será atualizada. * *");
        }

        return mapper.map(entity);
    }

    @DELETE
    @Path("{id}")
    public void delete(final @PathParam("id") String id) {
        final T entity = baseEntityFromPath(id);
        repository.remove(entity);
    }

}
