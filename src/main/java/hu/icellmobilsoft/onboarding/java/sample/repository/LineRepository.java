package hu.icellmobilsoft.onboarding.java.sample.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.util.BaseException;

public class LineRepository {
    private List<Line> lines;

    public LineRepository() {
        this.lines = new ArrayList<>();
    }

    public Line saveLine(Line line) {
        Optional<Line> existingLine = findLine(line.getId());
        if (existingLine.isPresent()) {
            Line currentLine = existingLine.get();
            currentLine.setName(line.getName());
            currentLine.setQuantity(line.getQuantity());
            currentLine.setUnitOfMeasure(line.getUnitOfMeasure());
            currentLine.setCustomUnitOfMeasure(line.getCustomUnitOfMeasure());
            currentLine.setUnitPrice(line.getUnitPrice());
        } else {
            if (line.getId() == null) {
                String lastLineId = lines.get(lines.size() - 1).getId();
                int nextLineId = Integer.parseInt(lastLineId) + 1;
                line.setId(String.format("%06d", nextLineId));
            }
            lines.add(line);
        }

        return line;
    }

    public List<Line> getAllLines() {
        return lines;
    }

    public List<Line> getLines(Collection<String> ids) {
        return lines.stream().filter(line -> ids.contains(line.getId())).toList();
    }

    public Line getLine(String id) throws BaseException {
        Optional<Line> optionalLine = findLine(id);
        if (optionalLine.isEmpty()) {
            throw new BaseException("Entity not found!");
        }

        return optionalLine.get();
    }

    public Optional<Line> findLine(String id) {
        return lines.stream().filter(invoice -> invoice.getId().equals(id)).findFirst();
    }

    public Line deleteLine(String id) throws BaseException {
        Line deletedline = getLine(id);
        lines.removeIf(line -> line.getId().equals(id));

        return deletedline;
    }
}
