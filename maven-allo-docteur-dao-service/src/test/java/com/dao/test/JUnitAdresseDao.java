/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.test;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Janvrot
 */
public class JUnitAdresseDao {
    
    private static final Log log = LogFactory.getLog(JUnitAdresseDao.class);
    private static IServiceFacade serviceFacade = null;
    // Compter le nombre d'utilisateurs dans votre base de données.

    private static List<Utilisateur> utilisateurs = null;
    private static List<Adresse> adresses = null;

    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private static MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }
    
    
    @Test
    public void testFindAllAdresses() {
        log.debug("Entree de la methode");
        adresses = serviceFacade.getAdresseDao().findAllAdresses();
        if (adresses != null) {
            log.debug("NB_ADRESSES_LIST: " + Constants.NB_ADRESSES_LIST + " , adresses.size(): " + adresses.size());
            Assert.assertEquals(Constants.NB_ADRESSES_LIST, adresses.size());
        } else if (adresses == null || adresses.isEmpty()) {
            Assert.fail("Aucun adresse n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");

        List<Adresse> adressesByVille = serviceFacade.getAdresseDao().findAdressesByVille(Constants.ADRESSES_FIND_BY_VILLE);
        List<Adresse> adressesByCodePostal = serviceFacade.getAdresseDao().findAdressesByCodePostal(Constants.ADRESSES_FIND_BY_CODE_POSTAL);

        if (adressesByVille != null) {
            log.debug("NB_ADRESSES_FIND_BY_VILLE: " + Constants.NB_ADRESSES_FIND_BY_VILLE + " , adressesByVille.size(): " + adressesByVille.size());
            Assert.assertEquals(Constants.NB_ADRESSES_FIND_BY_VILLE, adressesByVille.size());
        } else {
            Assert.fail("Aucune adresse avec la ville '" + Constants.ADRESSES_FIND_BY_VILLE + "' n'a ete trouve dans votre base de données");
        }
        if (adressesByCodePostal != null) {
            log.debug("NB_ADRESSES_FIND_BY_CODE_POSTAL: " + Constants.NB_ADRESSES_FIND_BY_CODE_POSTAL + " , adressesByCodePostal.size(): " + adressesByCodePostal.size());
            Assert.assertEquals(Constants.NB_ADRESSES_FIND_BY_CODE_POSTAL, adressesByCodePostal.size());
        } else {
            Assert.fail("Aucune adresse avec le code postal '" + Constants.ADRESSES_FIND_BY_CODE_POSTAL + "' n'a ete trouve dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeleteAdresse() {
        log.debug("Entree de la methode");
        Utilisateur userCRUD = new Utilisateur("Mr", "Jean", "Ass", "jean.ass@gmail.com", "passw0rd", new Date(System.currentTimeMillis()));
        userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        Assert.assertNotNull(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD.getPrenom());
        Assert.assertNotNull(userCRUD.getNom());
        Adresse adresseCRUD = new Adresse("5 rue de l'enfer", "75000", "Paris", "France", userCRUD.getIdUtilisateur());
        adresseCRUD = serviceFacade.getAdresseDao().createAdresse(adresseCRUD);
        Assert.assertNotNull(adresseCRUD);
        Assert.assertNotNull(adresseCRUD.getIdAdresse());
        Assert.assertNotNull(adresseCRUD.getRue());
        Assert.assertNotNull(adresseCRUD.getCodePostal());
        Assert.assertNotNull(adresseCRUD.getVille());
        Assert.assertNotNull(adresseCRUD.getPays());
        log.debug("Created adresseCRUD : " + adresseCRUD);
        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
        Assert.assertNotNull(adresseCRUD);
        adresseCRUD.setCodePostal("75 000 Bis");
        adresseCRUD.setVille("Paris Bis");
        adresseCRUD = serviceFacade.getAdresseDao().updateAdresse(adresseCRUD);
        Assert.assertNotNull(adresseCRUD);
        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
        log.debug("Updated adresseCRUD : " + adresseCRUD);
        Assert.assertEquals("75 000 Bis", adresseCRUD.getCodePostal());
        Assert.assertEquals("Paris Bis", adresseCRUD.getVille());
        Assert.assertTrue(serviceFacade.getAdresseDao().deleteAdresse(adresseCRUD));
        Assert.assertTrue(serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD));
        List<Utilisateur> utilisateursFinal = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        if (utilisateursFinal != null) {
            Assert.assertEquals(Constants.NB_UTILISATEURS_LIST, utilisateursFinal.size());
            log.debug("utilisateursFinal.size() : " + utilisateursFinal.size() + " , NB_UTILISATEURS_LIST: " + Constants.NB_UTILISATEURS_LIST);
        } else if (utilisateursFinal == null || utilisateursFinal.isEmpty()) {
            Assert.fail("Aucun utilisateur n'a ete trouve au final");
        }
        List<Adresse> adressesFinal = serviceFacade.getAdresseDao().findAllAdresses();
        if (adressesFinal != null) {
            Assert.assertEquals(Constants.NB_ADRESSES_LIST, adressesFinal.size());
            log.debug("adressesFinal.size() : " + adressesFinal.size() + " , NB_ADRESSES_LIST: " + Constants.NB_ADRESSES_LIST);
        } else if (adressesFinal == null || adressesFinal.isEmpty()) {
            Assert.fail("Aucune adresse n'a ete trouve au final");
        }
        log.debug("Sortie de la methode");
    }
    
    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        serviceFacade = null;
        utilisateurs = null;
        adresses = null;
        log.debug("Sortie de la methode");
    }
}
