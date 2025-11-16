<%-- 
    Document   : login
    Created on : 5/07/2019, 01:42:11 AM
    Author     : Darwin Garcia
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.quickelp.programa.persistencia.dao.TipoDocumentoDAO"%>
<%@page import="com.quickelp.programa.persistencia.vo.TipoDocumentoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-CO">
    
    <script language="javascript" type="text/javascript">
        function validarCampoVacios(){
            
            var vRegistrarNumeroIdentificacion=document.getElementById("RegistrarNumeroIdentificacion").value;
            var vRegistrarNombre=document.getElementById("RegistrarNombre").value;
            var vRegistrarApellido=document.getElementById("RegistrarApellido").value;
            var vRegistrarTelefono=document.getElementById("RegistrarTelefono").value;
            var vRegistrarCorreo=document.getElementById("RegistrarCorreo").value;
            var vRegistrarDireccion=document.getElementById("RegistrarDireccion").value;
            var vRegistrarClaveUsuario=document.getElementById("RegistrarClaveUsuario").value;
            var vValidarClaveUsuario=document.getElementById("validarClaveUsuario").value;
            
            if(vRegistrarNumeroIdentificacion.length===0){
                alert("Tiene que escribir su numero de identificacion");
                return false;
            }
            
            if(vRegistrarNombre.length===0){
                alert("Tiene que escribir su nombre");
                return false;
                
            }
            
            if (vRegistrarApellido.length===0){
                alert("Tiene que escribir su apellido");
                return false;
            }
            
            if (vRegistrarTelefono.length===0){
                alert("Tiene ")
            }
        }
        
    </script>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="shortcut icon" href="sources/favicon.ico" type="image/x-icon">
        <link rel="icon" href="sources/favicon.ico" type="image/x-icon">
        <link href="css/login/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <title>Quickelp</title>
        <link rel="stylesheet" href="css/login/hojalogueo.css">
        
    </head>
    <%
        TipoDocumentoVO tipoDoc = null;
        TipoDocumentoDAO tipoDocDAO = new TipoDocumentoDAO();
        List<TipoDocumentoVO> listaTipoDoc = tipoDocDAO.listadoTipoDoc();
        Iterator<TipoDocumentoVO> iterTipoDoc = listaTipoDoc.iterator();
    %>
    <body>
        <main>
            <div class="container" id="container">
                <div class="form-container sign-up-container">
                    <form class="form-horizontal" method="post" action="Usuario">
                        <h3>Registrarse</h3>
                        <span><i class="fa fa-user"></i> Ingrese los datos requeridos para registrarse.</span>
                        <div class="caja">                            
                            <select id="RegistrarTipoDoc" name="NuevoTipoDoc" class="form-control">
                                <%while (iterTipoDoc.hasNext()) {
                                    tipoDoc = iterTipoDoc.next();%>
                                <option value="<%=tipoDoc.getIdTipoDocumento()%>"><%=tipoDoc.getNombreTipoDoc()%></option>
                                <% }%>
                            </select>                                
                        </div>
                                
                        <input id="RegistrarNumeroIdentificacion" name="NuevoNumeroIdentificacion" type="text" class="font-icons" placeholder="&#xf2bb; Numero de Identificacion" />

                        <input type="hidden" value="3" name="NuevoIdRol">

                        <input id="RegistrarNombre" name="NuevoNombre" type="text" class="font-icons" placeholder="&#xf007; Nombre" required="" />
                        <input id="RegistrarApellido" name="NuevoApellido" type="text" class="font-icons" placeholder="&#xf007; Apellido" required="" />
                        <input id="RegistrarTelefono" name="NuevoTelefono" type="text" class="font-icons" placeholder="&#xf10b; Telefono" required="" />
                        <input id="RegistrarCorreo" name="NuevoCorreo" type="email" class="font-icons" placeholder="&#xf0e0; Correo" required="" />
                        <input id="RegistrarDireccion" name="NuevoDireccion" type="text" class="font-icons" placeholder="&#xf041; Direccion" />
                        <input id="RegistrarClaveUsuario "name="NuevoClaveUsuario" type="password"  class="font-icons" placeholder="&#xf084; Contraseña" required=""/>
                        <input id="ValidarClaveUsuario" name="ValidarClaveUsuario" type="password" class="font-icons" placeholder="&#xf084; Contraseña nuevamente" required=""/>
                        <!-- <button>Registrar</button>-->
                        <input type="submit" value="Agregar" class="boton-agregar">
                        <input type="hidden" value="nuevo_cliente" name="Peticion">
                    </form>
                </div> 
                <div class="form-container sign-in-container">
                    <form action="Sesion?Peticion=validar" method="post">
                        <img src="sources/logotipo.png">
                        <br>
                        <h2>Iniciar Sesion</h2>
                      
                        <input type="email" class="font-icons" placeholder="&#xf0e0; Correo" name="email" id="email" required=""/>
                        <input type="password" class="font-icons" placeholder="&#xf084; Contraseña" name="clave" id="clave" required=""/>
                        <a href="Usuario?accion=restablecer_clave">Olvido su contraseña? Haga click aqui.</a>


                        <button>Ingresar</button>

                        <a style="color:#990000">
                            <% if (request.getAttribute("error") != null) {%>
                            ${error}
                            <% } else { %>
                            ${exito}
                            <%}%></a>
                        <!-- <input type="hidden" value="validar" name="Peticion"> -->
                    </form>
                </div>
                <div class="overlay-container">
                    <div class="overlay">
                        <div class="overlay-panel overlay-left">
                            <img src="sources/logo-login.png" style="height: auto; width: auto">
                            <br>
                            <h1>Bienvenido</h1>
                            
                            <p>Mantente conectado con nuestros servicios</p>
                            <button class="ghost" id="signIn">Ingresar</button>
                        </div>
                        <div class="overlay-panel overlay-right">
                            <img src="sources/logo-color.png">
                            <br>
                            <h1>Bienvenido a Quickelp</h1>
                            
                            <p>Solicita su servicio tecnico con nosotros, iniciando sesion.</p>
                            <p></p>
                            <p>No se encuentra registrado? Haz click abajo</p>
                            <button class="ghost" id="signUp">Registrarse</button>
                        </div>
                    </div>
                </div>
            </div>

            <footer>
                <p>
                    © 2019 Copyright: <a href="#">Quickelp.com</a>
                </p>
            </footer>

        </main>
    </body>
    <script src="js/login/logueocustom.js"></script>
    <script src="js/login/font-awesome-min.js"></script>
</html>