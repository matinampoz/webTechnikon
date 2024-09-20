package ed.webtechnikon2.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author matin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private String username;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "propertyOwner")
    List<Property> properties;
}
