package hu.icellmobilsoft.onboarding.java.sample.exception;

import java.util.ArrayList;
import java.util.List;

import hu.icellmobilsoft.onboarding.java.sample.coffee.XMLValidationError;

public class XsdProcessingException extends BaseException {
    private static final long serialVersionUID = 1L;
    private final List<XMLValidationError> errors = new ArrayList();

    public XsdProcessingException(String message) {
        super(message);
    }

    public XsdProcessingException(String message, Throwable e) {
        super(message, e);
    }

    public XsdProcessingException(List<XMLValidationError> errors, Throwable e) {
        super("Xml validation failed", e);
        this.errors.addAll(errors);
    }

    public List<XMLValidationError> getErrors() {
        return this.errors;
    }
}
