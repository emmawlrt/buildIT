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
    private int id ;

    public DemoEvent(int ID) {
        this.id = ID;
    }
     public DemoEvent() {
        this.id = 1;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }
    
    
    
    public void kijkOfBeschikbaar(boolean trueOfFalse){
    //we doen hier iets
    boolean beschikbaar = trueOfFalse;
    if (beschikbaar){
        //creeer Event
    Event e1 = new Event("kijkOfBeschikbaar", 0, this.getID(), "Het item is gevonden");
        e1.addEventToLog();
        e1.saveEvent();
    
    }
    else{
        //creeren Event
    Event e1 = new Event("kijkOfBeschikbaar", 0, this.getID(), "Het item is NIET gevonden");
    e1.addEventToLog();
    e1.saveEvent();
    }
    }
}
