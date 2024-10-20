package hu.icellmobilsoft.onboarding.java.sample.rest;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListQueryType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineType;
import hu.icellmobilsoft.onboarding.java.sample.JavaSampleApp;
import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;

public class LineRest implements ILineRest {

    private SampleLineAction sampleLineAction;

    public LineRest() {
        sampleLineAction = JavaSampleApp.getSampleLineAction();
    }

    public String getHello() {
        return "Hello world!";
    }

    public LineType getLine(String id) throws BaseException {
        return sampleLineAction.getLine(id);
    };

    public LineListType lineQuery(LineListQueryType lineListQuery) throws BaseException {
        return sampleLineAction.lineQuery(lineListQuery);
    };

    public LineType postLine(LineType line) {
        return sampleLineAction.saveLine(line);
    }

    public LineType putLine(String id, LineType line) throws BaseException {
        getLine(id);
        line.setId(id); // a payload-ban az id nem szerepel, ezért külön be kell állítani
        return sampleLineAction.saveLine(line);
    }

    public LineType deleteLine(String id) throws BaseException {
        return sampleLineAction.deleteLine(id);
    }
}
