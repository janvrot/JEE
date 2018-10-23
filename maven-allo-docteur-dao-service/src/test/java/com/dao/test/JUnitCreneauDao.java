/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.test;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Creneau;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Janvrot
 */
public class JUnitCreneauDao {

    private static final Log log = LogFactory.getLog(JUnitUtilisateurDao.class);
    private static IServiceFacade serviceFacade = null;
    // Compter le nombre d'utilisateurs dans votre base de données.

    private static List<Creneau> creneaux = null;

    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private static MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

    @Test
    public void testFindAllCreneaux() {
        log.debug("Entree de la methode");
        creneaux = serviceFacade.getCreneauDao().findAllCreneaux();
        if (creneaux != null) {
            log.debug("NB_CRENEAUX_LIST: " + Constants.NB_CRENEAUX_LIST + " , creneaux.size(): " + creneaux.size());
            Assert.assertEquals(Constants.NB_CRENEAUX_LIST, creneaux.size());
            verifyDataCreneau(creneaux);
        }
        log.debug("Sortie de la methode");
    }

    public void verifyDataCreneau(List<Creneau> creneaux) {
        log.debug("Entree de la methode");
        creneaux = serviceFacade.getCreneauDao().findAllCreneaux();
        if (creneaux != null) {
            log.debug("creneaux.size(): " + creneaux.size());
            for (Creneau creneau : creneaux) {
                Assert.assertNotNull(creneau.getHeureDebut());
                Assert.assertNotNull(creneau.getHeureFin());
                Assert.assertNotNull(creneau.getMinuteDebut());
                Assert.assertNotNull(creneau.getMinuteFin());
            }
        } else if (creneaux == null || creneaux.isEmpty()) {
            Assert.fail("Aucun creneaux n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteCreneau() {
        log.debug("Entree de la methode");
        Creneau creneauCRUD = new Creneau(10, 10, 11, 0, 1);
        creneauCRUD = serviceFacade.getCreneauDao().createCreneau(creneauCRUD);
        Assert.assertNotNull(creneauCRUD);
        Assert.assertNotNull(creneauCRUD.getHeureDebut());
        Assert.assertNotNull(creneauCRUD.getHeureFin());
        Assert.assertNotNull(creneauCRUD.getMinuteDebut());
        Assert.assertNotNull(creneauCRUD.getMinuteFin());
        log.debug("Created creneauCRUD : " + creneauCRUD);
        creneauCRUD = serviceFacade.getCreneauDao().findCreneauById(creneauCRUD.getIdCreneau());
        Assert.assertNotNull(creneauCRUD);
        creneauCRUD.setHeureDebut(11);
        serviceFacade.getCreneauDao().updateCreneau(creneauCRUD);
        Assert.assertNotNull(creneauCRUD);
        creneauCRUD = serviceFacade.getCreneauDao().findCreneauById(creneauCRUD.getIdCreneau());
        log.debug("Updated creneauCRUD : " + creneauCRUD);
        Assert.assertTrue(serviceFacade.getCreneauDao().deleteCreneau(creneauCRUD));
        List<Creneau> creneauxFinal = serviceFacade.getCreneauDao().findAllCreneaux();
        if (creneauxFinal != null) {
            Assert.assertEquals(Constants.NB_CRENEAUX_LIST, creneauxFinal.size());
            log.debug("creneauxFinal.size() : " + creneauxFinal.size() + " , NB_CRENEAUX_LIST: " + Constants.NB_CRENEAUX_LIST);
        } else if (creneauxFinal == null || creneauxFinal.isEmpty()) {
            Assert.fail("Aucun creneau n'a ete trouve au final");
        }
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        serviceFacade = null;
        creneaux = null;
        log.debug("Sortie de la methode");
    }
}
