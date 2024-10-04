package ed.webtechnikon2.resources;

import dto.OwnerDTO;
import ed.webtechnikon2.modeles.Owner;
import ed.webtechnikon2.services.OwnerService;
import exceptions.OwnerException;
import exceptions.WebApplicationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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

    //http://localhost:8080/Technikon/resources/owner/properyOwners
    @Path("properyOwners")
    @GET
    @Produces("application/json")
    public List<OwnerDTO> getAllPropertyOwners() {

        return ownerService.getAll()
                .stream().map(OwnerDTO::new)
                .collect(Collectors.toList());
    }

    //http://localhost:8080/Technikon/resources/owner/id/1
    @Path("id/{ownerId}")
    @GET
    @Produces("text/json")
    public OwnerDTO getOwnerById(@PathParam("ownerId") Long ownerId) throws WebApplicationException {
        try {
            Owner owner = ownerService.findById(ownerId);
            OwnerDTO ownerdto = new OwnerDTO(owner);
            return ownerdto;

        } catch (OwnerException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Owner not found for ID: " + ownerId, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/Technikon/resources/owner/vat/12345
    @Path("vat/{vat}")
    @GET
    @Produces("text/json")
    public OwnerDTO getOwnerByVat(@PathParam("vat") String vat) throws WebApplicationException {
        try {
            Owner owner = ownerService.findOwnerByVat(vat);
            Logger.getLogger(OwnerResource.class.getName()).log(Level.INFO, "Found owner: {0}", owner);
            OwnerDTO ownerdto = new OwnerDTO(owner);
            return ownerdto;

        } catch (OwnerException ex) {
            Logger.getLogger(OwnerResource.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Owner not found for Vat: " + vat, Response.Status.NOT_FOUND);

        } catch (Exception ex) {
            Logger.getLogger(OwnerResource.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/Technikon/resources/owner/email/tade 
    @Path("email/{email}")
    @GET
    @Produces("text/json")
    public OwnerDTO getOwnerByEmail(@PathParam("email") String email) throws WebApplicationException {
        try {
            Owner owner = ownerService.findOwnerByEmail(email);
            OwnerDTO ownerdto = new OwnerDTO(owner);
            return ownerdto;

        } catch (OwnerException ex) {
            Logger.getLogger(OwnerResource.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Owner not found for email: " + email, Response.Status.NOT_FOUND);

        } catch (Exception ex) {
            Logger.getLogger(OwnerResource.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/Technikon/resources/owner/add
    @Path("add")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long createOwner(Owner owner) throws OwnerException {
        log.debug("property owner= " + owner.getName());

        ownerService.create(owner);
        return owner.getOwnerId();

    }

    // http://localhost:8080/Technikon/resources/owner/edit/1
    @PUT
    @Path("edit/{ownerId}")
    @Consumes("Application/json")
    @Produces("Application/json")
    public boolean updateOwner(@PathParam("ownerId") Long ownerId, OwnerDTO updatedOwnerDTO) {
        Optional<Owner> updated = ownerService.updateOwner(
                ownerId,
                updatedOwnerDTO.getName(),
                updatedOwnerDTO.getVatNumber(),
                updatedOwnerDTO.getEmail()
        );

        return updated.isPresent();
    }

    //http://localhost:8080/Technikon/resources/owner/delete/1
    @Path("delete/{ownerId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean delete(@PathParam("ownerId") Long ownerId) throws OwnerException {
        return ownerService.delete(ownerId);
    }

//    @Path("details/{ownerId}")
//    @GET
//    @Produces("text/plain")
//    public String showOwnerDetails(@PathParam("ownerId") Long ownerId) throws WebApplicationException {
//        try {
//            Owner owner = ownerService.findById(ownerId);
//            return owner.toString();
//
//        } catch (OwnerException ex) {
//            Logger.getLogger(OwnerResource.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException("Owner not found for ID: " + ownerId, Response.Status.NOT_FOUND);
//
//        } catch (Exception ex) {
//            Logger.getLogger(OwnerResource.class
//                    .getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
//        }
//    }
}
