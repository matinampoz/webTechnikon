package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Owner;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author matin
 */
@Slf4j
@RequestScoped
public class OwnerRepository implements Repository<Owner, Long> {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;

    private Class<Owner> getEntityClass() {
        return Owner.class;
    }

    private String getEntityClassName() {
        return Owner.class.getName();
    }

    @Override
    @Transactional
    public List findAll() {
        TypedQuery<Owner> query
                = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        try {
            Owner t = entityManager.find(getEntityClass(), id);
            return Optional.of(t);
        } catch (Exception e) {
            log.debug("An exception occured", e);
            return Optional.empty();
        }
    }

    @Transactional
    public Owner findOwnerByVat(String vat) {
        try {
            TypedQuery<Owner> typedQuery = entityManager.createQuery("from Owner where vatNumber =: data", Owner.class);
            typedQuery.setParameter("data", vat);
            return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(OwnerRepository.class.getName()).log(Level.WARNING, "No owner found with VAT: ", vat);
            return null;
        }
    }

    @Transactional
    public Owner findOwnerByEmail(String email) {
        try {
            TypedQuery<Owner> typedQuery = entityManager.createQuery("from Owner where email =: data", Owner.class);
            typedQuery.setParameter("data", email);
            return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(OwnerRepository.class.getName()).log(Level.WARNING, "No owner found with email: ", email);
            return null;
        }
    }

    @Override
    @Transactional
    public Owner create(Owner owner) {
        entityManager.persist(owner);
        return owner;
    }

    public boolean changeVisabilityById(Long id, boolean deleted) {
        Owner owner = entityManager.find(Owner.class, id);
        if (owner != null) {
            try {
                owner.setDeleted(deleted);
                entityManager.merge(owner);
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
