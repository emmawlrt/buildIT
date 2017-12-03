import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;
import java.util.Date;

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
    private int siteEngineerID; //We veranderen dit in een int, ipv van origineel String
    private int clerkID;
    private int worksEngineerID;
    
    //1ste Status
    public EquipmentRentalRequest(int requestNumber, String rentalStatus, Date requestDate, 
            String equipmentRequirements, String Address, int siteEngineerID)
    {
        this.requestNumber = requestNumber;
        this.requestDate = requestDate;
        this.rentalStatus = rentalStatus;
        this.equipmentRequirements = equipmentRequirements;
        this.purchaseOrder = new PurchaseOrder();
        //Hier zijn Flore en Alex nog aan bezig 
    }
    
    
    public EquipmentRentalRequest(int requestNumber, Date requestDate, 
            Date rentalPeriodStart, Date rentalPeriodEnd, String rentalStatus, 
            String equipmentRequirements) 
    {
        this.requestNumber = requestNumber;
        this.requestDate = requestDate;
        this.rentalPeriodStart = rentalPeriodStart;
        this.rentalPeriodEnd = rentalPeriodEnd;
        this.rentalStatus = rentalStatus;
        this.reasonForCancellationOrRefusal = null;
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

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public int getSiteEngineerID() {
        return siteEngineerID;
    }

    public String getSiteAddress() throws DBException {
        Connection con = null;
        try
            {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT siteAddress"
                        + "FROM SiteEngineer"
                        + "WHERE employeeID= " + siteEngineerID;
                ResultSet ers = stmt.executeQuery(sql);
                String address;
                if (ers.next()){
                    address = ers.getString("siteAddress");
                } else { //we verwachten geen volgende rij
                    DBConnector.closeConnection(con);
                    return null;
                }
                DBConnector.closeConnection(con);
                return address;
        }
        catch (DBException | SQLException e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
    }
    public int getClerkID() {
        return clerkID;
    }

    public int getWorksEngineerID() {
        return worksEngineerID;
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

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setSiteEngineerID(int siteEngineerID) {
        this.siteEngineerID = siteEngineerID;
    }

    public void setClerkID(int clerkID) {
        this.clerkID = clerkID;
    }

    public void setWorksEngineerID(int worksEngineerID) {
        this.worksEngineerID = worksEngineerID;
    }
    
    public double getTotalRentalPeriod ()
    {
        long RentalPeriod = rentalPeriodEnd.getTime() 
                - rentalPeriodStart.getTime();
        double daysBetween = (double)(RentalPeriod / (1000*60*60*24));
        return daysBetween;
    }
}
