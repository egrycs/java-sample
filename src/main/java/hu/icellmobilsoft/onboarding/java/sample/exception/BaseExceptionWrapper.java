package hu.icellmobilsoft.onboarding.java.sample.exception;

public interface BaseExceptionWrapper<E extends BaseException> {
    void setException(E var1);

    E getException();
}
