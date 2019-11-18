/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.server;


import Model.Server.MetriCalModel;
import Model.Server.MetricServer;
import java.util.List;
import javax.ejb.Local;
/**
 *
 * @author ahure
 */
@Local
public interface MetricServerService   {
    
    public List<MetriCalModel> getMetricsToUpdate();

    public boolean SaveMetrics(List<MetricServer> metrics);

    public boolean SaveMetric(MetricServer metric);
}
