package ed.webtechnikon2.modeles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ed.webtechnikon2.enums.RepairStatus;
import ed.webtechnikon2.enums.RepairType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author matin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long repairId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;

    @Enumerated(EnumType.STRING)
    private RepairType typeOfRepair;
    private String description;
    private String dateTime;
    private double cost;
    
    @Enumerated(EnumType.STRING)
    private RepairStatus status;
    
    private boolean isDeleted;

}
