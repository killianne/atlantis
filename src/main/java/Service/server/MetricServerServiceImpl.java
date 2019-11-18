/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.server;

import Model.Server.MetriCalModel;
import Model.Server.MetricServer;
import entity.Device;
import entity.Device_;
import entity.Domain;
import entity.Domain_;
import entity.Metric;
import entity.Metric_;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author ahure
 */
@Stateless
public class MetricServerServiceImpl implements MetricServerService {

    @PersistenceContext(unitName = "atlantPu", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public List<MetriCalModel> getMetricsToUpdate() {
        List<MetriCalModel> lst = null;
        lst = queryMetricToUpdate();
        if (lst != null && lst.size() > 0) {
            Domain domain = em.find(Domain.class, lst.get(0).getId());
            domain.setLastUpdate(new Date());
            em.merge(domain);
        } else {
            Domain domain = getDomainToUpdate().get(0);
            domain.setLastUpdate(new Date());
            em.merge(domain);
        }

        return lst;
    }

    private List<Domain> getDomainToUpdate() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Domain> cq = cb.createQuery(Domain.class);
        Root<Domain> root = cq.from(Domain.class);
        Subquery<Date> sq = cq.subquery(Date.class);
        Root<Domain> rootsub = sq.from(Domain.class);
        sq.select(cb.least(rootsub.<Date>get(Domain_.lastUpdate)));
        Predicate predDomain = cb.equal(root.get(Domain_.lastUpdate), sq);
        cq.where(predDomain);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    private List<MetriCalModel> queryMetricToUpdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MetriCalModel> cq = cb.createQuery(MetriCalModel.class);
        Root<Domain> root = cq.from(Domain.class);
        Join<Domain, Device> device = root.join(Domain_.devices);
        Join<Device, Metric> metric = device.join(Device_.metrics);
        Subquery<Date> sq = cq.subquery(Date.class);
        Root<Domain> rootsub = sq.from(Domain.class);
        sq.select(cb.least(rootsub.<Date>get(Domain_.lastUpdate)));
        Predicate predMetric = cb.greaterThan(metric.get(Metric_.createdAt).as(java.sql.Date.class), calendar.getTime());
        Predicate predDomain = cb.equal(root.get(Domain_.lastUpdate), sq);
        Predicate preds = cb.and(predMetric, predDomain);
        cq.where(preds);
        cq.select(cb.construct(MetriCalModel.class, root.get(Domain_.id), device.get(Device_.type), metric.get(Metric_.value)));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public boolean SaveMetrics(List<MetricServer> model) {
        for(MetricServer metr : model)
        {
            Metric metric = null;
            Device device = em.find(Device.class, metr.getId_device());
            if (device != null) {
            metric = metr.getMetricObj();
            metric.setDevice(device);
            metric = em.merge(metric);
            }
        }
        em.flush();
       return true;
    }

    @Override
    public boolean SaveMetric(MetricServer model) {
        Device device = em.find(Device.class, model.getId_device());
        Metric metric = null;
        if (device != null) {
            metric = model.getMetricObj();
            metric.setDevice(device);
            metric = em.merge(metric);
            em.flush();
        }
        if (metric == null) {
            return false;
        } else {
            return true;
        }
//        Domain domain = findDomainByMac(metric.getMacAddrDomain());
//        if(domain==null){
//            domain = new Domain();
//            domain.setDevices(new ArrayList<Device>());
//            domain.setLastUpdate(new Date());
//            domain.setMacAddress(metric.getMacAddrDomain());
//            domain.setName("default");
//        }
//        
//        Device device = findDeviceByMac(metric.getMacAddress());
//        if(device==null){
//            device = new Device();
//            device.setMacAddress(metric.getMacAddress());
//            device.setName("default");
//            device.setMetrics(new ArrayList<Metric>());
//            domain.getDevices().add(device);
//        }
//        TODO - Bad implement first imset don't set FK cause id not know
//        if(device.getDomain()==null){
//            device.setDomain(domain);
//        }
//        
//        Metric metricres = metric.getMetricObj();
//        metricres.setDevice(device);
//        device.getMetrics().add(metricres);
//        
//        em.merge(domain);
//        em.flush();
//        return false;
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
