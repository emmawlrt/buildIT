/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBuildIT {
    public static Equipment getEquipment(String equipCode) throws DBException{
        Connection con = null;
        try
            {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT code, description, type, siteAddress"
                        + "FROM Equipment "
                        + "WHERE code= " + equipCode;
                ResultSet ers = stmt.executeQuery(sql);
                String code, description, type, siteAddress;
                if (ers.next()){
                    code = ers.getString("code");
                    description = ers.getString("description");
                    type = ers.getString("type");
                    siteAddress = ers.getString("siteAddress");
                } else { //we verwachten geen volgende rij
                    DBConnector.closeConnection(con);
                    return null;
                }
                Equipment equipment = new Equipment(code, description, type, siteAddress);
                
                DBConnector.closeConnection(con);
                return equipment;
        }
        catch (Exception e) {
            e.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(e);
        }
    }
    public static Offer getOffer(String equipCode, String name) throws DBException{
    Connection con = null;
    try
    {
        con = DBConnector.getConnection();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "SELECT code, supplierName, dailyRentalPrice, supplierCode"
        + "FROM Offer "
        + "WHERE code= " + equipCode
        + "AND supplierName= " + name; //controleer of dit het juiste resultaat geeft
        ResultSet ers = stmt.executeQuery(sql);
        String code, supplierName, supplierCode;
        double dailyRentalPrice;
        if (ers.next()){
            code = ers.getString("code");
            supplierName = ers.getString("supplierName");
            dailyRentalPrice = ers.getDouble("dailyRentalPrice");
            supplierCode = ers.getString("supplierCode");
        } else {
            DBConnector.closeConnection(con);
            return null;
        }
        Offer offer = new Offer(code, supplierName, dailyRentalPrice, supplierCode);

        DBConnector.closeConnection(con);
        return offer;
    }
    catch (Exception e) {
        e.printStackTrace();
        DBConnector.closeConnection(con);
        throw new DBException(e);
    }
    }
}
