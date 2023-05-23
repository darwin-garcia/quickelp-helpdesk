<%-- 
    Document   : restablecer
    Created on : 18/09/2019, 02:43:07 AM
    Author     : Darwin Garcia
--%>
<%@page import="java.sql.Connection" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="com.quickelp.programa.persistencia.conexion.Conexion" %>
<%@page import="com.quickelp.programa.persistencia.vo.UsuarioVO" %>
<%@page import="com.quickelp.programa.persistencia.dao.UsuarioDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>JSP Page</title>
    </head>
    <body>
        <main>
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
                                <a class="nav-link" href="#">Inicio</a>
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
                                    <a class="dropdown-item" href="#">Consultar Usuarios</a>
                                    <a class="dropdown-item" href="#">Registrar Nuevo Usuario</a>
                                    <a class="dropdown-item" href="#">Modificar Usuario Registrado</a>
                                    <a class="dropdown-item" href="#">Eliminar Usuario</a>
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
                                    <label>$correo</label> <!-- Correo del Usuario-->
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
                <form class="form-horizontal" method="post" action="Usuario">
                    <!-- Password input-->
                    <!-- claveUsuario -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" style="color:black">Contraseña</label>
                        <div class="col-md-4">
                            <input id="ModificarClaveUsuario" name="ModificarClaveUsuario" type="password" placeholder="*" class="form-control input-md" required="">
                            <span class="help-block">Minimo 6 digitos</span>
                        </div>
                    </div>

                    <!-- Password input -->
                    <!-- Confirmar Contraseña -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" style="color:black">Repetir contraseña</label>
                        <div class="col-md-4">
                            <input id="ValidarClaveUsuario" name="ValidarClaveUsuario" type="password" placeholder="*" class="form-control input-md">
                            <span class="help-block">La contraseña debe ser la misma</span>
                        </div>
                    </div>

                    <!-- Button (Double) -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" style="color:black">Acciones</label>
                        <div class="col-md-8">
                            <!-- Boton de Guardar-->

                            <input type="submit" value="Modificar" class="btn btn-success">
                            <!-- Boton de Cancelar Registro-->
                            <a class="btn btn-danger" href="Usuario?accion=listar_usuarios">Regresar</a>
                        </div>
                    </div>
                    <input type="hidden" value="restablecer" name="Peticion">
                </form>
            </section>
        </main>
    </body>
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
