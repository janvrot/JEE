/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.test;

import com.cours.allo.docteur.dao.MySqlSingleton;
import com.cours.allo.docteur.dao.impl.AdresseDao;
import com.cours.allo.docteur.factory.AbstractDaoFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.cours.allo.docteur.service.ServiceFacade;
import com.ibatis.common.jdbc.ScriptRunner;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Janvrot
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.dao.test.JUnitAdresseDao.class,
    com.dao.test.JUnitUtilisateurDao.class,
    com.dao.test.JUnitCreneauDao.class,
    com.dao.test.JUnitMedecinDao.class,
    com.dao.test.JUnitPatientDao.class,
    com.dao.test.JUnitRendezVousDao.class
})
public class JUnitDao {
    
    private static final Log log = LogFactory.getLog(JUnitDao.class);
    private static IServiceFacade serviceFacade = null;
    
    private static MySqlSingleton mysql = MySqlSingleton.getInstance();
    private static MysqlDataSource mysqlDs = (MysqlDataSource) mysql.getDataSource();

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY);
        initDataBase();
    }

    public static void initDataBase() {
        // Initialiser les données de la base de données
        log.debug("Entree de la methode");
        try {
            File file = new File("base_test_junit_allo_docteur.sql");
            Reader reader = new FileReader(file);
            try {
                Connection co = mysqlDs.getConnection();
                ScriptRunner runner = new ScriptRunner(co, false, false);
                runner.runScript(reader);
                reader.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdresseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.debug("Sortie de la methode");
    }
    
}
