package hu.icellmobilsoft.onboarding.java.sample.handler;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class SampleExceptionHandler implements ExceptionMapper<BaseException> {

    @Override
    public Response toResponse(BaseException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }
}
