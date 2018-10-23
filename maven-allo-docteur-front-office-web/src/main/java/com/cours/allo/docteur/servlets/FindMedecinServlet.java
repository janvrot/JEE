/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class FindMedecinServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("search") != null) {
            String searchParam = (String) request.getParameter("searchName");
            List<Medecin> medecins;
            switch (request.getParameter("search")) {
                case "prenom":
                    medecins = serviceFacade.getMedecinDao().findMedecinByPrenom(searchParam);
                    request.setAttribute("medecins", medecins);
                    break;
                case "nom":
                    medecins = serviceFacade.getMedecinDao().findMedecinByNom(searchParam);
                    request.setAttribute("medecins", medecins);
                    break;
                case "ville":
                    medecins = serviceFacade.getMedecinDao().findMedecinByVille(searchParam);
                    request.setAttribute("medecins", medecins);
                    break;
            }
            this.getServletContext().getRequestDispatcher("/pages/findMedecin.jsp").forward(request, response);
        } else {
            List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
            request.setAttribute("medecins", medecins);
            this.getServletContext().getRequestDispatcher("/pages/findMedecin.jsp").forward(request, response);
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
