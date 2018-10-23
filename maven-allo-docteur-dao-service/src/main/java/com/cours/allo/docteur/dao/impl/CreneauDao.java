/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.impl;

import com.cours.allo.docteur.dao.ConnectionHelper;
import com.cours.allo.docteur.dao.ICreneauDao;
import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.entities.Creneau;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Janvrot
 */
public class CreneauDao implements ICreneauDao {

    private static final Log log = LogFactory.getLog(CreneauDao.class);
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();
    private String query = "";

    private Creneau findCreneauByRs(ResultSet rs) throws SQLException {
        int creneauId = rs.getInt(1);
        int medecinId = rs.getInt(2);
        int heureDebut = rs.getInt(3);
        int minuteDebut = rs.getInt(4);
        int heureFin = rs.getInt(5);
        int minuteFin = rs.getInt(6);
        int version = rs.getInt(7);
        Creneau creneau = new Creneau(creneauId, heureDebut, minuteDebut, heureFin, minuteFin, version, medecinId);
        return creneau;
    }

    @Override
    public List<Creneau> findAllCreneaux() {
        log.debug("Entree de la methode");
        query = "SELECT * from Creneau";
        List<Creneau> creneaux = new ArrayList();
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            rs = ps.executeQuery(query);
            while (rs.next()) {
                Creneau creneau = this.findCreneauByRs(rs);
                creneaux.add(creneau);
            }
            return creneaux;
        } catch (SQLException ex) {
            Logger.getLogger(CreneauDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Creneau findCreneauById(int idCreneau) {
        log.debug("Entree de la methode");
        String methodName = "findCreneauById";

        query = "SELECT * FROM Creneau WHERE idCreneau = ?";
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, idCreneau);
            rs = ps.executeQuery();
            if (rs.next()) {
                Creneau creneau = this.findCreneauByRs(rs);
                return creneau;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreneauDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Creneau createCreneau(Creneau creneau) {
        log.debug("Entree de la methode");
        query = "INSERT INTO `Creneau` (`idMedecin`, `heureDebut`, `minuteDebut`, `heureFin`, `minuteFin`) VALUES (";
        if (creneau.getIdCreneau() == null || findCreneauById(creneau.getIdCreneau()) == null) {
            query += "?, ?, ?, ?, ?);";
        }
        Connection co = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, creneau.getIdMedecin());
            ps.setInt(2, creneau.getHeureDebut());
            ps.setInt(3, creneau.getMinuteDebut());
            ps.setInt(4, creneau.getHeureFin());
            ps.setInt(5, creneau.getMinuteFin());
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                creneau.setIdCreneau(rs.getInt(1));
            }
            return creneau;
        } catch (Exception e) {
            Logger.getLogger(CreneauDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, rs);
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public Creneau updateCreneau(Creneau creneau) {
        log.debug("Entree de la methode");
        Creneau debug = findCreneauById(creneau.getIdCreneau());
        if (debug != null) {
            query = "UPDATE `creneau` SET `idMedecin`=?";
            query += ", `heureDebut` =?";
            query += ", `minuteDebut` =?";
            query += ", `heureFin` =?";
            query += ", `minuteFin` =?";
            query += ", `version` = `version` + 1";
            query += " WHERE `idCreneau` = ?";
            Connection co = null;
            PreparedStatement ps = null;
            try {
                co = mysqlDs.getConnection();
                ps = co.prepareStatement(query);
                ps.setInt(1, creneau.getIdMedecin());
                ps.setInt(2, creneau.getHeureDebut());
                ps.setInt(3, creneau.getMinuteDebut());
                ps.setInt(4, creneau.getHeureFin());
                ps.setInt(5, creneau.getMinuteFin());
                ps.setInt(6, creneau.getIdCreneau());
                ps.executeUpdate();
                return creneau;
            } catch (Exception e) {
                Logger.getLogger(CreneauDao.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                ConnectionHelper.closeSqlResources(co, ps, null);
            }
        }
        log.debug("Sortie de la methode");
        return null;
    }

    @Override
    public boolean deleteCreneau(Creneau creneau) {
        log.debug("Entree de la methode");
        Creneau debug = findCreneauById(creneau.getIdCreneau());
        if (debug != null) {
            query = "DELETE FROM `Creneau` WHERE `idCreneau` = ?";
        }
        Connection co = null;
        PreparedStatement ps = null;
        try {
            co = mysqlDs.getConnection();
            ps = co.prepareStatement(query);
            ps.setInt(1, creneau.getIdCreneau());
            ps.execute();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CreneauDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConnectionHelper.closeSqlResources(co, ps, null);
        }
        log.debug("Sortie de la methode");
        return false;
    }

}
