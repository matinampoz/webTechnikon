package dto;

import ed.webtechnikon2.enums.PropertyType;
import ed.webtechnikon2.modeles.Property;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *
 * @author matin
 */

@Data
public class PropertyDTO {

    private long propertyId;
    private String e9;
    private String address;
    private int yearOfConstruction;
    private PropertyType typeOfProperty;

    private OwnerDTO owner;

     public PropertyDTO(Property property) {
        this.propertyId = property.getPropertyId();
        this.e9 = property.getE9();
        this.address = property.getAddress();
        this.typeOfProperty = property.getTypeOfProperty();
        this.owner = new OwnerDTO(property.getPropertyOwner());
    }
}
