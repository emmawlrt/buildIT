
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class WorksEngineer extends Employee {
    
    public WorksEngineer(String employeeID, String emailAddress, String phoneNumber) {
        super(employeeID, emailAddress, phoneNumber);
    }
    
    public void ReviewRequest(EquipmentRentalRequest aRequest) {
        //WorksEngineer beslist of hij de request goedkeurt of niet
        System.out.println("Do you approve this RentalRequest?");
        Scanner keyboard = new Scanner(System.in);
        String answer = keyboard.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            //Als de request goedgekeurd is, dan wordt het equipment verzonden
            //Dit wil zeggen dat het equipment een adres toegewezen krijgt
            System.out.println("What is the reason you approved?");
            aRequest.setReasonForCancellationOrRefusal(keyboard.nextLine());
            aRequest.getPurchaseOrder().getSelectedEquipment().setSiteAddress(address);
        } else {
            System.out.println("What is the reason you rejected?");
            aRequest.setReasonForCancellationOrRefusal(keyboard.nextLine());
        }
    }
}
