/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eawillae
 */
public class Equipment {
    
    private String code;
    private String description;
    private String type;
    private String siteAddress;

    public Equipment(String code, String description, String type, String siteAddress) {
        this.code = code;
        this.description = description;
        this.type = type;
        this.siteAddress = siteAddress;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
    
}
