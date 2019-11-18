/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author ahure
 */
@StaticMetamodel(Device.class)
public class Device_ {
  public static volatile SingularAttribute<Device, String> macAddress;
  public static volatile SingularAttribute<Device, String> name;
  public static volatile SingularAttribute<Device, Domain> domain;
  public static volatile SingularAttribute<Device, String> type;
  public static volatile CollectionAttribute<Device, Metric> metrics;
  public static volatile SingularAttribute<Device, Long> id;
  


}