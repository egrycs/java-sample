import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Xml parser
 */
public class App {

    public static void main(String[] args) throws Exception {
        URL peldaXmlUrl = App.class.getClassLoader().getResource("pelda.xml");
        URL peldaJsonUrl = App.class.getClassLoader().getResource("pelda.json");
        SampleLineAction sampleLineAction = new SampleLineAction(new InvoiceRepository(), new LineRepository());
        String peldaXmlString = new String(Files.readAllBytes(Paths.get(peldaXmlUrl.toURI())));
        // sampleLineAction.loadFromXml(peldaXmlString);
        String peldaJsonString = new String(Files.readAllBytes(Paths.get(peldaJsonUrl.toURI())));
        sampleLineAction.loadFromJson(peldaJsonString);
        System.out.println(sampleLineAction.getAllInvoicesData());
        // System.out.println(sampleLineAction.getInvoiceData("12345"));
        // System.out.println(sampleLineAction.getInvoiceData("54321"));
        // System.out.println(sampleLineAction.getInvoiceData("32154"));
    }
}
