package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Property;
import ed.webtechnikon2.repositories.PropertyRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 *
 * @author matin
 */
@RequestScoped
public class PropertyService implements Service<Property, Long>{
    @Inject
    private PropertyRepository propertyRepository;
}
