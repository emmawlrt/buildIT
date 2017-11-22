/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class ConstructionSite {
    
    private String address;
      // Type instantievariabele is siteEngineer, want aggregatie.
    private SiteEngineer siteEngineer;

    public ConstructionSite(String address, SiteEngineer siteEngineer) {
        this.address = address;
        this.siteEngineer = siteEngineer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
