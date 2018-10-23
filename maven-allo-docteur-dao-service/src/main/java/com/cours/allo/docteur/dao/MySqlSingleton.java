/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.utils.Constants;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class MySqlSingleton {

    private static final Log log = LogFactory.getLog(MySqlSingleton.class);
    public final static String className = MySqlSingleton.class.getName();
    // Objet DataSource
    private DataSource dataSource = null;

    /**
     * Constructeur privé
     */
    private MySqlSingleton() {
        String methodName = "MySqlSingleton";
        System.out.println("I am private constructor of MySqlSingleton class.");
        this.dataSource = getDataSource();
    }

    public DataSource getDataSource() {
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(Constants.DATABASE_URL);
            mysqlDS.setUser(Constants.DATABASE_USER);
            mysqlDS.setPassword(Constants.DATABASE_PASSWORD);
        } catch (Exception e) {
                e.printStackTrace();
        }
        return mysqlDS;
    }

    /**
     * Holder
     */
    private static class SingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
        private final static MySqlSingleton instance = new MySqlSingleton();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     *
     * @return
     */
    public static MySqlSingleton getInstance() {
        String methodName = "MySqlSingleton";
        return SingletonHolder.instance;
    }
}
