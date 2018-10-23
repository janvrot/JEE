/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.IAdresseDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Adresse;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class AdresseDao implements IAdresseDao {

    private static final Log log = LogFactory.getLog(AdresseDao.class);
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();
    private String query = "";

    private Adresse findAdresseByRs(ResultSet rs) throws SQLException {
        int row = rs.getRow();
        int adresseId = rs.getInt(1);
        int utilisateurId = rs.getInt(2);
        String rue = rs.getString(3);
        String codePostal = rs.getString(4);
        String ville = rs.getString(5);
        String pays = rs.getString(6);
        Boolean principale = rs.getBoolean(7);
        int version = rs.getInt(8);
        Adresse adresse = new Adresse(adresseId, rue, codePostal, ville, pays, principale, version, utilisateurId);
        return adresse;
    }

    @Override
    public List<Adresse> findAllAdresses() {
        log.debug("Entree de la methode");
        query = "SELECT idAdresse, idUtilisateur, rue, codePostal, ville, pays, principale, version from Adresse";
        List<Adresse> adresses = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
            }
            return adresses;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Adresse findAdresseById(int idAdresse) {
        log.debug("Entree de la methode");
        query = "SELECT idAdresse, idUtilisateur, rue, codePostal, ville, pays, principale, version from Adresse where idAdresse= ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idAdresse);
            rs = ps.executeQuery();;
            if (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                return adresse;
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
    public List<Adresse> findAdressesByVille(String ville) {
        log.debug("Entree de la methode");
        query = "SELECT idAdresse, idUtilisateur, rue, codePostal, ville, pays, principale, version from Adresse where ville=?";
        List<Adresse> adresses = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, ville);
            rs = ps.executeQuery();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
            }
            return adresses;
        } catch (SQLException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public List<Adresse> findAdressesByCodePostal(String codePostal) {
        log.debug("Entree de la methode");
        query = "SELECT idAdresse, idUtilisateur, rue, codePostal, ville, pays, principale, version from Adresse where codePostal= ?";
        List<Adresse> adresses = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setString(1, codePostal);
            rs = ps.executeQuery();
            while (rs.next()) {
                Adresse adresse = this.findAdresseByRs(rs);
                adresses.add(adresse);
            }
            return adresses;
        } catch (Exception ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Adresse createAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        query = "INSERT INTO `Adresse` (`idUtilisateur`, `rue`, `codePostal`, `ville`, `pays`, `principale`) VALUES (";
        if (adresse.getIdAdresse() == null || findAdresseById(adresse.getIdAdresse()) == null) {
            query += "?, ?, ?, ?, ?, 1);";
        }
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, adresse.getIdUtilisateur());
            ps.setString(2, adresse.getRue());
            ps.setString(3, adresse.getCodePostal());
            ps.setString(4, adresse.getVille());
            ps.setString(5, adresse.getPays());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                adresse.setIdAdresse(rs.getInt(1));
            }
            return adresse;
        } catch (Exception e) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        Adresse debug = findAdresseById(adresse.getIdAdresse());
        if (debug != null) {
            query = "UPDATE `Adresse` SET";
            query += " `rue` =?";
            query += ", `codePostal` =?";
            query += ", `ville` =?";
            query += ", `pays` =?";
            if (adresse.isPrincipale()) {
                query += ",`principale` ='1'";
            } else {
                query += ",`principale` ='0'";
            }
            query += ", version = version + 1 WHERE `idAdresse` = ?";
            Connection co = null;
            PreparedStatement ps = null;
            try {
                co = mysqlDs.getConnection();
                ps = co.prepareStatement(query);
                ps.setString(1, adresse.getRue());
                ps.setString(2, adresse.getCodePostal());
                ps.setString(3, adresse.getVille());
                ps.setString(4, adresse.getPays());
                ps.setInt(5, adresse.getIdAdresse());
                ps.executeUpdate();
                return adresse;
            } catch (Exception e) {
                Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                ConnectionHelper.closeSqlResources(co, ps, null);
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteAdresse(Adresse adresse) {
        log.debug("Entree de la methode");
        Adresse debug = findAdresseById(adresse.getIdAdresse());
        if (debug != null) {
            query = "DELETE FROM `Adresse` WHERE `idAdresse` = ?";
        }
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, adresse.getIdAdresse());
            ps.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return false;
    }

}
