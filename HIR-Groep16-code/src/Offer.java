/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class Offer {
private String code; //van het specifieke equipment
private String supplierName;
private double dailyRentalPrice;
private String supplierCode;

public Offer(String code, String supplierName, double dailyRentalPrice, String supplierCode) {
this.code = code;
this.supplierName = supplierName;
this.dailyRentalPrice = dailyRentalPrice;
this.supplierCode = supplierCode;
}

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public String getSupplierName() {
return supplierName;
}

public void setSupplierName(String supplierName) {
this.supplierName = supplierName;
}

public double getDailyRentalPrice() {
return dailyRentalPrice;
}

public void setDailyRentalPrice(double dailyRentalPrice) {
this.dailyRentalPrice = dailyRentalPrice;
}

public String getSupplierCode() {
return supplierCode;
}

public void setSupplierCode(String supplierCode) {
this.supplierCode = supplierCode;
}

}
