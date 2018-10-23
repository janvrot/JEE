/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.main;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Creneau;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.exception.CustomException;
import com.cours.allo.docteur.dao.impl.AdresseDao;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.cours.allo.docteur.utils.Constants;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);
    private static IServiceFacade serviceFacade = null;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date jour = dateFormat.parse("08/11/1994");
//        throw new CustomException("Ceci est un test", 1);
        
        //test creneau
        
//        Creneau creneauCRUD = new Creneau(10, 10, 11, 0, 1);
//        creneauCRUD = serviceFacade.getCreneauDao().createCreneau(creneauCRUD);   
//        creneauCRUD = serviceFacade.getCreneauDao().findCreneauById(creneauCRUD.getIdCreneau());
//        creneauCRUD.setHeureDebut(12);
//        serviceFacade.getCreneauDao().updateCreneau(creneauCRUD);
//        serviceFacade.getCreneauDao().deleteCreneau(creneauCRUD);
        
        
        //test medecin
        
//        Medecin medecinCRUD = new Medecin("AAABBBCCC01", "0687521578", 2);
//        medecinCRUD = serviceFacade.getMedecinDao().createMedecin(medecinCRUD);
//        medecinCRUD = serviceFacade.getMedecinDao().findMedecinById(medecinCRUD.getIdMedecin());
//        medecinCRUD.setNumeroTelephone("0000000000");
//        serviceFacade.getMedecinDao().updateMedecin(medecinCRUD);
//        serviceFacade.getMedecinDao().deleteMedecin(medecinCRUD);
//        System.out.println(serviceFacade.getMedecinDao().findMedecinByPrenom(Constants.MEDECINS_FIND_BY_PRENOM));
        
        // test patient
//          serviceFacade.getPatientDao().findAllPatients();
//        Patient patientCRUD = new Patient("172295016841975", "0672389145", 3);
//        patientCRUD = serviceFacade.getPatientDao().createPatient(patientCRUD);
//        patientCRUD = serviceFacade.getPatientDao().findPatientById(patientCRUD.getIdPatient());
//        patientCRUD.setNumeroTelephone("0000000000");
//        patientCRUD = serviceFacade.getPatientDao().updatePatient(patientCRUD);
//        serviceFacade.getPatientDao().deletePatient(patientCRUD);
        
        //test rdv
        
//        RendezVous rendezVousCRUD = new RendezVous(jour, Constants.PRIX_CONSULTATION, 2, 3);
//        serviceFacade.getRendezVousDao().createRendezVous(rendezVousCRUD);
//        rendezVousCRUD = serviceFacade.getRendezVousDao().findRendezVousById(rendezVousCRUD.getIdRendezVous());
//        rendezVousCRUD.setPresent(true);
//        rendezVousCRUD = serviceFacade.getRendezVousDao().updateRendezVous(rendezVousCRUD);
//        serviceFacade.getRendezVousDao().deleteRendezVous(rendezVousCRUD);
        
        
        // TODO code application logic here
//        MySqlSingleton singleton = MySqlSingleton.getInstance();
//        AdresseDao adresseDao = new AdresseDao();
//        List<Adresse> allAdresses = adresseDao.findAllAdresses();
//        System.out.println(adresseDao.findAdresseById(2));
//        List<Adresse> allAdresses = adresseDao.findAdressesByVille("Paris");
//        for (Adresse adresse : allAdresses  ) {
//            System.out.println(adresse);
//        }
//        Adresse trente = adresseDao.findAdresseById(84);
//         Utilisateur userCRUD = new Utilisateur("Mr", "Jean", "Ass", "jean.ass@gmail.com", "passw0rd", new Date(System.currentTimeMillis()));
//         System.out.println(serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD));
//         System.out.println(serviceFacade.getUtilisateurDao().findAllUtilisateurs());
//         System.out.println(serviceFacade.getCreneauDao().findCreneauById(1));
//         serviceFacade.getUtilisateurDao().deleteUtilisateur(userCRUD);
//        System.out.println(adresseDao.updateAdresse(trente));
//        Adresse adresseCRUD = new Adresse("97 boulevard Voltaire","75011","Paris","France", userCRUD.getIdUtilisateur());
//        System.out.println(serviceFacade.getAdresseDao().createAdresse(adresseCRUD));
//        adresseCRUD.setCodePostal("75 000 Bis");
//        adresseCRUD.setVille("Paris Bis");
//        adresseCRUD = serviceFacade.getAdresseDao().updateAdresse(adresseCRUD);
//        adresseCRUD = serviceFacade.getAdresseDao().findAdresseById(adresseCRUD.getIdAdresse());
//        System.out.println(serviceFacade.getUtilisateurDao().findAllUtilisateurs());
//        System.out.println(serviceFacade.getUtilisateurDao().findUtilisateursByPrenom("Jean"));
    }
}
