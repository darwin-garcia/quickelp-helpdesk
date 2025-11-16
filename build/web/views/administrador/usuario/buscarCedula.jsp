<%-- 
    Document   : buscarCedula
    Created on : 5/11/2019, 10:34:39 AM
    Author     : Darwin Garcia
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.quickelp.programa.persistencia.dao.TipoDocumentoDAO"%>
<%@page import="com.quickelp.programa.persistencia.vo.TipoDocumentoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-CO">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Quickelp. Buscar Cliente por Cedula</title>
        <!-- CSS de Terceros -->
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
        <main>
            <header>

            </header>
            <%
                TipoDocumentoVO tipoDoc = null;
                TipoDocumentoDAO tipoDocDAO = new TipoDocumentoDAO();
                List<TipoDocumentoVO> listaTipoDoc = tipoDocDAO.listadoTipoDoc();
                Iterator<TipoDocumentoVO> iterTipoDoc = listaTipoDoc.iterator();
            %>
            <section>
                <h1>Buscar Usuario</h1>
                <form class="form-horizontal" method="post" action="Usuario">
                    <div class="form-group">
                        <label class="col-sm-3 col-form-label">Tipo Identificacion</label>
                            <select id="BuscarIdTipoDoc" name="BuscarIdTipoDoc" class="form-control">
                                <% while (iterTipoDoc.hasNext()) {
                                        tipoDoc = iterTipoDoc.next();%>                                                            
                                <option value="<%=tipoDoc.getIdTipoDocumento()%>"><%=tipoDoc.getNombreTipoDoc()%></option><%}%>
                            </select>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" style="color:black">ID Usuario:</label> 
                        <input type="text" name="BuscarCedula" value="" class="form-control input-md">
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
                    <input type="hidden" value="consulta_ced" name="Peticion">
                </form>
            </section>

        </main>
    </body>
</html>
