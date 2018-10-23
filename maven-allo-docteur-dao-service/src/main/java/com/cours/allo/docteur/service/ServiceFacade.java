/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.service;

import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.factory.AbstractDaoFactory.FactoryDaoType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    private final FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

    // On liste toutes les DAO : un DAO pour chaque entité (Personne ect ....)
    private IUtilisateurDao utilisateurDao = null;
    private IAdresseDao adresseDao = null;
    private ICreneauDao creneauDao = null;
    private IMedecinDao medecinDao = null;
    private IPatientDao patientDao = null;
    private IRendezVousDao rendezVousDao = null;

    public ServiceFacade() {
        // mettre tous les DAO
        AbstractDaoFactory abstractDaoFactory = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION);
        utilisateurDao = abstractDaoFactory.getUtilisateurDao();
        adresseDao = abstractDaoFactory.getAdresseDao();
        creneauDao = abstractDaoFactory.getCreneauDao();
        medecinDao = abstractDaoFactory.getMedecinDao();
        patientDao = abstractDaoFactory.getPatientDao();
        rendezVousDao = abstractDaoFactory.getRendezVousDao();
    }

    public ServiceFacade(FactoryDaoType factoryType) {
        // mettre tous les DAO
        AbstractDaoFactory abstractDaoFactory = AbstractDaoFactory.getFactory(factoryType);
        utilisateurDao = abstractDaoFactory.getUtilisateurDao();
        adresseDao = abstractDaoFactory.getAdresseDao();
        creneauDao = abstractDaoFactory.getCreneauDao();
        medecinDao = abstractDaoFactory.getMedecinDao();
        patientDao = abstractDaoFactory.getPatientDao();
        rendezVousDao = abstractDaoFactory.getRendezVousDao();
    }

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return adresseDao;
    }
    
    @Override
    public ICreneauDao getCreneauDao() {
        return creneauDao;
    }
    
    @Override
    public IMedecinDao getMedecinDao() {
        return medecinDao;
    }
    
    @Override
    public IPatientDao getPatientDao() {
        return patientDao;
    }
    
    @Override
    public IRendezVousDao getRendezVousDao() {
        return rendezVousDao;
    }
}
