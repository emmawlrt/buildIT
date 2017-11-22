
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
public class Invoice {
    
    private int invoiceNumber;
    private int supplierInvoiceNumber;
    private Date date;

    public Invoice(int invoiceNumber, int supplierInvoiceNumber, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.supplierInvoiceNumber = supplierInvoiceNumber;
        this.date = date;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public int getSupplierInvoiceNumber() {
        return supplierInvoiceNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setSupplierInvoiceNumber(int supplierInvoiceNumber) {
        this.supplierInvoiceNumber = supplierInvoiceNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
