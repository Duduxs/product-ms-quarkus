package org.edudev;

import org.edudev.arch.db.MongoConfig;
import org.edudev.domain.products.Product;
import org.edudev.domain.products.Products;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

import static java.util.logging.Logger.*;

@Path("/hello")
public class GreetingResource {

    @Inject
    MongoConfig configTest;

    @Inject
    Products products;

    private final Logger log = getLogger(GreetingResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product hello() {
        log.warning(configTest.getPassword());
        return products
                .findById("c65275ca-0a04-49ce-82dd-36f9513a1884").orElse(null);
    }
}