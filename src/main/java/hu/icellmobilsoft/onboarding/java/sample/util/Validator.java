package hu.icellmobilsoft.onboarding.java.sample.util;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class Validator {

    public static void validateByXsd(String xmlUri, String xsdUri) {
        try {
            // XSD séma betöltése
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdUri));

            // Validator létrehozása
            javax.xml.validation.Validator validator = schema.newValidator();

            // XML fájl validálása
            validator.validate(new StreamSource(new File(xmlUri)));
            System.out.println("XML file is valid against the schema.");

        } catch (SAXException e) {
            System.out.println("XML file is NOT valid.");
            System.out.println("Reason: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to read files.");
            e.printStackTrace();
        }
    }
}
