package org.edudev.domain.products;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/products")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProductsService {

    @Inject
    Products products;

    @Inject
    ProductDTOMapper productMapper;

    @GET
    @Path("{id}")
    public ProductDTO findById(@PathParam("id") final String id) {
        return productMapper.map(products.findById(id).orElse(null));
    }
}
