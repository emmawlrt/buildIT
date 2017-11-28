
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
    public Offer selectMostCostEff(String equipmentType) throws DBException{ 
    // type offer omdat je 1 code van equipment, prijs... moet bekomen die het meeste geschikt is
    /*CREATE TABLE Equipment (
    code varchar (50) NOT NULL,
    description varchar (50) NOT NULL,
    type varchar (50) NOT NULL,
    siteAddress varchar (50),
    PRIMARY KEY (code)
    );*/
    Connection con = null;
    try {
        con = DBConnector.getConnection();
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String sql = "SELECT code "
        + "FROM equipment "
        + "WHERE type= " + equipmentType;

        //nu zou ik de codes willen gebruiken om in een andere database
        //de prijzen die bij deze codes horen eruit te halen
        ResultSet srs = stmt.executeQuery(sql);
        ArrayList<String> codes = new ArrayList<>();
        while(srs.next()){ //codes die overeenkomen met de equipment type in een arraylist zetten zodat je deze verder kan gebruiken
            codes.add(srs.getString("code"));
            //nu moeten we nog de prijzen van de overeenkomstige code vinden
        }
        /*CREATE TABLE Offer(
        code varchar(30) NOT NULL,
        supplierName varchar(40) NOT NULL,
        dailyRentalPrice double NOT NULL,
        supplierCode varchar(30) NOT NULL,
        PRIMARY KEY(code,name))*/
        String sql2 = null;
        for(int i = 0; i < codes.size(); i++){ 
            // in offer staat er 30 en in equipment 50?? vanwaar komen deze getallen??
            sql2 = "SELECT dailyRentalPrice "
            + "FROM offer "
            + "WHERE code= " + codes.get(i);
        }
        ResultSet srs2 = stmt.executeQuery(sql2);
        //HashMap<Double,String> linken = new HashMap();
        ArrayList<Double> dailyRentalPrices = new ArrayList();
        while(srs2.next()){ 
            //codes die overeenkomen met de equipment type in een arraylist zetten zodat je deze verder kan gebruiken
            dailyRentalPrices.add(srs2.getDouble("dailyRentalPrice"));
        }
        double lowestPrice = dailyRentalPrices.get(0);
        String codeLowestPrice = null;
        //de minimale prijs zoeken
        for(int i = 0 ; i < dailyRentalPrices.size(); i++){
            if(lowestPrice > dailyRentalPrices.get(i+1)){
                lowestPrice = dailyRentalPrices.get(i+1);
                codeLowestPrice = codes.get(i+1);
            }
        }
        String sql3 = "SELECT supplierName, supplierCode "
        + "FROM offer "
        + "WHERE code= " + codeLowestPrice; 
        // code is niet primary key => combinatie van 2 => klopt het dan hoe ik het doe?
        ResultSet srs3 = stmt.executeQuery(sql3);
        String supplierName;
        String supplierCode;
        if (srs.next()) {
        supplierName = srs3.getString("supplierName");
        supplierCode = srs3.getString("supplierCode");
        }
        else {
        DBConnector.closeConnection(con);
        return null;
        }
        DBConnector.closeConnection(con);
        Offer offer = new Offer(codeLowestPrice,supplierName,lowestPrice, supplierCode);
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
