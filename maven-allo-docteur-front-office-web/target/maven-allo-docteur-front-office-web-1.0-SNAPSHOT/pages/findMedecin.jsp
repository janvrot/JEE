<%-- 
    Document   : findMedecin
    Created on : 1 août 2018, 20:09:20
    Author     : Janvrot
--%>

<%@page import="com.cours.allo.docteur.dao.entities.Medecin"%>
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
                <div class="container content-outer search-patient">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                RECHERCHE Medecin
                            </h1>
                            <form class="search-form" action="${pageContext.request.contextPath}/FindMedecinServlet" method="get">
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
                                        <th>Numéro accréditation</th>
                                        <th>Numéro de téléphone</th>
                                        <th>Adresse</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%

                                        if (request.getAttribute("medecins") != null) {
                                            List<Medecin> medecins = (List<Medecin>) request.getAttribute("medecins");
                                            for (Medecin medecin : medecins) {
                                                out.println("<tr>");
                                                out.println("<td>" + medecin.getUtilisateur().getCivilite() + " " + medecin.getUtilisateur().getPrenom() + " " + medecin.getUtilisateur().getNom() + "</td>");
                                                out.println("<td>" + medecin.getUtilisateur().getIdentifiant() + "</td>");
                                                out.println("<td>" + medecin.getNumeroAccreditation() + "</td>");
                                                out.println("<td>" + medecin.getNumeroTelephone() + "</td>");
                                                out.println("<td>" + medecin.getUtilisateur().getAdresse().getRue() + " " + medecin.getUtilisateur().getAdresse().getCodePostal() + " " + medecin.getUtilisateur().getAdresse().getVille() + " " + medecin.getUtilisateur().getAdresse().getPays() + "</td>");
                                                out.println("</tr>");
                                            }
                                        }
                                        else {
                                            out.println("<tr><td>Aucun medecin trouve</td></tr>");
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
