/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Mobile;

import API.token.SecuritySession;
import Model.DeviceModel;
import Model.DomainKeyValue;
import Model.MetricModel;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Service.Mobile.MobileTransfertService;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author ahure
 */
@Stateless
@Path("metric")
@Produces({MediaType.APPLICATION_JSON})
public class MetricController {
    
    @Inject
    private MobileTransfertService mobileTransfertService;
    
    @GET
    @SecuritySession.Secured
    @Path("/{id}/{duration}/")
    public Response metrics(@PathParam("id") Long idDevice,@PathParam("duration")int duration) {
        System.out.println(duration);
        
        List<MetricModel> metrics = mobileTransfertService.getMetricsFromDevice(idDevice,null);
        
        return Response.ok().entity(metrics.subList(0, duration)).header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }

}
