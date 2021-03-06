import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class Event {
    
    private String activityType;
    private int rental; 
//Identificatie van de case, d.w.z. procesuitvoering � hiervoor wordt het requestNumber gebruikt, 
//0 indien niet verbonden met rentalRequest
    private int initiator;
//
    private String outcome;
    //timestamp variabele:
    private Date date = new Date(); 
    private Timestamp time = new Timestamp(date.getTime());
    //Eventlog ArrayList
    private static ArrayList<Event> log = new ArrayList<Event>(100);

    public ArrayList<Event> getLog() {
        return log;
    }

    public void setLog(ArrayList<Event> log) {
        this.log = log;
    }
    
    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getRental() {
        return rental;
    }

    public void setRental(int rental) {
        this.rental = rental;
    }

    public int getInitiator() {
        return initiator;
    }

    public void setInitiator(int initiator) {
        this.initiator = initiator;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.time = currentTime;
    }
    
    

    public Event(String activityType, int rental, int initiator, String outcome) {
        this.activityType = activityType;
        this.rental = rental;
        this.initiator = initiator;
        this.outcome = outcome;
    }

    public Event() {
        this.activityType = null;
        this.rental = 0;
        this.initiator = 0;
        this.outcome = null;
    }
     
    public void addEventToLog(){
    log.add(this);
    System.out.println("Event added to log.");
    }
    public static void printEventLog(){
        System.out.println("-EVENT LOG-");
    for (int i=0;i<log.size();i++){
    Event thisEvent = log.get(i);
    System.out.print(thisEvent.getTime()+"  ");
    System.out.println("activiteit: " + thisEvent.getActivityType()+ "  initiator: "+ 
            thisEvent.getInitiator()+ "  rental: "+thisEvent.getRental()+"  outcome: "+thisEvent.getOutcome());
    }
    }
    
    //methode voor het opslaan van een Event in de database
    public void saveEvent(){
    try{
            this.saveToDB();
            System.out.println("Event added to database.");
        }
        catch (Exception ex){
            System.out.println("Probleem bij connecteren met database bij het toevoegen van het Event.");
        }
    }
    private void saveToDB() throws DBException {
		Connection con = null;
		try {
			
                    con = DBConnector.getConnection();
                        
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String sql = "SELECT time "
					+ "FROM Event "
					+ "WHERE (activityType, time) = "
					+ "( '" + this.getActivityType()+ "', "
                                        +"'" + this.getTime() +"')"+ ";";
                       	 
			ResultSet srs = stmt.executeQuery(sql);
                       
			if (srs.next()) {
				// UPDATE (wanneer je een Event vindt met dezelfde PK
			
                                sql = "UPDATE Event "
						+ "SET activityType = '" + this.getActivityType()+ "'"
						+ ", rental = " + this.getRental()
						+ ", initiator = '" + this.getInitiator()
						+ "'" + ", outcome = " + this.getOutcome()
						+ ", time = " + this.getTime()+ "'";
                           
				stmt.executeUpdate(sql);
                          
			} else {
				// INSERT
				sql = "INSERT into Event "
						+ "(activityType, rental, initiator, outcome, time) "
						+ "VALUES (" + " '"+this.getActivityType()+"'"
                                                +", " + this.getRental() 
                                                + ", '" + this.getInitiator()+ "'"
						+ ", '" + this.getOutcome()+"'"
						+ ", '" + this.getTime()+ "');";
                            
				stmt.executeUpdate(sql);
			}
			
			DBConnector.closeConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
			DBConnector.closeConnection(con);
			throw new DBException(ex);
		}
	}
     
      //public static void main (String [] args){ //demo voor timestamp
      //Event demo = new Event();
      //System.out.println("timestamp demo. de tijd is nu: ");
      //System.out.println(demo.currentTime);}
    
}

