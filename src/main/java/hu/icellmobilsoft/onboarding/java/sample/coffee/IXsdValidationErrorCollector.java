package hu.icellmobilsoft.onboarding.java.sample.coffee;

import java.util.List;

import jakarta.xml.bind.ValidationEventHandler;

public interface IXsdValidationErrorCollector extends ValidationEventHandler {

    void clearErrors();
    List<XMLValidationError> getErrors();
}
