<%-- 
    Document   : lista
    Created on : 30/09/2019, 11:22:12 AM
    Author     : Darwin Garcia
--%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="com.quickelp.programa.persistencia.dao.EquipoDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.quickelp.programa.persistencia.vo.EquipoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-CO">
    <!--***************************************************************************************************** -->
    <!--Encabezado. Dashboard del Administrador. Lista de Equipos -->
    <!--***************************************************************************************************** -->
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Quickelp. Lista de Equipos</title>
        <!-- CSS de Terceros -->
        <link rel="stylesheet" href="vendors/iconfonts/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
        <!-- fin -->
        <!-- Font Awesome -->
        <link rel="stylesheet" href="vendors/css/font-awesome-5.08.css">
        <script src="js/font-awesome-gen.js"></script>
        <!-- CSS Personalizado -->
        <link rel="stylesheet" href="css/dashboard/style.css">
        <!-- Iconos -->
        <link rel="icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="sources/favicon.ico" type="image/x-icon"/>
    </head>
    <!--***************************************************************************************************** -->
    <!--Cuerpo del Web Page -->
    <!--***************************************************************************************************** -->
    <body>
        <!--Scroll y NAV Superior-->
        <div class="container-scroller">
            <!-- Navegacion -->
            <nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
                <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
                    <!--Logotipo de la pagina (solo imagen) -->
                    <a class="navbar-brand brand-logo logotipo" href="index.html"><img src="sources/dashboard/logo/logo.png" alt="logo"/>uickelp</a>
                    <a class="navbar-brand brand-logo-mini" href="index.html"><img src="sources/dashboard/logo/logo.png" alt="logo" /></a>
                </div>
                <div class="navbar-menu-wrapper d-flex align-items-stretch">
                    <div class="search-field d-none d-md-block">
                        <form class="d-flex align-items-center h-100" action="#">
                            <h3>Consulta de Equipos Registrados por Cliente</h3>
                    </div>
                    <ul class="navbar-nav navbar-nav-right">

                        <li class="nav-item d-none d-lg-block full-screen-link">
                            <a class="nav-link">
                                <i class="mdi mdi-fullscreen" id="fullscreen-button"></i>
                            </a>
                        </li>
                        <li class="nav-item dropdown">

                        </li>
                        <li class="nav-item dropdown">

                        </li>
                        <li class="nav-item nav-logout d-none d-lg-block">
                            <a class="nav-link" href="#">
                                <i class="mdi mdi-power"></i>
                            </a>
                        </li>
                        <!-- ****************************************Panel Superior Derecha************************************ -->
                        <li class="nav-item nav-profile dropdown">
                            <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
                                <div class="nav-profile-img">
                                    <img src="sources/dashboard/Avatar.png" alt="image">
                                    <span class="availability-status online"></span>             
                                </div>
                                <!-- ********************Texto del Usuario Logueado**************** -->
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
                                <a class="dropdown-item" href="Sesion?accion=raiz">
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
                <!--*****************************************************************************************************-->
                <!--  Seccion o cuerpo dentro del sitio -->
                <section class="main-panel">
                    <div class="content-wrapper">
                        <div class="page-header">
                            <!--Cabecera de Navegacion del Usuario. Donde esta ubicado actualmente -->            
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                                    <li class="breadcrumb-item"><a href="#">Equipo</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Consultar</li>
                                </ol>
                            </nav>
                        </div><!-- Fin de esta cabecera de navegacion visible -->

                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Todos los equipos por cliente</h4>
                                    </p>
                                    <table class="table table-responsive">
                                        <thead>
                                            <tr>
                                                <th>Id.Equipo</th>
                                                <th>Tipo Equipo</th>
                                                <th>Nombre Marca</th>
                                                <th>Modelo</th>
                                                <th>Numero Serie</th>                           
                                                <th>Nombre</th>
                                                <th>Apellido</th>
                                                <th>Telefono</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>

                                        <%
                                            EquipoVO equipo = null;
                                            EquipoDAO eqDAO = new EquipoDAO();
                                            List<EquipoVO> listaEquipos = eqDAO.listadoEquipos();
                                            Iterator<EquipoVO> iter = listaEquipos.iterator();
                                            //Ciclo que permite mostrar los elementos de la Lista Usuario
                                            while (iter.hasNext()) {
                                                equipo = iter.next();%>
                                        <tbody>
                                            <tr>                                
                                                <td><%=equipo.getIdRegistro()%></td>
                                                <td><%=equipo.getTipoEquipo()%></td>
                                                <td><%=equipo.getIdmarca().getNombreMarca()%></td>
                                                <td><%=equipo.getModelo()%></td>
                                                <td><%=equipo.getNumSerial()%></td>

                                                <td><%=equipo.getIdUsuario().getNombre()%></td>                           
                                                <td><%=equipo.getIdUsuario().getApellido()%></td>                           
                                                <td><%=equipo.getIdUsuario().getTelefono()%></td>                           
                                                <td>
                                                    <a class="btn btn-inverse-danger  btn-icon" href="Equipo?accion=eliminar_equipo&id=<%=Integer.toString(equipo.getIdRegistro())%>"><i class="formas fas fa-laptop">*</i></a>
                                                </td>                                                              
                                            </tr> 
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                    <a class="btn btn-outline-success btn-fw" href="Equipo?accion=registrar_equipo"><i class="formas fas fa-laptop"></i>+ Registrar Nuevo Equipo al Usuario</a> 

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
                <!--****************************************************************************************************-->
                
                <!-- partial -->
            </div>
            <!-- main-panel ends -->
        </div>
        <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->

    <!-- Plugins de JavaScript -->
    <script src="vendors/js/vendor.bundle.base.js"></script>
    <script src="vendors/js/vendor.bundle.addons.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page-->
    <!-- End plugin js for this page-->
    <!-- JavaScript Canvas -->
    <script src="js/dashboard/off-canvas.js"></script>
    <script src="js/dashboard/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page-->
    <script src="js/dashboard/dashboard.js"></script>
    <!-- End custom js for this page-->
    <footer class="footer">
        <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2019 Todos los derechos Reservados</span>
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Quickelp Centro de Servicios</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hecho con <i class="mdi mdi-heart text-danger"> </i>Por Quickelp.com</span>
        </div>
    </footer>
</body>

<!--***************************************************************************************************** -->
<!--Fin Web Page -->
<!--***************************************************************************************************** -->
</html>