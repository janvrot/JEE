/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.test;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
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
public class JUnitPatientDao {
    
    private static final Log log = LogFactory.getLog(JUnitUtilisateurDao.class);
    private static IServiceFacade serviceFacade = null;
    // Compter le nombre de medecins dans votre base de données.

    private static List<Patient> patients = null;

    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private static MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
    }

    @Test
    public void testFindAllPatients() {
        log.debug("Entree de la methode");
        patients = serviceFacade.getPatientDao().findAllPatients();
        if (patients != null) {
            log.debug("NB_PATIENTS_LIST: " + Constants.NB_PATIENTS_LIST + " , patients.size(): " + patients.size());
            Assert.assertEquals(Constants.NB_PATIENTS_LIST, patients.size());
            verifyDataPatient(patients);
        }
        log.debug("Sortie de la methode");
    }

    public void verifyDataPatient(List<Patient> patients) {
        log.debug("Entree de la methode");
        patients = serviceFacade.getPatientDao().findAllPatients();
        if (patients != null) {
            log.debug("patients.size(): " + patients.size());
            for (Patient patient : patients) {
                Assert.assertNotNull(patient.getNumeroSecuriteSociale());
            }
        } else if (patients == null || patients.isEmpty()) {
            Assert.fail("Aucun patient n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entree de la methode");
        List<Patient> patientsByPrenom = serviceFacade.getPatientDao().findPatientByPrenom(Constants.PATIENTS_FIND_BY_PRENOM);
        List<Patient> patientsByNom = serviceFacade.getPatientDao().findPatientByNom(Constants.PATIENTS_FIND_BY_NOM);
        List<Patient> patientsByVille = serviceFacade.getPatientDao().findPatientByVille(Constants.PATIENTS_FIND_BY_VILLE);

        if (patientsByPrenom != null) {
            log.debug("PATIENTS_FIND_BY_PRENOM: " + Constants.PATIENTS_FIND_BY_PRENOM + " , patientsByPrenom.size(): " + patientsByPrenom.size());
            Assert.assertEquals(Constants.NB_PATIENTS_FIND_BY_PRENOM, patientsByPrenom.size());
            verifyDataPatient(patientsByPrenom);
        } else {
            Assert.fail("Aucun patient avec le prenom '" + Constants.PATIENTS_FIND_BY_PRENOM + "' n'a ete trouve dans votre base de données");
        }
        if (patientsByNom != null) {
            log.debug("PATIENTS_FIND_BY_NOM: " + Constants.PATIENTS_FIND_BY_NOM + " , patientsByNom.size(): " + patientsByNom.size());
            Assert.assertEquals(Constants.NB_PATIENTS_FIND_BY_NOM, patientsByNom.size());
            verifyDataPatient(patientsByNom);
        } else {
            Assert.fail("Aucun patient avec le nom '" + Constants.PATIENTS_FIND_BY_NOM + "' n'a ete trouve dans votre base de données");
        }
        if (patientsByVille != null) {
            log.debug("PATIENTS_FIND_BY_VILLE: " + Constants.PATIENTS_FIND_BY_VILLE + " , patientsByVille.size(): " + patientsByVille.size());
            Assert.assertEquals(Constants.NB_PATIENTS_FIND_BY_VILLE, patientsByVille.size());
            verifyDataPatient(patientsByVille);
        } else {
            Assert.fail("Aucun patient avec la ville '" + Constants.PATIENTS_FIND_BY_VILLE + "' n'a ete trouve dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteMedecin() {
        log.debug("Entree de la methode");
        Patient patientCRUD = new Patient("172295016841975", "0672389145", 3);
        patientCRUD = serviceFacade.getPatientDao().createPatient(patientCRUD);
        Assert.assertNotNull(patientCRUD);
        Assert.assertNotNull(patientCRUD.getNumeroSecuriteSociale());
        Assert.assertNotNull(patientCRUD.getNumeroTelephone());
        log.debug("Created patientCRUD : " + patientCRUD);
        patientCRUD = serviceFacade.getPatientDao().findPatientById(patientCRUD.getIdPatient());
        Assert.assertNotNull(patientCRUD);
        patientCRUD.setNumeroTelephone("0674066274");
        patientCRUD = serviceFacade.getPatientDao().updatePatient(patientCRUD);
        Assert.assertNotNull(patientCRUD);
        patientCRUD = serviceFacade.getPatientDao().findPatientById(patientCRUD.getIdPatient());
        log.debug("Updated patientCRUD : " + patientCRUD);
        Assert.assertEquals("0674066274", patientCRUD.getNumeroTelephone());
        Assert.assertTrue(serviceFacade.getPatientDao().deletePatient(patientCRUD));
        List<Patient> patientFinal = serviceFacade.getPatientDao().findAllPatients();
        if (patientFinal != null) {
            Assert.assertEquals(Constants.NB_PATIENTS_LIST, patientFinal.size());
            log.debug("patientFinal.size() : " + patientFinal.size() + " , NB_PATIENTS_LIST: " + Constants.NB_PATIENTS_LIST);
        } else if (patientFinal == null || patientFinal.isEmpty()) {
            Assert.fail("Aucun patient n'a ete trouve au final");
        }
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        String methodName = "terminate";
        log.debug("Entree de la methode");
        serviceFacade = null;
        patients = null;
        log.debug("Sortie de la methode");
    }
}
