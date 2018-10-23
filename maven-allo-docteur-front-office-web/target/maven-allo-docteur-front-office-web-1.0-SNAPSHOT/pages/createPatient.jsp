<%-- 
    Document   : createUser
    Created on : 1 août 2018, 11:49:43
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
          <a href="${pageContext.request.contextPath}/LoginServlet" class="logo">
            <img src="./assets/images/logo.png" alt="">
          </a>
          <ul class="loginMenu">
            <li><a class="active" href="${pageContext.request.contextPath}/CreatePatientServlet">Création compte</a></li>
            <li><a href="${pageContext.request.contextPath}/LoginServlet">Connexion</a></li>
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
                <form action="${pageContext.request.contextPath}/CreatePatientServlet" method="post">
                  <p>
                  <select name="civility" requires>
                    <option selected value="Civilité">Civilité</option>
                    <option value="Mr">Homme</option>
                    <option value="Mme">Femme</option>
                  </select>
                </p>
                  <p>
                  <input type="text" name="firstName" placeholder="Prénom" required>
                  </p>
                  <p>
                  <input type="text" name="name" placeholder="Nom" required>
                  </p>
                  <p>
                  <input type="email" name="identifier" placeholder="Identifiant" required>
                  </p>
                  <p>
                  <input type="text" name="password" placeholder="Mot de passe" required>
                  </p>
                  <p>
                    <input autocomplete="off" data-toggle="datepicker" type="text" name="naissance" placeholder="Date de naissance" required>
                  </p>
                  <p>
                  <input type="text" name="secu" placeholder="Numéro de Securite sociale" required pattern="[0-9]{17}">
                  </p>
                  <p>
                  <input type="text" name="tel" placeholder="Numéro de Téléphone" required pattern="[0-9]{10}">
                  </p>
                  <p>
                  <input type="text" name="street" placeholder="Rue" required>
                  </p>
                  <p>
                  <input type="text" name="postalCode" placeholder="Code postale" required pattern="[0-9]{5}">
                  </p>
                  </p>
                  <p>
                  <select name="city" required>
                    <option value="Paris" >Paris</option>
                    <option value="Lavale" >lavale</option>
                    <option value="Lille" >Lille</option>
                    <option value="Lyon" >Lion</option>
                  </select>
                  </p>
                   <p>
                  <input type="text" name="Pays" placeholder="Pays" required>
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
