package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Property;
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
public class PropertyRepository implements Repository<Property, Long>{
    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;

    private Class<Property> getEntityClass() {
        return Property.class;
    }

    private String getEntityClassName() {
        return Property.class.getName();
    }
    
    @Override
    @Transactional
    public List findAll() {
        TypedQuery<Property> query
                = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }
    
    @Override
    @Transactional
    public Property create(Property property) {
        entityManager.persist(property);
        return property;
    }


    @Override
    public Optional<Property> findById(Long id) {
        try {
            Property t = entityManager.find(getEntityClass(), id);
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured", e);
             return Optional.empty();  
        }
    }

  
    
    @Override
    public boolean deleteById(Long id) {
        Property property = entityManager.find(Property.class, id);
        if (property != null) {
            try {
                property.setDeleted(true);
                entityManager.merge(property);
            } catch (Exception e) {
                System.out.println("An exception occurred");
            }
            return true;
        }
        return false;
    }
    
    
}
