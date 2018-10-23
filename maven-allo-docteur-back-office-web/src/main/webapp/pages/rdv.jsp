<%-- 
    Document   : rdv
    Created on : 2 août 2018, 12:26:04
    Author     : Janvrot
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.cours.allo.docteur.dao.entities.RendezVous"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    List<RendezVous> rendezVous = (List<RendezVous>) request.getAttribute("rendezVous");

%>


<!DOCTYPE html>
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
                    <a href="${pageContext.request.contextPath}/ManageMedecinServlet" class="logo">
                        <img src="./assets/images/logo.png" alt="">
                    </a>
                    <ul class="nav">
                        <li><a class="active" href="${pageContext.request.contextPath}/ManageMedecinServlet">Médecin</a></li>
                        <li><a href="${pageContext.request.contextPath}/UpdateMedecinServlet">Informations médecins</a></li>
                        <li><a href="${pageContext.request.contextPath}/FindPatientServlet">Recherche patient</a></li>
                        <li><a href="#">Présence rendez-vous</a></li>
                        <li><a href="${pageContext.request.contextPath}/CreateMedecinServlet">Parrainer un médecin</a></li>
                        <li><a href="#">Rendez-vous journée</a></li>
                    </ul>
                </div>
            </div>

            <div class="container-fluid clearfix">
                <div class="container content-outer forgot-password">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                Rechercher un rendez-vous
                            </h1>

                            <table class="presence">
                                <thead>
                                    <tr>
                                        <th>Prénom et Nom</th>
                                        <th>Identifiant</th>
                                        <th>Numéro de sécurité sociale</th>
                                        <th>Numéro de téléphone</th>
                                        <th>Adresse</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%                                        if (rendezVous.isEmpty()) {
                                            out.println("<td>Aucun rendez vous</td>");
                                        } else {
                                            for (RendezVous rdv : rendezVous) {
                                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                                String jourRendezVous = dateFormat.format(rdv.getJour());
                                                out.println("<tr>");
                                                out.println("<td>" + rdv.getPatient().getUtilisateur().getCivilite() + rdv.getPatient().getUtilisateur().getPrenom() + rdv.getPatient().getUtilisateur().getNom() + "</td>");
                                                out.println("<td>" + rdv.getPatient().getUtilisateur().getIdentifiant() + "</td>");
                                                out.println("<td>" + rdv.getPatient().getNumeroSecuriteSociale() + "</td>");
                                                out.println("<td>" + rdv.getPatient().getNumeroTelephone() + "</td>");
                                                out.println("<td>" + rdv.getPatient().getUtilisateur().getAdresse().getRue() + rdv.getPatient().getUtilisateur().getAdresse().getCodePostal() + rdv.getPatient().getUtilisateur().getAdresse().getVille() + rdv.getPatient().getUtilisateur().getAdresse().getPays() + "</td>");
                                                out.println("<td>" + jourRendezVous + "<br>de<br>" + rdv.getCreneau().getHeureDebut() + "h" + rdv.getCreneau().getMinuteDebut() + "-" + rdv.getCreneau().getHeureFin() + "h" + rdv.getCreneau().getMinuteFin() + "</td>");
                                                out.println("</tr>");
                                            }
                                        }
                                    %>



                                </tbody>
                            </table>
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
