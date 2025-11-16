/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.EquipoDAO;
import com.quickelp.programa.persistencia.dao.MarcaDAO;
import com.quickelp.programa.persistencia.dao.UsuarioDAO;
import com.quickelp.programa.persistencia.vo.EquipoVO;
import com.quickelp.programa.persistencia.vo.MarcaVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Equipo", urlPatterns = {"/Equipo"})
public class Equipo extends HttpServlet {

    //Vistas de Equipo(Administrador)
    String listarAdmin = "views/administrador/equipos/lista.jsp";
    String agregarAdmin = "views/administrador/equipos/registro.jsp";
    String editarAdmin = "views/administrador/equipos/modificar.jsp";
    //Vistas de Marca Equipos(Solo Administrador)
//    String listarMarca = "views/administrador/equipos/marca/lista.jsp";
//    String agregarMarca = "views/administrador/equipos/marca/registro.jsp";
//    String editarMarca = "views/administrador/equipos/marca/editar.jsp";
    //Vistas de Equipo(Cliente)
    String listarCliente = "views/cliente/equipos/lista.jsp";
    String agregarCliente = "views/cliente/equipos/registro.jsp";

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

        EquipoVO eqVO = new EquipoVO();
        EquipoDAO eqDAO = new EquipoDAO();

        MarcaVO marVO = new MarcaVO();
        MarcaDAO marDAO = new MarcaDAO();

