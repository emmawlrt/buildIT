/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
import java.util.Scanner;

public class DemoClass {
    public static void main (String [] args) throws DBException 
    {
        DemoEvent test = new DemoEvent(6358);
        test.kijkOfBeschikbaar(true);
        test.kijkOfBeschikbaar(false);
        DemoEvent test2 = new DemoEvent(8932);
        test2.kijkOfBeschikbaar(true);
        
        Event.printEventLog();
        
        System.out.println("What type of equipment do you want?");
        Scanner keyboard = new Scanner(System.in);
        //siteEngineerObject.CheckTypeAvailability(keyboard.nextLine());
    }
}
