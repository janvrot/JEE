/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Adresse;
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
public class UpdateMedecinServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        int idMedecin = (Integer) session.getAttribute("medecinId");
        Medecin medecin = serviceFacade.getMedecinDao().findMedecinById(idMedecin);
        request.setAttribute("medecin", medecin);
        this.getServletContext().getRequestDispatcher("/pages/updateMedecin.jsp").forward(request, response);
    }

    private boolean updateUser(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        int idMedecin = (Integer) session.getAttribute("medecinId");
        Medecin medecinUp = serviceFacade.getMedecinDao().findMedecinById(idMedecin);
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
        int medecinId = medecinUp.getIdMedecin();
        int userId = medecinUp.getUtilisateur().getIdUtilisateur();
        int adresseId = medecinUp.getUtilisateur().getAdresse().getIdAdresse();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(naissance);

        List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
        boolean result = false;
        for (Medecin medecin : medecins) {
            if (medecin.getUtilisateur().getIdentifiant().equals(identifiant)) {
                result = false;       
            } else {
                Utilisateur utilisateur = new Utilisateur(userId, civilite, prenom, nom, identifiant, password, dateNaissance);
                serviceFacade.getUtilisateurDao().updateUtilisateur(utilisateur);
                Adresse adresse = new Adresse(adresseId, rue, cp, ville, pays);
                serviceFacade.getAdresseDao().updateAdresse(adresse);
                Medecin newMedecin = new Medecin(medecinId, accreditation, tel);
                serviceFacade.getMedecinDao().updateMedecin(newMedecin);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (!this.updateUser(request)) {
            log.debug("User already exist");
            response.sendRedirect(this.getServletContext().getContextPath() + "/ManageMedecinServlet");
        } else {
            log.debug("good");
            response.sendRedirect(this.getServletContext().getContextPath() + "/ManageMedecinServlet");
        }
            this.updateUser(request);
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
