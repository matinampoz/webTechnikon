package ed.webtechnikon2.resources;

import ed.webtechnikon2.modeles.Property;
import ed.webtechnikon2.services.PropertyService;
import exceptions.PropertyException;
import exceptions.WebApplicationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
    
    @Path("property/{propertyId}")
    @GET
    @Produces("text/json")
    public Property getPropertyById(Long propertyId) throws  WebApplicationException {
        try {
            return propertyService.findById(propertyId);
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
}
