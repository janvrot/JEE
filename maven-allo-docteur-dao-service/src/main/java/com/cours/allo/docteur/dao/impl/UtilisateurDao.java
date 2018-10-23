/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IUtilisateurDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();
    private String query = "";

    private Adresse findAdresseByRs(ResultSet rs) throws SQLException {
        int adresseId = rs.getInt(13);
        int adresseUserId = rs.getInt(14);
        String rue = rs.getString(15);
        String cp = rs.getString(16);
        String ville = rs.getString(17);
        String pays = rs.getString(18);
        boolean principal = checkNumber(rs.getInt(19));
        int versionAdresse = rs.getInt(20);
        Adresse adresse = new Adresse(adresseId, rue, cp, ville, pays, principal, versionAdresse, adresseUserId);
        return adresse;
    }

    private Utilisateur findUtilisateurByRs(ResultSet rs) throws SQLException {
        int row = rs.getRow();
        int utilisateurId = rs.getInt(1);
        String civilite = rs.getString(2);
        String nom = rs.getString(3);
        String prenom = rs.getString(4);
        String identifiant = rs.getString(5);
        String password = rs.getString(6);
        Date dateNaissance = rs.getDate(7);
        Date dateCreation = rs.getDate(8);
        Date dateModification = rs.getDate(9);
        boolean actif = checkNumber(rs.getInt(10));
        boolean marquerEffacer = checkNumber(rs.getInt(11));
        int version = rs.getInt(12);
        Utilisateur utilisateur = new Utilisateur(utilisateurId, civilite, nom, prenom, identifiant, password, dateNaissance, dateCreation, dateModification, actif, marquerEffacer, version);
        return utilisateur;
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        log.debug("Entree de la methode");
        query = "SELECT * FROM `Utilisateur` INNER JOIN `Adresse` ON utilisateur.idUtilisateur = adresse.idUtilisateur";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            rs = ps.executeQuery(query);
            Map<Integer, Utilisateur> map = new HashMap();
            List<Adresse> adresses = new ArrayList();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
                Utilisateur utilisateur = this.findUtilisateurByRs(rs);
                map.put(utilisateur.getIdUtilisateur(), utilisateur);
            }
            for (Map.Entry<Integer, Utilisateur> entry : map.entrySet()) {
                Adresse adresseByUser = new Adresse();
                for (Adresse adresse : adresses) {
                    if (Objects.equals(adresse.getIdUtilisateur(), entry.getKey())) {
                        adresseByUser = adresse;
                    }
                }
                entry.getValue().setAdresse(adresseByUser);
            }
            List<Utilisateur> utilisateurs = new ArrayList<>(map.values());
            return utilisateurs;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    private boolean checkNumber(int statut) {
        return statut == 1;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur
    ) {
        log.debug("Entree de la methode");
        String methodName = "findUtilisateurById";

        query = "SELECT * FROM Utilisateur LEFT JOIN adresse ON adresse.idUtilisateur = utilisateur.idUtilisateur WHERE utilisateur.idUtilisateur = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idUtilisateur);
            rs = ps.executeQuery();
            Map<Integer, Utilisateur> map = new HashMap();
            List<Adresse> adresses = new ArrayList();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
                Utilisateur utilisateur = this.findUtilisateurByRs(rs);
                map.put(utilisateur.getIdUtilisateur(), utilisateur);
            }
            for (Map.Entry<Integer, Utilisateur> entry : map.entrySet()) {
                Adresse adresseByUser = new Adresse();
                for (Adresse adresse : adresses) {
                    if (Objects.equals(adresse.getIdUtilisateur(), entry.getKey())) {
                        adresseByUser = adresse;
                    }
                }
                entry.getValue().setAdresse(adresseByUser);
                Utilisateur utilisateur = entry.getValue();
                return utilisateur;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom
    ) {
        log.debug("Entree de la methode");
        query = "SELECT * FROM Utilisateur LEFT JOIN adresse ON adresse.idUtilisateur = utilisateur.idUtilisateur WHERE utilisateur.prenom = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, prenom);
            rs = ps.executeQuery();
            Map<Integer, Utilisateur> map = new HashMap();
            List<Adresse> adresses = new ArrayList();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
                Utilisateur utilisateur = this.findUtilisateurByRs(rs);
                map.put(utilisateur.getIdUtilisateur(), utilisateur);
            }
            for (Map.Entry<Integer, Utilisateur> entry : map.entrySet()) {
                Adresse adresseByUser = new Adresse();
                for (Adresse adresse : adresses) {
                    if (Objects.equals(adresse.getIdUtilisateur(), entry.getKey())) {
                        adresseByUser = adresse;
                    }
                }
                entry.getValue().setAdresse(adresseByUser);
            }
            List<Utilisateur> utilisateurs = new ArrayList<>(map.values());
            return utilisateurs;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom
    ) {
        log.debug("Entree de la methode");
        query = "SELECT * FROM Utilisateur LEFT JOIN adresse ON adresse.idUtilisateur = utilisateur.idUtilisateur WHERE utilisateur.nom = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            Map<Integer, Utilisateur> map = new HashMap();
            List<Adresse> adresses = new ArrayList();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
                Utilisateur utilisateur = this.findUtilisateurByRs(rs);
                map.put(utilisateur.getIdUtilisateur(), utilisateur);
            }
            for (Map.Entry<Integer, Utilisateur> entry : map.entrySet()) {
                Adresse adresseByUser = new Adresse();
                for (Adresse adresse : adresses) {
                    if (Objects.equals(adresse.getIdUtilisateur(), entry.getKey())) {
                        adresseByUser = adresse;
                    }
                }
                entry.getValue().setAdresse(adresseByUser);
            }
            List<Utilisateur> utilisateurs = new ArrayList<>(map.values());
            return utilisateurs;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByCodePostal(String codePostal
    ) {
        query = "SELECT * FROM `Utilisateur` INNER JOIN `Adresse` ON utilisateur.idUtilisateur = adresse.idUtilisateur WHERE adresse.codePostal = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, codePostal);
            rs = ps.executeQuery();
            Map<Integer, Utilisateur> map = new HashMap();
            List<Adresse> adresses = new ArrayList();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
                Utilisateur utilisateur = this.findUtilisateurByRs(rs);
                map.put(utilisateur.getIdUtilisateur(), utilisateur);
            }
            for (Map.Entry<Integer, Utilisateur> entry : map.entrySet()) {
                Adresse adresseByUser = new Adresse();
                for (Adresse adresse : adresses) {
                    if (Objects.equals(adresse.getIdUtilisateur(), entry.getKey())) {
                        adresseByUser = adresse;
                    }
                }
                entry.getValue().setAdresse(adresseByUser);
            }
            List<Utilisateur> utilisateurs = new ArrayList<>(map.values());
            return utilisateurs;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user
    ) {
        log.debug("Entree de la methode");
        Timestamp dateStart = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateCreation = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
        query = "INSERT INTO `Utilisateur` (`civilite`, `prenom`, `nom`, `identifiant`, `motPasse`, `dateNaissance`, `dateCreation`) VALUES (";
        Connection co = null;
        PreparedStatement ps = null;
        if (user.getIdUtilisateur() == null || findUtilisateurById(user.getIdUtilisateur()) == null) {
            query += "?, ?, ?, ?, ?, ?, ?);";
        }
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getCivilite());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getNom());
            ps.setString(4, user.getIdentifiant());
            ps.setString(5, user.getMotPasse());
            ps.setTimestamp(6, dateStart);
            ps.setTimestamp(7, dateCreation);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setIdUtilisateur(rs.getInt(1));
            }
            return user;
        } catch (Exception e) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user
    ) {
        log.debug("Entree de la methode");
        Utilisateur debug = findUtilisateurById(user.getIdUtilisateur());
        Timestamp dateNaissance = new java.sql.Timestamp(user.getDateNaissance().getTime());
        Timestamp dateModification = new java.sql.Timestamp(new Date(System.currentTimeMillis()).getTime());
        Connection co = null;
        PreparedStatement ps = null;
        if (debug != null) {
            query = "UPDATE `Utilisateur` SET ";
            query += "`civilite` = ?";
            query += ", `prenom` = ?";
            query += ", `nom` = ?";
            query += ", `identifiant` = ?";
            query += ", `motPasse` = ?";
            query += ", `dateNaissance` = ?";
            query += ", `dateModification` = ?";
            query += ", `version` = `version` + 1 WHERE `idUtilisateur` = ?";
            try {
                co = mysqlDs.getConnection();
                ps = co.prepareStatement(query);
                ps.setString(1, user.getCivilite());
                ps.setString(2, user.getPrenom());
                ps.setString(3, user.getNom());
                ps.setString(4, user.getIdentifiant());
                ps.setString(5, user.getMotPasse());
                ps.setTimestamp(6, dateNaissance);
                ps.setTimestamp(7, dateModification);
                ps.setInt(8, user.getIdUtilisateur());
                ps.executeUpdate();
                return user;
            } catch (Exception e) {
                Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                ConnectionHelper.closeSqlResources(co, ps, null);
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user
    ) {
        log.debug("Entree de la methode");
        Utilisateur debug = findUtilisateurById(user.getIdUtilisateur());
        Connection co = null;
        PreparedStatement ps = null;
        if (debug != null) {
            query = "DELETE FROM `Utilisateur` WHERE `idUtilisateur` = ?";
        }
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, user.getIdUtilisateur());
            ps.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return false;
    }
}
