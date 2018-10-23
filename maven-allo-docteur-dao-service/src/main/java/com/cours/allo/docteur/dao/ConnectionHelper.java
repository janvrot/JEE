/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.impl.UtilisateurDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ConnectionHelper {

    private static final Log log = LogFactory.getLog(ConnectionHelper.class);
    public final static String className = ConnectionHelper.class.getName();

    public static void closeSqlResources(Connection connection, PreparedStatement preparedStatement, ResultSet result) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
