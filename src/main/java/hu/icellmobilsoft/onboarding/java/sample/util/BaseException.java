package hu.icellmobilsoft.onboarding.java.sample.util;

public class BaseException extends Exception {

    // Üzenet alapú konstruktor
    public BaseException(String message) {
        super(message);
    }

    // Üzenet és oka (másik kivétel) alapú konstruktor
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    // Csak az oka (másik kivétel) alapú konstruktor
    public BaseException(Throwable cause) {
        super(cause);
    }

    // Alapértelmezett konstruktor
    public BaseException() {
        super("Alapértelmezett BaseException üzenet");
    }
}