/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.token;

import API.Mobile.*;
import Model.Common;
import Model.DomainKeyValue;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Service.Mobile.MobileTransfertService;
import Service.TokenService;
import Service.TokenServiceImpl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author ahure
 */
@Stateless
@Path("token")
@Produces({MediaType.APPLICATION_JSON})
public class TokenEndpointController {
    
    @Inject
    private TokenService tokenService;
    
    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    public Response token(String token)
    {
        System.out.println(token);
        tokenService.addToken(token);
        return Response.ok().entity(token).build();
    }
    
//    @POST
//    @Path("test")    
//    public Response tokens(TokenEndpointModel model) throws UnsupportedEncodingException {
//        if(model!=null)
//        {
//            Base64.Encoder encoder = Base64.getEncoder();  
//            Client client = ClientBuilder.newClient();
//            WebTarget target;
//            System.out.println(model.getCode());
//            target = client.target("https://atlantisproject.b2clogin.com/atlantisproject.onmicrosoft.com/oauth2/v2.0/")
//                    .path("token")
//                    .queryParam("p", "b2c_1_signuporsignin")
//                    .queryParam("grant_type", "authorization_code")
//                    .queryParam("client_id", "27fb84fe-4baf-4b6b-bfe7-f2d0638f2790")
//                    .queryParam("redirect_uri", URLEncoder.encode("http://localhost:8090/login",StandardCharsets.UTF_8.toString()))
//                    .queryParam("client_secret", "Zg2%5E04%23WjA%23h%256Q%7B%5DeK53J%26%60")
//                    .queryParam("code",URLEncoder.encode(encoder.encodeToString(model.getCode().getBytes()),"UTF-8"));
//            String response = target.request().header("Content-Type", "base64").get(String.class);
//            System.out.println(response );
//            return Response.ok(response).build();
//        }
//       
//        
//        System.out.println("API.token.TokenEndpointController.tokens()//param"+
//                model.getClient_id()+ " - " + model.getCode());
//        Map<String,String> params = new HashMap<>();
//        params.put("p", "b2c_1_signuporsignin");       
//        params.put("grant_type", "authorization_code");
//        params.put("redirect_url", "http://localhost:8090/login");       
//        params.put("client_id",model.getClient_id() );
//        params.put("code", model.getCode());   
//        //Zg2^04#WjA#h%6Q{]eK53J&`
//        params.put("client_secret", "Zg2%5E04%23WjA%23h%256Q%7B%5DeK53J%26%60");
//        String cont="";
//        try {
//            String param = Common.getParamsString(params);
//            URL url = new URL("https://atlantisproject.b2clogin.com/atlantisproject.onmicrosoft.com/oauth2/v2.0/token");
//            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Accept","*");
//            con.setDoOutput(true);            
//            con.setDoInput(true);
//            con.setReadTimeout(10000);
//            con.setConnectTimeout(15000);
//            con.setInstanceFollowRedirects(false);
//            try (OutputStream os = con.getOutputStream(); BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"))) {
//                writer.write(param);
//                writer.flush();
//            }
//            
//            con.connect();
//            
//            System.out.println(con.getResponseCode());
//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(con.getInputStream(),"utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine = null;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println(response.toString());
//            }
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(TokenEndpointController.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }
//     
//        return Response.ok().entity(cont).build();
//    }
//
}
