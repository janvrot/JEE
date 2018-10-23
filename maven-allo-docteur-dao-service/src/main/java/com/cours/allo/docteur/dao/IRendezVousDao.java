/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.RendezVous;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Janvrot
 */
public interface IRendezVousDao {
    
    public List<RendezVous> findAllRendezVous();

    public RendezVous findRendezVousById(int idRendezVous);

    public RendezVous createRendezVous(RendezVous rendezVous);

    public RendezVous updateRendezVous(RendezVous rendezVous);

    public boolean deleteRendezVous(RendezVous rendezVous);
    
    public List<RendezVous> findAllRendezVousByPatient(int idPatient);
    
    public List<RendezVous> fingRendezVousByMedecin(int idMedecin);
    
    public RendezVous findNextRendezVous(int idPatient);
    
    public List<RendezVous> findRendezVousByMedecinAndDate(String identifiant, String dateRdv) throws ParseException;
}
