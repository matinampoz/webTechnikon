package ed.webtechnikon2.repositories;

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
public class PropertyRepository {
    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;
}
