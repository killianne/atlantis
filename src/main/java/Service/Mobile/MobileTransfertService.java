/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Mobile;


import Model.DeviceModel;
import Model.DomainKeyValue;
import Model.MetricModel;
import entity.UserEnt;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author ahure
 */
@Local
public interface MobileTransfertService   {
    
    public List<MetricModel> getMetricsFromDevice(Long idDevices,Date date);
    public List<DomainKeyValue> getDomainsByUser(String username);   
    public List<DeviceModel> getDeviceByDomain(Long idDomain);

}
