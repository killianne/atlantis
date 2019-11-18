/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.server;

import Model.Server.DeviceServerModel;
import entity.Device;
import entity.Device_;
import entity.Domain;
import entity.Domain_;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ahure
 */
@Stateless
public class DomDeviceServerServiceImpl implements DomDeviceServerService {

    @PersistenceContext(unitName = "atlantPu", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    //
    @Override
    public Device SaveDevice(DeviceServerModel model) {
        Device device = new Device();
        Domain domain = findDomainByMac(model.getMacDomain());
        device.setMacAddress(model.getMacAddress());
        device.setName(model.getName());
        device.setType(model.getDeviceType());
        device.setDomain(domain);
        device=em.merge(device);
        em.flush();
        return device;
    }

    @Override
    public Domain SaveDomain(DeviceServerModel model) {
        Domain domain = new Domain();
        domain.setIp(model.getIp());
        domain.setMacAddress(model.getMacDomain());
        domain.setLastUpdate(new Date());
        domain.setName(model.getName());
        domain=em.merge(domain);
        em.flush();
        return domain;
    }
    
    public Domain findDomainByMac(String mac) {
        Domain domain;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Domain> cq = cb.createQuery(Domain.class);
            Root<Domain> root = cq.from(Domain.class);
            Predicate pred = cb.like(root.get(Domain_.macAddress), mac);
            cq.where(pred);
            cq.select(root);
            domain = em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            domain = null;
        }
        return domain;
    }

    public Device findDeviceByMac(String mac) {
        Device device;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Device> cq = cb.createQuery(Device.class);
            Root<Device> root = cq.from(Device.class);
            Predicate pred = cb.like(root.get(Device_.macAddress), mac);
            cq.where(pred);
            cq.select(root);
            device = em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            device = null;
        }

        return device;
    }

}
