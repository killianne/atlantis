/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.token;

import Service.TokenServiceImpl;
import entity.UserEnt;
import entity.UserEnt_;
import java.io.IOException;
import java.util.List;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ahure
 */
@SecuritySession.Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String REALM = "Atlante";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @PersistenceContext(unitName = "atlantPu", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("FILTERING");


        // Get the Authorization header from the request
        String authorizationHeader
                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {

            // Validate the token
            validateToken(token, requestContext);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private void validateToken(String token, ContainerRequestContext requestContext) throws Exception {
        try {
            List<UserEnt> users = null;
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UserEnt> cq = cb.createQuery(UserEnt.class);
            Root<UserEnt> root = cq.from(UserEnt.class);
            Predicate pred = cb.like(root.get(UserEnt_.token), token);;
            cq.where(pred);
            cq.select(root);
            users = em.createQuery(cq).getResultList();
            if (users.size()>0) {
                System.out.println("Valid access for : "+users.get(0).getUsername());
            } else {
                abortWithUnauthorized(requestContext);
                System.out.println("Access-Refused");
                System.out.println(token);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
