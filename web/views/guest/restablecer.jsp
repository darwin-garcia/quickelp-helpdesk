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
<html lang="es-CO">
    <head>
        <link rel="stylesheet" href="vendors/iconfonts/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
        <link rel="stylesheet" href="css/dashboard/style.css">

        <link href="css/login/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Restablecer contraseña de acceso</title>
        <link rel="shortcut icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="icon" href="sources/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <main role="main">
            <div class="container-scroller">
                <div class="container-fluid page-body-wrapper full-page-wrapper">
                    <div class="content-wrapper d-flex align-items-center auth">
                        <div class="row w-100">
                            <div class="col-lg-4 mx-auto">
                                <div class="auth-form-light text-left p-5">
                                    <div class="brand-logo">
                                        <img src="sources/logotipo.png">
                                    </div>
                                    <h4>Hola! No puedes ingresar?</h4>
                                    <h6 class="font-weight-light">Completa la informacion requerida para continuar.</h6>
                                    <form class="pt-3" method="post" action="Usuario">
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-lg" id="Correo" name="Correo" placeholder="&#xf0e0; Correo">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-lg" id="ClaveUsuario" name="ClaveUsuario" placeholder="&#xf084; Contraseña">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-lg" id="ValidarClaveUsuario" name="ValidarClaveUsuario" placeholder="&#xf084; Repetir Contraseña">
                                        </div>
                                        <div class="mt-3">
                                            <input type="submit" value="Restablecer contraseña" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
                                        </div>

                                        <div class="mb-2">

                                        </div>
                                        <div class="text-center mt-4 font-weight-light">
                                            No tienes cuenta? <a href="Sesion?accion=Ingresar" class="text-primary">Registrate aqui</a>
                                        </div>
                                        <input type="hidden" value="reset_clave" name="Peticion">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- content-wrapper ends -->
                </div>
                <!-- page-body-wrapper ends -->
            </div>
            <footer class="footer">
                <div class="d-sm-flex justify-content-center justify-content-sm-between">
                    <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2019 Todos los derechos Reservados</span>
                    <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Quickelp Centro de Servicios</span>
                    <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hecho con <i class="mdi mdi-heart text-danger"> </i>Por Quickelp.com</span>
                </div>
            </footer>


        </main>	
    </body>
    <script src="js/login/font-awesome-min.js"></script>
    <script src="vendors/js/vendor.bundle.base.js"></script>
    <script src="vendors/js/vendor.bundle.addons.js"></script>
    <!-- endinject -->
    <!-- inject:js -->
    <script src="js/dashboard/off-canvas.js"></script>
    <script src="js/dashboard/misc.js"></script>
</html>
