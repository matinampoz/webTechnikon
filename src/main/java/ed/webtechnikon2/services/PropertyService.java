package ed.webtechnikon2.services;

import ed.webtechnikon2.modeles.Property;
import ed.webtechnikon2.repositories.PropertyRepository;
import exceptions.PropertyException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author matin
 */
@RequestScoped
public class PropertyService implements Service<Property, Long> {

    @Inject
    private PropertyRepository propertyRepository;

    @Override
    public Long create(Property property) throws PropertyException {
        propertyRepository.create(property);
        return property.getPropertyId();
    }

    //@Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findById(Long k) throws PropertyException {
        if (k == null) {
            throw new PropertyException("Invalid id");
        }

        return propertyRepository.findById(k)
                .orElseThrow(() -> new PropertyException("id not found"));

    }

    public Property findPropertiesByUsersId(Long k) throws PropertyException {
        if (k == null) {
            throw new PropertyException("Invalid id");
        }

        Optional<Property> properties = propertyRepository.findPropertiesByOwnersId(k);
        return properties.orElseThrow(() -> new PropertyException("Property not found for ID: " + k));
    }

    public Property findPropertiesByUsersVat(String k) throws PropertyException {
        if (k == null) {
            throw new PropertyException("Invalid vat");
        }

        Optional<Property> properties = propertyRepository.findPropertiesByOwnersVat(k);

        return properties.orElseThrow(() -> new PropertyException("Property not found for VAT: " + k));
    }

    @Override
    public boolean delete(Long id) throws PropertyException {
        if (id == null) {
            throw new PropertyException("Invalid id");
        }
        return propertyRepository.deleteById(id);
    }

}
