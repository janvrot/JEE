/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Adresse;
import com.cours.allo.docteur.dao.entities.Creneau;
import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.Utilisateur;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Janvrot
 */
public class CreateMedecinServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;
    private Object session;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/pages/createMedecin.jsp").forward(request, response);
    }

    private boolean createUser(HttpServletRequest request) throws ParseException {
        String civilite = request.getParameter("civility");
        String prenom = request.getParameter("firstName");
        String nom = request.getParameter("name");
        String identifiant = request.getParameter("identifier");
        String password = request.getParameter("password");
        String naissance = request.getParameter("naissance");
        String accreditation = request.getParameter("numAcreditation");
        String tel = request.getParameter("tel");
        String rue = request.getParameter("street");
        String cp = request.getParameter("postalCode");
        String ville = request.getParameter("city");
        String pays = request.getParameter("Pays");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(naissance);

        List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
        boolean result = false;
        for (Medecin medecin : medecins) {
            if (medecin.getUtilisateur().getIdentifiant().equals(identifiant)) {
                result = false;
            } else {
                result = true;
            }
        }
        if (result) {
            Utilisateur utilisateur = new Utilisateur(civilite, prenom, nom, identifiant, password, dateNaissance);
            Utilisateur newUser = serviceFacade.getUtilisateurDao().createUtilisateur(utilisateur);
            Adresse adresse = new Adresse(rue, cp, ville, pays, newUser.getIdUtilisateur());
            serviceFacade.getAdresseDao().createAdresse(adresse);
            Medecin newMedecin = new Medecin(accreditation, tel, newUser.getIdUtilisateur());
            Medecin createdMedecin = serviceFacade.getMedecinDao().createMedecin(newMedecin);
            this.addCreneauxMatin(createdMedecin.getIdMedecin(), 12, 8);
            this.addCreneauxMatin(createdMedecin.getIdMedecin(), 19, 14);
        }
        return result;
    }

    private void addCreneauxMatin(int idMedecin, int stop, int hStart) {
        int hDebut = hStart;
        int mDebut = 0;
        int hFin = hStart;
        int mFin = 15;

        while (hFin <= stop) {
            Creneau creneau = new Creneau(hDebut, mDebut, hFin, mFin, idMedecin);
            serviceFacade.getCreneauDao().createCreneau(creneau);
            if (hFin == stop) {
                break;
            }
            mDebut += 15;
            mFin += 15;
            if (mDebut == 60) {
                hDebut += 1;
                mDebut = 0;
            }
            if (mFin == 60) {
                hFin += 1;
                mFin = 0;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!this.createUser(request)) {
                log.debug("User already exist");
                response.sendRedirect(this.getServletContext().getContextPath() + "/ManageMedecinServlet");
            } else {
                log.debug("good");
                response.sendRedirect(this.getServletContext().getContextPath() + "/ManageMedecinServlet");
            }
            this.createUser(request);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateMedecinServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
