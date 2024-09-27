package dto;

import ed.webtechnikon2.enums.RepairStatus;
import ed.webtechnikon2.enums.RepairType;
import ed.webtechnikon2.modeles.Repair;
import lombok.Data;

/**
 *
 * @author matin
 */
@Data
public class RepairDTO {

    private long repairId;
    private long propertyId;
    private RepairType typeOfRepair;
    private String description;
    private String dateTime;
    private double cost;
    private RepairStatus status;
    private boolean isDeleted;

    private PropertyDTO property;

    public RepairDTO(Repair repair) {
        this.repairId = repair.getRepairId();
        this.typeOfRepair = repair.getTypeOfRepair();
        this.description = repair.getDescription();
        this.dateTime = repair.getDateTime();
        this.cost = repair.getCost();
        this.status = repair.getStatus();
        this.isDeleted = repair.isDeleted();
        this.property = new PropertyDTO(repair.getProperty());
    }
}
