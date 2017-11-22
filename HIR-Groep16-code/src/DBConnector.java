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
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Deze klasse heb ik aangemaakt omdat het openen en sluiten van een connectie
 * niet enkel voor de DBStudent klasse zou zijn, maar voor alle klasse in de
 * persistence layer.
 *
 * @author stevmert
 */
public class DBConnector {

    private static final String DB_NAME = "BINFG16";//vul hier uw databank naam in
    private static final String DB_PASS = "y87WYf65";//vul hier uw databank paswoord in

    public static Connection getConnection() throws DBException {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String protocol = "jdbc";
            String subProtocol = "mysql";
            String subName = "//mysqlha2.ugent.be/" + DB_NAME + "?user=" + DB_NAME + "&password=" + DB_PASS;
            String URL = protocol + ":" + subProtocol + ":" + subName;

            con = DriverManager.getConnection(URL);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection(con);
            throw new DBException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            closeConnection(con);
            throw new DBException(e);
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection(con);
            throw new DBException(e);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException sqle) {
            //do nothing
        }
    }
}
