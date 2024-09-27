package ed.webtechnikon2.resources;

import dto.PropertyDTO;
import ed.webtechnikon2.modeles.Property;
import ed.webtechnikon2.services.PropertyService;
import exceptions.PropertyException;
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
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author matin
 */
@Slf4j
@Path("property")
public class PropertyResource {

    @Inject
    private PropertyService propertyService;

    @Path("properties")
    @GET
    @Produces("text/json")
    public List<Property> getAllProperties() {
        return propertyService.getAll();
    }

    @Path("/propertiesOfOwner/{ownersId}")
    @GET
    @Produces("text/json")
    public PropertyDTO getPropertiesByUsersId(@PathParam("ownersId") Long ownersId) throws WebApplicationException {
        try {
            //owner has only one property
            Property property = propertyService.findPropertiesByUsersId(ownersId);
            PropertyDTO propertyDTO = new PropertyDTO(property);
            return propertyDTO;
        } catch (PropertyException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Properties not found for ID: " + ownersId, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(PropertyResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Path("/propertiesOfOwnerWithVat/{vat}")
    @GET
    @Produces("text/json")
    public PropertyDTO findPropertiesByUsersVat(@PathParam("vat") String vat) throws WebApplicationException {
        //owner has only one property
        try {
            Property property = propertyService.findPropertiesByUsersVat(vat);
            PropertyDTO propertyDTO = new PropertyDTO(property);
            return propertyDTO;
        } catch (PropertyException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Properties not found for Vat: " + vat, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(PropertyResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Path("{propertyId}")
    @GET
    @Produces("text/json")
    public PropertyDTO getPropertyById(@PathParam("propertyId") Long propertyId) throws WebApplicationException {
        try {
            Property property = propertyService.findById(propertyId);
            PropertyDTO propertyDTO = new PropertyDTO(property);
            return propertyDTO;
        } catch (PropertyException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Property not found for ID: " + propertyId, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(PropertyResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Path("add")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long createProperty(Property property) throws PropertyException {
        log.debug("property= " + property.getPropertyId());

        propertyService.create(property);
        return property.getPropertyId();

    }

    @Path("delete/{propertyId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean delete(@PathParam("propertyId") Long propertyId) throws PropertyException {
        return propertyService.delete(propertyId);
    }

    @Path("details/{propertyId}")
    @GET
    @Produces("text/plain")
    public String showPropertyDetails(@PathParam("propertyId") Long propertyId) throws WebApplicationException {
        try {
            Property property = propertyService.findById(propertyId);
            return property.toString();
        } catch (PropertyException ex) {
            Logger.getLogger(PropertyResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("Property not found for ID: " + propertyId, Response.Status.NOT_FOUND);
        } catch (Exception ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException("An internal error occurred", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
