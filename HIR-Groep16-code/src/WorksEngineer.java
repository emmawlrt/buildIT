
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
    
    public void ReviewRequest(EquipmentRentalRequest aRequest) throws DBException {
        //WorksEngineer beslist of hij de request goedkeurt of niet
        System.out.println("Do you approve this RentalRequest?");
        Scanner keyboard = new Scanner(System.in);
        String answer = keyboard.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            //Als de request goedgekeurd is, dan wordt het equipment verzonden
            //Dit wil zeggen dat het equipment een adres toegewezen krijgt
            System.out.println("What is the reason you approved?");
            aRequest.setReasonForCancellationOrRefusal(keyboard.nextLine());
            String address = aRequest.getSiteAddress();
            aRequest.getPurchaseOrder().getSelectedEquipment().setSiteAddress(address);
            aRequest.setRentalStatus("Approved");
            Event approval = new Event("ReviewRequest", aRequest.getRequestNumber(), 
                    aRequest.getWorksEngineerID(), "The request is approved");
            //Eigenlijk wil ik hier this.getEmployeeID() doen, maar we zitten nog 
            // met de inconsequentie dat enkele ID's nu String zijn, en ander int...
            approval.addEventToLog();
            approval.saveEvent();
        } else {
            System.out.println("What is the reason you rejected?");
            aRequest.setReasonForCancellationOrRefusal(keyboard.nextLine());
            aRequest.setRentalStatus("Rejected");
            Event rejection = new Event("ReviewRequest", aRequest.getRequestNumber(), 
                    aRequest.getWorksEngineerID(), "The request is rejected");
            rejection.addEventToLog();
            rejection.saveEvent();
        }
    }
}
