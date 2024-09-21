package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Repair;
import ed.webtechnikon2.repositories.RepairRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 *
 * @author matin
 */
@RequestScoped
public class RepairService implements Service<Repair, Long>{
    @Inject
    private RepairRepository repairRepository;
}
