package ed.webtechnikon2.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ed.webtechnikon2.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;
import lombok.Setter;

/**
 *
 * @author matin
 */
@Data
@Entity
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerId;

    @Column(unique = true)
    private String vatNumber;
    private String name;
    private String surname;
    private String address;
    private long phoneNumber;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.OWNER;

    @JsonIgnore
    @OneToMany(mappedBy = "propertyOwner")
    List<Property> properties;
    
    private boolean isDeleted;

   
    
}
