package org.edudev;

import org.edudev.arch.db.MongoConfig;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/hello")
public class GreetingResource {

    @Inject
    MongoConfig configTest;

    private final Logger log = Logger.getLogger(GreetingResource.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.warning(configTest.getPassword());
        return "hey";
    }
}