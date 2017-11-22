
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
public class EquipmentType {
    
    private String type;
    private ArrayList<Equipment> equipList;

    // We creëren pas een Equipment-lijst van dit type (vb boren) als er een 
    // type wordt ingegeven dus de lijst met unieke elementen is afhankelijk 
    // van het type (=compositie)
    public EquipmentType(String type) {
        this.type = type;
        equipList = new ArrayList<>();
    }
    
}
