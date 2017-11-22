import java.time.Instant;
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
public class PurchaseOrder {
    
    private int orderNumber;
    private Date orderDate;
    // Is dit een foreign key of niet?
    private Equipment selectedEquipment;
    
    public PurchaseOrder() {
        this.orderNumber = 0;
        this.orderDate = null;
        this.selectedEquipment = null;
    }
    public PurchaseOrder(int orderNumber, Date orderDate, Equipment selectedEquipment) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.selectedEquipment = selectedEquipment;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Equipment getSelectedEquipment() {
        return selectedEquipment;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setSelectedEquipment(Equipment selectedEquipment) {
        this.selectedEquipment = selectedEquipment;
    }
    
    public double getTotalRentalPrice(double dailyRentalPrice, 
            EquipmentRentalRequest request) 
    {
        return dailyRentalPrice * request.getTotalRentalPeriod();
    }
}
