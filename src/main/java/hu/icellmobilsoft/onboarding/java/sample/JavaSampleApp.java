package hu.icellmobilsoft.onboarding.java.sample;

import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/rest/sampleService")
public class JavaSampleApp extends Application {

    private static SampleLineAction sampleLineAction;

    public JavaSampleApp() {
        sampleLineAction = new SampleLineAction(new InvoiceRepository(), new LineRepository());
        sampleLineAction.loadFromXml("pelda.xml", "xsd/sampleInvoice.xsd");
    }

    public static SampleLineAction getSampleLineAction() {
        return sampleLineAction;
    }
}
