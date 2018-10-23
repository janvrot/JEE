/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.test;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Medecin;
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
public class JUnitMedecinDao {

    private static final Log log = LogFactory.getLog(JUnitUtilisateurDao.class);
    private static IServiceFacade serviceFacade = null;
    // Compter le nombre de medecins dans votre base de données.

    private static List<Medecin> medecins = null;

    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private static MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

    @Test
    public void testFindAllMedecins() {
        log.debug("Entree de la methode");
        medecins = serviceFacade.getMedecinDao().findAllMedecins();
        if (medecins != null) {
            log.debug("NB_MEDECINS_LIST: " + Constants.NB_MEDECINS_LIST + " , medecins.size(): " + medecins.size());
            Assert.assertEquals(Constants.NB_MEDECINS_LIST, medecins.size());
            verifyDataMedecin(medecins);
        }
        log.debug("Sortie de la methode");
    }

    public void verifyDataMedecin(List<Medecin> medecins) {
        log.debug("Entree de la methode");
        medecins = serviceFacade.getMedecinDao().findAllMedecins();
        if (medecins != null) {
            log.debug("medecins.size(): " + medecins.size());
            for (Medecin medecin : medecins) {
                Assert.assertNotNull(medecin.getNumeroAccreditation());
            }
        } else if (medecins == null || medecins.isEmpty()) {
            Assert.fail("Aucun medecin n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");
        List<Medecin> medecinsByPrenom = serviceFacade.getMedecinDao().findMedecinByPrenom(Constants.MEDECINS_FIND_BY_PRENOM);
        List<Medecin> medecinsByNom = serviceFacade.getMedecinDao().findMedecinByNom(Constants.MEDECINS_FIND_BY_NOM);
        List<Medecin> medecinsByVille = serviceFacade.getMedecinDao().findMedecinByVille(Constants.MEDECINS_FIND_BY_VILLE);

        if (medecinsByPrenom != null) {
            log.debug("MEDECINS_FIND_BY_PRENOM: " + Constants.MEDECINS_FIND_BY_PRENOM + " , medecinsByPrenom.size(): " + medecinsByPrenom.size());
            Assert.assertEquals(Constants.NB_MEDECINS_FIND_BY_PRENOM, medecinsByPrenom.size());
            verifyDataMedecin(medecinsByPrenom);
        } else {
            Assert.fail("Aucun medecin avec le prenom '" + Constants.MEDECINS_FIND_BY_PRENOM + "' n'a ete trouve dans votre base de données");
        }
        if (medecinsByNom != null) {
            log.debug("MEDECINS_FIND_BY_NOM: " + Constants.MEDECINS_FIND_BY_NOM + " , medecinsByNon.size(): " + medecinsByNom.size());
            Assert.assertEquals(Constants.NB_MEDECINS_FIND_BY_NOM, medecinsByNom.size());
            verifyDataMedecin(medecinsByNom);
        } else {
            Assert.fail("Aucun medecin avec le nom '" + Constants.MEDECINS_FIND_BY_NOM + "' n'a ete trouve dans votre base de données");
        }
        if (medecinsByVille != null) {
            log.debug("MEDECINS_FIND_BY_VILLE: " + Constants.MEDECINS_FIND_BY_VILLE + " , medecinsByVille.size(): " + medecinsByVille.size());
            Assert.assertEquals(Constants.NB_MEDECINS_FIND_BY_VILLE, medecinsByVille.size());
            verifyDataMedecin(medecinsByVille);
        } else {
            Assert.fail("Aucun medecin avec la ville '" + Constants.MEDECINS_FIND_BY_VILLE + "' n'a ete trouve dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteMedecin() {
        log.debug("Entree de la methode");
        Medecin medecinCRUD = new Medecin("AAABBBCCC01", "0687521578", 2);
        medecinCRUD = serviceFacade.getMedecinDao().createMedecin(medecinCRUD);
        Assert.assertNotNull(medecinCRUD);
        Assert.assertNotNull(medecinCRUD.getNumeroAccreditation());
        Assert.assertNotNull(medecinCRUD.getNumeroTelephone());
        log.debug("Created medecinCRUD : " + medecinCRUD);
        medecinCRUD = serviceFacade.getMedecinDao().findMedecinById(medecinCRUD.getIdMedecin());
        Assert.assertNotNull(medecinCRUD);
        medecinCRUD.setNumeroTelephone("0674066274");
        medecinCRUD = serviceFacade.getMedecinDao().updateMedecin(medecinCRUD);
        Assert.assertNotNull(medecinCRUD);
        medecinCRUD = serviceFacade.getMedecinDao().findMedecinById(medecinCRUD.getIdMedecin());
        log.debug("Updated medecinCRUD : " + medecinCRUD);
        Assert.assertEquals("0674066274", medecinCRUD.getNumeroTelephone());
        Assert.assertTrue(serviceFacade.getMedecinDao().deleteMedecin(medecinCRUD));
        List<Medecin> medecinFinal = serviceFacade.getMedecinDao().findAllMedecins();
        if (medecinFinal != null) {
            Assert.assertEquals(Constants.NB_MEDECINS_LIST, medecinFinal.size());
            log.debug("medecinFinal.size() : " + medecinFinal.size() + " , NB_MEDECINS_LIST: " + Constants.NB_MEDECINS_LIST);
        } else if (medecinFinal == null || medecinFinal.isEmpty()) {
            Assert.fail("Aucun medecin n'a ete trouve au final");
        }
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        serviceFacade = null;
        medecins = null;
        log.debug("Sortie de la methode");
    }
}
