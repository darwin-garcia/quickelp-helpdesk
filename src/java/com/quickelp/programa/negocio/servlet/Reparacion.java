/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.ReparacionDAO;
import com.quickelp.programa.persistencia.dao.ServicioDAO;
import com.quickelp.programa.persistencia.dao.UsuarioDAO;
import com.quickelp.programa.persistencia.vo.EquipoVO;
import com.quickelp.programa.persistencia.vo.ReparacionVO;
import com.quickelp.programa.persistencia.vo.ServicioVO;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import com.quickelp.programa.persistencia.vo.Usuario_TipoServicioVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darwin Garcia
 */
@WebServlet(name = "Reparacion", urlPatterns = {"/Reparacion"})
public class Reparacion extends HttpServlet {
    
    String listaRepTecnico="views/funcionario/reparaciones/listado.jsp";
    String registrarRepTecnico="views/funcionario/reparaciones/registro.jsp";
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
        response.setContentType("text/html;charset=UTF-8");

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

        ReparacionVO reVO = new ReparacionVO();
        ReparacionDAO reDAO = new ReparacionDAO();
        UsuarioVO usVO = new UsuarioVO();
        UsuarioDAO usDAO = new UsuarioDAO();
        
        String action = request.getParameter("accion");
        int idt = 0, idr = 0;
        switch (action) {
            //Listar los diagnosticos desde el Rol Tecnico
            case "lista_tecnico":
                idt = Integer.parseInt(request.getParameter("id"));
                try {
                    List<ReparacionVO> listaTec = reDAO.consultarReparaciones(idt);
                    request.setAttribute("diagnostico", listaTec);
                    
                    usVO = usDAO.consultaUsuario(idt);
                    request.setAttribute("usuario", usVO);
                    
                    request.getRequestDispatcher(listaRepTecnico).forward(request, response);
                } catch (Exception e) {
                    System.err.print("No se puede enviar la informacion con la ID del usuario tecnico:" + idt + " para acceder a la lista de Diagnosticos. Acceso Cancelado. "+e);
                }
                break;
            //Generar un informe de diagnostico en el Rol Tecnico
            case "registrar_tecnico":
                idt = Integer.parseInt(request.getParameter("id"));
                usVO = usDAO.consultaUsuario(idt);
                request.setAttribute("usuario", usVO);
                request.getRequestDispatcher(registrarRepTecnico).forward(request, response);
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
        processRequest(request, response);

        ReparacionVO reparacion = new ReparacionVO();
        ReparacionDAO reparacionDAO = new ReparacionDAO();
        ServicioVO servicio = new ServicioVO();
        UsuarioVO usuario = new UsuarioVO();
        EquipoVO equipo = new EquipoVO();
        TipoServicioVO tipoServicio = new TipoServicioVO();
        //VO de los usuarios tecnicos y especialidad
        Usuario_TipoServicioVO tecnico = new Usuario_TipoServicioVO();
        //DAO de las tablas de Quickelp
        ServicioDAO servicioDAO = new ServicioDAO();
        //Variable que recibe la direccion o ruta
        String url = request.getParameter("Peticion");
        switch (url) {
            case "guardar_infotecnico":
                servicio.setIdServicio(Integer.parseInt(request.getParameter("RegistrarServicio")));
                reparacion.setFechaEntrega(Date.valueOf(request.getParameter("RegistrarFechaEntrega")));
                reparacion.setSolucion(request.getParameter("RegistrarDescripcion"));
                reparacion.setIdServicio(servicio);
                try{
                    boolean insertado = reparacionDAO.AgregarReparaciones(reparacion);
                    request.setAttribute("exito", "Informe de reparacion registrado correctamente.");
                }catch(Exception e){
                    request.setAttribute("error", "No se puede registrar la reparacion solicitada. Excepcion: " + e);
                }
                request.getRequestDispatcher("views/funcionario/home.jsp").forward(request, response);
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
