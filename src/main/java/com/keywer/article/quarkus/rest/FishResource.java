package com.keywer.article.quarkus.rest;

import com.keywer.article.quarkus.domain.Family;
import com.keywer.article.quarkus.domain.Fish;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.hibernate.engine.spi.QueryParameters;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/fish")
public class FishResource {

    private static final String X_TOTAL_COUNT = "X-Total-Count";

    @Context
    protected UriInfo uriInfo;

    @Inject
    private QueryStringDefaults qsd;

    @GET
    @Path("family/{fishFamily}")
    public int countByFamily(@NotBlank @PathParam("fishFamily") String fishFamily) {
        return Fish.countByFamily(fishFamily);
    }

    @GET
    public Response getAllFish() {
        QueryParameters query = qsd.builder().queryEncoded(uriInfo.getRequestUri().getRawQuery()).build();

        PanacheQuery<Fish> allFishs = Fish.findAll();
        allFishs.page(Page.of(1,25));

        return Response.ok(allFishs.list()).header(X_TOTAL_COUNT, allFishs.count()).build();
    }
}