
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
public class SiteEngineer extends Employee {
    
    public SiteEngineer(String employeeID, String emailAddress, String phoneNumber) {
        super(employeeID, emailAddress, phoneNumber);
    }
    public boolean CheckTypeAvailability(String equipmentType) throws DBException{
        Connection con = null;
        try
            {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT  type"
                        + "FROM EquipmentType "
                        + "WHERE type= " + equipmentType;
                ResultSet ers = stmt.executeQuery(sql);
                boolean available = false;
                // Er is een waarde gevonden met deze PK.
                if (ers.next()){  
                   available = true;
                } else { //Er is geen waarde gevonden met deze PK.
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
}
