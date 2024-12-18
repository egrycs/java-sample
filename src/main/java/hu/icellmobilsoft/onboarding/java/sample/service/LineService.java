package hu.icellmobilsoft.onboarding.java.sample.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.icellmobilsoft.onboarding.dto.sample.invoice.LineListQueryType;
import hu.icellmobilsoft.onboarding.dto.sample.invoice.UnitOfMeasureType;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import hu.icellmobilsoft.onboarding.java.sample.model.Line;
import hu.icellmobilsoft.onboarding.java.sample.exception.BaseException;

@Model
public class LineService {

    @Inject
    private EntityManager em;

    @Transactional
    public Line saveLine(Line line) {
        String lineId = line.getId();
        Line existingLine = null;

        if (lineId != null) {
            existingLine = findLine(lineId);
        }

        if (existingLine != null) {
            existingLine.setName(line.getName());
            existingLine.setQuantity(line.getQuantity());
            existingLine.setUnitOfMeasure(line.getUnitOfMeasure());
            existingLine.setCustomUnitOfMeasure(line.getCustomUnitOfMeasure());
            existingLine.setUnitPrice(line.getUnitPrice());
            em.persist(existingLine);
        } else {
            if (lineId == null) {
                String lastLineId = getLastRecord();
                int nextLineId = Integer.parseInt(lastLineId) + 1;
                line.setId(String.format("%06d", nextLineId));
            }

            em.persist(line);
        }

        return line;
    }

    public List<Line> getAllLines(LineListQueryType lineListQuery) {
        String name = lineListQuery.getName();
        UnitOfMeasureType unitOfMeasure = lineListQuery.getUnitOfMeasure();
        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Line> cq = cb.createQuery(Line.class);
        Root<Line> lineRoot = cq.from(Line.class);
        cq.select(lineRoot);

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.equal(lineRoot.get("name"), name));
        }
        if (unitOfMeasure != null && !unitOfMeasure.value().isEmpty()) {
            predicates.add(cb.equal(lineRoot.get("unitOfMeasure"), unitOfMeasure.value()));
        }
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return em.createQuery(cq).getResultList();
    }

    public List<Line> getLines(Collection<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Line> cq = cb.createQuery(Line.class);
        Root<Line> lineRoot = cq.from(Line.class);

        cq.select(lineRoot).where(lineRoot.get("id").in(ids));

        return em.createQuery(cq).getResultList();
    }

    public Line getLine(String id) throws BaseException {
        Line line = findLine(id);
        if (line == null) {
            throw new BaseException("Entity not found!");
        }

        return line;
    }

    public Line findLine(String id) {
        return em.find(Line.class, id);
    }

    @Transactional
    public Line deleteLine(String id) throws BaseException {
        Line deletedLine = getLine(id);
        em.remove(deletedLine);

        return deletedLine;
    }

    private String getLastRecord() {
        String sql = "SELECT id FROM Line ORDER BY ROWID DESC FETCH FIRST 1 ROW ONLY";

        return (String) em.createNativeQuery(sql).getResultList().get(0);
    }
}
