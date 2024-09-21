package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Repair;
import ed.webtechnikon2.repositories.RepairRepository;
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
    public Long create(Repair t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Repair> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Repair findById(Long k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