        UsuarioVO usVO = new UsuarioVO();
        UsuarioDAO usDAO = new UsuarioDAO();
        int idc = 0;
        int idr = 0;
        String action = request.getParameter("accion");
        switch (action) {
            case "listar_equipos":
                request.getRequestDispatcher(listarAdmin).forward(request, response);
                break;
            case "listar_cliente":// No funciona
                idc = Integer.parseInt(request.getParameter("id"));
                try {
                    List<EquipoVO> listadoCli = eqDAO.listadoxCliente(idc);
                    request.setAttribute("equipo", listadoCli);

                    usVO = usDAO.consultaUsuario(idc);
                    request.setAttribute("usuario", usVO);

                } catch (Exception e) {
                    System.err.print("No se puede enviar la informacion con la ID de Usuario:" + idc + " en el perfil logueado. Acceso Cancelado");
                }
                request.getRequestDispatcher(listarCliente).forward(request, response);
                break;
            case "registrar_equipo":
                request.getRequestDispatcher(agregarAdmin).forward(request, response);
                break;
            case "registrar_cliente":
                request.getRequestDispatcher(agregarCliente).forward(request, response);
                break;
            case "registrar_clienteserv":
                request.getRequestDispatcher("views/cliente/servicios/registroEquipo.jsp").forward(request, response);
                break;
            case "eliminar_equipo":
                idr = Integer.parseInt(request.getParameter("id"));
                try {
                    eqVO.setIdRegistro(idr);//ID del Equipo
                    boolean eliminado = eqDAO.EliminarEquipo(idr);//Metodo abstracto de Eliminar que captura la Id.Equipo
                    if (eliminado == true) {
                        request.setAttribute("exito", "Equipo eliminado.");//Mensaje Posterior
                        request.getRequestDispatcher(listarAdmin).forward(request, response);//Redirecciona a la lista de equipos registrados
                    } else {
                        request.setAttribute("error", "No se puede borrar el equipo: " + idr);
                        request.getRequestDispatcher(listarAdmin).forward(request, response);//Redirecciona a la lista de equipos registrados
                    }

                    processRequest(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede borrar el usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede borrar el equipo con la id:" + idr + " seleccionado desde el Servlet. Excepcion: " + e);
                }
                request.getRequestDispatcher(listarAdmin).forward(request, response);
                break;
            case "eliminar_cliente":
                idc = Integer.parseInt(request.getParameter("id"));
                idr = Integer.parseInt(request.getParameter("idr"));

                try {
                    eqVO.setIdRegistro(idr);// ID Equipo                    
                    boolean eliminado = eqDAO.EliminarEquipo(idr);//Metodo abstracto de Eliminar que captura la Id.Equipo
                    //
                    if (eliminado == true) {
                        //idUsuCli
                        
                        usVO = usDAO.consultaUsuario(idc);
                        request.setAttribute("usuario", usVO);

                        request.setAttribute("exito", "Equipo eliminado.");//Mensaje Posterior
                    } else {
                        request.setAttribute("error", "No se puede borrar el equipo: " + idr);
                    }

                } catch (Exception e) {
                    request.setAttribute("error", "No se puede borrar el usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede borrar el equipo con la id:" + idr + " seleccionado desde el Servlet. Excepcion: " + e);
                }

                response.sendRedirect("Equipo?accion=listar_cliente&id=" + idc);
                break;
            //--------------------Marca de Equipos-----------------------------
//            case "listar_marca":
//                request.getRequestDispatcher(listarMarca).forward(request, response);
//                break;
//            case "registrar_marca":
//                request.getRequestDispatcher(agregarMarca).forward(request, response);
//                break;
//            
//            case "eliminar_marca":
//                int idM = Integer.parseInt(request.getParameter("id"));
//                try{
//                    marVO.setIdMarca(idM);
//                    boolean eliminado = marDAO.EliminarMarca(idM);
//                    request.setAttribute("exito", "Equipo eliminado.");//Mensaje Posterior
//                    request.getRequestDispatcher(listarMarca).forward(request, response);//Redirecciona a la lista de equipos registrados
//                    processRequest(request, response);
//                }catch(Exception e){
//                    request.setAttribute("error", "No se puede eliminar la Marca de equipo seleccionada de la base de datos. Excepcion: " + e);
//                    System.err.print("No se puede eliminar la Marca de equipo seleccionada. Excepcion: " + e);
//                }
//                break;
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
        //Marca
        MarcaVO marcas = new MarcaVO();
        MarcaDAO marcaDAO = new MarcaDAO();
        //Usuario
        UsuarioVO usuario = new UsuarioVO();
        //Equipo
        EquipoVO equipo = new EquipoVO();
        EquipoDAO equipoDAO = new EquipoDAO();
        //Llamado desde la vista hacia el controlador
        int idu = 0;
        String url = request.getParameter("Peticion");//Parametro que recibe el input hidden
        switch (url) {
            //-------------------------------Acciones o Peticiones Posteriores de Registrar Equipo------------------------------------
            case "registrar":
                equipo.setTipoEquipo(request.getParameter("RegistrarTipoEquipo"));
                marcas.setIdMarca(Integer.parseInt(request.getParameter("RegistrarMarca")));
                equipo.setModelo(request.getParameter("RegistrarModelo"));
                equipo.setNumSerial(request.getParameter("RegistrarNumeroSerie"));
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("RegistrarId")));
                equipo.setIdUsuario(usuario);
                equipo.setIdmarca(marcas);
                try {
                    //equipoDAO.AgregarEquipo(equipo);
                    boolean insertado = equipoDAO.AgregarEquipo(equipo);
                    request.setAttribute("exito", "Equipo registrado correctamente.");
                    //processRequest(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede registrar el equipo en la base de datos. Excepcion: " + e);
                    System.err.print("No se puede registrar la informacion desde el Servlet. Excepcion: " + e);
                }
                request.getRequestDispatcher(listarAdmin).forward(request, response);
                break;
            case "registrar_cli":
                idu = Integer.parseInt(request.getParameter("RegistrarId"));
                equipo.setTipoEquipo(request.getParameter("RegistrarTipoEquipo"));
                marcas.setIdMarca(Integer.parseInt(request.getParameter("RegistrarMarca")));
                equipo.setModelo(request.getParameter("RegistrarModelo"));
                equipo.setNumSerial(request.getParameter("RegistrarNumeroSerie"));
                //usuario.setIdUsuario(Integer.parseInt(request.getParameter("RegistrarId")));
                usuario.setIdUsuario(idu);
                equipo.setIdUsuario(usuario);//Llave foranea ID Usuario en Tabla Equipo
                equipo.setIdmarca(marcas);//Llave foreanea ID Marca en Tabla Equipos
                try {
                    boolean insertado = equipoDAO.AgregarEquipo(equipo);
                    request.setAttribute("exito", "Equipo registrado. Seleccione 'Consultar' en el menu Equipos");
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede registrar el equipo en la base de datos. Excepcion: " + e);
                    System.err.print("No se puede registrar la informacion desde el Servlet. Excepcion: " + e);
                }
                // En este caso me debe dirigir a la consulta de equipos con mi ID Usuario correspondiente.
                response.sendRedirect("Equipo?accion=listar_cliente&id=" + idu);
                //request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);
                break;
            case "registrar_servicli":
                idu = Integer.parseInt(request.getParameter("RegistrarId"));
                equipo.setTipoEquipo(request.getParameter("RegistrarTipoEquipo"));
                marcas.setIdMarca(Integer.parseInt(request.getParameter("RegistrarMarca")));
                equipo.setModelo(request.getParameter("RegistrarModelo"));
                equipo.setNumSerial(request.getParameter("RegistrarNumeroSerie"));

                usuario.setIdUsuario(idu);
                equipo.setIdUsuario(usuario);
                equipo.setIdmarca(marcas);
                try {
                    //equipoDAO.AgregarEquipo(equipo);
                    boolean insertado = equipoDAO.AgregarEquipo(equipo);
                    request.setAttribute("exito", "Equipo registrado correctamente.");
                    //processRequest(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede registrar el equipo en la base de datos. Excepcion: " + e);
                    System.err.print("No se puede registrar la informacion desde el Servlet. Excepcion: " + e);
                }
                // Por favor arreglar esto para que se dirija de nuevo a la solicitud del servicio con la ID Usuario que asegura su informacion como sus equipos registrados
                response.sendRedirect("Servicio?accion=solicita_cliente&id=" + idu);
                //request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);
                break;
            //-------------Acciones o Peticiones posteriores de Marca Equipo---------------------------------------------------
//            case "guardar_marca":
//                marcas.setNombreMarca(request.getParameter("nombreMarca"));
//                try{
//                    boolean insertado = marcaDAO.AgregarMarca(marcas);
//                    request.setAttribute("exito", "Marca de equipo registrado correctamente.");
//                }catch(Exception e){
//                    request.setAttribute("error", "No se puede registrar la marca de Equipo en la base de datos. Excepcion: " + e);
//                    System.err.print("No se puede registrar la marca de Equipo desde el Servlet. Excepcion: " + e);
//                }
//                request.getRequestDispatcher(listarMarca).forward(request, response);
//                break;           
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
