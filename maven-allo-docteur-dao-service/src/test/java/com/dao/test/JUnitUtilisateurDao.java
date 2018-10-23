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
public class JUnitUtilisateurDao {

    private static final Log log = LogFactory.getLog(JUnitUtilisateurDao.class);
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
    public void testFindAllUtilisateurs() {
        log.debug("Entree de la methode");
        utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        if (utilisateurs != null) {
            log.debug("NB_UTILISATEURS_LIST: " + Constants.NB_UTILISATEURS_LIST + " , utilisateurs.size(): " + utilisateurs.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_LIST, utilisateurs.size());
            verifyDataUtilisateur(utilisateurs);
        }
        log.debug("Sortie de la methode");
    }

    public void verifyDataUtilisateur(List<Utilisateur> utilisateurs) {
        log.debug("Entree de la methode");
        utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        if (utilisateurs != null) {
            log.debug("utilisateurs.size(): " + utilisateurs.size());
            for (Utilisateur user : utilisateurs) {
                Assert.assertNotNull(user.getAdresse());
            }
        } else if (utilisateurs == null || utilisateurs.isEmpty()) {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");
        List<Utilisateur> utilisateursByPrenom = serviceFacade.getUtilisateurDao().findUtilisateursByPrenom(Constants.UTILISATEUR_FIND_BY_PRENOM);
        List<Utilisateur> utilisateursByNom = serviceFacade.getUtilisateurDao().findUtilisateursByNom(Constants.UTILISATEUR_FIND_BY_NOM);
        List<Utilisateur> utilisateursByCodePostal = serviceFacade.getUtilisateurDao().findUtilisateursByCodePostal(Constants.UTILISATEUR_FIND_BY_CODE_POSTAL);

        if (utilisateursByPrenom != null) {
            log.debug("NB_UTILISATEURS_FIND_BY_PRENOM: " + Constants.NB_UTILISATEURS_FIND_BY_PRENOM + " , utilisateursByPrenom.size(): " + utilisateursByPrenom.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_PRENOM, utilisateursByPrenom.size());
            verifyDataUtilisateur(utilisateursByPrenom);
        } else {
            Assert.fail("Aucun utilisateur avec le prenom '" + Constants.UTILISATEUR_FIND_BY_PRENOM + "' n'a ete trouve dans votre base de données");
        }
        if (utilisateursByNom != null) {
            log.debug("NB_UTILISATEURS_FIND_BY_NOM: " + Constants.NB_UTILISATEURS_FIND_BY_NOM + " , utilisateursByNom.size(): " + utilisateursByNom.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_NOM, utilisateursByNom.size());
            verifyDataUtilisateur(utilisateursByNom);
        } else {
            Assert.fail("Aucun utilisateur avec le nom '" + Constants.UTILISATEUR_FIND_BY_NOM + "' n'a ete trouve dans votre base de données");
        }
        if (utilisateursByCodePostal != null) {
            log.debug("NB_UTILISATEURS_FIND_BY_CODE_POSTAL: " + Constants.NB_UTILISATEURS_FIND_BY_CODE_POSTAL + " , utilisateursByCodePostal.size(): " + utilisateursByCodePostal.size());
            Assert.assertEquals(Constants.NB_UTILISATEURS_FIND_BY_CODE_POSTAL, utilisateursByCodePostal.size());
            verifyDataUtilisateur(utilisateursByCodePostal);
        } else {
            Assert.fail("Aucun utilisateur avec le code postal '" + Constants.UTILISATEUR_FIND_BY_CODE_POSTAL + "' n'a ete trouve dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteUtilisateur() {
        log.debug("Entree de la methode");
        Utilisateur userCRUD = new Utilisateur("Mr", "Jean", "Ass", "jean.ass@gmail.com", "passw0rd", new Date(System.currentTimeMillis()));
        userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        Assert.assertNotNull(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD.getPrenom());
        Assert.assertNotNull(userCRUD.getNom());
        log.debug("Created userCRUD : " + userCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        Assert.assertNotNull(userCRUD);
        userCRUD.setPrenom("Jean Bis");
        userCRUD.setNom("Ass Bis");
        userCRUD = serviceFacade.getUtilisateurDao().updateUtilisateur(userCRUD);
        Assert.assertNotNull(userCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().findUtilisateurById(userCRUD.getIdUtilisateur());
        log.debug("Updated userCRUD : " + userCRUD);
        Assert.assertEquals("Jean Bis", userCRUD.getPrenom());
        Assert.assertEquals("Ass Bis", userCRUD.getNom());
        Assert.assertTrue(serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD));
        List<Utilisateur> utilisateursFinal = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        if (utilisateursFinal != null) {
            Assert.assertEquals(Constants.NB_UTILISATEURS_LIST, utilisateursFinal.size());
            log.debug("utilisateursFinal.size() : " + utilisateursFinal.size() + " , NB_UTILISATEURS_LIST: " + Constants.NB_UTILISATEURS_LIST);
        } else if (utilisateursFinal == null || utilisateursFinal.isEmpty()) {
            Assert.fail("Aucun utilisateur n'a ete trouve au final");
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
