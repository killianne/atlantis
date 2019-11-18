/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entity.UserEnt;
import entity.Device;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahure
 */

public class UserModel implements Serializable {
    
    public UserModel(){
        
    }
    
    public UserModel(UserEnt user){
        this.id=user.getId();
        this.email=user.getEmail();
        this.token=user.getToken();
        this.username=user.getUsername();
        this.devices= new ArrayList<DeviceModel>();
           
                
    }
    
    private static final long serialVersionUID = 1L;
  
    private Long id;

    private String username;

    private String email;

    private String token;

    private List<DeviceModel> devices;

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
