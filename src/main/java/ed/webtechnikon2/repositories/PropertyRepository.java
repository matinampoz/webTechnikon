package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Property;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
public class PropertyRepository implements Repository<Property, Long> {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;

    private Class<Property> getEntityClass() {
        return Property.class;
    }

    public <T> String getEntityClassName(Class<T> clazz) {
        return clazz.getName();
    }
    
    //@Override
    @Transactional
    public List findAll() {
        String entityName = getEntityClassName(Property.class);
        TypedQuery<Property> query
                = entityManager.createQuery("FROM " + entityName + " p WHERE p.isDeleted = false", Property.class);
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

    public Optional<Property> findPropertiesByOwnersId(Long ownerId) {
        try {
            TypedQuery<Property> query = entityManager.createQuery(
                    "SELECT p FROM Property p WHERE p.propertyOwner.ownerId = :ownerId", Property.class);
            query.setParameter("ownerId", ownerId);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            log.debug("No property found for ID: " + ownerId, e);
            return Optional.empty();
        } catch (Exception e) {
            log.debug("An exception occurred", e);
            return Optional.empty();
        }
    }

    public Optional<Property> findPropertiesByOwnersVat(String vat) {
        try {
            TypedQuery<Property> query = entityManager.createQuery(
                    "SELECT p FROM Property p WHERE p.propertyOwner.vatNumber = :vat", Property.class);
            query.setParameter("vat", vat);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            log.debug("No property found for VAT: " + vat, e);
            return Optional.empty();
        } catch (Exception e) {
            log.debug("An exception occurred", e);
            return Optional.empty();
        }
    }

    @Transactional
    public boolean changeVisabilityById(Long id, boolean deleted) {
        Property property = entityManager.find(Property.class, id);
        if (property != null) {
            try {
                property.setDeleted(deleted);
                entityManager.merge(property);
            } catch (Exception e) {
                System.out.println("An exception occurred");
            }
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return changeVisabilityById(id, true);
    }

    @Override
    public boolean unDeleteById(Long id) {
        return changeVisabilityById(id, false);
    }

}
