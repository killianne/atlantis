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
public class MetriCalModel {
    
    private Long id;
    
    private String type;

    private float value;

    public MetriCalModel(Long id, String type, float value) {
        this.id = id;
        this.type = type;
        System.out.println(type);
        this.value = value;
    }

    public MetriCalModel() {
    }

    
    
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
    
    
    
}
