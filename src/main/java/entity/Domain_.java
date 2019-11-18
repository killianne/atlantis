/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
@StaticMetamodel(Domain.class)
public class Domain_ {
  public static volatile SingularAttribute<Domain, Long> id;
  public static volatile SingularAttribute<Domain, String> macAddress;
  public static volatile SingularAttribute<Domain, String> name;
  public static volatile SingularAttribute<Domain, Date> lastUpdate;
  public static volatile SingularAttribute<Domain, UserEnt> user;
  public static volatile CollectionAttribute<Domain, Device> devices;
  



}