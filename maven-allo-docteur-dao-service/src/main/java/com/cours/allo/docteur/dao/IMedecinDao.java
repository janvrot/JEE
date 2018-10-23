/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Medecin;
import java.util.List;

/**
 *
 * @author Janvrot
 */
public interface IMedecinDao {
    
    public List<Medecin> findAllMedecins();

    public Medecin findMedecinById(int idMedecin);

    public Medecin createMedecin(Medecin medecin);

    public Medecin updateMedecin(Medecin medecin);

    public boolean deleteMedecin(Medecin medecin);
    
    public List<Medecin> findMedecinByNom(String nom);
    
    public List<Medecin> findMedecinByPrenom(String prenom);
    
    public List<Medecin> findMedecinByVille(String ville);
}
