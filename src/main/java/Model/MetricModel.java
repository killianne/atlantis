/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entity.Metric;
import java.io.Serializable;
import java.util.Date;

public class MetricModel implements Serializable {

    public MetricModel() {
    }

    public MetricModel(String type, float value,Date createdAt) {
        this.type = type;
        this.value = value;
        System.out.println(createdAt);
        this.createdAt=createdAt;
    }
    public MetricModel(float value,Date createdAt) {
        this.value = value;
        System.out.println(createdAt);
        this.createdAt=createdAt;
    }
    
    
    public MetricModel(Metric metric) {
        this.id = metric.getId();
        this.value = metric.getValue();
    }

    
    
    private static final long serialVersionUID = 1L;
  
    private Long id;

    private String type;

    private float value;
    
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
