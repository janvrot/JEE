<%-- 
    Document   : updateMedecin
    Created on : 2 août 2018, 09:43:18
    Author     : Janvrot
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.cours.allo.docteur.dao.entities.Medecin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Medecin medecin = (Medecin) request.getAttribute("medecin");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateNaissance = dateFormat.format(medecin.getUtilisateur().getDateNaissance());

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
                <div class="container content-outer personal-information">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                INFORMATIONS PERSONNELLES DU MÉDECIN
                            </h1>
                            <div class="formulaire">
                                <form action="${pageContext.request.contextPath}/UpdateMedecinServlet" method="post">
                                    <p>
                                        <select name="civility" required>
                                            <% out.println("<option selected value=\"" + medecin.getUtilisateur().getCivilite() + "\">" + medecin.getUtilisateur().getCivilite() + "</option>");%>
                                            <option value="Mr">Mr</option>
                                            <option value="Mme">Mme</option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="firstName" value="<% out.println(medecin.getUtilisateur().getPrenom()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="name" value="<% out.println(medecin.getUtilisateur().getNom()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="identifier" value="<% out.println(medecin.getUtilisateur().getIdentifiant()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="password" value="<% out.println(medecin.getUtilisateur().getMotPasse()); %>" required>
                                    </p>
                                    <p>
                                        <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" value="<% out.print(dateNaissance); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="tel" value="<% out.println(medecin.getNumeroTelephone()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="numAcreditation" value="<% out.println(medecin.getNumeroAccreditation()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="street" value="<% out.println(medecin.getUtilisateur().getAdresse().getRue()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="postalCode" value="<% out.println(medecin.getUtilisateur().getAdresse().getCodePostal()); %>" required>
                                    </p>

                                    <p>
                                        <select name="city">
                                            <% out.println("<option value=\"" + medecin.getUtilisateur().getAdresse().getVille() + "\" selected>" + medecin.getUtilisateur().getAdresse().getVille() + "</option>");%>
                                            <option value="ville" >Paris</option>
                                            <option value="ville" >lavale</option>
                                            <option value="ville" >Lille</option>
                                            <option value="ville" >Lion</option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="pays" value="<% out.print(medecin.getUtilisateur().getAdresse().getPays());%>" required>
                                    </p>
                                    <p>
                                        <button type="submit">Modifier</button>
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

        <!-- build:js scripts/main.js -->
        <script src="./assets/scripts/jquery.slicknav.js"></script>    
        <script src="./assets/scripts/main.js"></script>
        <!-- endbuild -->
    </body>
</html>
