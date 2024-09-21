package ed.webtechnikon2.repositories;

import ed.webtechnikon2.modeles.Repair;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
}
