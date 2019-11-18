/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.server;

import Model.Server.DeviceServerModel;
import entity.Device;
import entity.Domain;
import javax.ejb.Local;

/**
 *
 * @author ahure
 */
@Local
public interface DomDeviceServerService {
    public Device SaveDevice(DeviceServerModel device); 
    public Domain SaveDomain(DeviceServerModel device);

}
