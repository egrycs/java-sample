import java.math.BigDecimal;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.*;
import hu.icellmobilsoft.onboarding.java.sample.action.SampleLineAction;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.util.InvoiceDataTypeConverter;
import hu.icellmobilsoft.onboarding.java.sample.util.LineConverter;

/**
 * App
 */
public class App {

    public static void main(String[] args) throws Exception {
        SampleLineAction sampleLineAction = new SampleLineAction(new InvoiceRepository(), new LineRepository());
        InvoiceDataTypeConverter invoiceDataTypeConverter = new InvoiceDataTypeConverter();
        LineConverter lineConverter = new LineConverter();
        sampleLineAction.loadFromXml("pelda.xml", "xsd/sampleInvoice.xsd");

        // sampleLineAction.loadFromJson("pelda.json");

        // sampleLineAction.getAllInvoiceData()
        // .getInvoiceData()
        // .forEach(invoiceDataType -> System.out.println(invoiceDataTypeConverter.convert(invoiceDataType)));

        // System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.getInvoiceData("12345")));
        // System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.getInvoiceData("54321")));
        // System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.getInvoiceData("32154")));

        // InvoiceDataListQueryType invoiceListQuery = new InvoiceDataListQueryType();

        // invoiceListQuery.setInvoiceNumber("SIMMPLE");
        // sampleLineAction.invoiceDataQuery(invoiceListQuery)
        // .getInvoiceData()
        // .forEach(invoiceDataType -> System.out.println(invoiceDataTypeConverter.convert(invoiceDataType)));

        // invoiceListQuery.setInvoiceType("NORMAL");
        // sampleLineAction.invoiceDataQuery(invoiceListQuery)
        // .getInvoiceData()
        // .forEach(invoiceDataType -> System.out.println(invoiceDataTypeConverter.convert(invoiceDataType)));

        // System.out.println(sampleLineAction.deleteLine("000003")); // BaseException
        // System.out.println(lineConverter.convert(sampleLineAction.deleteLine("000004")));
        // System.out.println(sampleLineAction.deleteLine("000004")); // Entity not found!

        /*{
            "invoice": {
            "invoiceNumber": "JAVA2024-10/20",
                    "invoiceType": "SIMPLE",
                    "supplierTaxNumber": "32468972",
                    "customerTaxNumber": "31415926",
                    "lines": {
                "line": [
                "000001",
                        "000002",
                        "000003"
            ]
            },
            "sumPrice": 2700
        },
            "lines": {
            "line": [
            {
                "name": "Batáta",
                    "quantity": 1,
                    "unitOfMeasure": "KG",
                    "customUnitOfMeasure": null,
                    "unitPrice": 1300
            },
            {
                "name": "Körte",
                    "quantity": 1,
                    "unitOfMeasure": "KG",
                    "customUnitOfMeasure": null,
                    "unitPrice": 800
            },
            {
                "name": "Újhagyma",
                    "quantity": 3,
                    "unitOfMeasure": "CUSTOM",
                    "customUnitOfMeasure": "köteg",
                    "unitPrice": 200
            }
        ]
        }
        }*/

        InvoiceDataType invoiceDataType = new InvoiceDataType();

        InvoiceType invoiceType = new InvoiceType();
        invoiceType.setInvoiceNumber("JAVA2024-10/20");
        invoiceType.setInvoiceType("NORMAL");
        invoiceType.setSupplierTaxNumber("32468972");
        invoiceType.setCustomerTaxNumber("31415926");
        InvoiceLineListType invoiceLineListType = new InvoiceLineListType();
        invoiceLineListType.getLine().add("000001");
        invoiceLineListType.getLine().add("000002");
        invoiceLineListType.getLine().add("000003");
        invoiceType.setLines(invoiceLineListType);
        invoiceType.setSumPrice(new BigDecimal(2700));

        LineListType lineListType = new LineListType();

        LineType lineType1 = new LineType();
        lineType1.setName("Batáta");
        lineType1.setQuantity(1);
        lineType1.setUnitOfMeasure(UnitOfMeasureType.KG);
        lineType1.setCustomUnitOfMeasure(null);
        lineType1.setUnitPrice(new BigDecimal(1300));

        lineListType.getLine().add(lineType1);

        LineType lineType2 = new LineType();
        lineType2.setName("Körte");
        lineType2.setQuantity(1);
        lineType2.setUnitOfMeasure(UnitOfMeasureType.KG);
        lineType2.setCustomUnitOfMeasure(null);
        lineType2.setUnitPrice(new BigDecimal(800));

        lineListType.getLine().add(lineType2);

        LineType lineType3 = new LineType();
        lineType3.setName("Újhagyma");
        lineType3.setQuantity(3);
        lineType3.setUnitOfMeasure(UnitOfMeasureType.CUSTOM);
        lineType3.setCustomUnitOfMeasure("köteg");
        lineType3.setUnitPrice(new BigDecimal(200));

        lineListType.getLine().add(lineType3);

        invoiceDataType.setInvoice(invoiceType);
        invoiceDataType.setLines(lineListType);

        System.out.println(invoiceDataTypeConverter.convert(sampleLineAction.saveInvoiceData(invoiceDataType)));

    }
}
