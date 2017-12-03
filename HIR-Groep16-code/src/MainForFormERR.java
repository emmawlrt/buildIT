import java.util.HashMap;
import java.util.*;

/**
 *
 * @author Alexandre
 */
public class MainForFormERR {

    private HashMap<Integer, EquipmentRentalRequest> requests;

    public MainForFormERR() {
        requests = new HashMap<Integer, EquipmentRentalRequest>();
    }

    public static void main(String[] args) {
        EquipmentRentalRequestForm f = new EquipmentRentalRequestForm(new MainForFormERR()); //MyFrame is hier EquipmentRentalRequestForm
        f.setVisible(true);
    }

    public void addEquipmentRentalRequest(EquipmentRentalRequest r) {
        requests.put(r.getRequestNumber(), r);
    }

}

