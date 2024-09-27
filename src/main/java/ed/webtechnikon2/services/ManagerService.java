package ed.webtechnikon2.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author matin
 */
@RequestScoped
public class ManagerService {
    @PersistenceContext(unitName = "Persistence")
    protected EntityManager em;

    public String doWork(String input) {
       return "processing the " + input;
    }
}
