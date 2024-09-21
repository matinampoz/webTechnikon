package ed.webtechnikon2.resources;

import ed.webtechnikon2.services.PropertyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

/**
 *
 * @author matin
 */
@Path("Property")
public class PropertyResource {
    @Inject
    private PropertyService propertyService;
}
