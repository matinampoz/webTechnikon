package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Owner;
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
        TypedQuery<Owner> typedQuery = entityManager.createQuery("from Owner where vat =: data", Owner.class);
        typedQuery.setParameter("data", vat);
        return typedQuery.getSingleResult();
    }

    @Transactional
    public Owner findOwnerByEmail(String email) {
        TypedQuery<Owner> typedQuery = entityManager.createQuery("from Owner where email =: data", Owner.class);
        typedQuery.setParameter("data", email);
        return typedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public Owner create(Owner owner) {
        entityManager.persist(owner);
        return owner;
    }

    @Override
    public boolean deleteById(Long id) {
        Owner owner = entityManager.find(Owner.class, id);
        if (owner != null) {
            try {
                owner.setDeleted(true);
                entityManager.merge(owner);
            } catch (Exception e) {
                System.out.println("An exception occurred");
            }
            return true;
        }
        return false;
    }
}
