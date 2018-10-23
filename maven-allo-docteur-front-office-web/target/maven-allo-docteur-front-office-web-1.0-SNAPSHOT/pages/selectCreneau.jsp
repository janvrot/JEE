<%-- 
    Document   : selectCreneau
    Created on : 2 août 2018, 01:30:55
    Author     : Janvrot
--%>

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
          <a href="./index.html" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
          <ul class="loginMenu">
            <li><a href="creation-compte.html">Création compte</a></li>
            <li><a href="allo-doctor-compte-patient.html">Connexion</a></li>
          </ul>
          <ul class="nav">
            <li><a href="patients.html">Patient</a></li>
            <li><a href="rendez-vous.html">Prendre rendez vous</a></li>
            <li><a href="modification-informations-patient.html">Informations patient</a></li>
            <li><a class="active" href="selection-creneau.html ">Selection créneau</a></li>
            <li><a href="futurs-rendez-vous.html ">Futurs rendez-vous</a></li>
            <li><a href="recherche-medecin.html ">Recherche médecin</a></li>
          </ul>
        </div>
      </div>

      <div class="container-fluid clearfix">
        <div class="container content-outer rendez-vous">
          <div class="content-container clearfix">
            <div class="content">
              <h1 class="site-title">
                SÉLECTION CRÉNEAU
              </h1>
              <p>Rendez vous du Docteur Valéry NANO le 23/11/2017</p>
              <table class="selection-creneau">
                <thead>
                  <tr>
                    <th>Créneau horaire</th>
                    <th>Action</th>
                   
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td title="Créneau horaire">8h00-8h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">8h15-8h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">8h30-8h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">8h45-9h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">9h15-9h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">9h30-9h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">9h45-10h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>

                   <tr>
                    <td title="Créneau horaire">10h00-10h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">10h15-10h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">10h30-10h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">10h45-11h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>

                  <tr>
                    <td title="Créneau horaire">11h00-11h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">11h15-11h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">11h30-11h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">11h45-12h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">14h00-14h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">14h15-14h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">14h30-14h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">14h45-15h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">15h15-15h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">15h30-15h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">15h45-16h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>

                   <tr>
                    <td title="Créneau horaire">16h00-16h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">16h15-16h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">16h30-16h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">16h45-17h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>

                  <tr>
                    <td title="Créneau horaire">17h00-17h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">17h15-17h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">17h30-17h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">17h45-18h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>

                  <tr class="spacer"></tr>

                  <tr>
                    <td title="Créneau horaire">18h00-18h15</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td> 
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">18h15-18h30</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">18h30-18h45</td>
                    <td title="Action"><a href="#" role="button">Réserver</a></td>
                  </tr>
                  <tr class="spacer"></tr>
                  <tr>
                    <td title="Créneau horaire">18h45-19h00</td>
                    <td title="Action"><a href="#" role="button">Supprimer</a></td>
                  </tr>
                  
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
