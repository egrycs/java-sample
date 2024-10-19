package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineType;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/line")
public interface ILineRest {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String getHello();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineType getLine(@PathParam("id") String id) throws BaseException;

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineListType getAllLines();

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineType postLine(LineType line);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineType putLine(@PathParam("id") String id, LineType line) throws BaseException;

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineType deleteLine(@PathParam("id") String id) throws BaseException;
}
