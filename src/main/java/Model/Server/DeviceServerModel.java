/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Server;

/**
 *
 * @author ahure
 */
public class DeviceServerModel {
    
    private String macDomain;
    
    private String macAddress;
    
    private String name;
    
    private String deviceType;
    
    private String ip;

    public String getMacDomain() {
        return macDomain;
    }

    public void setMacDomain(String macDomain) {
        this.macDomain = macDomain;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
