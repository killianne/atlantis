/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Server;

import Model.Server.DeviceServerModel;
import Model.Server.MetriCalModel;
import Model.Server.MetricServer;
import Service.server.DomDeviceServerService;
import Service.server.MetricServerService;
import entity.Device;
import entity.Domain;
import java.util.List;
import javax.inject.Inject;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ahure
 */
@Stateless
@Path("server")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class MetricServerController {
    
    @Inject
    private MetricServerService metricServerService;
    
    @Inject
    private DomDeviceServerService domDeviceServerService;
    
    @POST
    @Path("device")
    public Response postDevice(DeviceServerModel model){
        Device device=domDeviceServerService.SaveDevice(model);
        return Response.ok().entity(device.getId()).build();
    }
    
    @POST
    @Path("domain")
    public Response postDomain(DeviceServerModel model){
        Domain domain=domDeviceServerService.SaveDomain(model);
        return Response.ok().entity(domain.getId()).build();
    }
    
    @POST
    @Path("metric")
    public Response postMetric(MetricServer metric) {
        System.out.println("API.Server.MetricServerController.postMetric()");
        Response.ResponseBuilder builder = Response.status(Response.Status.ACCEPTED);
        boolean save=false;
        try{
            save = metricServerService.SaveMetric(metric);
        }
        catch(Exception e){
            builder.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        if(save==false)
            builder.status(Response.Status.NOT_MODIFIED);
        return builder.build();
    }
    
    @POST
    @Path("metrics")
    public Response postMetric(List<MetricServer> metrics) {
        Response.ResponseBuilder builder = Response.status(Response.Status.ACCEPTED);
        boolean save=false;
        try{
            save = metricServerService.SaveMetrics(metrics);
        }
        catch(Exception e){
            builder.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        if(save==false)
            builder.status(Response.Status.NOT_MODIFIED);
        return builder.build();
    }
    
    @GET
    @Path("metric")
    public Response getMetricToUpdate() {
        List<MetriCalModel> metricCals=null;
        try{
            metricCals=metricServerService.getMetricsToUpdate();
            
        }
        catch(Exception e){
            System.out.println(e);
            return Response.serverError().build();
        }
        return Response.ok().entity(metricCals).build();
    }

}
