/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Mobile;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ahure
 */
@Stateless
@Path("command")
@Produces({MediaType.APPLICATION_JSON})
public class CommandController {
    
    @POST
    @Path("/{id_device}/{type}/")
    public Response postCommand(@PathParam("id_device") Long id_device,@PathParam("type") String type){
        
        Client client = ClientBuilder.newClient();
        WebTarget target;
        target = client.target("http://192.168.43.219:2890/device/")
                .path(id_device+"/command/"+type);
        System.out.println(type);
        Response resp = target.request().get();
                
        
        return Response.ok().entity(resp.getEntity()).build();
    }
    
}
