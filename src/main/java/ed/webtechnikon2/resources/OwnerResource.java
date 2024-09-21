package ed.webtechnikon2.resources;

import ed.webtechnikon2.modeles.Owner;
import ed.webtechnikon2.services.OwnerService;
import exceptions.OwnerException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author matin
 */
@Slf4j
@Path("owner")
public class OwnerResource {

    @Inject
    private OwnerService ownerService;

    @Path("home")
    @GET
    public String home() {
        return "welcome to technikon";
    }

    @Path("properyOwners")
    @GET
    @Produces("text/json")
    public List<Owner> getAllPropertyOwners() {
        return ownerService.getAll();
    }
    
    @Path("owner/{ownerId}")
    @GET
    @Produces("text/json")
    public Owner getOwnerById(Long ownerId) {
        try {
            return ownerService.findById(ownerId);
        } catch (OwnerException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            return new Owner(); // tha to allaksw
        }
    }

    @Path("add")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long createOwner(Owner owner) throws OwnerException {
        log.debug("property owner= " + owner.getName());

        ownerService.create(owner);
        return owner.getOwnerId();

    }

}
