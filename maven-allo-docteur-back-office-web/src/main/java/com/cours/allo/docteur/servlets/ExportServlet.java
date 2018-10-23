/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
public class ExportServlet extends HttpServlet {

   private static final Log log = LogFactory.getLog(LoginServlet.class);
    private IServiceFacade serviceFacade = null;

    @Override
    public void init() throws ServletException {
        serviceFacade = ServiceFactory.getDefaultServiceFacade();
    }

    private void createRendezVousJson(HttpServletRequest request) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("RendezVous.json"));
        } catch (IOException ex) {
            Logger.getLogger(ManageMedecinServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession();
            int idMedecin = (Integer) session.getAttribute("medecinId");
            List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().fingRendezVousByMedecin(idMedecin);
        String jsonUser = new Gson().toJson(rendezVous);
        try {
            writer.write(jsonUser);
        } catch (IOException ex) {
            Logger.getLogger(ManageMedecinServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManageMedecinServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createRendezVousCsv(HttpServletRequest request) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("RendezVous.csv"));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder sb = new StringBuilder();
            HttpSession session = request.getSession();
            int idMedecin = (Integer) session.getAttribute("medecinId");
            List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().fingRendezVousByMedecin(idMedecin);
            sb.append("civilite");
            sb.append(",");
            sb.append("prenom");
            sb.append(",");
            sb.append("nom");
            sb.append(",");
            sb.append("identifiant");
            sb.append(",");
            sb.append("numeroSecuriteSociale");
            sb.append(",");
            sb.append("numeroTelephone");
            sb.append(",");
            sb.append("adresse");
            sb.append(",");
            sb.append("Creneau");
            sb.append(",");
            sb.append("jour");
            sb.append("\r\n");
            for (RendezVous rdv : rendezVous) {
                String jour = dateFormat.format(rdv.getJour());
                sb.append(rdv.getPatient().getUtilisateur().getCivilite());
                sb.append(",");
                sb.append(rdv.getPatient().getUtilisateur().getPrenom());
                sb.append(",");
                sb.append(rdv.getPatient().getUtilisateur().getNom());
                sb.append(",");
                sb.append(rdv.getPatient().getUtilisateur().getIdentifiant());
                sb.append(",");
                sb.append(rdv.getPatient().getNumeroSecuriteSociale());
                sb.append(",");
                sb.append(rdv.getPatient().getNumeroTelephone());
                sb.append(",");
                sb.append(rdv.getPatient().getUtilisateur().getAdresse().getRue()).append(rdv.getPatient().getUtilisateur().getAdresse().getCodePostal()).append(rdv.getPatient().getUtilisateur().getAdresse().getVille()).append(rdv.getPatient().getUtilisateur().getAdresse().getPays());
                sb.append(",");
                sb.append(rdv.getCreneau().getHeureDebut()).append("h").append(rdv.getCreneau().getMinuteDebut()).append("-").append(rdv.getCreneau().getHeureFin()).append("h").append(rdv.getCreneau().getMinuteFin());
                sb.append(",");
                sb.append(jour);
                sb.append("\r\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManageMedecinServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dlFile(String file, HttpServletResponse response, String ct) throws IOException {
        response.setContentType(ct);
        response.setHeader("Content-Disposition",
                "attachment;filename=" + file);
        ServletContext ctx = getServletContext();
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "rdvJson":
                this.createRendezVousJson(request);
                this.dlFile("RendezVous.json", response, "application/json");
                break;
            case "rdvCsv":
                this.createRendezVousCsv(request);
                this.dlFile("RendezVous.csv", response, "text/csv");
                break;
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
