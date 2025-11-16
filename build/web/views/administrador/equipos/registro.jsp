<%-- 
    Document   : registro
    Created on : 30/09/2019, 11:22:01 AM
    Author     : Darwin Garcia
--%>

<%@page import="com.quickelp.programa.persistencia.vo.UsuarioVO"%>
<%@page import="com.quickelp.programa.persistencia.dao.UsuarioDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.quickelp.programa.persistencia.dao.MarcaDAO"%>
<%@page import="com.quickelp.programa.persistencia.vo.MarcaVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-CO">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Registrar Equipo</title>        
        <link rel="stylesheet" href="vendors/iconfonts/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
        <!-- fin -->
        <!-- CSS Personalizado -->
        <link rel="stylesheet" href="css/dashboard/style.css">
        <!-- Iconos -->
        <link rel="icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="sources/favicon.ico" type="image/x-icon"/>
    </head>    
    <body>
        <!--Scroll y NAV Superior-->
        <div class="container-scroller">
            <!-- partial:partials/_navbar.html -->
            <nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
                <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
                    <!--Logotipo de la pagina (solo imagen) -->
                    <a class="navbar-brand brand-logo logotipo" href="index.html"><img src="sources/dashboard/logo/logo.png" alt="logo"/>uickelp</a>
                    <a class="navbar-brand brand-logo-mini" href="index.html"><img src="sources/dashboard/logo/logo.png" alt="logo" /></a>
                </div>
                <div class="navbar-menu-wrapper d-flex align-items-stretch">
                    <div class="search-field d-none d-md-block">
                        <form class="d-flex align-items-center h-100" action="#">
                            <h3>Registro de Equipo</h3></form>
                    </div>
                    <ul class="navbar-nav navbar-nav-right">
                        <li class="nav-item nav-logout d-none d-lg-block">
                            <a class="nav-link" href="#">
                                <i class="mdi mdi-power"></i>
                            </a>
                        </li>
                        <li class="nav-item nav-profile dropdown">
                            <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
                                <div class="nav-profile-img">
                                    <img src="sources/dashboard/Avatar.png" alt="image">
                                    <span class="availability-status online"></span>             
                                </div>
                                <div class="nav-profile-text">
                                    <p class="mb-1 text-black">${nombre} ${apellido}</p>
                                </div>
                            </a>
                            <div class="dropdown-menu navbar-dropdown" aria-labelledby="profileDropdown">
                                <a class="dropdown-item">
                                    <!--Mostrar el correo ingresado en el dashboard -->
                                    <i class="mdi mdi-email mr-2 text-success"></i>
                                    ${correo}
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="mdi mdi-cached mr-2 text-success"></i>
                                    Perfil de Usuario ID: ${idUsu}
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">
                                    <i class="mdi mdi-logout mr-2 text-primary"></i>
                                    Cerrar Sesion
                                </a>
                            </div>
                        </li>
                    </ul>
                    <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                        <span class="mdi mdi-menu"></span>
                    </button>
                </div>
            </nav><!--FIN DEL NAV SUPERIOR-->
            <!-- ******************************************************************************************************************* -->
            <div class="container-fluid page-body-wrapper">
                <!-- SIDEBAR O BARRA LATERAL IZQUIERDA-->
                <!-- partial:partials/_sidebar.html -->
                <nav class="sidebar sidebar-offcanvas" id="sidebar"><!-- NAV DEL SIDEBAR-->
                    <ul class="nav">
                        <li class="nav-item nav-profile">
                            <a href="#" class="nav-link">
                                <div class="nav-profile-image">
                                    <!--Foto del usuario como prueba -->
                                    <img src="sources/dashboard/Avatar.png" alt="profile">
                                    <span class="login-status online"></span> <!--change to offline or busy as needed-->              
                                </div>
                                <div class="nav-profile-text d-flex flex-column">
                                    <span class="font-weight-bold mb-2">${nombre} ${apellido}</span>
                                    <span class="text-secondary text-small">${nombreRol}</span>
                                </div>
                                <i class="mdi mdi-bookmark-check text-danger nav-profile-badge"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="index.html">
                                <span class="menu-title">Inicio</span>
                                <i class="mdi mdi-home menu-icon"></i>
                            </a>
                        </li>
                        <!-- ****************************************Panel de Servicios************************************ -->
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#ui-basic1" aria-expanded="false" aria-controls="ui-basic">
                                <span class="menu-title">Servicios</span>
                                <i class="menu-arrow"></i>
                                <i class="mdi mdi-clipboard-alert menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic1">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="Servicio?accion=listar_activos">Consultar los asignados</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Servicio?accion=listar_inactivos">Consultar los Cancelados</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Servicio?accion=listar_clienteadm">Consultar los clientes</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Servicio?accion=solicita_admin">Crear nuevo Servicio</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Servicio?accion=asignar_tecnico">Asignar Servicio al tecnico</a></li>
                                </ul>
                            </div>
                        </li>
                        <!-- ****************************************Panel de Diagnosticos************************************ -->
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#ui-basic2" aria-expanded="false" aria-controls="ui-basic">
                                <span class="menu-title">Diagnosticos</span>
                                <i class="menu-arrow"></i>
                                <i class="mdi mdi-file-check menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic2">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="#">Consultar Todos</a></li>
                                </ul>
                            </div>
                        </li>
                        <!-- ****************************************Panel de Reparaciones************************************ -->
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#ui-basic3" aria-expanded="false" aria-controls="ui-basic">
                                <span class="menu-title">Reparaciones</span>
                                <i class="menu-arrow"></i>
                                <i class="mdi mdi-wrench menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic3">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="#">Consultar</a></li>
                                </ul>
                            </div>
                        </li>
                        <!-- ****************************************Panel de Equipos************************************ -->
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#ui-basic4" aria-expanded="false" aria-controls="ui-basic">
                                <span class="menu-title">Equipos</span>
                                <i class="menu-arrow"></i>
                                <i class="mdi mdi-laptop-mac menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic4">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="Equipo?accion=listar_equipos">Consultar</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Equipo?accion=registrar_equipo">Registrar</a></li>
                                </ul>
                            </div>
                        </li>
                        <!-- ****************************************Panel de Usuarios************************************ -->
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="collapse" href="#ui-basic5" aria-expanded="false" aria-controls="ui-basic">
                                <span class="menu-title">Usuarios</span>
                                <i class="menu-arrow"></i>
                                <i class="mdi mdi-account menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic5">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="Usuario?accion=listar_usuarios">Consultar</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Usuario?accion=registrar_usuarios">Registrar</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="Usuario?accion=agregar_esp">Nueva Especialidad Tecnico</a></li>
                                </ul>
                            </div>
                        </li>
                        <!-- ****************************************Botones de Accion Rapida************************************ -->
                        
                    </ul>
                </nav><!--FIN DEL NAV NAV DEL SIDEBAR-->
                <!-- ************************************************************************************************************************************************************ -->
                <!-- *********************************Desde aqui se puede editar toda la seccion de la web para aplicar cambios************************************************** -->
                <!-- ************************************************************************************************************************************************************ -->
                <!--  Seccion o cuerpo dentro del sitio -->
                <%
                    MarcaVO mar = null;
                    MarcaDAO marDAO = new MarcaDAO();
                    List<MarcaVO> listaMarcas = marDAO.listadoMarca();
                    Iterator<MarcaVO> iterMarcas = listaMarcas.iterator();
                    
                    UsuarioVO usu = null;
                    UsuarioDAO usuDAO = new UsuarioDAO();
                    List<UsuarioVO> listaUsuarios = usuDAO.listadoUsuarios();
                    Iterator<UsuarioVO> iterUsuario = listaUsuarios.iterator();
                %>
                <section class="main-panel">
                    <div class="content-wrapper">
                        <div class="page-header">
                            <!--Cabecera de Navegacion del Usuario. Donde esta ubicado actualmente -->            
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="Sesion?accion=HomeAdministrador">Home</a></li>
                                    <li class="breadcrumb-item"><a href="#">Usuario</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Registro</li>
                                </ol>
                            </nav>
                        </div><!-- Fin de esta cabecera de navegacion visible -->

                        <div class="col-12 grid-margin"><!-- Panel del Formulario-->
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Registro de Nuevo Usuario</h4>
                                    <!-- Form Action donde recoge los datos ingresados -->

                                    <form class="form-horizontal" method="post" action="Equipo">


                                        <!-- Form Name -->
                                        <legend>Registro de Equipos</legend>

                                        <!-- Tipos de Equipo -->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="selectbasic">Tipo de Equipo</label>
                                            <div class="col-md-4">
                                                <select id="RegistrarTipoEquipo" name="RegistrarTipoEquipo" class="form-control">
                                                    <option value="Portatil">Laptop / Portatil</option>
                                                    <option value="Escritorio">Desktop / Escritorio</option>
                                                </select>
                                            </div>
                                        </div>                                    
                                        <!-- Marcas de Equipo -->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="selectbasic">Marca</label>
                                            <div class="col-md-4">
                                                <select id="RegistrarMarca" name="RegistrarMarca" class="form-control">
                                                    <% while (iterMarcas.hasNext()) {
                                                        mar = iterMarcas.next();%>
                                                    <option value="<%=mar.getIdMarca()%>"><%=mar.getNombreMarca()%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                        <!-- Modelo-->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="textinput">Modelo</label>  
                                            <div class="col-md-4">
                                                <input id="RegistarModelo" name="RegistrarModelo" type="text" placeholder="Modelo del equipo" class="form-control input-md" required="">

                                            </div>
                                        </div>
                                        <!-- numero de serie-->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="textinput">Numero de Serie</label>  
                                            <div class="col-md-4">
                                                <input id="RegistrarNumeroSerie" name="RegistrarNumeroSerie" type="text" placeholder="Numero de Serie" class="form-control input-md" required="">

                                            </div>
                                        </div>   

                                        
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="selectbasic">Usuario</label>
                                            <div class="col-md-4">
                                                <select id="RegistrarId" name="RegistrarId" class="form-control">
                                                    <% while (iterUsuario.hasNext()) {
                                                        usu = iterUsuario.next();%>
                                                        <option value="<%=usu.getIdUsuario()%>"><%=usu.getTipoIdentificacion().getNombreTipoDoc()%> <%=usu.getNumeroIdentificacion()%> <%=usu.getNombre()%> <%=usu.getApellido()%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>                                      

                                        <!-- Button (Double) -->
                                        <div class="form-group">
                                            <label class="col-md-4 control-label" for="button1id"></label>
                                            <div class="col-md-8">
                                                <!--Boton de Agregar -->
                                                <button id="button1id" name="registrar" class="btn btn-success">Guardar</button>
                                                <a class="btn btn-danger" href="Equipo?accion=listar_equipos">Regresar</a>
                                            </div>
                                        </div>
                                        <input type="hidden" value="registrar" name="Peticion">

                                    </form>





                                </div><!-- Fin del class:card-->
                            </div><!-- Fin del Panel Formulario -->
                        </div><!-- Fin de: content-wrapper-->
                    </div><!-- Fin de: container-fluid page-body-wrapper-->
                </section>
                <!-- partial:partials/_footer.html -->
               
                <!-- partial -->
            </div><!-- container-fluid page-body-wrapper -->
        </div>
        <!-- Container Scroller -->


        <!-- Plugins de JavaScript -->
        <script src="vendors/js/vendor.bundle.base.js"></script>
        <script src="vendors/js/vendor.bundle.addons.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page-->
        <!-- End plugin js for this page-->
        <!-- JavaScript Canvas -->
        <script src="js/off-canvas.js"></script>
        <script src="js/misc.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page-->
        <script src="js/dashboard.js"></script>
        <!-- End custom js for this page-->
        <footer class="footer">
        <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2019 Todos los derechos Reservados</span>
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Quickelp Centro de Servicios</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hecho con <i class="mdi mdi-heart text-danger"> </i>Por Quickelp.com</span>
        </div>
    </footer>
    </body>
</html>
