/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ahure
 */
public class DomainKeyValue {
    
    private String DomainName;
    private String MacAddress;
    private Long id;

    public DomainKeyValue() {
    }

    public DomainKeyValue(String DomainName, String MacAddress, Long id) {
        this.DomainName = DomainName;
        this.MacAddress = MacAddress;
        this.id = id;
    }

    
    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String DomainName) {
        this.DomainName = DomainName;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String MacAddress) {
        this.MacAddress = MacAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
