package com.keywer.article.quarkus.rest;

import com.keywer.article.quarkus.domain.Fish;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.apache.http.HttpStatus;

import javax.json.Json;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("/fish")
public class FishResource {

    private static final String X_TOTAL_COUNT = "X-Total-Count";

    @Context
    protected UriInfo uriInfo;

    @GET
    @Path("family/{fishFamily}")
    public long countByFamily(@NotBlank @PathParam("fishFamily") String fishFamily) {
        return Fish.count("family.name", fishFamily);
    }

    @GET
    public Response getAllFish(Page page) {
        PanacheQuery<Fish> allFishs = Fish.findAll().page(page);
        return Response.ok(allFishs.list()).header(X_TOTAL_COUNT, allFishs.count()).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }
}