package hu.icellmobilsoft.onboarding.java.sample.util;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SampleExceptionHandler implements ExceptionMapper<BaseException> {
    @Override
    public Response toResponse(BaseException exception) {
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage()).build();
    }
}
