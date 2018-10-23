<%-- 
    Document   : home
    Created on : 1 août 2018, 10:58:33
    Author     : Janvrot
--%>

<%@page import="com.cours.allo.docteur.dao.entities.Patient"%>
<%@page import="com.cours.allo.docteur.dao.entities.RendezVous"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

    Patient patient = (Patient) request.getAttribute("patient");
    String enter;

    if (request.getAttribute("rendezVous") != null) {
        RendezVous rendezVous = (RendezVous) request.getAttribute("rendezVous");
        enter = "Bonjour " + patient.getUtilisateur().getCivilite() + " " + patient.getUtilisateur().getPrenom() + " "
                + patient.getUtilisateur().getNom() + ", votre prochain rendez-vous est pour le " + rendezVous.getJour()
                + " a " + rendezVous.getCreneau().getHeureDebut() + "h" + rendezVous.getCreneau().getMinuteDebut()
                + " avec le docteur " + rendezVous.getCreneau().getMedecin().getUtilisateur().getPrenom() + " "
                + rendezVous.getCreneau().getMedecin().getUtilisateur().getNom() + " dont le cabinet se situe au "
                + rendezVous.getCreneau().getMedecin().getUtilisateur().getAdresse().getRue() + ", " + rendezVous.getCreneau().getMedecin().getUtilisateur().getAdresse().getCodePostal()
                + " " + rendezVous.getCreneau().getMedecin().getUtilisateur().getAdresse().getVille() + " Téléphone " + rendezVous.getCreneau().getMedecin().getNumeroTelephone();
    } else {
        enter = "Bonjour " + patient.getUtilisateur().getCivilite() + " " + patient.getUtilisateur().getPrenom() + " "
                + patient.getUtilisateur().getNom() + " vous n'avez pas encore prise de rendez-vous";
    }

%>

<!DOCTYPE html>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Allo Docteur</title>

        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <!-- Place favicon.ico in the root directory -->

        <!-- build:css styles/vendor.css -->
        <!-- bower:css -->
        <!-- endbower -->
        <!-- endbuild -->

        <!-- build:css styles/main.css -->
        <link rel="stylesheet" href="./assets/styles/bootstrap.css"> 
        <link rel="stylesheet" href="./assets/styles/main.css">
        <link rel="stylesheet" href="./assets/styles/rwd.css">

        <!-- endbuild -->

        <!-- build:js scripts/vendor/modernizr.js -->
        <!-- endbuild -->
    </head>
    <body class="clearfix">
        <!--[if IE]>
          <p class="browserupgrade">Vous utilisez un <strong>obsolète</strong> navigateur. Merci de se <a href="http://browsehappy.com/">mettre à jour</a> pour améliorer votre experience.</p>
        <![endif]-->

        <div class="container-fluid  no-padding ">
            <div class="container-fluid clearfix no-padding header-container ">
                <div class="container header-content">
                    <a href="${pageContext.request.contextPath}/ManagePatientServlet" class="logo">
                        <img src="./assets/images/logo.png" alt="">
                    </a>
                    <ul class="nav">
                        <li><a class="active" href="${pageContext.request.contextPath}/ManagePatientServlet">Patient</a></li>
                        <li><a href="${pageContext.request.contextPath}/ChooseRendezVousServlet">Prendre rendez vous</a></li>
                        <li><a href="${pageContext.request.contextPath}/UpdatePatientServlet">Informations patient</a></li>
                        <li><a href="${pageContext.request.contextPath}/RendezVousServlet">Futurs rendez-vous</a></li>
                        <li><a href="${pageContext.request.contextPath}/FindMedecinServlet">Recherche médecin</a></li>
                    </ul>
                </div>
            </div>

            <div class="container-fluid clearfix">
                <div class="container content-outer patient-home">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                ACCUEIL DES PATIENTS
                            </h1>
                            <div class="home-links">
                                <p>
                                    <%                        out.println(enter);

                                    %>

                                </p>
                                <div class="links">
                                    <a href="${pageContext.request.contextPath}/UpdatePatientServlet">Modifier mes informations personnelles</a>
                                    <a href="${pageContext.request.contextPath}/ChooseRendezVousServlet">Prendre un rendez-vous</a>
                                    <a href="${pageContext.request.contextPath}/RendezVousServlet">Voir mes futur rendez-vous</a>
                                    <a href="${pageContext.request.contextPath}/FindMedecinServlet">Rechercher medecin</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=medecinPdf">Exporter les médecins au format PDF.</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=medecinCsv">Exporter les médecins au format CSV.</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=rdvPdf">Exporter mes futurs rendez-vous au format PDF.</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=rdvCsv">Exporter tous mes rendez-vous au format CSV.</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=rdvJson">Exporter tous mes rendez-vous au format JSON.</a>
                                    <a href="#">Exporter tous mes rendez-vous au format XML.</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>



        <!-- build:js scripts/vendor.js -->
        <!-- bower:js -->
        <script src="./assets/scripts/jquery.js"></script>
        <!-- endbower -->
        <!-- endbuild -->

        <script src="./assets/scripts/jquery.slicknav.js"></script> 
        <!-- build:js scripts/main.js -->
        <script src="./assets/scripts/main.js"></script>
        <!-- endbuild -->
    </body>
</html>


