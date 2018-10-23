/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

/**
 *
 * @author Janvrot
 */

public class Creneau {
    private static final long serialVersionUID = 1L;
    private Integer idCreneau;
    private Integer heureDebut;
    private Integer minuteDebut;
    private Integer heureFin;
    private Integer minuteFin;
    private Integer version;
    private Integer idMedecin;
    private Medecin medecin;

    public Creneau() {
    }
    
    public Creneau(int idCreneau, int heureDebut, int minuteDebut, int heureFin, int minuteFin, int version, int idMedecin) {
        this.idCreneau = idCreneau;
        this.heureDebut = heureDebut;
        this.minuteDebut = minuteDebut;
        this.heureFin = heureFin;
        this.minuteFin = minuteFin;
        this.version = version;
        this.idMedecin = idMedecin;
    }
    
    public Creneau(int heureDebut, int minuteDebut, int heureFin, int minuteFin, int idMedecin) {
        this.heureDebut = heureDebut;
        this.minuteDebut = minuteDebut;
        this.heureFin = heureFin;
        this.minuteFin = minuteFin;
        this.idMedecin = idMedecin;
    }
    
    public Creneau(int heureDebut, int minuteDebut, int heureFin, int minuteFin) {
        this.heureDebut = heureDebut;
        this.minuteDebut = minuteDebut;
        this.heureFin = heureFin;
        this.minuteFin = minuteFin;
    }
    
    public Creneau(int heureDebut, int minuteDebut, int heureFin, int minuteFin, Medecin medecin) {
        this.heureDebut = heureDebut;
        this.minuteDebut = minuteDebut;
        this.heureFin = heureFin;
        this.minuteFin = minuteFin;
        this.medecin = medecin;
    }

    public Creneau(Integer idCreneau) {
        this.idCreneau = idCreneau;
    }

    public Integer getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(Integer idCreneau) {
        this.idCreneau = idCreneau;
    }

    public Integer getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Integer heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Integer getMinuteDebut() {
        return minuteDebut;
    }

    public void setMinuteDebut(Integer minuteDebut) {
        this.minuteDebut = minuteDebut;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public Integer getMinuteFin() {
        return minuteFin;
    }

    public void setMinuteFin(Integer minuteFin) {
        this.minuteFin = minuteFin;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }
    
    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCreneau != null ? idCreneau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creneau)) {
            return false;
        }
        Creneau other = (Creneau) object;
        if ((this.idCreneau == null && other.idCreneau != null) || (this.idCreneau != null && !this.idCreneau.equals(other.idCreneau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
                return String.format("[idCreneau=%s , heureDebut=%s , minuteDebut=%s , heureFin=%s , minuteFin=%s , version=%s , idMedecin=%s\n", idCreneau, heureDebut, minuteDebut, heureFin, minuteFin, version, idMedecin);
    }
    
}
