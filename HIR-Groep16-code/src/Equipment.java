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
    private String address;

    public Equipment(String code, String description, String type, String address) {
        this.code = code;
        this.description = description;
        this.type = type;
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}