package hu.icellmobilsoft.onboarding.java.sample.exception;

public class BaseException extends Exception {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException() {
        super("Alapértelmezett BaseException üzenet");
    }
}