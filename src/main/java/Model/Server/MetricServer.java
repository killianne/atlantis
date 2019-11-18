/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Server;

import entity.Metric;
import java.util.Date;


/**
 *
 * @author ahure
 */
public class MetricServer {
    
    private Long id_device;
    private float value;
    private Date date;

    public Long getId_device() {
        return id_device;
    }

    public void setId_device(Long id_device) {
        this.id_device = id_device;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Metric getMetricObj(){
        Metric metric = new Metric();
        metric.setValue(this.value);
        metric.setCreatedAt(this.date);
        return metric;
    }
    
}
