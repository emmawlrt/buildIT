
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class SiteEngineer extends Employee {
    
    private String siteAddress;
    
    public SiteEngineer(String employeeID, String emailAddress, String phoneNumber, String siteAddress) {
        super(employeeID, emailAddress, phoneNumber);
        this.siteAddress = siteAddress;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
    
    public boolean CheckTypeAvailability(String equipmentType) throws DBException{
        Connection con = null;
        try
            {
                boolean available = false;
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT  type"
                        + "FROM Equipment"
                        + "WHERE type= " + equipmentType + "AND siteAddress= " + null;
                ResultSet ers = stmt.executeQuery(sql);
                ArrayList<String> equips = new ArrayList<>();
                // = ArrayList van alles dat behoort tot dit type en niet uitgeleend is.
                while (ers.next()){  
                   equips.add(ers.getString("code"));
                }
                // Overlopen welke equipments een supplierCode hebben
                for (String anEquipmentCode: equips) {
                    String cmd = "SELECT code"
                        + "FROM Offer"
                        + "WHERE code= " + anEquipmentCode;
                    ResultSet srs = stmt.executeQuery(cmd);
                    if (!srs.next()) { //in 'Offer' zitten enkel de relaties met een supplier
                        //we hebben geen equipment in eigen stock, we moeten een RRequest verzenden
                        available = true;
                    } else { //min 1 waarde gevonden die aan deze vereisten voldoet
                    //dit equipment is in eigen stock en we moeten dus geen RentalRequest verzenden
                    }
                }
                DBConnector.closeConnection(con);
                return available;
            }
        catch (DBException | SQLException e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
    }
}
