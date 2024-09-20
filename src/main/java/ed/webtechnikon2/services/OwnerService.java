package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Owner;
import ed.webtechnikon2.repositories.OwnerRepository;
import exceptions.OwnerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 *
 * @author matin
 */
@RequestScoped
public class OwnerService implements Service<Owner, Long>{
    
    @Inject
    private   OwnerRepository ownerRepository;

    @Override
    public Long create(Owner owner) throws OwnerException {
        ownerRepository.create(owner);
        return owner.getOwnerId();
    }
    
}
