package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InitDataRequest;
import hu.icellmobilsoft.onboarding.java.sample.constant.XsdConstants;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.validation.ValidateXML;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/initData")
public interface IInitDataRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    String postInitData(@ValidateXML(xsdPath = XsdConstants.XSD_PATH) InitDataRequest request) throws BaseException;
}
