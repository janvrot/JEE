<%-- 
    Document   : dateRendezVous
    Created on : 2 août 2018, 00:35:56
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
                <div class="container content-outer rendez-vous">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                PRISE DE RENDEZ VOUS
                            </h1>
                            <div class="formulaire rendez-vous-form">
                                <form action="${pageContext.request.contextPath}/SelectCreneauServlet" method="post">
                                    <p>
                                        <select name="medecin" required>
                                            <%
                                                List<Medecin> medecins = (List<Medecin>) request.getAttribute("medecins");
                                                for (Medecin medecin : medecins) {
                                                    out.println("<option value=\"" + medecin.getUtilisateur().getIdentifiant() + "\">" + medecin.getUtilisateur().getPrenom() + " " + medecin.getUtilisateur().getNom() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </p>
                                    <p>
                                        <input autocomplete="off" data-toggle="datepicker" type="text" name="date" placeholder="Date du rendez vous" required>
                                    </p>
                                    <p>
                                    <p>
                                        <button type="submit">Valider</button>
                                    </p>
                                    <p>
                                </form>
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
        <script src="https://fengyuanchen.github.io/datepicker/js/datepicker.js"></script>

        <!-- build:js scripts/main.js -->
        <script src="./assets/scripts/main.js"></script>
        <!-- endbuild -->
    </body>
</html>
