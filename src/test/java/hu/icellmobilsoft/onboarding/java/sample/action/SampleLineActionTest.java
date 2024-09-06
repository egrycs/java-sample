package hu.icellmobilsoft.onboarding.java.sample.action;

import hu.icellmobilsoft.onboarding.java.sample.model.InvoiceData;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.rest.LineDeleteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class SampleLineActionTest {

    private final LineRepository lineRepository = new LineRepository();

    private final SampleLineAction sampleLineAction = new SampleLineAction(new InvoiceRepository(), lineRepository);

    private String peldaXmlString;

    private String peldaJsonString;

    @BeforeEach
    void setUp() throws URISyntaxException, IOException {
        URL peldaXmlUrl = SampleLineActionTest.class.getClassLoader().getResource("pelda.xml");
        URL peldaJsonUrl = SampleLineActionTest.class.getClassLoader().getResource("pelda.json");
        peldaXmlString = new String(Files.readAllBytes(Paths.get(peldaXmlUrl.toURI())));
        peldaJsonString = new String(Files.readAllBytes(Paths.get(peldaJsonUrl.toURI())));
    }

    @Test
    void testLoadFromXml() {
        sampleLineAction.loadFromXml(peldaXmlString);

        assertFalse(lineRepository.getAllLines().isEmpty());
    }

    @Test
    void testLoadFromJson() {
        sampleLineAction.loadFromJson(peldaJsonString);

        assertFalse(lineRepository.getAllLines().isEmpty());
    }

    @Test
    void testGetInvoiceData() {
        sampleLineAction.loadFromJson(peldaJsonString);
        InvoiceData result = sampleLineAction.getInvoiceData("12345");

        assertNotNull(result);
    }

    @Test
    void testDeleteLineSuccess() throws LineDeleteException {
        sampleLineAction.loadFromJson(peldaJsonString);
        Line deletedLine = sampleLineAction.deleteLine("000004");

        assertFalse(lineRepository.getAllLines().isEmpty());
        assertNotNull(deletedLine);
    }

    @Test
    void testDeleteLineAssignedToInvoice() {
        sampleLineAction.loadFromJson(peldaJsonString);
        LineDeleteException thrown = assertThrows(LineDeleteException.class, () -> {
            sampleLineAction.deleteLine("000001");
        });

        assertTrue(thrown.getMessage().contains("is assigned to an invoice"));
    }
}
