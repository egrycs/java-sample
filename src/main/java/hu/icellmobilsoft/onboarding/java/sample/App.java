package hu.icellmobilsoft.onboarding.java.sample;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import hu.icellmobilsoft.onboarding.java.sample.model.Sample;
import java.io.File;
import java.io.IOException;

/**
 * Xml parser
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Sample sample = xmlMapper.readValue(new File("C:\\git\\java-sample\\src\\main\\resources\\pelda.xml"), Sample.class);
        System.out.println(sample);
    }
}
