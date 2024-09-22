package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Repair;
import ed.webtechnikon2.repositories.RepairRepository;
import exceptions.RepairException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 *
 * @author matin
 */
@RequestScoped
public class RepairService implements Service<Repair, Long>{
    @Inject
    private RepairRepository repairRepository;

    @Override
    public Long create(Repair repair) throws RepairException {
        repairRepository.create(repair);
        return repair.getRepairId();
    }

    @Override
    public List<Repair> getAll() {
        return repairRepository.findAll();
    }

    @Override
    public Repair findById(Long k) throws RepairException{
        if (k == null) {
            throw new RepairException("Invalid id");
        }
        
        return repairRepository.findById(k)
                          .orElseThrow(() -> new RepairException("id not found"));
    
    }
    
    
    
}
