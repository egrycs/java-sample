package hu.icellmobilsoft.onboarding.java.sample.repository;

import hu.icellmobilsoft.onboarding.java.sample.model.Line;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class LineRepository {
    private List<Line> lines;

    public LineRepository() {
        this.lines = new ArrayList<>();
    }

    public void saveLine(Line line) {
        Optional<Line> existingLine = findLine(line.getId());
        if (existingLine.isPresent()) {
            Line currentLine = existingLine.get();
            currentLine.setName(line.getName());
            currentLine.setQuantity(line.getQuantity());
            currentLine.setUnitOfMeasure(line.getUnitOfMeasure());
            currentLine.setCustomUnitOfMeasure(line.getCustomUnitOfMeasure());
            currentLine.setUnitPrice(line.getUnitPrice());
        } else {
            lines.add(line);
        }
    }

    public List<Line> getAllLines() {
        return lines;
    }

    public List<Line> getLines(Collection<String> ids) {
        return lines.stream().filter(line -> ids.contains(line.getId())).toList();
    }

    public Optional<Line> findLine(String id) {
        return lines.stream().filter(line -> line.getId().equals(id)).findFirst();
    }

    public Line deleteLine(String id) {
        Line deletedLine = this.findLine(id).orElseThrow(() -> new IllegalStateException("Entity not found!"));
        lines.removeIf(line -> line.getId().equals(id));
        return deletedLine;
    }
}
