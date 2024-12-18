package hu.icellmobilsoft.onboarding.java.sample.coffee;

import jakarta.enterprise.context.Dependent;
import jakarta.xml.bind.ValidationEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Dependent
public class XsdValidationErrorCollector implements IXsdValidationErrorCollector {
    private List<XMLValidationError> errors;

    public XsdValidationErrorCollector() {
    }

    public boolean handleEvent(ValidationEvent event) {
        if (!StringUtils.isBlank(event.getMessage()) && (event.getSeverity() == 1 || event.getSeverity() == 2)) {
            XMLValidationError xmlValidationError = new XMLValidationError();
            xmlValidationError.setError(event.getMessage());
            if (!Objects.isNull(event.getLocator())) {
                xmlValidationError.setLineNumber(event.getLocator().getLineNumber());
                xmlValidationError.setColumnNumber(event.getLocator().getColumnNumber());
            }

            this.getErrorList().add(xmlValidationError);
        }

        return true;
    }

    public void clearErrors() {
        this.getErrorList().clear();
    }

    private List<XMLValidationError> getErrorList() {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }

        return this.errors;
    }

    public List<XMLValidationError> getErrors() {
        return Collections.unmodifiableList(this.getErrorList());
    }
}

