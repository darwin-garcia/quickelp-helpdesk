/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.RolDAO;
import com.quickelp.programa.persistencia.dao.UsuarioDAO;
import com.quickelp.programa.persistencia.vo.RolVO;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darwin Garcia
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {

    String listar = "views/usuario/lista.jsp";
    String agregar = "views/usuario/registro.jsp";
    String editar = "views/usuario/actualizar.jsp";
    String borrar = "views/usuario/eliminar.jsp";

    UsuarioVO usVO = new UsuarioVO();
    UsuarioDAO usDAO = new UsuarioDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        String action = request.getParameter("accion");
        switch (action) {
            case "listar_usuarios":
                request.getRequestDispatcher("views/usuario/lista.jsp").forward(request, response);
                break;
            case "registrar_usuarios":
                request.getRequestDispatcher("views/usuario/registro.jsp").forward(request, response);
                break;
            case "editar_usuarios":

                try {
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("usuario", usVO);
                    request.getRequestDispatcher("views/usuario/actualizar.jsp").forward(request, response);
                } catch (Exception e) {

                }
                break;
            case "restablecer_usuarios":
                try{
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("usuario", usVO);
                    request.getRequestDispatcher("views/usuario/restablecer.jsp").forward(request, response);
                }catch(Exception e){
                    
                }
                break;
            case "listar_roles":
                request.getRequestDispatcher("views/rol/lista.jsp").forward(request, response);
                break;
            case "registrar_roles":
                request.getRequestDispatcher("views/rol/registro.jsp").forward(request, response);
                break;
            //Guardar Datos en la tabla Rol
            case "guardar_rol":
                RolVO rol = new RolVO();
                RolDAO rolDAO = new RolDAO();
                String idRol = request.getParameter("idRol");
                String nombreRol = request.getParameter("nombreRol");
                
                rol.setIdRol(Integer.parseInt(idRol));
                rol.setNombreRol(nombreRol);
                rolDAO.AgregarRoles(rol);
                request.getRequestDispatcher("views/rol/lista.jsp").forward(request, response);
                break;
            case "listar_documentos":
                request.getRequestDispatcher("views/tipoDocumento/lista.jsp").forward(request, response);
                break;
            case "registrar_documentos":
                request.getRequestDispatcher("views/tipoDocumento/registro.jsp").forward(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        RolVO roles = new RolVO();
        TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
        UsuarioVO usuario = new UsuarioVO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        String url = request.getParameter("Peticion");

        switch (url) {
            case "registrar":
                //usuario.getIdUsuario(request.setAttribute("PosIdUsuario", "idUsuario"));//Obtener el # id actual que esta registrando el usuario
                roles.setIdRol(Integer.parseInt(request.getParameter("RegistrarIdRol")));
                tipoDocumento.setIdTipoDocumento(Integer.parseInt(request.getParameter("RegistrarTipoDoc")));
                usuario.setNumeroIdentificacion(Double.parseDouble(request.getParameter("RegistrarNumeroIdentificacion")));
                usuario.setNombre(request.getParameter("RegistrarNombre"));
                usuario.setApellido(request.getParameter("RegistrarApellido"));
                usuario.setTelefono(Double.parseDouble(request.getParameter("RegistrarTelefono")));
                usuario.setCorreo(request.getParameter("RegistrarCorreo"));
                usuario.setDireccion(request.getParameter("RegistrarDireccion"));
                usuario.setClaveUsuario(request.getParameter("RegistrarClaveUsuario"));
                
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(roles);  
                try {
                    usuarioDAO.AgregarUsuario(usuario);
                    boolean insertado = usuarioDAO.AgregarUsuario(usuario);
                    processRequest(request, response);
                } catch (Exception a) {
                    System.err.print("No se puede registrar la informacion desde el Servlet. Excepcion: "+a);
                }
                request.getRequestDispatcher("views/usuario/lista.jsp").forward(request, response);
                break;
            case "actualizar":
                roles.setIdRol(Integer.parseInt(request.getParameter("ModificarIdRol")));
                tipoDocumento.setIdTipoDocumento(Integer.parseInt(request.getParameter("ModificarTipoDoc")));
                usuario.setNumeroIdentificacion(Double.parseDouble(request.getParameter("ModificarNumeroIdentificacion")));
                usuario.setNombre(request.getParameter("ModificarNombre"));
                usuario.setApellido(request.getParameter("ModificarApellido"));
                usuario.setTelefono(Double.parseDouble(request.getParameter("ModificarTelefono")));
                usuario.setCorreo(request.getParameter("ModificarCorreo"));
                usuario.setDireccion(request.getParameter("ModificarDireccion"));
                usuario.setClaveUsuario(request.getParameter("ModificarClaveUsuario"));
                
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(roles);
                try {
                    usuarioDAO.ModificarUsuario(usuario);
                    boolean modificado = usuarioDAO.ModificarUsuario(usuario);
                    processRequest(request, response);
                } catch (Exception m) {
                    System.err.print("No se puede modificar la informacion del usuario actual desde el Servlet. Excepcion: "+m);
                }
                request.getRequestDispatcher("views/usuario/lista.jsp").forward(request, response);
                break;
            case "eliminar":
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                try{
                    usuarioDAO.EliminarUsuario(usuario.getIdUsuario());
                    boolean eliminado = usuarioDAO.EliminarUsuario(usuario.getIdUsuario());
                    processRequest(request, response);
                }catch(Exception e){
                    request.setAttribute("error", "No se puede eliminar el usuario actual de la base de datos. Excepcion: "+e);
                }
                break;
            case "guardar_rol":
                
                break;
            case "guardar_tipo":
                break;
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
