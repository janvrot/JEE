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

public class Patient {
    private static final long serialVersionUID = 1L;
    private Integer idPatient;
    private String numeroSecuriteSociale;
    private String numeroTelephone;
    private Integer version;
    private Utilisateur utilisateur;
    private Integer idUtilisateur;

    public Patient() {
    }

    public Patient(Integer idPatient) {
        this.idPatient = idPatient;
    }
    
    public Patient(int idPatient, String numeroSecuriteSociale, String numeroTelephone) {
        this.idPatient = idPatient;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.numeroTelephone = numeroTelephone;
    }
    
    public Patient(String numeroSecuriteSociale, String numeroTelephone, Integer idUtilisateur) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.numeroTelephone = numeroTelephone;
        this.idUtilisateur = idUtilisateur;
    }
    
    public Patient(String numeroSecuriteSociale, String numeroTelephone, Utilisateur utilisateur) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.numeroTelephone = numeroTelephone;
        this.utilisateur = utilisateur;
    }
    
    public Patient(int idPatient, String numeroSecuriteSociale, String numeroTelephone, Utilisateur utilisateur) {
        this.idPatient = idPatient;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.numeroTelephone = numeroTelephone;
        this.utilisateur = utilisateur;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatient != null ? idPatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.idPatient == null && other.idPatient != null) || (this.idPatient != null && !this.idPatient.equals(other.idPatient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[idPatient=%s , utilisateur=%s , secu=%s , telephone=%s , version=%s \n", idPatient, utilisateur, numeroSecuriteSociale, numeroTelephone, version);
    }
    
}
