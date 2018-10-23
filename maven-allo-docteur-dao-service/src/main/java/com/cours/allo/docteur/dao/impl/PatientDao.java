/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IPatientDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Janvrot
 */
public class PatientDao implements IPatientDao {

    private static final Log log = LogFactory.getLog(PatientDao.class);
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();
    private String query = "";

    private Patient findPatientByRs(ResultSet rs) throws SQLException {
        Integer patientId = rs.getInt(1);
        String secu = rs.getString(2);
        String telephone = rs.getString(3);
        String civilite = rs.getString(4);
        String nom = rs.getString(5);
        String prenom = rs.getString(6);
        String identifiant = rs.getString(7);
        String password = rs.getString(8);
        Utilisateur user = new Utilisateur(civilite, prenom, nom, identifiant, password);
        Patient patient = new Patient(patientId, secu, telephone, user);
        return patient;
    }

    private Patient findPatientForUpdate(ResultSet rs) throws SQLException {
        Integer patientId = rs.getInt(1);
        String secu = rs.getString(2);
        String telephone = rs.getString(3);
        Integer userId = rs.getInt(4);
        String civilite = rs.getString(5);
        String prenom = rs.getString(6);
        String nom = rs.getString(7);
        String identifiant = rs.getString(8);
        String password = rs.getString(9);
        Date dateNaissance = rs.getDate(10);
        Integer adresseId = rs.getInt(11);
        String rue = rs.getString(12);
        String cp = rs.getString(13);
        String ville = rs.getString(14);
        String pays = rs.getString(15);
        Adresse adresse = new Adresse(adresseId, rue, cp, ville, pays);
        Utilisateur user = new Utilisateur(userId, civilite, prenom, nom, identifiant, password, dateNaissance, adresse);
        Patient patient = new Patient(patientId, secu, telephone, user);
        return patient;
    }

    @Override
    public List<Patient> findAllPatients() {
        log.debug("Entree de la methode");
        query = "SELECT patient.idPatient, patient.numeroSecuriteSociale, patient.numeroTelephone, utilisateur.idUtilisateur, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.idAdresse, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM `patient` INNER JOIN `utilisateur` ON patient.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON adresse.idUtilisateur = patient.idUtilisateur";
        List<Patient> patients = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Patient patient = this.findPatientForUpdate(rs);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Patient findPatientById(int idPatient) {
        log.debug("Entree de la methode");

        query = "SELECT patient.idPatient, patient.numeroSecuriteSociale, patient.numeroTelephone, utilisateur.idUtilisateur, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.idAdresse, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM `patient` INNER JOIN `utilisateur` ON patient.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON adresse.idUtilisateur = patient.idUtilisateur WHERE idPatient = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idPatient);
            rs = ps.executeQuery();
            if (rs.next()) {
                Patient patient = this.findPatientForUpdate(rs);
                return patient;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Patient createPatient(Patient patient) {
        log.debug("Entree de la methode");
        query = "INSERT INTO `Patient` (`idUtilisateur`, `numeroSecuriteSociale`, `numeroTelephone`) VALUES (";
        if (patient.getIdPatient() == null || findPatientById(patient.getIdPatient()) == null) {
            query += "?, ?, ?);";
        }
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, patient.getIdUtilisateur());
            ps.setString(2, patient.getNumeroSecuriteSociale());
            ps.setString(3, patient.getNumeroTelephone());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                patient.setIdPatient(rs.getInt(1));
            }
            return patient;
        } catch (Exception e) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        log.debug("Entree de la methode");
        Patient debug = findPatientById(patient.getIdPatient());
        if (debug != null) {
            query = "UPDATE `patient` SET";
            query += " `numeroSecuriteSociale` =?";
            query += ", `numeroTelephone` =?";
            query += ", `version` = `version` + 1";
            query += " WHERE `idPatient` = ?";
            Connection co = null;
            PreparedStatement ps = null;
            try {
                co = mysqlDs.getConnection();
                ps = co.prepareStatement(query);
                ps.setString(1, patient.getNumeroSecuriteSociale());
                ps.setString(2, patient.getNumeroTelephone());
                ps.setInt(3, patient.getIdPatient());
                ps.executeUpdate();
                return patient;
            } catch (Exception e) {
                Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                ConnectionHelper.closeSqlResources(co, ps, null);
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        log.debug("Entree de la methode");
        Patient debug = findPatientById(patient.getIdPatient());
        if (debug != null) {
            query = "DELETE FROM `patient` WHERE `idPatient` = ?";
        }
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, patient.getIdPatient());
            ps.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return false;
    }

    @Override
    public List<Patient> findPatientByNom(String nom) {
        log.debug("Entree de la methode");

        query = "SELECT patient.idPatient, patient.numeroSecuriteSociale, patient.numeroTelephone, utilisateur.idUtilisateur, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.idAdresse, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM `patient` INNER JOIN `utilisateur` ON patient.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON adresse.idUtilisateur = patient.idUtilisateur WHERE utilisateur.nom = ?";
        List<Patient> patients = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = this.findPatientForUpdate(rs);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Patient> findPatientByPrenom(String prenom) {
        log.debug("Entree de la methode");

        query = "SELECT patient.idPatient, patient.numeroSecuriteSociale, patient.numeroTelephone, utilisateur.idUtilisateur, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.idAdresse, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM `patient` INNER JOIN `utilisateur` ON patient.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON adresse.idUtilisateur = patient.idUtilisateur WHERE utilisateur.prenom = ?";
        List<Patient> patients = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, prenom);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = this.findPatientForUpdate(rs);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Patient> findPatientByVille(String ville) {
        log.debug("Entree de la methode");

        query = "SELECT patient.idPatient, patient.numeroSecuriteSociale, patient.numeroTelephone, utilisateur.idUtilisateur, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.idAdresse, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM `patient` INNER JOIN `utilisateur` ON patient.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON adresse.idUtilisateur = patient.idUtilisateur WHERE adresse.ville = ?";
        List<Patient> patients = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, ville);
            rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = this.findPatientForUpdate(rs);
                patients.add(patient);
            }
            return patients;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }
}
