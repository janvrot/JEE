/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Patient;
import java.util.List;

/**
 *
 * @author Janvrot
 */
public interface IPatientDao {
    
    public List<Patient> findAllPatients();

    public Patient findPatientById(int idPatient);

    public Patient createPatient(Patient patient);

    public Patient updatePatient(Patient patient);

    public boolean deletePatient(Patient patient);
    
    public List<Patient> findPatientByNom(String nom);
    
    public List<Patient> findPatientByPrenom(String prenom);
    
    public List<Patient> findPatientByVille(String ville);
}
