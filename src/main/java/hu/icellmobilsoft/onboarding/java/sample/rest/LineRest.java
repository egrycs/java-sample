package hu.icellmobilsoft.onboarding.java.sample.rest;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Model
public class LineRest implements ILineRest {

    @Inject
    private SampleLineAction sampleLineAction;

    public String getHello() {
        return "Hello world!";
    }

    public LineResponse getLine(String id) throws BaseException {
        LineResponse response = new LineResponse();
        response.setLine(sampleLineAction.getLine(id));

        return response;
    };

    public LineListQueryResponse lineQuery(LineListQueryRequest request) throws BaseException {
        LineListQueryType queryParam = request.getQueryParam();
        LineListQueryResponse response = new LineListQueryResponse();
        response.setLines(sampleLineAction.lineQuery(queryParam));

        return response;
    };

    public LineResponse postLine(LineRequest request) {
        LineType line = request.getLine();
        line.setId(null);
        LineResponse response = new LineResponse();
        response.setLine(sampleLineAction.saveLine(line));

        return response;
    }

    public LineResponse putLine(String id, LineRequest request) throws BaseException {
        LineType line = request.getLine();
        getLine(id);
        line.setId(id); // a payload-ban az id nem szerepel, ezért külön be kell állítani
        LineResponse response = new LineResponse();
        response.setLine(sampleLineAction.saveLine(line));

        return response;
    }

    public LineResponse deleteLine(String id) throws BaseException {
        LineResponse response = new LineResponse();
        response.setLine(sampleLineAction.deleteLine(id));

        return response;
    }
}
