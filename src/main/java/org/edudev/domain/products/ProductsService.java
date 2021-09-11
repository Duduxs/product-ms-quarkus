package org.edudev.domain.products;

import org.edudev.arch.services.CrudService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/products")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProductsService extends CrudService<Product, ProductDTO> {

    public ProductsService() { super(); }

    @Inject
    public ProductsService(final Products repository, final ProductDTOMapper mapper) { super(repository, mapper); }

}
