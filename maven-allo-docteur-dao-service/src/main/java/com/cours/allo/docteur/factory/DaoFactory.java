/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.factory;

import com.cours.allo.docteur.dao.impl.AdresseDao;
import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.impl.CreneauDao;
import com.cours.allo.docteur.dao.impl.MedecinDao;
import com.cours.allo.docteur.dao.impl.PatientDao;
import com.cours.allo.docteur.dao.impl.RendezVousDao;
import com.cours.allo.docteur.dao.impl.UtilisateurDao;

/**
 *
 * @author ElHadji
 */
public class DaoFactory extends AbstractDaoFactory {

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new UtilisateurDao();
    }

    @Override
    public IAdresseDao getAdresseDao() {
        return new AdresseDao();
    }
    
    @Override
    public ICreneauDao getCreneauDao() {
        return new CreneauDao();
    }
    
    @Override
    public IMedecinDao getMedecinDao() {
        return new MedecinDao();
    }
    
    @Override
    public IPatientDao getPatientDao() {
        return new PatientDao();
    }
    
    @Override
    public IRendezVousDao getRendezVousDao() {
        return new RendezVousDao();
    }
}
