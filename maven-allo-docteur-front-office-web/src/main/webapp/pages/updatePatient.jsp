<%-- 
    Document   : updateUser.jsp
    Created on : 1 août 2018, 14:38:35
    Author     : Janvrot
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.cours.allo.docteur.dao.entities.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    Patient patient = (Patient) request.getAttribute("patient");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateNaissance = dateFormat.format(patient.getUtilisateur().getDateNaissance());

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
                <div class="container content-outer creation-compte">
                    <div class="content-container clearfix">
                        <div class="content">
                            <h1 class="site-title">
                                CRÉATION COMPTE
                            </h1>
                            <div class="formulaire">
                                <form action="${pageContext.request.contextPath}/UpdatePatientServlet" method="post">
                                    <p>
                                        <select name="civility" required>
                                            <% out.println("<option selected value=\"" + patient.getUtilisateur().getCivilite() + "\">" + patient.getUtilisateur().getCivilite() + "</option>");%>
                                            <option value="Mr">Mr</option>
                                            <option value="Mme">Mme</option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="firstName" value="<% out.println(patient.getUtilisateur().getPrenom()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="name" value="<% out.println(patient.getUtilisateur().getNom()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="identifier" value="<% out.println(patient.getUtilisateur().getIdentifiant()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="password" value="<% out.println(patient.getUtilisateur().getMotPasse()); %>" required>
                                    </p>
                                    <p>
                                        <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" value="<% out.print(dateNaissance); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="secu" value="<% out.println(patient.getNumeroSecuriteSociale()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="tel" value="<% out.println(patient.getNumeroTelephone()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="street" value="<% out.println(patient.getUtilisateur().getAdresse().getRue()); %>" required>
                                    </p>
                                    <p>
                                        <input type="text" name="postalCode" value="<% out.println(patient.getUtilisateur().getAdresse().getCodePostal()); %>" required>
                                    </p>
                                    </p>
                                    <p>
                                        <select name="city" required>
                                            <% out.println("<option value=\"" + patient.getUtilisateur().getAdresse().getVille() + "\" selected>" + patient.getUtilisateur().getAdresse().getVille() + "</option>");%>
                                            <option value="Paris" >Paris</option>
                                            <option value="Lavale" >Lavale</option>
                                            <option value="Lille" >Lille</option>
                                            <option value="Lyon" >Lion</option>
                                        </select>
                                    </p>
                                    <p>
                                        <input type="text" name="Pays" value="<% out.print(patient.getUtilisateur().getAdresse().getPays());%>" required>
                                    </p>

                                    <p>
                                        <button type="submit">Envoyer</button>
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
