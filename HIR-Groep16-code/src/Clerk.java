
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class Clerk extends Employee {
    
    public Clerk(String employeeID, String emailAddress, String phoneNumber) {
        super(employeeID, emailAddress, phoneNumber);
    }
    //Work in progress.
    public boolean CheckEquipmentAvailability(Offer anOffer) throws DBException{
        String equipCode = anOffer.getCode();
        boolean available = false;
        Connection con = null;
        try
            {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT code, description, type, siteAddress"
                        + "FROM Equipment "
                        + "WHERE code= " + equipCode + "AND siteAddress =" + null;
                ResultSet ers = stmt.executeQuery(sql);
                String code, description, type, siteAddress;
                if (ers.next()){
                    code = ers.getString("code");
                    description = ers.getString("description");
                    type = ers.getString("type");
                    siteAddress = ers.getString("siteAddress");
                } else { //we verwachten geen volgende rij
                    DBConnector.closeConnection(con);
                }
                
                DBConnector.closeConnection(con);
                return available;
        }
        catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
    }
    public Offer selectMostCostEff(String equipmentType){
        
   }
}
