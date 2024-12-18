package hu.icellmobilsoft.onboarding.java.sample.handler;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.Providers;

import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseExceptionWrapper;
import hu.icellmobilsoft.onboarding.java.sample.exception.XsdProcessingException;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {

    @Context
    private Providers providers;

    @Override
    public Response toResponse(Exception e) {
        Response result = null;
        if (e instanceof BaseExceptionWrapper) {
            Exception unwrappedException = this.unwrapException((Exception) ((BaseExceptionWrapper) e));
            if (unwrappedException instanceof XsdProcessingException) {
                result = handleWrappedException(unwrappedException);
            }
        }

        return result != null ? result : Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    }

    protected <WRAPPED extends Exception & BaseExceptionWrapper<?>> Exception unwrapException(Exception wrappedException) {
        Object unwrapped;
        if (((BaseExceptionWrapper) wrappedException).getException() != null) {
            unwrapped = ((BaseExceptionWrapper) wrappedException).getException();
        } else {
            unwrapped = wrappedException;
        }

        return (Exception) unwrapped;
    }

    protected <E extends BaseException> Response handleWrappedException(Exception exception) {
        if (exception == null) {
            return null;
        } else {
            ExceptionMapper<E> mapper = (ExceptionMapper<E>) this.providers.getExceptionMapper(exception.getClass());
            if (mapper == null) {
                return null;
            } else {
                return mapper.toResponse((E) exception);
            }
        }
    }
}
