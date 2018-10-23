/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.dao.entities;

import java.util.Date;

/**
 *
 * @author Janvrot
 */
public class RendezVous {

    private static final long serialVersionUID = 1L;
    private Integer idRendezVous;
    private Date jour;
    private Double prixConsultation;
    private Boolean present;
    private Integer version;
    private Integer idCreneau;
    private Integer idPatient;
    private Creneau creneau;
    private Patient patient;

    public RendezVous() {
    }

    public RendezVous(Integer idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public RendezVous(Integer idRendezVous, Date jour) {
        this.idRendezVous = idRendezVous;
        this.jour = jour;
    }
    
    public RendezVous(Date jour, Creneau creneau) {
        this.creneau = creneau;
        this.jour = jour;
    }
    
    public RendezVous(Date jour, Creneau creneau, Patient patient) {
        this.creneau = creneau;
        this.jour = jour;
        this.patient = patient;
    }
    
    public RendezVous(Date jour, Creneau creneau, int idPatient, int idCreneau) {
        this.creneau = creneau;
        this.jour = jour;
        this.idPatient = idPatient;
        this.idCreneau = idCreneau;
    }

    public RendezVous(int idRendezVous, Date jour, Double prixConsultation, Boolean present, int version, int idCreneau, int idPatient) {
        this.idRendezVous = idRendezVous;
        this.jour = jour;
        this.prixConsultation = prixConsultation;
        this.present = present;
        this.version = version;
        this.idCreneau = idCreneau;
        this.idPatient = idPatient;
    }

    public RendezVous(Date jour, Double prixConsultation, int idCreneau, int idPatient) {
        this.jour = jour;
        this.prixConsultation = prixConsultation;
        this.idCreneau = idCreneau;
        this.idPatient = idPatient;
    }
    
    public RendezVous(Date jour) {
        this.jour = jour;
    }

    public Integer getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(Integer idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public Double getPrixConsultation() {
        return prixConsultation;
    }

    public void setPrixConsultation(Double prixConsultation) {
        this.prixConsultation = prixConsultation;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIdCreneau() {
        return idCreneau;
    }

    public void setIdCreneau(Integer idCreneau) {
        this.idCreneau = idCreneau;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }
    
    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRendezVous != null ? idRendezVous.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RendezVous)) {
            return false;
        }
        RendezVous other = (RendezVous) object;
        if ((this.idRendezVous == null && other.idRendezVous != null) || (this.idRendezVous != null && !this.idRendezVous.equals(other.idRendezVous))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[idRendezVous=%s , jour=%s , prixConsultation=%s , present=%s , version=%s , idCreneau=%s , idPatient=%s \n", idRendezVous, jour, prixConsultation, present, version , idCreneau, idPatient);
    }

}
