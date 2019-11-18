/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ahure
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(API.Mobile.CommandController.class);
        resources.add(API.Mobile.DeviceController.class);
        resources.add(API.Mobile.DomainController.class);
        resources.add(API.Mobile.MetricCalController.class);
        resources.add(API.Mobile.MetricController.class);
        resources.add(API.Server.MetricServerController.class);
        resources.add(API.token.AuthenticationFilter.class);
        resources.add(API.token.TestSecurity.class);
        resources.add(API.token.TokenEndpointController.class);
    }
    
}
