package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Owner;
import ed.webtechnikon2.repositories.OwnerRepository;
import exceptions.OwnerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author matin
 */
@RequestScoped
public class OwnerService implements Service<Owner, Long>{
    
    @Inject
    private   OwnerRepository ownerRepository;
    
    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findById(Long k) throws OwnerException{
        if (k == null) {
            throw new OwnerException("Invalid id");
        }
        
        Optional<Owner> owner = ownerRepository.findById(k);
        if (owner.isPresent()) {
            return owner.get();
        }
        throw new OwnerException("id not found");
    }
    
    

    @Override
    public Long create(Owner owner) throws OwnerException {
        ownerRepository.create(owner);
        return owner.getOwnerId();
    }
    
}
