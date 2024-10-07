import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.util.InvoiceDataTypeConverter;

/**
 * Xml parser
 */
public class App {

    public static void main(String[] args) throws Exception {
        SampleLineAction sampleLineAction = new SampleLineAction(new InvoiceRepository(), new LineRepository());
        InvoiceDataTypeConverter invoiceDataTypeConverter = new InvoiceDataTypeConverter();
        sampleLineAction.loadFromXml("pelda.xml", "xsd/sampleInvoice.xsd");
        // sampleLineAction.loadFromJson("pelda.json");
        sampleLineAction.getAllInvoicesData()
                .getInvoiceData()
                .forEach(invoiceDataType -> System.out.println(invoiceDataTypeConverter.convert(invoiceDataType)));
        // System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.getInvoiceData("12345")));
        // System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.getInvoiceData("54321")));
        // System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.getInvoiceData("32154")));
        // sampleLineAction.queryInvoicesData("SIMMPLE0002", null)
        //         .getInvoiceData()
        //         .forEach(invoiceDataType -> System.out.println(invoiceDataTypeConverter.convert(invoiceDataType)));
        // sampleLineAction.queryInvoicesData(null, "NORMAL")
        //         .getInvoiceData()
        //         .forEach(invoiceDataType -> System.out.println(invoiceDataTypeConverter.convert(invoiceDataType)));
        // System.out.println(sampleLineAction.deleteLine("000003")); // LineDeleteException
        // System.out.println(sampleLineAction.deleteLine("000004"));
        // System.out.println(sampleLineAction.deleteLine("000004")); // Entity not found!
    }
}
