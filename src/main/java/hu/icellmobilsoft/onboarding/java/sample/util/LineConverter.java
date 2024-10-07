package hu.icellmobilsoft.onboarding.java.sample.util;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceLineListType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.InvoiceType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.UnitOfMeasureType;
import hu.icellmobilsoft.onboarding.java.sample.model.Invoice;
import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineType;

public class LineConverter {

    public Line convert(LineType lineType) {
        Line invoiceLine = new Line();

        invoiceLine.setId(lineType.getId());
        invoiceLine.setName(lineType.getName());
        invoiceLine.setQuantity(lineType.getQuantity());
        invoiceLine.setUnitOfMeasure(lineType.getUnitOfMeasure().value());
        invoiceLine.setCustomUnitOfMeasure(lineType.getCustomUnitOfMeasure());
        invoiceLine.setUnitPrice(lineType.getUnitPrice());

        return invoiceLine;
    }

    public LineType convert(Line line) {
        LineType lineType = new LineType();

        lineType.setId(line.getId());
        lineType.setName(line.getName());
        lineType.setQuantity(line.getQuantity());
        lineType.setUnitOfMeasure(UnitOfMeasureType.fromValue(line.getUnitOfMeasure()));
        lineType.setCustomUnitOfMeasure(line.getCustomUnitOfMeasure());
        lineType.setUnitPrice(line.getUnitPrice());

        return lineType;
    }
}
