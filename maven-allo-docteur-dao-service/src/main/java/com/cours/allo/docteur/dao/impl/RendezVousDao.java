/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IRendezVousDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Creneau;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.dao.exception.CustomException;
import com.cours.allo.docteur.utils.Constants;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class RendezVousDao implements IRendezVousDao {

    private static final Log log = LogFactory.getLog(PatientDao.class);
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();
    private String query = "";

    private RendezVous findRendezVousByRs(ResultSet rs) throws SQLException {
        int rdvId = rs.getInt(1);
        Date jour = rs.getDate(2);
        double prix = rs.getDouble(3);
        Boolean present = rs.getBoolean(4);
        int patientId = rs.getInt(5);
        int creneauId = rs.getInt(6);
        int version = rs.getInt(7);
        RendezVous rendezVous = new RendezVous(rdvId, jour, prix, present, version, creneauId, patientId);
        return rendezVous;
    }

    private RendezVous findAllInfosRendezVous(ResultSet rs) throws SQLException {
        Date jour = rs.getDate(1);
        int hDebut = rs.getInt(2);
        int mDebut = rs.getInt(3);
        int hFin = rs.getInt(4);
        int mFin = rs.getInt(5);
        String telephone = rs.getString(6);
        String accreditation = rs.getString(7);
        String civilite = rs.getString(8);
        String prenom = rs.getString(9);
        String nom = rs.getString(10);
        String identifiant = rs.getString(11);
        String rue = rs.getString(12);
        String cp = rs.getString(13);
        String ville = rs.getString(14);
        String pays = rs.getString(15);

        Adresse adresse = new Adresse(rue, cp, ville, pays);
        Utilisateur user = new Utilisateur(civilite, prenom, nom, identifiant, adresse);
        Medecin medecin = new Medecin(accreditation, telephone, user);
        Creneau creneau = new Creneau(hDebut, mDebut, hFin, mFin, medecin);
        RendezVous rendezVous = new RendezVous(jour, creneau);
        return rendezVous;
    }
    
    private RendezVous findAllInfosRendezVousMedecin(ResultSet rs) throws SQLException {
        Date jour = rs.getDate(1);
        int hDebut = rs.getInt(2);
        int mDebut = rs.getInt(3);
        int hFin = rs.getInt(4);
        int mFin = rs.getInt(5);
        String telephone = rs.getString(6);
        String secu = rs.getString(7);
        String civilite = rs.getString(8);
        String prenom = rs.getString(9);
        String nom = rs.getString(10);
        String identifiant = rs.getString(11);
        String rue = rs.getString(12);
        String cp = rs.getString(13);
        String ville = rs.getString(14);
        String pays = rs.getString(15);

        Adresse adresse = new Adresse(rue, cp, ville, pays);
        Utilisateur user = new Utilisateur(civilite, prenom, nom, identifiant, adresse);
        Patient patient = new Patient(secu, telephone, user);
        Creneau creneau = new Creneau(hDebut, mDebut, hFin, mFin);
        RendezVous rendezVous = new RendezVous(jour, creneau, patient);
        return rendezVous;
    }

    private RendezVous findTakeRdv(ResultSet rs) throws SQLException {
        Date jour = rs.getDate(1);
        int patientId = rs.getInt(2);
        int creneauId = rs.getInt(3);
        int hDebut = rs.getInt(4);
        int mDebut = rs.getInt(5);
        int hFin = rs.getInt(6);
        int mFin = rs.getInt(7);
        String prenom = rs.getString(8);
        String nom = rs.getString(9);

        Utilisateur user = new Utilisateur(prenom, nom);
        Medecin medecin = new Medecin(user);
        Creneau creneau = new Creneau(hDebut, mDebut, hFin, mFin, medecin);
        RendezVous rendezVous = new RendezVous(jour, creneau, patientId, creneauId);
        return rendezVous;
    }

    @Override
    public List<RendezVous> findAllRendezVous() {
        log.debug("Entree de la methode");
        query = "SELECT * from rendezvous";
        List<RendezVous> rendezVous = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                RendezVous rdv = this.findRendezVousByRs(rs);
                rendezVous.add(rdv);
            }
            return rendezVous;
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public RendezVous findRendezVousById(int idRendezVous) {
        log.debug("Entree de la methode");

        query = "SELECT * FROM rendezvous WHERE idRendezVous = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idRendezVous);
            rs = ps.executeQuery();
            if (rs.next()) {
                RendezVous rendezVous = this.findRendezVousByRs(rs);
                return rendezVous;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public RendezVous createRendezVous(RendezVous rendezVous) {
        log.debug("Entree de la methode");
        Timestamp jour = new java.sql.Timestamp(rendezVous.getJour().getTime());
        query = "INSERT INTO `rendezvous` (`jour`, `prixConsultation`, `idPatient`, `idCreneau`) VALUES (";
        Connection co = null;
        PreparedStatement ps = null;
        if (rendezVous.getIdRendezVous() == null || findRendezVousById(rendezVous.getIdRendezVous()) == null) {
            query += "?, ?, ?, ?);";
        }
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, jour);
            ps.setDouble(2, Constants.PRIX_CONSULTATION);
            ps.setInt(3, rendezVous.getIdPatient());
            ps.setInt(4, rendezVous.getIdCreneau());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                rendezVous.setIdRendezVous(rs.getInt(1));
            }
            return rendezVous;
        } catch (Exception e) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public RendezVous updateRendezVous(RendezVous rendezVous) {
        log.debug("Entree de la methode");
        RendezVous debug = findRendezVousById(rendezVous.getIdRendezVous());
        Timestamp jour = new java.sql.Timestamp(rendezVous.getJour().getTime());
        Connection co = null;
        PreparedStatement ps = null;
        if (debug != null) {
            query = "UPDATE `rendezvous` SET ";
            query += "`jour` = ?";
            query += ", `prixConsultation` = ?";
            query += ", `idPatient` = ?";
            query += ", `idCreneau` = ?";
            query += ", `present` = ?";
            query += ", `version` = `version` + 1 WHERE `idRendezVous` = ?";
            try {
                co = mysqlDs.getConnection();
                ps = co.prepareStatement(query);
                ps.setTimestamp(1, jour);
                ps.setDouble(2, Constants.PRIX_CONSULTATION);
                ps.setInt(3, rendezVous.getIdPatient());
                ps.setInt(4, rendezVous.getIdCreneau());
                if (rendezVous.getPresent()) {
                    ps.setInt(5, 1);
                } else {
                    ps.setInt(5, 0);
                }
                ps.setBoolean(5, rendezVous.getPresent());
                ps.setInt(6, rendezVous.getIdRendezVous());
                ps.executeUpdate();
                return rendezVous;
            } catch (Exception e) {
                Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                ConnectionHelper.closeSqlResources(co, ps, null);
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteRendezVous(RendezVous rendezVous) {
        log.debug("Entree de la methode");
        RendezVous debug = findRendezVousById(rendezVous.getIdRendezVous());
        Connection co = null;
        PreparedStatement ps = null;
        if (debug != null) {
            query = "DELETE FROM `rendezvous` WHERE `idRendezVous` = ?";
        }
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, rendezVous.getIdRendezVous());
            ps.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return false;
    }

    @Override
    public List<RendezVous> findAllRendezVousByPatient(int idPatient) {
        log.debug("Entree de la methode");

        query = "SELECT rendezvous.jour, creneau.heureDebut, creneau.minuteDebut, creneau.heureFin, creneau.minuteFin, medecin.numeroTelephone, medecin.numeroAccreditation, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM rendezvous INNER JOIN `creneau` ON rendezvous.idCreneau = creneau.idCreneau INNER JOIN `medecin` ON creneau.idMedecin = medecin.idMedecin INNER JOIN utilisateur ON medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse on utilisateur.idUtilisateur = adresse.idUtilisateur WHERE rendezvous.idPatient = ?";
        List<RendezVous> rendezVous = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idPatient);
            rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous rdv = this.findAllInfosRendezVous(rs);
                rendezVous.add(rdv);
            }
            return rendezVous;
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public RendezVous findNextRendezVous(int idPatient) {
        log.debug("Entree de la methode");
        query = "SELECT rendezvous.jour, creneau.heureDebut, creneau.minuteDebut, creneau.heureFin, creneau.minuteFin, medecin.numeroTelephone, medecin.numeroAccreditation, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM rendezvous INNER JOIN `creneau` ON rendezvous.idCreneau = creneau.idCreneau INNER JOIN `medecin` ON creneau.idMedecin = medecin.idMedecin INNER JOIN utilisateur ON medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse on utilisateur.idUtilisateur = adresse.idUtilisateur WHERE rendezvous.idPatient = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idPatient);
            rs = ps.executeQuery();
            if (rs.next()) {
                RendezVous rendezVous = this.findAllInfosRendezVous(rs);
                return rendezVous;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<RendezVous> findRendezVousByMedecinAndDate(String identifiant, String dateRdv) throws ParseException {
        log.debug("Entree de la methode");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date jour = formatter.parse(dateRdv);
        query = "select rendezvous.jour, rendezvous.idPatient, rendezvous.idCreneau,  creneau.heureDebut, creneau.minuteDebut, creneau.heureFin, creneau.minuteFin, utilisateur.prenom, utilisateur.nom FROM rendezvous INNER JOIN creneau ON rendezvous.idCreneau = creneau.idCreneau INNER JOIN medecin ON medecin.idMedecin = creneau.idMedecin INNER JOIN utilisateur ON utilisateur.idUtilisateur = medecin.idUtilisateur WHERE rendezvous.jour = ? AND utilisateur.identifiant = ?";
        List<RendezVous> rendezVous = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(jour.getTime()));
            ps.setString(2, identifiant);
            rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous rdv = this.findTakeRdv(rs);
                rendezVous.add(rdv);
            }
            return rendezVous;
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<RendezVous> fingRendezVousByMedecin(int idMedecin) {
        log.debug("Entree de la methode");
        query = "SELECT rendezvous.jour, creneau.heureDebut, creneau.minuteDebut, creneau.heureFin, creneau.minuteFin, patient.numeroTelephone, patient.numeroSecuriteSociale, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM rendezvous INNER JOIN `creneau` ON rendezvous.idCreneau = creneau.idCreneau INNER JOIN `patient` ON rendezvous.idPatient = patient.idPatient INNER JOIN utilisateur ON patient.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse on utilisateur.idUtilisateur = adresse.idUtilisateur WHERE creneau.idMedecin = ?";
        List<RendezVous> rendezVous = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idMedecin);
            rs = ps.executeQuery();
            while (rs.next()) {
                RendezVous rdv = this.findAllInfosRendezVousMedecin(rs);
                rendezVous.add(rdv);
            }
            return rendezVous;
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

}
