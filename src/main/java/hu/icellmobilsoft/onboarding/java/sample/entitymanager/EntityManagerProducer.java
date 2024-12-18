package hu.icellmobilsoft.onboarding.java.sample.entitymanager;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Dependent
public class EntityManagerProducer {

    @PersistenceContext(unitName = "javaSamplePU")
    private EntityManager em;

    @Dependent
    @Produces
    public EntityManager produceEntityManager() {
        System.out.println("EntityManager: " + em.toString());
        return em;
    }
}
