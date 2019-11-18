/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Mobile;

import Model.MetricCalModel;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ahure
 */
@Stateless
@Path("metrical")
@Produces({MediaType.APPLICATION_JSON})
public class MetricCalController {
    
    
    @GET
    @Path("/{id}/{duration}/")
    public Response metricCals(@PathParam("id") Long idDomain,@PathParam("duration")int duration) {
        
        List<MetricCalModel> metrics=null;
        Client client = ClientBuilder.newClient();
        WebTarget target;
        target = client.target("http://192.168.43.219:2888/calcul/inDomain/")
                .path(idDomain+"/"+duration);
               
        Response resp = target.request().get();
        
        
        return Response.ok().entity(resp.getEntity()).header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }
}
