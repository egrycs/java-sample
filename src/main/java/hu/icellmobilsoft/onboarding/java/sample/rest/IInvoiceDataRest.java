package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.constant.XsdConstants;
import hu.icellmobilsoft.onboarding.java.sample.validation.ValidateXML;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Path("/invoice")
public interface IInvoiceDataRest {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataResponse getInvoiceData(@PathParam("id") String id) throws BaseException;

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataListQueryResponse invoiceDataQuery(@ValidateXML(xsdPath = XsdConstants.XSD_PATH) InvoiceDataListQueryRequest request) throws BaseException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataResponse postInvoiceData(@ValidateXML(xsdPath = XsdConstants.XSD_PATH) InvoiceDataRequest request);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataResponse putInvoiceData(@PathParam("id") String id, @ValidateXML(xsdPath = XsdConstants.XSD_PATH) InvoiceDataRequest request) throws BaseException;

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataResponse deleteInvoice(@PathParam("id") String id) throws BaseException;
}
