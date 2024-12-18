package hu.icellmobilsoft.onboarding.java.sample.util;

import jakarta.annotation.Priority;
import jakarta.interceptor.Interceptor;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.BaseResponseType;

@Provider
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Priority(Interceptor.Priority.APPLICATION)
public class JsonResponseMessageBodyWriter extends JsonMessageBodyWriterBase<BaseResponseType> {
}
