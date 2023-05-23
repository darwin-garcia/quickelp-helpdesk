<%-- 
    Document   : actualizar
    Created on : 5/07/2019, 03:09:21 AM
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
            <div class="container mt-4" style="margin-top:50px">
                    <div class="card border-info">
                        <div class="card-header bg-info">
                            <h4>Modificar Usuario</h4>
                        </div>
                        <div class="card-body">
                            <form class="form-horizontal" method="post" action="Usuario">
                                <fieldset>
                                    <%
                                        UsuarioVO usu = new UsuarioVO();
                                        usu=(UsuarioVO)request.getAttribute("usuario");
                                          
                                    %>
                                    <!-- Form Name -->
                                    <legend>Modificar Usuario </legend>
                                    <!-- idUsuario -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">ID Usuario: <% out.print(usu.getIdUsuario());%> </label>  
                                    </div>
                                    <!-- idUsuario -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Tipo Documento: <% //out.print(usu.getTipoIdentificacion().getNombreTipoDoc());%> </label>  
                                    </div>
                                    <!-- idUsuario -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Numero Documento: <% //out.print(usu.getNumeroIdentificacion());%> </label>  
                                    </div>
                                    
                                    <!-- Select Rol de Usuario -->
                                    <!-- idRol -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Rol</label>
                                        <div class="col-md-4">
                                            <!--Seleccionar Rol de usuario. Puedo Listar? -->
                                            <select id="ModificaIdRol" name="ModificarIdRol" class="form-control">
                                                <option value="1">Administrador</option>
                                                <option value="2">Tecnico</option>
                                                <option value="3">Cliente</option>
                                            </select>
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <!-- nombre -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Nombre</label>  
                                        <div class="col-md-4">
                                            <input id="ModificarNombre" name="ModificarNombre" type="text" placeholder="*" class="form-control input-md" required="">
                                            <span class="help-block">Digite nombres completos</span>  
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <!-- apellido -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Apellido</label>  
                                        <div class="col-md-4">
                                            <input id="ModificarApellido" name="ModificarApellido" type="text" placeholder="*" class="form-control input-md" required="">
                                            <span class="help-block">help</span>  
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <!-- telefono -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Telefono</label>  
                                        <div class="col-md-4">
                                            <input id="ModificarTelefono" name="ModificarTelefono" type="text" placeholder="*" class="form-control input-md" required="">
                                            <span class="help-block">Maximo 10 digitos</span>  
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <!-- correo -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Correo</label>  
                                        <div class="col-md-4">
                                            <input id="ModificarCorreo" name="ModificarCorreo" type="text" placeholder="ejemplo@mail.com" class="form-control input-md" required="">
                                            <span class="help-block">Ingrese un correo valido</span>  
                                        </div>
                                    </div>

                                    <!-- Text input-->
                                    <!-- direccion -->
                                    <div class="form-group">
                                        <label class="col-md-4 control-label" style="color:black">Direccion</label>  
                                        <div class="col-md-4">
                                            <input id="ModificarDireccion" name="ModificarDireccion" type="text" placeholder="placeholder" class="form-control input-md">
                                            <span class="help-block">Ej: Carrera 13 38-29</span>  
                                        </div>
                                    </div>

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
                                    <input type="hidden" value="actualizar" name="Peticion">
                                </fieldset>
                            </form>

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
</html>
