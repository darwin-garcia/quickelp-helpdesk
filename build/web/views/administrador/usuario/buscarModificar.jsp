<%-- 
    Document   : buscarModificar
    Created on : 29/09/2019, 06:15:44 PM
    Author     : Darwin Garcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Buscar</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark"><!--Barra de Navegacion -->
                <!--Logotipo de Quickelp -->
                <a class="navbar-brand logotipo" href="#"><img src="sources/logo.png" height="20" width="20"> Quickelp</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!--Botones de la izquierda del Nav -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <!--Boton de Inicio. Se dirige al index -->
                        <li class="nav-item">
                            <a class="nav-link" href="#">Lista de Usuarios</a>
                        </li>
                        <!--Menu de Solicitudes -->
                        <li class="nav-item dropdown">
                            <a style="color:white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Solicitudes
                            </a>
                            <div class="dropdown-content-custom" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Consultar Servicios</a>
                                <a class="dropdown-item" href="#">Solicitar Servicio Tecnico</a>
                                <a class="dropdown-item" href="#">Modificar Servicio Tecnico</a>
                                <a class="dropdown-item" href="#">Cancelar Servicio Tecnico</a>
                                <a style="color:blue" class="dropdown-item" href="Servicio?accion=listar_tipo">Mostrar Tipo de Servicio</a>
                                <a style="color:blue" class="dropdown-item" href="Servicio?accion=listar_tipo">Registrar Tipo de Servicio</a>
                                <a style="color:green" class="dropdown-item" href="Servicio?accion=listar_estado">Mostrar Estados de Servicio</a>
                                <a style="color:green" class="dropdown-item" href="Servicio?accion=listar_estado">Registrar Estado de Servicio</a>
                            </div>
                        </li>
                        <!--Menu de Diagnosticos -->
                        <li class="nav-item dropdown">
                            <a style="color:white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Diagnosticos
                            </a>
                            <div class="dropdown-content-custom" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Consultar Diagnosticos</a>
                                <a class="dropdown-item" href="#">Registrar Diagnosticos</a>
                                <a class="dropdown-item" href="#">Modificar Diagnosticos</a>
                            </div>
                        </li>
                        <!--Menu de Reparaciones -->
                        <li class="nav-item dropdown">
                            <a style="color:white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Reparaciones
                            </a>
                            <div class="dropdown-content-custom" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="#">Consultar Reparaciones</a>
                                <a class="dropdown-item" href="#">Registrar Reparaciones</a>
                                <a class="dropdown-item" href="#">Modificar Reparaciones</a>
                            </div>
                        </li>
                        <!--Menu de Usuarios -->
                        <li class="nav-item dropdown">
                            <a style="color:white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Usuarios
                            </a>
                            <div class="dropdown-content-custom" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="Usuario?accion=listar_usuarios">Consultar Usuarios</a>
                                <a class="dropdown-item" href="Usuario?accion=registrar_usuarios">Registrar Nuevo Usuario</a>
                                <a class="dropdown-item" href="Usuario?accion=buscar_editar">Modificar Usuario Registrado</a>
                                <a class="dropdown-item" href="Usuario?accion=buscar_eliminar">Eliminar Usuario</a>
                                <a style="color:blue" class="dropdown-item" href="Usuario?accion=listar_roles">Mostrar Roles de Usuario</a>                                    
                                <a style="color:green" class="dropdown-item" href="Usuario?accion=listar_documentos">Mostrar Tipos de documento</a>  
                            </div>
                        </li>
                        <!-- Botones de la derecha-->
                    </ul>
                    <ul class="navbar-nav navbar-right">
                        <!--Item del Usuario actual -->
                        <li class="nav-item dropdown">
                            <a style="color:white " class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"><img src="sources/login/Avatar.png" height="20" width="20"> $Administrador</a>
                            <div class="dropdown-content-custom">
                                <a class="dropdown-item"><img src="sources/login/Avatar.png" height="80" width="80"></a>
                                <label>$NombreUsuario</label><!-- Nombre del Usuario-->
                                <label>${correo}</label> <!-- Correo del Usuario-->
                                <label>$rol</label><!--Rol del usuario activo-->
                                <div class="dropdown-divider"></div>
                                <a style="color:blue" class="dropdown-item" href="Usuario?accion=listar">Ver Perfil de usuario</a>
                                <a class="dropdown-item" href="Usuario?accion=actualizar">Modificar Usuario</a>
                                <a style="color:red" class="dropdown-item" href="Sesion?accion=raiz">Cerrar Sesion</a>
                            </div> 
                        </li>
                    </ul>
                </div>
            </nav><!--Fin de la barra de navegacion -->
        </header>
        <section>

        </section>
        <footer id="sticky-footer" class="py-4 bg-dark text-white-50">
            <center>© 2019 Copyright: <a href="#">Quickelp.com</a></center>
        </footer>
    </body>
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/navbar.js"></script>
</html>
