/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;

import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ServiceFactory {

    private static final Log log = LogFactory.getLog(ServiceFactory.class);

    public enum FactoryServiceType {

        DEFAULT_SERVICE_FACADE
    }

    public static IServiceFacade getDefaultServiceFacade() {
        return getServiceFacade(FactoryServiceType.DEFAULT_SERVICE_FACADE, null);
    }

    public static IServiceFacade getServiceFacade(FactoryServiceType serviceType, AbstractDaoFactory.FactoryDaoType daoType) {
        IServiceFacade serviceFacade = null;
        log.debug("serviceType: " + serviceType);
        switch (serviceType) {
            case DEFAULT_SERVICE_FACADE:
            default:
                if (daoType != null) {
                    serviceFacade = new ServiceFacade(daoType);
                } else {
                    serviceFacade = new ServiceFacade();
                }
                break;
        }
        return serviceFacade;
    }
}
