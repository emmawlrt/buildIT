import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eliz
 */
public class DemoEvent {
    private String id ;

    public DemoEvent(String ID) {
        this.id = ID;
    }
     public DemoEvent() {
        this.id = "testID";
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }
    
    
    
    public void kijkOfBeschikbaar(boolean trueOfFalse){
    //we doen hier iets
    boolean beschikbaar = trueOfFalse;
    if (beschikbaar){
        //creeer Event
    Event e1 = new Event("kijkOfBeschikbaar", 0, this.getID(), "Het item is gevonden");
        Event.addEventToLog(e1);
        Event.saveEvent(e1);
    
    }
    else{
        //creeren Event
    Event e1 = new Event("kijkOfBeschikbaar", 0, this.getID(), "Het item is NIET gevonden");
   Event.addEventToLog(e1);
   Event.saveEvent(e1);
    }
    }
}
