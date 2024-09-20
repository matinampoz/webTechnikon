package ed.webtechnikon2.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ed.webtechnikon2.enums.PropertyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long propertyId;
    private String e9;
    private String address;
    private int yearOfConstruction;
    @Enumerated(EnumType.STRING)
    private PropertyType typeOfProperty;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ownerId")
    private Owner propertyOwner;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Repair> repairs;

}
