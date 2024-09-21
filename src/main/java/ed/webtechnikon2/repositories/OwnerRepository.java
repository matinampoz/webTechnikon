package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Owner;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 *
 * @author matin
 */
@RequestScoped
public class OwnerRepository implements Repository<Owner, Long>{

    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;
    
    private Class<Owner> getEntityClass() {
        return Owner.class;
    }

    private String getEntityClassName() {
        return Owner.class.getName();
    }
    
//    @Override
//    @Transactional
//    public Owner create(Owner owner) {
//        entityManager.persist(owner);
//        return owner;
//    }
    
}
