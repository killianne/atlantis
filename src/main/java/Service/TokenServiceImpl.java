/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Token.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import entity.Device;
import entity.Device_;
import entity.UserEnt;
import entity.UserEnt_;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahure
 */
@Singleton
public class TokenServiceImpl implements TokenService {

    //TODO
    //ADD required everywhere expet auth 
    
    @PersistenceContext(unitName = "atlantPu", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public void addToken(String token) {

        RSAPublicKey publicKey = null;
        RSAPrivateKey privateKey = null;
        try {
            DecodedJWT jwt = JWT.decode(token);
            System.out.println(jwt.getKeyId());
            Map<String, Claim> claims = jwt.getClaims();
            UserEnt user = AddUser(claims.get("given_name").asString());
            user.setToken(token);
            em.merge(user);
        } catch (JWTVerificationException exception) {
            System.out.println(exception);
        }
    }

    private UserEnt AddUser(String username) {
        List<UserEnt> users = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEnt> cq = cb.createQuery(UserEnt.class);
        Root<UserEnt> root = cq.from(UserEnt.class);
        Predicate pred = cb.like(root.get(UserEnt_.username), username);
        cq.where(pred);
        cq.select(root);
        users = em.createQuery(cq).getResultList();
        
        if(users.size()>0)
            return users.get(0);
        UserEnt user = new UserEnt();
        user.setUsername(username);
        user.setEmail("default");
        return user;
    }
}
