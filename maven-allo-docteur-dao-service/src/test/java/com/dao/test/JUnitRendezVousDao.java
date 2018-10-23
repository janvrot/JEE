/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.test;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class JUnitRendezVousDao {
    
    private static final Log log = LogFactory.getLog(JUnitUtilisateurDao.class);
    private static IServiceFacade serviceFacade = null;
    // Compter le nombre de medecins dans votre base de données.

    private static List<RendezVous> rendezVous = null;

    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private static MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

    @Test
    public void testFindAllRendezVous() {
        log.debug("Entree de la methode");
        rendezVous = serviceFacade.getRendezVousDao().findAllRendezVous();
        if (rendezVous != null) {
            log.debug("NB_RDV_LIST: " + Constants.NB_RDV_LIST + " , rendezVous.size(): " + rendezVous.size());
            Assert.assertEquals(Constants.NB_RDV_LIST, rendezVous.size());
            verifyDataRendezVous(rendezVous);
        }
        log.debug("Sortie de la methode");
    }

    public void verifyDataRendezVous(List<RendezVous> rendezVous) {
        log.debug("Entree de la methode");
        rendezVous = serviceFacade.getRendezVousDao().findAllRendezVous();
        if (rendezVous != null) {
            log.debug("rendezVous.size(): " + rendezVous.size());
            for (RendezVous rdv : rendezVous) {
                Assert.assertNotNull(rdv.getJour());
            }
        } else if (rendezVous == null || rendezVous.isEmpty()) {
            Assert.fail("Aucun rendezVous n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");
        List<RendezVous> rendezVousByMedecin = serviceFacade.getRendezVousDao().fingRendezVousByMedecin(Constants.RDV_FIND_BY_ID_MEDECIN);
        List<RendezVous> rendezVousByPatient = serviceFacade.getRendezVousDao().findAllRendezVousByPatient(Constants.RDV_FIND_BY_ID_PATIENT);

        if (rendezVousByMedecin != null) {
            log.debug("RDV_FIND_BY_ID_MEDECIN: " + Constants.RDV_FIND_BY_ID_MEDECIN + " , rendezVousByMedecin.size(): " + rendezVousByMedecin.size());
            Assert.assertEquals(Constants.NB_RDV_FIND_BY_ID_MEDECIN, rendezVousByMedecin.size());
            verifyDataRendezVous(rendezVousByMedecin);
        } else {
            Assert.fail("Aucun rendezVous avec l'id de medecin '" + Constants.RDV_FIND_BY_ID_MEDECIN + "' n'a ete trouve dans votre base de données");
        }
        if (rendezVousByPatient != null) {
            log.debug("RDV_FIND_BY_ID_PATIENT: " + Constants.RDV_FIND_BY_ID_PATIENT + " , rendezVousByPatient.size(): " + rendezVousByPatient.size());
            Assert.assertEquals(Constants.NB_RDV_FIND_BY_ID_PATIENT, rendezVousByPatient.size());
            verifyDataRendezVous(rendezVousByPatient);
        } else {
            Assert.fail("Aucun medecin avec le nom '" + Constants.MEDECINS_FIND_BY_NOM + "' n'a ete trouve dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteMedecin() throws ParseException {
        log.debug("Entree de la methode");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date jour = dateFormat.parse("08/11/1994");
        RendezVous rendezVousCRUD = new RendezVous(jour, Constants.PRIX_CONSULTATION, 2, 3);
        rendezVousCRUD = serviceFacade.getRendezVousDao().createRendezVous(rendezVousCRUD);
        Assert.assertNotNull(rendezVousCRUD);
        Assert.assertNotNull(rendezVousCRUD.getJour());
        log.debug("Created rendezVousCRUD : " + rendezVousCRUD);
        rendezVousCRUD = serviceFacade.getRendezVousDao().findRendezVousById(rendezVousCRUD.getIdRendezVous());
        Assert.assertNotNull(rendezVousCRUD);
        rendezVousCRUD.setJour(dateFormat.parse("08/12/1994"));
        rendezVousCRUD = serviceFacade.getRendezVousDao().updateRendezVous(rendezVousCRUD);
        Assert.assertNotNull(rendezVousCRUD);
        rendezVousCRUD = serviceFacade.getRendezVousDao().findRendezVousById(rendezVousCRUD.getIdRendezVous());
        log.debug("Updated rendezVousCRUD : " + rendezVousCRUD);
        Assert.assertTrue(serviceFacade.getRendezVousDao().deleteRendezVous(rendezVousCRUD));
        List<RendezVous> rendezVousFinal = serviceFacade.getRendezVousDao().findAllRendezVous();
        if (rendezVousFinal != null) {
            Assert.assertEquals(Constants.NB_RDV_LIST, rendezVousFinal.size());
            log.debug("rendezVousFinal.size() : " + rendezVousFinal.size() + " , NB_RDV_LIST: " + Constants.NB_RDV_LIST);
        } else if (rendezVousFinal == null || rendezVousFinal.isEmpty()) {
            Assert.fail("Aucun rendezVous n'a ete trouve au final");
        }
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        serviceFacade = null;
        rendezVous = null;
        log.debug("Sortie de la methode");
    }
}
