<%-- 
    Document   : findPatient
    Created on : 2 août 2018, 13:36:58
    Author     : Janvrot
--%>

<%@page import="com.cours.allo.docteur.dao.entities.Patient"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div class="container content-outer search-patient">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                RECHERCHE PATIENT
                            </h1>
                            <form class="search-form" action="${pageContext.request.contextPath}/FindPatientServlet" method="post">
                                <select name="search" required>
                                    <option value="prenom" >Prénom</option>
                                    <option value="nom" >Nom</option>
                                    <option value="ville" >Ville</option>
                                </select>
                                <input type="text" name="searchName" placeholder="Entrez la valeur" required>
                                <button type="submit">Rechercher</button>
                            </form>

                            <table>
                                <thead>
                                    <tr>
                                        <th title="Prénom et Nom">Prénom et Nom</th>
                                        <th>Identifiant</th>
                                        <th>Numéro de sécurité sociale</th>
                                        <th>Numéro de téléphone</th>
                                        <th>Adresse</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%

                                        if (request.getAttribute("patients") != null) {
                                            List<Patient> patients = (List<Patient>) request.getAttribute("patients");
                                            for (Patient patient : patients) {
                                                out.println("<tr>");
                                                out.println("<td>" + patient.getUtilisateur().getCivilite() + " " + patient.getUtilisateur().getPrenom() + " " + patient.getUtilisateur().getNom() + "</td>");
                                                out.println("<td>" + patient.getUtilisateur().getIdentifiant() + "</td>");
                                                out.println("<td>" + patient.getNumeroSecuriteSociale()+ "</td>");
                                                out.println("<td>" + patient.getNumeroTelephone() + "</td>");
                                                out.println("<td>" + patient.getUtilisateur().getAdresse().getRue() + " " + patient.getUtilisateur().getAdresse().getCodePostal() + " " + patient.getUtilisateur().getAdresse().getVille() + " " + patient.getUtilisateur().getAdresse().getPays() + "</td>");
                                                out.println("</tr>");
                                            }
                                        } else {
                                            out.println("<tr><td>Aucun patient trouve</td></tr>");
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
