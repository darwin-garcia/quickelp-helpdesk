<%-- 
    Document   : lista
    Created on : 5/07/2019, 03:13:14 AM
    Author     : Darwin Garcia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="com.quickelp.programa.persistencia.conexion.Conexion" %>
<%@page import="com.quickelp.programa.persistencia.vo.TipoServicioVO" %>
<%@page import="com.quickelp.programa.persistencia.dao.TipoServicioDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Lista de Tipo Servicio</title>
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
                                <a class="nav-link" href="#">Lista de Tipo Servicio</a>
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
                                    <a class="dropdown-item" href="Usuario?accion=listar_usuarios">Registrar Nuevo Usuario</a>
                                    <a class="dropdown-item" href="Usuario?accion=listar_usuarios">Modificar Usuario Registrado</a>
                                    <a class="dropdown-item" href="Usuario?accion=listar_usuarios">Eliminar Usuario</a>
                                    <a style="color:blue" class="dropdown-item" href="Usuario?accion=listar_roles">Mostrar Roles de Usuario</a>
                                    <a style="color:blue" class="dropdown-item" href="Usuario?accion=listar_roles">Registrar Roles de Usuario</a>
                                    <a style="color:green" class="dropdown-item" href="Usuario?accion=listar_documentos">Mostrar Tipos de documento</a>
                                    <a style="color:green" class="dropdown-item" href="Usuario?accion=listar_documentos">Registrar nuevo Tipo de documento</a>
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
                <h1>Lista de Tipo Servicio</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id. </th>
                            <th>Tipo Servicio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <%
                        //Connection conexion = null;
                        TipoServicioVO tServ = null;
                        TipoServicioDAO dao = new TipoServicioDAO();
                        List<TipoServicioVO> listado = dao.listar();
                        Iterator<TipoServicioVO> iterador = listado.iterator();

                        while (iterador.hasNext()) {
                            tServ = iterador.next();
                    %>
                    <tbody>
                        <tr>                                
                            <td><%=tServ.getIdTipoServicio()%></td>
                            <td><%=tServ.getNombreTipoServicio()%></td>
                            <td>
                                <a class="btn btn-warning" href="">Editar</a>
                                <a class="btn btn-danger" href="">Eliminar</a>
                            </td>                                                              
                        </tr> 
                        <%}%>
                    </tbody>
                </table>
                <a class="btn btn-success" href="Servicio?accion=registrar_tipos">Registrar Nuevo Tipo de Servicio</a> 

            </section>
            <footer>

            </footer>
        </main>

    </body>
    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</html>
