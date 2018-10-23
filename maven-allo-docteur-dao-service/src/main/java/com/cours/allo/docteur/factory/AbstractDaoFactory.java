/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public abstract class AbstractDaoFactory {

    public static String className = AbstractDaoFactory.class.getName();
    private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

    public enum FactoryDaoType {

        MANUAL_LIST_DAO_FACTORY, MANUAL_MAP_DAO_FACTORY, JDBC_DAO_FACTORY;
    }

    public abstract IUtilisateurDao getUtilisateurDao();

    public abstract IAdresseDao getAdresseDao();
    
    public abstract ICreneauDao getCreneauDao();
    
    public abstract IMedecinDao getMedecinDao();
    
    public abstract IPatientDao getPatientDao();
    
    public abstract IRendezVousDao getRendezVousDao();

    /**
     * Méthode pour récupérer une factory de DAO
     *
     * @param daoType
     * @return AbstractDaoFactory
     */
    public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
        String methodName = "getFactory";
        AbstractDaoFactory factory = null;
        if (daoType == FactoryDaoType.JDBC_DAO_FACTORY) {
            factory = new DaoFactory();
        }
        log.debug("daoType: " + daoType);
        return factory;
    }
}
