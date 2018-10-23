<%-- 
    Document   : home
    Created on : 2 août 2018, 08:22:02
    Author     : Janvrot
--%>

<%@page import="com.cours.allo.docteur.dao.entities.Medecin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Medecin medecin = (Medecin) request.getAttribute("medecin");
    System.out.println(medecin.getUtilisateur());
    String enter;

    enter = "Bonjour " + medecin.getUtilisateur().getCivilite() + " " + medecin.getUtilisateur().getPrenom() + " "
            + medecin.getUtilisateur().getNom() + ", votre chiffre d’affaire du mois est de XXXX euros";

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
                <div class="container content-outer medecin-home">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                ACCUEIL DES MÉDECIN
                            </h1>
                            <div class="home-links">
                                <p><% out.println(enter); %></p>
                                <div class="links">
                                    <a href="${pageContext.request.contextPath}/UpdateMedecinServlet">Modifier mes informations personnelles</a>
                                    <a href="#">Voir les rendez-vous de la journée</a>
                                    <a href="${pageContext.request.contextPath}/RendezVousServlet">Rechercher mes rendez-vous</a>
                                    <a href="${pageContext.request.contextPath}/CreateMedecinServlet">Parrainer un médecin</a>
                                    <a href="#">Présence au rendez-vous</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=rdvCsv">Exporter mes futurs rendez-vous au format CSV</a>
                                    <a href="#">Exporter mes futurs rendez-vous au format XML</a>
                                    <a href="${pageContext.request.contextPath}/ExportServlet?action=rdvJson">Exporter mes futurs rendez-vous au format Json</a>
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

        <!-- build:js scripts/main.js -->
        <script src="./assets/scripts/jquery.slicknav.js"></script>
        <script src="./assets/scripts/main.js"></script>
        <!-- endbuild -->
    </body>
</html>
