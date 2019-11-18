/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Mobile;


import Model.DeviceModel;
import Model.DomainKeyValue;
import Model.MetricModel;
import entity.Device;
import entity.Device_;
import entity.Domain;
import entity.Domain_;
import entity.Metric;
import entity.Metric_;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import entity.UserEnt;
import entity.UserEnt_;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ahure
 */
@Stateless
public class MobileTransfertServiceImpl implements MobileTransfertService {
    
    @PersistenceContext(unitName = "atlantPu",type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Override
    public List<DomainKeyValue> getDomainsByUser(String username){
        List<DomainKeyValue>  domains;
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<DomainKeyValue> cq = cb.createQuery(DomainKeyValue.class);
            Root<Domain> root = cq.from(Domain.class);
            Join<Domain,UserEnt> user = root.join(Domain_.user);
            Predicate pred = cb.equal(user.get(UserEnt_.username), username);
            cq.where(pred);
            cq.select(cb.construct(DomainKeyValue.class, root.get(Domain_.name),root.get(Domain_.macAddress),root.get(Domain_.id)));
            domains = em.createQuery(cq).getResultList();
        }catch(NoResultException e){
            System.out.println(e);
            domains=new ArrayList<>();
        }
        return domains;
    }

    @Override
    public List<DeviceModel> getDeviceByDomain(Long idDomain) {
        List<DeviceModel>  devices;
        System.out.println("init");
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<DeviceModel> cq = cb.createQuery(DeviceModel.class);
            Root<Device> root = cq.from(Device.class);
            Join<Device,Domain> domain = root.join(Device_.domain);
            Predicate pred = cb.equal(domain.get(Domain_.id), idDomain);
            cq.where(pred);
            cq.select(cb.construct(DeviceModel.class, root.get(Device_.id),root.get(Device_.name),root.get(Device_.macAddress),root.get(Device_.type)));
            devices = em.createQuery(cq).getResultList();
        }catch(NoResultException e){
            System.out.println(e);
            devices=new ArrayList<>();
        }
                System.out.println("end");
        return devices;
    }
    
    @Override
    public List<MetricModel> getMetricsFromDevice(Long idDevices,Date date) {
        List<MetricModel>  metrics;
        try{System.out.println("init");
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MetricModel> cq = cb.createQuery(MetricModel.class);
            Root<Metric> root = cq.from(Metric.class);
            Join<Metric,Device> device = root.join(Metric_.device);
            Predicate pred = cb.equal(device.get(Device_.id), idDevices);
            cq.where(pred);
            cq.select(cb.construct(MetricModel.class,root.get(Metric_.value),root.get(Metric_.createdAt).as(Date.class)));
            metrics = em.createQuery(cq).getResultList();
        }catch(NoResultException e){
            System.out.println(e);
            metrics=new ArrayList<>();
        }
        return metrics;
    }
    
    
}
