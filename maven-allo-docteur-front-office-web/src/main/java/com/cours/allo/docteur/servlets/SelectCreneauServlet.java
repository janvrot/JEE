/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.RendezVous;
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
public class SelectCreneauServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idPatient = (Integer) session.getAttribute("patientId");
        request.setAttribute("idPatient", idPatient);
        String jour = request.getParameter("date");
        String identifiant = request.getParameter("medecin");
        List<RendezVous> rendezVous;
        
        try {
            rendezVous = serviceFacade.getRendezVousDao().findRendezVousByMedecinAndDate(identifiant, jour);
            List<Patient> patients = serviceFacade.getPatientDao().findAllPatients();

            for (Patient patient : patients) {
                for (RendezVous rdv : rendezVous) {
                    
                    if (patient.getIdPatient().equals(rdv.getIdPatient())) {
                        rendezVous.remove(rdv);
                    }
                }
            }

            request.setAttribute("rendezVous", rendezVous);
            this.getServletContext().getRequestDispatcher("/pages/selectCreneau.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SelectCreneauServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
