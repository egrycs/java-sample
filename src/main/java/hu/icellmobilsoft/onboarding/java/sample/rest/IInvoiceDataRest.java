package hu.icellmobilsoft.onboarding.java.sample.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListQueryType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;

@Path("/invoice")
public interface IInvoiceDataRest {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataType getInvoiceData(@PathParam("id") String id) throws BaseException;

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataListType invoiceDataQuery(InvoiceDataListQueryType invoiceListQuery) throws BaseException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataType postInvoiceData(InvoiceDataType invoiceData);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataType putInvoiceData(@PathParam("id") String id, InvoiceDataType invoiceData) throws BaseException;

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    InvoiceDataType deleteInvoice(@PathParam("id") String id) throws BaseException;
}
