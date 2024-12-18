package hu.icellmobilsoft.onboarding.java.sample.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.constant.XsdConstants;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.validation.ValidateXML;

@Path("/line")
public interface ILineRest {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String getHello();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineResponse getLine(@PathParam("id") String id) throws BaseException;

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineListQueryResponse lineQuery(@ValidateXML(xsdPath = XsdConstants.XSD_PATH) LineListQueryRequest request) throws BaseException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineResponse postLine(@ValidateXML(xsdPath = XsdConstants.XSD_PATH) LineRequest request);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineResponse putLine(@PathParam("id") String id, @ValidateXML(xsdPath = XsdConstants.XSD_PATH) LineRequest request) throws BaseException;

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    LineResponse deleteLine(@PathParam("id") String id) throws BaseException;
}
