package ed.webtechnikon2.resources;

import dto.RepairDTO;
import ed.webtechnikon2.modeles.Repair;
import ed.webtechnikon2.services.RepairService;
import exceptions.RepairException;
import exceptions.WebApplicationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author matin
 */
@Slf4j
@Path("repair")
public class RepairResource {

    @Inject
    private RepairService repairService;

    //http://localhost:8080/Technikon/resources/repair/repairs
    @Path("repairs")
    @GET
    @Produces("text/json")
    public List<RepairDTO> getAllRepairs() {
        return repairService.getAll()
                .stream()
                .map(RepairDTO::new)
                .collect(Collectors.toList());
    }

    //http://localhost:8080/Technikon/resources/repair/1
    @Path("{repairId}")
    @GET
    @Produces("text/json")
    public RepairDTO getRepairById(@PathParam("repairId") Long repairId) throws WebApplicationException {
        try {
            Repair repair = repairService.findById(repairId);
            RepairDTO repairDTO = new RepairDTO(repair);
            return repairDTO;
        } catch (RepairException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Repair not found for ID: " + repairId, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(RepairResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/Technikon/resources/repair/repairsOfOwner/1
    //not done. this function will be availiablei in the upcoming release using DTO class to avoid back reference
    @Path("/repairsOfOwner/{ownersId}")
    @GET
    @Produces("text/json")
    public List<Repair> getRepairsByUsersId(@PathParam("ownersId") Long ownersId) throws WebApplicationException {
        try {
            return repairService.findRepairsByUsersId(ownersId);
        } catch (RepairException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Repairs not found for ID: " + ownersId, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(RepairResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/Technikon/resources/repair/add
    @Path("add")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long createRepair(Repair repair) throws RepairException {
        log.debug("Repair= " + repair.getRepairId());

        repairService.create(repair);
        return repair.getRepairId();

    }

     //http://localhost:8080/Technikon/resources/repair/delete/1
    @Path("delete/{repairId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean delete(@PathParam("repairId") Long repairId) throws RepairException {
        return repairService.delete(repairId);
    }
}
