package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Property;
import ed.webtechnikon2.repositories.PropertyRepository;
import exceptions.PropertyException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 *
 * @author matin
 */
@RequestScoped
public class PropertyService implements Service<Property, Long>{
    @Inject
    private PropertyRepository propertyRepository;

    @Override
    public Long create(Property property) throws PropertyException {
        propertyRepository.create(property);
        return property.getPropertyId();
    }

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findById(Long k) throws PropertyException{
        if (k == null) {
            throw new PropertyException("Invalid id");
        }
        
        return propertyRepository.findById(k)
                          .orElseThrow(() -> new PropertyException("id not found"));
    
    }

    
    @Override
    public boolean delete(Long id) throws PropertyException {
        if (id == null) {
            throw new PropertyException("Invalid id");
        }
        return propertyRepository.deleteById(id);
    }
    
    
}
