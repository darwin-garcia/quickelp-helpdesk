<%-- 
    Document   : eliminar
    Created on : 5/07/2019, 03:09:35 AM
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
<html lang="es-CO">
    <head>
        <title>Quickelp - Pagina Principal de Usuario</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <meta name="viewport" content="width=device-width, user-scalable=no">
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
                                <a class="nav-link" href="#">Inicio</a>
                            </li>			      
                            <li class="nav-item dropdown">
                                <a style="color:white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Servicios
                                </a>
                                <div class="dropdown-content-custom" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="#">Manteinimento de equipos</a>
                                    <a class="dropdown-item" href="#">Configuracion de equipos</a>
                                    <a class="dropdown-item" href="#">Soporte tecnico calificado</a>
                                    <a class="dropdown-item" href="#">Reparacion de equipos</a>
                                </div>
                            </li>

                        </ul>
                        <ul class="navbar-nav navbar-right">
                            <li class="nav-item dropdown">
                                <a style="color:white" class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"><img src="sources/login/Avatar.png" height="20" width="20"> Usuario</a>
                                <div class="dropdown-content-custom">
                                    <a class="dropdown-item"><img src="sources/login/Avatar.png" height="80" width="80"></a>
                                    <a>Bienvenido</a>
                                    <div class="dropdown-divider"></div>
                                    <a style="color:blue" class="dropdown-item" href="Controlador?accion=ingreso">Iniciar Sesion</a>
                                    <a style="color:red" class="dropdown-item" href="Controlador?accion=registrar">Registrarse</a>
                                </div> 
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <section>

            </section>
            <footer>

            </footer>            
        </main>	
    </body>