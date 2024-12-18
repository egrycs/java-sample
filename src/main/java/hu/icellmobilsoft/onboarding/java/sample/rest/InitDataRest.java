package hu.icellmobilsoft.onboarding.java.sample.rest;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InitDataRequest;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.SourceType;
import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.constant.XsdConstants;

@Model
public class InitDataRest implements IInitDataRest {

    @Inject
    private SampleLineAction sampleLineAction;

    public String postInitData(InitDataRequest request) {
        SourceType source = request.getSource();

        return source.equals(SourceType.XML) ? sampleLineAction.loadFromXml("pelda.xml", XsdConstants.XSD_PATH)
                : sampleLineAction.loadFromJson("pelda.json", XsdConstants.XSD_PATH);
    }
}
