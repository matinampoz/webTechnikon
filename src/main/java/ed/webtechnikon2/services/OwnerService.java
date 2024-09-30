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
public class OwnerService implements Service<Owner, Long> {

    @Inject
    private OwnerRepository ownerRepository;

    //@Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findById(Long k) throws OwnerException {
        if (k == null) {
            throw new OwnerException("Invalid id");
        }

        return ownerRepository.findById(k)
                .orElseThrow(() -> new OwnerException("id not found"));

    }

    public Owner findOwnerByVat(String vatNumber) throws OwnerException {
        if (vatNumber == null) {
            throw new OwnerException("Invalid vat number");
        }
        Owner owner = ownerRepository.findOwnerByVat(vatNumber);
        if (owner == null) {
            throw new OwnerException("Owner not found for VAT: " + vatNumber);
        }
        return owner;
    }

    public Owner findOwnerByEmail(String email) throws OwnerException {
        if (email == null) {
            throw new OwnerException("Invalid email");
        }
        Owner owner = ownerRepository.findOwnerByEmail(email);
        if (owner == null) {
            throw new OwnerException("Owner not found for email: " + email);
        }
        return owner;
    }

  public Optional<Owner> updateOwner(Long id, String newName, String newVat, String newEmail) {
    return ownerRepository.update(id, newName, newVat, newEmail);
}

    @Override
    public boolean delete(Long id) throws OwnerException {
        if (id == null) {
            throw new OwnerException("Invalid id");
        }
        return ownerRepository.deleteById(id);
    }

    @Override
    public Long create(Owner owner) throws OwnerException {
        ownerRepository.create(owner);
        return owner.getOwnerId();
    }

}
