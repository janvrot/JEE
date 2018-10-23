/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao;

import com.cours.allo.docteur.dao.entities.Creneau;
import java.util.List;

/**
 *
 * @author Janvrot
 */
public interface ICreneauDao {
    
    public List<Creneau> findAllCreneaux();

    public Creneau findCreneauById(int idCreneau);

    public Creneau createCreneau(Creneau creneau);

    public Creneau updateCreneau(Creneau creneau);

    public boolean deleteCreneau(Creneau creneau);
}
