package hu.icellmobilsoft.onboarding.java.sample.util;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.BaseRequestType;
import jakarta.annotation.Priority;
import jakarta.interceptor.Interceptor;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Priority(Interceptor.Priority.APPLICATION)
public class JsonRequestMessageBodyReader extends JsonMessageBodyReaderBase<BaseRequestType> {
}
