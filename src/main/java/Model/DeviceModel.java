/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entity.Device;
import entity.Metric;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahure
 */

public class DeviceModel implements Serializable {
    
    public DeviceModel(){
        
    }

    public DeviceModel(Long id, String name, String macAddress,String type) {
        this.id = id;
        this.name = name;
        this.macAddress = macAddress;
        this.type=type;
    }

    
    
    public DeviceModel(Device dev) {
        this.id = dev.getId();
        this.name = dev.getName();
        this.macAddress = dev.getMacAddress();
        this.metrics=new ArrayList<MetricModel>();
        if(dev.getMetrics()!=null){
            for(Metric metric : dev.getMetrics())
               this.metrics.add(new MetricModel(metric));
        }
    }
    
    private static final long serialVersionUID = 1L;
  
    private Long id;

    private String name;

    private String macAddress;

    private List<MetricModel> metrics;
    
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public List<MetricModel> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricModel> metrics) {
        this.metrics = metrics;
    }
       
    
}
