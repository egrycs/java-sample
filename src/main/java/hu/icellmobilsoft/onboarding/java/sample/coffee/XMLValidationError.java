package hu.icellmobilsoft.onboarding.java.sample.coffee;

import java.io.Serializable;

public class XMLValidationError implements Serializable {
    private static final long serialVersionUID = 1L;
    private int lineNumber;
    private int columnNumber;
    private String error;

    public XMLValidationError() {
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}
