package dto;

import ed.webtechnikon2.modeles.Owner;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author matin
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private long ownerId;
    private String vatNumber;
    private String name;
    private String surname;
    private String address;
    private long phoneNumber;
    private String email;

     public OwnerDTO(Owner owner) {
        this.ownerId = owner.getOwnerId();
        this.vatNumber = owner.getVatNumber();
        this.name = owner.getName();
        this.surname = owner.getSurname();
        this.address = owner.getAddress();
        this.phoneNumber = owner.getPhoneNumber();
        this.email = owner.getEmail();
    }
}
