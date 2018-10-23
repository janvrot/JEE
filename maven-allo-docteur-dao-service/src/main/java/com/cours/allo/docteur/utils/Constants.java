/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.utils;

/**
 *
 * @author ElHadji
 */
public class Constants {

    // Url de connexion en base de donnée

    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/base_allo_docteur?useSSL=false";
    // Utilisateur de la base de données
    public static String DATABASE_USER = "application";
    // Mot de passe de la base de données
    public static String DATABASE_PASSWORD = "passw0rd";

    public static Double PRIX_CONSULTATION = 25.00;

    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Cle Url base de donnees
    public static String DATABASE_URL_KEY = "DATABASE_URL_KEY";
    // Cle utilisateur
    public static String DATABASE_USER_KEY = "DATABASE_USER_KEY";
    // Cle mot passe
    public static String DATABASE_PASSWORD_KEY = "DATABASE_PASSWORD_KEY";

    // Drivers Jdbc key
    public static String JDBC_DRIVER_KEY = "JDBC_DRIVER_KEY";

    public static String SQL_JUNIT_PATH_FILE = "base_test_junit_allo_docteur.sql";
    
    public static final int NB_UTILISATEURS_LIST = 26;
    public static final int NB_ADRESSES_LIST = 26;
    public static final int NB_UTILISATEURS_FIND_BY_PRENOM = 1;
    public static final int NB_UTILISATEURS_FIND_BY_NOM = 1;
    public static final int NB_UTILISATEURS_FIND_BY_CODE_POSTAL = 6;
    public static final String UTILISATEUR_FIND_BY_PRENOM = "Jerome";
    public static final String UTILISATEUR_FIND_BY_NOM = "Petit";
    public static final String UTILISATEUR_FIND_BY_CODE_POSTAL = "75000";

    public static final int NB_ADRESSES_FIND_BY_VILLE = 6;
    public static final int NB_ADRESSES_FIND_BY_CODE_POSTAL = 6;

    public static final String ADRESSES_FIND_BY_VILLE = "Paris";
    public static final String ADRESSES_FIND_BY_CODE_POSTAL = "75000";
    
    public static final int NB_CRENEAUX_LIST = 252;
    
    public static final int NB_MEDECINS_LIST = 7;
    public static final String MEDECINS_FIND_BY_PRENOM = "Jerome";
    public static final String MEDECINS_FIND_BY_NOM = "Cantin";
    public static final String MEDECINS_FIND_BY_VILLE = "Paris";
    public static final int NB_MEDECINS_FIND_BY_PRENOM = 1;
    public static final int NB_MEDECINS_FIND_BY_NOM = 1;
    public static final int NB_MEDECINS_FIND_BY_VILLE = 1;
    
    public static final int NB_PATIENTS_LIST = 19;
    public static final String PATIENTS_FIND_BY_PRENOM = "Dimitry";
    public static final String PATIENTS_FIND_BY_NOM = "Gozin";
    public static final String PATIENTS_FIND_BY_VILLE = "Paris";
    public static final int NB_PATIENTS_FIND_BY_PRENOM = 1;
    public static final int NB_PATIENTS_FIND_BY_NOM = 1;
    public static final int NB_PATIENTS_FIND_BY_VILLE = 5;
    
    public static final int NB_RDV_LIST = 1197;
    public static final int RDV_FIND_BY_ID_MEDECIN = 1;
    public static final int NB_RDV_FIND_BY_ID_MEDECIN = 171;
    public static final int RDV_FIND_BY_ID_PATIENT = 1;
    public static final int NB_RDV_FIND_BY_ID_PATIENT = 84;

}
