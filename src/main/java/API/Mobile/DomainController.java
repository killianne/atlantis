/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.Mobile;

import API.token.SecuritySession;
import Model.DomainKeyValue;
import Model.UserModel;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Service.Mobile.MobileTransfertService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import entity.UserEnt;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

/**
 *
 * @author ahure
 */
@Stateless
@Path("domain")
@Produces({MediaType.APPLICATION_JSON})
public class DomainController {
    
    @Inject
    private MobileTransfertService userManagerService;
    
    @GET
    public Response user(ContainerRequestContext requestContext) {
        String key =requestContext.getHeaders().getFirst("Authorization");
        if(key!=null){
            System.out.println(key);
        }
        String username="axel";
         RSAPublicKey publicKey = null;
        RSAPrivateKey privateKey = null;
        try {
            DecodedJWT jwt = JWT.decode(key.split(" ")[1]);
            System.out.println(jwt.getKeyId());
            Map<String, Claim> claims = jwt.getClaims();
            username=claims.get("given_name").asString();
        } catch (JWTVerificationException exception) {
            System.out.println(exception);
        }
        System.out.println("API.Mobile.DomainController.user()");
        List<DomainKeyValue> domains = userManagerService.getDomainsByUser(username);
        return Response.ok().entity(domains).header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
    }

}
