
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
public class Clerk extends Employee {
    
    public Clerk(String employeeID, String emailAddress, String phoneNumber) {
        super(employeeID, emailAddress, phoneNumber);
    }
        public Offer selectMostCostEff(String code) throws DBException{ 
    
    Connection con = null;
    try {
        con = DBConnector.getConnection();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        
        String sql2 = "SELECT dailyRentalPrice "
            + "FROM offer "
            + "WHERE code= " + code;
        
        ResultSet srs2 = stmt.executeQuery(sql2);
        ArrayList<Double> dailyRentalPrices = new ArrayList();
        while(srs2.next()){ 
            //codes die overeenkomen met de equipment type in een arraylist zetten zodat je deze verder kan gebruiken
            dailyRentalPrices.add(srs2.getDouble("dailyRentalPrice"));
        }
        double lowestPrice = dailyRentalPrices.get(0);
        //de minimale prijs zoeken
        for(int i = 0 ; i < dailyRentalPrices.size(); i++){
            if(lowestPrice > dailyRentalPrices.get(i+1)){
                lowestPrice = dailyRentalPrices.get(i+1);
            }
        }
        String sql3 = "SELECT supplierName, supplierCode "
        + "FROM offer "
        + "WHERE code= " + code + "AND dailyRentalPrice= " + lowestPrice; 
        // code en dailyrentalprice zijn niet primary key => het kan zijn dat er meedere codes zijn met dezelfde laagste prijs
        // => je selecteert dan de eerste laagste prijs en de eerste supplier die hoort bij de code en die laagste prijs
        // we gaan ervanuit dat kwaliteiten hetzelfde zijn!
        ResultSet srs3 = stmt.executeQuery(sql3);
        String supplierName;
        String supplierCode;
        if (srs3.next()) {
        supplierName = srs3.getString("supplierName");
        supplierCode = srs3.getString("supplierCode");
        }
        else {
        DBConnector.closeConnection(con);
        return null;
        }
        DBConnector.closeConnection(con);
        Offer offer = new Offer(code,supplierName,lowestPrice, supplierCode);
        return offer;


   } catch (DBException ex) {
    ex.printStackTrace();
    DBConnector.closeConnection(con);
    throw ex;
   } catch (SQLException ex) {
    ex.printStackTrace();
    DBConnector.closeConnection(con);
    throw new DBException(ex);
   }
   }

   //VRAAG: hoe komen we aan de code van het equipment? iemand moet dit op voorhand al selecteren
    public boolean suitableEquipment(String code) throws DBException{
        //aan de hand van de code checken of er minimum 1 supplier is die dat specifieke equipment verhuurt
        boolean suitable = false;
        Connection con = null;
    try {
        con = DBConnector.getConnection();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT supplierName "
                    + "FROM Offer "
                    + "WHERE code= " + code;
        ResultSet srs = stmt.executeQuery(sql);
        
        if(srs.next()){// er is minstens 1 supplier
            suitable = true;   
        }
        else{
            DBConnector.closeConnection(con);
            
        }
        DBConnector.closeConnection(con);
            
    }   catch (SQLException ex) {
            ex.printStackTrace();
            DBConnector.closeConnection(con);
            throw new DBException(ex);
        }
    
        return suitable;
    }
    
    public boolean CheckEquipmentAvailability(Offer selectedOffer) throws DBException{
        String equipCode = selectedOffer.getCode();
        boolean available = false;
        Connection con = null;
        try
            {
                con = DBConnector.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT code"
                        + "FROM Equipment"
                        + "WHERE code= " + equipCode + "AND siteAddress =" + null;
                ResultSet ers = stmt.executeQuery(sql);
                if (ers.next()){
                    available = true;
                } else { //we verwachten geen volgende rij
                    DBConnector.closeConnection(con);
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
