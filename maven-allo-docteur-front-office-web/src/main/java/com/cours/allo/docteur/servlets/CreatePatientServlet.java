/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Adresse;
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
public class CreatePatientServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/pages/createPatient.jsp").forward(request, response);
    }

    private boolean addUser(HttpServletRequest request) throws ParseException {
        String civilite = request.getParameter("civility");
        String prenom = request.getParameter("firstName");
        String nom = request.getParameter("name");
        String identifiant = request.getParameter("identifier");
        String password = request.getParameter("password");
        String naissance = request.getParameter("naissance");
        String secu = request.getParameter("secu");
        String tel = request.getParameter("tel");
        String rue = request.getParameter("street");
        String cp = request.getParameter("postalCode");
        String ville = request.getParameter("city");
        String pays = request.getParameter("Pays");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(naissance);

        List<Patient> patients = serviceFacade.getPatientDao().findAllPatients();
        boolean result = false;
        for (Patient patient : patients) {
            if (patient.getUtilisateur().getIdentifiant().equals(identifiant)) {
                result = false;       
            } else {
                Utilisateur utilisateur = new Utilisateur(civilite, prenom, nom, identifiant, password, dateNaissance);
                Utilisateur newUser = serviceFacade.getUtilisateurDao().createUtilisateur(utilisateur);
                Adresse adresse = new Adresse(rue, cp, ville, pays, newUser.getIdUtilisateur());
                serviceFacade.getAdresseDao().createAdresse(adresse);
                Patient newPatient = new Patient(secu, tel, newUser.getIdUtilisateur());
                serviceFacade.getPatientDao().createPatient(newPatient);
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
            if (!this.addUser(request)) {
            System.out.println("User already exist");
            response.sendRedirect(this.getServletContext().getContextPath() + "/CreatePatientServlet");
        } else {
            System.out.println("good");
            response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
        }
            this.addUser(request);
        } catch (ParseException ex) {
            Logger.getLogger(CreatePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
