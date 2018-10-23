/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.allo.docteur.servlets;

import com.cours.allo.docteur.dao.entities.Medecin;
import com.cours.allo.docteur.dao.entities.Patient;
import com.cours.allo.docteur.dao.entities.RendezVous;
import com.cours.allo.docteur.factory.ServiceFactory;
import com.cours.allo.docteur.service.IServiceFacade;
import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession();
        int idPatient = (Integer) session.getAttribute("patientId");
        List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().findAllRendezVousByPatient(idPatient);
        String jsonUser = new Gson().toJson(rendezVous);
        try {
            writer.write(jsonUser);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createRendezVousCsv(HttpServletRequest request) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("RendezVous.csv"));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder sb = new StringBuilder();
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().findAllRendezVousByPatient(idPatient);
            sb.append("civilite");
            sb.append(",");
            sb.append("prenom");
            sb.append(",");
            sb.append("nom");
            sb.append(",");
            sb.append("identifiant");
            sb.append(",");
            sb.append("numeroAccreditation");
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
                sb.append(rdv.getCreneau().getMedecin().getUtilisateur().getCivilite());
                sb.append(",");
                sb.append(rdv.getCreneau().getMedecin().getUtilisateur().getPrenom());
                sb.append(",");
                sb.append(rdv.getCreneau().getMedecin().getUtilisateur().getNom());
                sb.append(",");
                sb.append(rdv.getCreneau().getMedecin().getUtilisateur().getIdentifiant());
                sb.append(",");
                sb.append(rdv.getCreneau().getMedecin().getNumeroAccreditation());
                sb.append(",");
                sb.append(rdv.getCreneau().getMedecin().getNumeroTelephone());
                sb.append(",");
                sb.append(rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getRue()).append(rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getCodePostal()).append(rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getVille()).append(rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getPays());
                sb.append(",");
                sb.append(rdv.getCreneau().getHeureDebut()).append("h").append(rdv.getCreneau().getMinuteDebut()).append("-").append(rdv.getCreneau().getHeureFin()).append("h").append(rdv.getCreneau().getMinuteFin());
                sb.append(",");
                sb.append(jour);
                sb.append("\r\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createMedecinCsv(HttpServletRequest request) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Medecins.csv"));
            StringBuilder sb = new StringBuilder();
            List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
            sb.append("civilite");
            sb.append(",");
            sb.append("prenom");
            sb.append(",");
            sb.append("nom");
            sb.append(",");
            sb.append("identifiant");
            sb.append(",");
            sb.append("numeroAccreditation");
            sb.append(",");
            sb.append("numeroTelephone");
            sb.append(",");
            sb.append("adresse");
            sb.append("\r\n");
            for (Medecin medecin : medecins) {
                sb.append(medecin.getUtilisateur().getCivilite());
                sb.append(",");
                sb.append(medecin.getUtilisateur().getPrenom());
                sb.append(",");
                sb.append(medecin.getUtilisateur().getNom());
                sb.append(",");
                sb.append(medecin.getUtilisateur().getIdentifiant());
                sb.append(",");
                sb.append(medecin.getNumeroAccreditation());
                sb.append(",");
                sb.append(medecin.getNumeroTelephone());
                sb.append(",");
                sb.append(medecin.getUtilisateur().getAdresse().getRue()).append(medecin.getUtilisateur().getAdresse().getCodePostal()).append(medecin.getUtilisateur().getAdresse().getVille()).append(medecin.getUtilisateur().getAdresse().getPays());
                sb.append("\r\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createMedecinPdf(HttpServletRequest request) throws DocumentException {
        try {
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            List<Medecin> medecins = serviceFacade.getMedecinDao().findAllMedecins();
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Medecins.pdf"));
            document.open();
            document.add(new Paragraph("civilite,prenom,nom,identifiant,numeroAccreditation,numeroTelephone,adresse"));
            for (Medecin medecin : medecins) {
                document.add(new Paragraph(medecin.getUtilisateur().getCivilite() + ","
                        + medecin.getUtilisateur().getPrenom() + ","
                        + medecin.getUtilisateur().getNom() + ","
                        + medecin.getUtilisateur().getIdentifiant() + ","
                        + medecin.getNumeroAccreditation() + ","
                        + medecin.getNumeroTelephone() + ","
                        + medecin.getUtilisateur().getAdresse().getRue() + medecin.getUtilisateur().getAdresse().getCodePostal() + medecin.getUtilisateur().getAdresse().getVille() + medecin.getUtilisateur().getAdresse().getPays()));
            }
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createRendezVousPdf(HttpServletRequest request) throws DocumentException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            int idPatient = (Integer) session.getAttribute("patientId");
            List<RendezVous> rendezVous = serviceFacade.getRendezVousDao().findAllRendezVousByPatient(idPatient);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("RendezVous.pdf"));
            document.open();
            document.add(new Paragraph("civilite,prenom,nom,identifiant,numeroAccreditation,numeroTelephone,adresse,Creneau,jour"));
            for (RendezVous rdv : rendezVous) {
                String jour = dateFormat.format(rdv.getJour());
                document.add(new Paragraph(rdv.getCreneau().getMedecin().getUtilisateur().getCivilite() + ","
                        + rdv.getCreneau().getMedecin().getUtilisateur().getPrenom() + ","
                        + rdv.getCreneau().getMedecin().getUtilisateur().getNom() + ","
                        + rdv.getCreneau().getMedecin().getUtilisateur().getIdentifiant() + ","
                        + rdv.getCreneau().getMedecin().getNumeroAccreditation() + ","
                        + rdv.getCreneau().getMedecin().getNumeroTelephone() + ","
                        + rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getRue() + rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getCodePostal() + rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getVille() + rdv.getCreneau().getMedecin().getUtilisateur().getAdresse().getPays() + ","
                        + rdv.getCreneau().getHeureDebut() + "h" + rdv.getCreneau().getMinuteDebut() + "-" + rdv.getCreneau().getHeureFin() + "h" + rdv.getCreneau().getMinuteFin() + ","
                        + jour));
            }
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            case "medecinCsv":
                this.createMedecinCsv(request);
                this.dlFile("Medecins.csv", response, "text/csv");
                break;
            case "rdvCsv":
                this.createRendezVousCsv(request);
                this.dlFile("RendezVous.csv", response, "text/csv");
                break;
            case "medecinPdf":
                try {
                    this.createMedecinPdf(request);
                } catch (DocumentException ex) {
                    Logger.getLogger(ExportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dlFile("Medecins.pdf", response, "application/pdf");
                break;
            case "rdvPdf":
                try {
                    this.createRendezVousPdf(request);
                } catch (DocumentException ex) {
                    Logger.getLogger(ExportServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dlFile("RendezVous.pdf", response, "application/pdf");
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
