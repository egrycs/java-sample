package hu.icellmobilsoft.onboarding.java.sample.action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceDataType;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.repository.InvoiceRepository;
import hu.icellmobilsoft.onboarding.java.sample.repository.LineRepository;
import hu.icellmobilsoft.onboarding.java.sample.rest.LineDeleteException;

public class SampleLineActionTest {

    private final LineRepository lineRepository = new LineRepository();

    private final SampleLineAction sampleLineAction = new SampleLineAction(new InvoiceRepository(), lineRepository);

    @Test
    void testLoadFromXml() {
        sampleLineAction.loadFromXml("pelda.xml", "xsd/sampleInvoice.xsd");

        assertFalse(lineRepository.getAllLines().isEmpty());
    }

    @Test
    void testLoadFromJson() {
        sampleLineAction.loadFromJson("pelda.json");

        assertFalse(lineRepository.getAllLines().isEmpty());
    }

    @Test
    void testGetInvoiceData() {
        sampleLineAction.loadFromJson("pelda.json");
        InvoiceDataType result = sampleLineAction.getInvoiceData("12345");

        assertNotNull(result);
    }

    @Test
    void testDeleteLineSuccess() throws LineDeleteException {
        sampleLineAction.loadFromJson("pelda.json");
        Line deletedLine = sampleLineAction.deleteLine("000004");

        assertFalse(lineRepository.getAllLines().isEmpty());
        assertNotNull(deletedLine);
    }

    @Test
    void testDeleteLineAssignedToInvoice() {
        sampleLineAction.loadFromJson("pelda.json");
        LineDeleteException thrown = assertThrows(LineDeleteException.class, () -> {
            sampleLineAction.deleteLine("000001");
        });

        assertTrue(thrown.getMessage().contains("is assigned to an invoice"));
    }
}
