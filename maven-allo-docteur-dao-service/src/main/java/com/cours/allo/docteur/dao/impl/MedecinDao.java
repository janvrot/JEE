/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IMedecinDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Medecin;
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
public class MedecinDao implements IMedecinDao {

    private static final Log log = LogFactory.getLog(MedecinDao.class);
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();
    private String query = "";

    private Medecin findMedecinByRs(ResultSet rs) throws SQLException {
        int medecinId = rs.getInt(1);
        int userId = rs.getInt(2);
        String accreditation = rs.getString(3);
        String telephone = rs.getString(4);
        int version = rs.getInt(5);
        Medecin medecin = new Medecin(medecinId, accreditation, telephone, version, userId);
        return medecin;
    }
    
    private Medecin findAllInfosMedecin(ResultSet rs) throws SQLException {
        int medecinId = rs.getInt(1);
        String accreditation = rs.getString(2);
        String tel = rs.getString(3);
        String civilite = rs.getString(4);
        String prenom = rs.getString(5);
        String nom = rs.getString(6);
        String identifiant = rs.getString(7);
        String password = rs.getString(8);
        Date dateNaissance = rs.getDate(9);
        String rue = rs.getString(10);
        String codePostal = rs.getString(11);
        String ville = rs.getString(12);
        String pays = rs.getString(13);
        Adresse adresse = new Adresse(rue, codePostal, ville, pays);
        Utilisateur user = new Utilisateur(civilite, prenom, nom, identifiant, password, dateNaissance, adresse);
        Medecin medecin = new Medecin(medecinId, accreditation, tel, user);
        return medecin;
    }
    
    private Medecin findAllInfosForUpdate(ResultSet rs) throws SQLException {
        int medecinId = rs.getInt(1);
        String accreditation = rs.getString(2);
        String tel = rs.getString(3);
        String civilite = rs.getString(4);
        int userId = rs.getInt(5);
        String prenom = rs.getString(6);
        String nom = rs.getString(7);
        String identifiant = rs.getString(8);
        String password = rs.getString(9);
        Date dateNaissance = rs.getDate(10);
        int adresseId = rs.getInt(11);
        String rue = rs.getString(12);
        String codePostal = rs.getString(13);
        String ville = rs.getString(14);
        String pays = rs.getString(15);
        Adresse adresse = new Adresse(adresseId, rue, codePostal, ville, pays);
        Utilisateur user = new Utilisateur(userId, civilite, prenom, nom, identifiant, password, dateNaissance, adresse);
        Medecin medecin = new Medecin(medecinId, accreditation, tel, user);
        return medecin;
    }

    @Override
    public List<Medecin> findAllMedecins() {
        log.debug("Entree de la methode");
        query = "SELECT medecin.idMedecin, medecin.numeroAccreditation, medecin.numeroTelephone, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM medecin INNER JOIN utilisateur On medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON utilisateur.idUtilisateur = adresse.idUtilisateur";
        List<Medecin> medecins = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Medecin medecin = this.findAllInfosMedecin(rs);
                medecins.add(medecin);
            }
            return medecins;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Medecin findMedecinById(int idMedecin) {
        log.debug("Entree de la methode");
        String methodName = "findMedecinById";

        query = "SELECT medecin.idMedecin, medecin.numeroAccreditation, medecin.numeroTelephone, utilisateur.civilite, utilisateur.idUtilisateur, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.idAdresse, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM `medecin` INNER JOIN `utilisateur` ON medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON adresse.idUtilisateur = medecin.idUtilisateur WHERE medecin.idMedecin = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idMedecin);
            rs = ps.executeQuery();
            if (rs.next()) {
                Medecin medecin = this.findAllInfosForUpdate(rs);
                return medecin;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Medecin createMedecin(Medecin medecin) {
        log.debug("Entree de la methode");
        query = "INSERT INTO `Medecin` (`idUtilisateur`, `numeroAccreditation`, `numeroTelephone`) VALUES (";
        if (medecin.getIdMedecin() == null || findMedecinById(medecin.getIdMedecin()) == null) {
            query += "?, ?, ?);";
        }
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, medecin.getIdUtilisateur());
            ps.setString(2, medecin.getNumeroAccreditation());
            ps.setString(3, medecin.getNumeroTelephone());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                medecin.setIdMedecin(rs.getInt(1));
            }
            return medecin;
        } catch (Exception e) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        log.debug("Entree de la methode");
        Medecin debug = findMedecinById(medecin.getIdMedecin());
        if (debug != null) {
            query = "UPDATE `medecin` SET";
            query += " `numeroAccreditation` =?";
            query += ", `numeroTelephone` =?";
            query += ", `version` = `version` + 1";
            query += " WHERE `idMedecin` = ?";
            Connection co = null;
            PreparedStatement ps = null;
            try {
                co = mysqlDs.getConnection();
                ps = co.prepareStatement(query);
                ps.setString(1, medecin.getNumeroAccreditation());
                ps.setString(2, medecin.getNumeroTelephone());
                ps.setInt(3, medecin.getIdMedecin());
                ps.executeUpdate();
                return medecin;
            } catch (Exception e) {
                Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                ConnectionHelper.closeSqlResources(co, ps, null);
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteMedecin(Medecin medecin) {
        log.debug("Entree de la methode");
        Medecin debug = findMedecinById(medecin.getIdMedecin());
        if (debug != null) {
            query = "DELETE FROM `medecin` WHERE `idMedecin` = ?";
        }
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, medecin.getIdMedecin());
            ps.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return false;
    }

    @Override
    public List<Medecin> findMedecinByNom(String nom) {
        log.debug("Entree de la methode");
        query = "SELECT medecin.idMedecin, medecin.numeroAccreditation, medecin.numeroTelephone, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM medecin INNER JOIN utilisateur On medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON utilisateur.idUtilisateur = adresse.idUtilisateur WHERE utilisateur.nom=?";
        List<Medecin> medecins = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medecin medecin = this.findAllInfosMedecin(rs);
                medecins.add(medecin);
            }
            return medecins;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Medecin> findMedecinByPrenom(String prenom) {
        log.debug("Entree de la methode");
        query = "SELECT medecin.idMedecin, medecin.numeroAccreditation, medecin.numeroTelephone, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM medecin INNER JOIN utilisateur On medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON utilisateur.idUtilisateur = adresse.idUtilisateur WHERE utilisateur.prenom=?";
        List<Medecin> medecins = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, prenom);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medecin medecin = this.findAllInfosMedecin(rs);
                medecins.add(medecin);
            }
            return medecins;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Medecin> findMedecinByVille(String ville) {
        log.debug("Entree de la methode");
        query = "SELECT medecin.idMedecin, medecin.numeroAccreditation, medecin.numeroTelephone, utilisateur.civilite, utilisateur.prenom, utilisateur.nom, utilisateur.identifiant, utilisateur.motPasse, utilisateur.dateNaissance, adresse.rue, adresse.codePostal, adresse.ville, adresse.pays FROM medecin INNER JOIN utilisateur On medecin.idUtilisateur = utilisateur.idUtilisateur INNER JOIN adresse ON utilisateur.idUtilisateur = adresse.idUtilisateur WHERE adresse.ville=?";
        List<Medecin> medecins = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, ville);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medecin medecin = this.findAllInfosMedecin(rs);
                medecins.add(medecin);
            }
            return medecins;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }
}
