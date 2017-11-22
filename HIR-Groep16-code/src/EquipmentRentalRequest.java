import java.time.Period;
import java.util.Date;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class EquipmentRentalRequest {
    
    private int requestNumber;
    private Date requestDate;
    private Date rentalPeriodStart;
    private Date rentalPeriodEnd;
    private String rentalStatus;
    private String reasonForCancellationOrRefusal;
    private String equipmentRequirements;
    private PurchaseOrder purchaseOrder;

    public EquipmentRentalRequest(int requestNumber, Date requestDate, 
            Date rentalPeriodStart, Date rentalPeriodEnd, String rentalStatus, 
            String reasonForCancellationOrRefusal, String equipmentRequirements) 
    {
        this.requestNumber = requestNumber;
        this.requestDate = requestDate;
        this.rentalPeriodStart = rentalPeriodStart;
        this.rentalPeriodEnd = rentalPeriodEnd;
        this.rentalStatus = rentalStatus;
        this.reasonForCancellationOrRefusal = reasonForCancellationOrRefusal;
        this.equipmentRequirements = equipmentRequirements;
        this.purchaseOrder = new PurchaseOrder();
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getRentalPeriodStart() {
        return rentalPeriodStart;
    }

    public Date getRentalPeriodEnd() {
        return rentalPeriodEnd;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public String getReasonForCancellationOrRefusal() {
        return reasonForCancellationOrRefusal;
    }

    public String getEquipmentRequirements() {
        return equipmentRequirements;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setRentalPeriodStart(Date rentalPeriodStart) {
        this.rentalPeriodStart = rentalPeriodStart;
    }

    public void setRentalPeriodEnd(Date rentalPeriodEnd) {
        this.rentalPeriodEnd = rentalPeriodEnd;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public void setReasonForCancellationOrRefusal(String 
            reasonForCancellationOrRefusal) {
        this.reasonForCancellationOrRefusal = reasonForCancellationOrRefusal;
    }

    public void setEquipmentRequirements(String equipmentRequirements) {
        this.equipmentRequirements = equipmentRequirements;
    }
    
    public double getTotalRentalPeriod ()
    {
        long RentalPeriod = rentalPeriodEnd.getTime() 
                - rentalPeriodStart.getTime();
        double daysBetween = (double)(RentalPeriod / (1000*60*60*24));
        return daysBetween;
    }
}
