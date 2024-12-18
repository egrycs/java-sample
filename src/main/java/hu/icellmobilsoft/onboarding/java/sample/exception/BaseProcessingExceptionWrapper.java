package hu.icellmobilsoft.onboarding.java.sample.exception;

import jakarta.ws.rs.ProcessingException;

public class BaseProcessingExceptionWrapper extends ProcessingException implements BaseExceptionWrapper<BaseException> {
    private static final long serialVersionUID = 1L;
    private BaseException exception;

    public BaseProcessingExceptionWrapper(BaseException exception) {
        super(exception);
        this.exception = exception;
    }

    public void setException(BaseException exception) {
        this.exception = exception;
    }

    public BaseException getException() {
        return this.exception;
    }
}