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
public class Medecin {
    private static final long serialVersionUID = 1L;
    private Integer idMedecin;
    private String numeroAccreditation;
    private String numeroTelephone;
    private Integer version;
    private Integer idUtilisateur;
    private Utilisateur utilisateur;

    public Medecin() {
    }
    
    public Medecin(int idMedecin, String numeroAccreditation, String numeroTelephone, int version, int idUtilisateur) {
        this.idMedecin = idMedecin;
        this.numeroAccreditation = numeroAccreditation;
        this.numeroTelephone = numeroTelephone;
        this.version = version;
        this.idUtilisateur = idUtilisateur;
    }
    
    public Medecin(String numeroAccreditation, String numeroTelephone, int idUtilisateur) {
        this.numeroAccreditation = numeroAccreditation;
        this.numeroTelephone = numeroTelephone;
        this.idUtilisateur = idUtilisateur;
    }
    
    public Medecin(String numeroAccreditation, String numeroTelephone, Utilisateur utilisateur) {
        this.numeroAccreditation = numeroAccreditation;
        this.numeroTelephone = numeroTelephone;
        this.utilisateur = utilisateur;
    }
    
    public Medecin(int idMedecin, String numeroAccreditation, String numeroTelephone, Utilisateur utilisateur) {
        this.idMedecin = idMedecin;
        this.numeroAccreditation = numeroAccreditation;
        this.numeroTelephone = numeroTelephone;
        this.utilisateur = utilisateur;
    }
    
    public Medecin(int idMedecin, String numeroAccreditation, String numeroTelephone) {
        this.idMedecin = idMedecin;
        this.numeroAccreditation = numeroAccreditation;
        this.numeroTelephone = numeroTelephone;
    }

    public Medecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }
    
    public Medecin(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNumeroAccreditation() {
        return numeroAccreditation;
    }

    public void setNumeroAccreditation(String numeroAccreditation) {
        this.numeroAccreditation = numeroAccreditation;
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

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedecin != null ? idMedecin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.idMedecin == null && other.idMedecin != null) || (this.idMedecin != null && !this.idMedecin.equals(other.idMedecin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[idMedecin=%s , idUtilisateur=%s , accreditation=%s , telephone=%s , version=%s \n", idMedecin, idUtilisateur, numeroAccreditation, numeroTelephone, version);
    }
    
}
