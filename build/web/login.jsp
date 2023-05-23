<%-- 
    Document   : login
    Created on : 5/07/2019, 01:42:11 AM
    Author     : Darwin Garcia
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-CO">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>    
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bienvenido a Quickelp</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
    </head>
    <body>
        <main>
            <header>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a class="navbar-brand logotipo" href="#"><img src="sources/logo.png" height="20" width="20"> Quickelp</a>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="Sesion?accion=raiz">Inicio Sesion</a>
                            </li>			      
                        </ul>			    
                    </div>
                </nav>
            </header>
            <section id="login">            
                <div class="modal-dialog text-center">
                    <div class="col-sm-8 main-section">
                        <div class="modal-content">
                            <div class=" Kimono-img">
                                <img src="sources/login/Avatar.png">
                            </div>
                            <!--Boton de Login -->
                            <form class="col-12" method="post" action="Sesion?accion=validar">
                                <!--Input de Correo -->
                                <div class="form-group" id="user-group">
                                    <input type="text" class="form-control" placeholder="Correo" name="email" id="email"/>
                                </div>
                                <!--Input de Contraseña -->
                                <div class="form-group" id="contrasena-group">
                                    <input type="password" class="form-control" placeholder="Contraseña" name="clave" id="clave"/>
                                </div>
                                <!--Boton de Ingresar -->
                                <button id="send" class="btn btn-danger"><i class="fas fa-sign-in-alt"></i> Ingresar </button>
                            </form>                          
                            <!--Metodo alterno de ingreso cuando se olvida la contraseña -->
                            <div class="col-12 forgot">
                                <a href="#">Olvido la contraseña? Click Aqui</a>
                                <a href="Usuario?accion=Restablecer">Registrarse</a>
                            </div>
                            <!--Texto en pantalla en el Form-->
                            <div class="col-12 forgot">
                                <a style="color:#990000">
                                <% if (request.getAttribute("error") != null) {%>
                                ${error}
                                <% } else { %>
                                ${exito}
                                <%}%></a>
                            </div>
                        </div>
                    </div>
                </div>

            </section>
            <footer>

            </footer>
        </main>  
    </body>    
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
</html>
