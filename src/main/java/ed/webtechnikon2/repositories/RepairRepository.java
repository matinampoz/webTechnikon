package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Repair;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author matin
 */
@Slf4j
@RequestScoped
public class RepairRepository implements Repository<Repair, Long>{
    
    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;
    
    private Class<Repair> getEntityClass() {
        return Repair.class;
    }

    private String getEntityClassName() {
        return Repair.class.getName();
    }

    @Override
    @Transactional
    public Repair create(Repair repair) {
        entityManager.persist(repair);
        return repair;
    }

    @Override
    @Transactional
    public List findAll() {
        TypedQuery<Repair> query
                = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    @Override
    public Optional<Repair> findById(Long id) {
        try {
            Repair t = entityManager.find(getEntityClass(), id);
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured", e);
             return Optional.empty();  
        }
    }

    public boolean changeVisabilityById(Long id, boolean deleted) {
        Repair repair = entityManager.find(Repair.class, id);
        if (repair != null) {
            try {
                repair.setDeleted(deleted);
                entityManager.merge(repair);
            } catch (Exception e) {
                System.out.println("An exception occurred");
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean deleteById(Long id) {
        return changeVisabilityById(id, true);
    }
    
    @Override
    public boolean unDeleteById(Long id) {
        return changeVisabilityById(id, false);
    }
    
    
    
}
