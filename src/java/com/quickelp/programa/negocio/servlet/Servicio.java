/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.ServicioDAO;
import com.quickelp.programa.persistencia.dao.EquipoDAO;
import com.quickelp.programa.persistencia.vo.EquipoVO;
import com.quickelp.programa.persistencia.vo.ServicioVO;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import com.quickelp.programa.persistencia.dao.UsuarioDAO;
import com.quickelp.programa.persistencia.vo.Usuario_TipoServicioVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darwin Garcia
 */
@WebServlet(name = "Servicio", urlPatterns = {"/Servicio"})
public class Servicio extends HttpServlet {

    private String listarActivosAdmin = "views/administrador/servicios/listarActivos.jsp";
    private String listarInactivosAdmin = "views/administrador/servicios/listarInactivos.jsp";
    private String listarCientesxAdmin = "views/administrador/servicios/listarClientes.jsp";
    private String agregarAdmin = "views/administrador/servicios/solicitud.jsp";
    private String editarAdmin = "views/administrador/servicios/actualizar.jsp";
    private String cancelarAdmin = "views/administrador/servicios/cancelar.jsp";

    private String agregarCliente = "views/cliente/servicios/solicitud.jsp";
    private String agregarClienteFecha = "views/cliente/servicios/solicitudFecha.jsp";
    private String listarCliente = "views/cliente/servicios/listaCliente.jsp";
    private String listarClientePend = "views/cliente/servicios/preLista.jsp";
    private String cancelarCliente = "views/cliente/servicios/cancelar.jsp";

    private String verServicioTecnico = "views/funcionario/servicios/verServicio.jsp";
    private String listarTecnico = "views/funcionario/servicios/listaTecnico.jsp";
    private String listarDisponibles = "views/funcionario/servicios/listaPend.jsp";
    private String asigarServTec = "views/administrador/servicios/asignarServicios.jsp";

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
        ServicioVO serVO = new ServicioVO();
        ServicioDAO servDAO = new ServicioDAO();
        UsuarioVO usVO = new UsuarioVO();
        UsuarioDAO usDAO = new UsuarioDAO();
        EquipoVO eqVO = new EquipoVO();
        EquipoDAO eqDAO = new EquipoDAO();
        String action = request.getParameter("accion");
        int idc = 0, idt = 0, idr = 0, ids = 0;
        switch (action) {
            //Listar Servicios desde el Administrador
            case "listar_activos":
                request.getRequestDispatcher(listarActivosAdmin).forward(request, response);
                break;
            case "listar_inactivos":
                request.getRequestDispatcher(listarInactivosAdmin).forward(request, response);
                break;
            case "listar_clienteadm":
                request.getRequestDispatcher(listarCientesxAdmin).forward(request, response);
                break;
            //*Listar Servicios desde el cliente
            case "prelista_cli": //Consultar la lista de Servicios que no estan asignados a un tecnico
                idc = Integer.parseInt(request.getParameter("id"));
                try {
                    List<ServicioVO> listaCli = servDAO.buscarServiciosPendientes(idc);
                    request.setAttribute("servicio", listaCli);

                    usVO = usDAO.consultaUsuario(idc);
                    request.setAttribute("usuario", usVO);

                } catch (Exception e) {
                    System.err.print("No se puede enviar la informacion con la ID de Usuario:" + idc + " en el perfil logueado. Acceso Cancelado");
                }
                // En este caso me debe dirigir a la consulta de equipos con mi ID Usuario correspondiente.
                request.getRequestDispatcher(listarClientePend).forward(request, response);
                break;
            case "listar_cliente":
                idc = Integer.parseInt(request.getParameter("id"));
                try {
                    List<ServicioVO> listaCli = servDAO.buscarServiciosxIdCliente(idc);
                    request.setAttribute("servicio", listaCli);

                    usVO = usDAO.consultaUsuario(idc);
                    request.setAttribute("usuario", usVO);

                    request.getRequestDispatcher(listarCliente).forward(request, response);
                } catch (Exception e) {
                    System.err.print("No se puede enviar la informacion con la ID de Usuario:" + idc + " en el perfil logueado. Acceso Cancelado");
                }
                break;
            //*Listar Servicios desde el tecnico
            case "listar_tecnico":
                idt = Integer.parseInt(request.getParameter("id"));
                try {
                    List<ServicioVO> listaCli = servDAO.buscarServiciosxIdTecnico(idt);
                    request.setAttribute("servicio", listaCli);
                    usVO = usDAO.consultaUsuario(idt);
                    request.setAttribute("usuario", usVO);
                    request.getRequestDispatcher(listarTecnico).forward(request, response);
                } catch (Exception e) {
                    System.err.print("No se puede enviar la informacion con la ID de Usuario:" + idt + " en el perfil logueado. Acceso Cancelado");
                }
                break;
            case "listar_disponibles":
                idt = Integer.parseInt(request.getParameter("id"));
                try {
                    List<ServicioVO> listaCli = servDAO.buscarServiciosxIdTecnico(idt);
                    request.setAttribute("servicio", listaCli);
                    usVO = usDAO.consultaUsuario(idt);
                    request.setAttribute("usuario", usVO);
                    request.getRequestDispatcher(listarDisponibles).forward(request, response);
                } catch (Exception e) {
                    System.err.print("No se puede enviar la informacion con la ID de Usuario:" + idt + " en el perfil logueado. Acceso Cancelado");
                }
                break;
            case "ver_servicio":
                ids = Integer.parseInt(request.getParameter("ids"));
                try {
                    serVO = servDAO.consultarServicio(ids);
                    request.setAttribute("servicio", serVO);
                    request.getRequestDispatcher(verServicioTecnico).forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede consultar el ticket de Servicio seleccionado.");
                    System.err.print("No se puede consultar el ticket de Servicio seleccionado desde el Servlet. Excepcion: " + e);
                }
                break;
            // Asignar el servicio a un tecnico
            case "asignar_tecnico":

                request.getRequestDispatcher(asigarServTec).forward(request, response);
                break;
            //* Solicitudes de servicio desde el rol Administrador
            case "solicita_admin":
                request.getRequestDispatcher(agregarAdmin).forward(request, response);
                break;
            //* Solicitudes de servicio desde el rol Cliente
            case "solicita_cliente":// Solicitar un servicio con fecha actual incluida. Envio la ID Usuario para mostrar los equipos del cliente desde el action.
                idc = Integer.parseInt(request.getParameter("id"));
                usVO = usDAO.consultaUsuario(idc);
                request.setAttribute("usuario", usVO);
                request.getRequestDispatcher(agregarCliente).forward(request, response);
                break;
            case "solicitar_fecha":
                idc = Integer.parseInt(request.getParameter("id"));
                usVO = usDAO.consultaUsuario(idc);
                request.setAttribute("usuario", usVO);
                request.getRequestDispatcher(agregarClienteFecha).forward(request, response);
                break;

            case "scancela_cliente":// Solicitar un servicio con fecha actual incluida. Envio la ID Usuario para mostrar los equipos del cliente desde el action.
                idc = Integer.parseInt(request.getParameter("id"));
                try {
                    List<ServicioVO> listaServCli = servDAO.buscarServiciosxIdCliente(idc);
                    request.setAttribute("servicio", listaServCli);
                    
                    usVO = usDAO.consultaUsuario(idc);
                    request.setAttribute("usuario", usVO);
                    request.getRequestDispatcher(cancelarCliente).forward(request, response);
                } catch (Exception e) {

                }

                break;
            //* Cliente cancela el Servicio
            case "cancelar_cliente":
                idc = Integer.parseInt(request.getParameter("id"));
                idr = Integer.parseInt(request.getParameter("idr"));

                try {
                    serVO.setIdServicio(idr);//ID Servicio
                    //********Metodo abstracto de Eliminar que captura la Id.Equipo
                    boolean cancelado = servDAO.CancelarServicio(idr);
                    //
                    if (cancelado == true) {
                        usVO = usDAO.consultaUsuario(idc);
                        request.setAttribute("usuario", usVO);

                        request.setAttribute("exito", "Servicio cancelado.");//Mensaje Posterior
                    } else {
                        request.setAttribute("error", "No se puede cancelar este ticket: " + idr + ". El servicio esta finalizado.");
                    }

                } catch (Exception e) {
                    request.setAttribute("error", "No se puede enviar la cancelacion del sevicio seleccionado en la base de datos. Excepcion: " + e);
                    System.err.print("No se puede cancelar el servicio con ticket:" + idr + " seleccionado desde el Servlet. Excepcion: " + e);
                }

                response.sendRedirect("Servicio?accion=prelista_cli&id=" + idc);
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
        //VO de las tablas de Quickelp
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
        int myid = 0;
        switch (url) {
            case "asignar_eltecnico":

                servicio.setIdServicio(Integer.parseInt(request.getParameter("AsignaServicio")));
                tecnico.setIdUsuTipo(Integer.parseInt(request.getParameter("RegistrarTecnico")));

                servicio.setIdTipoSer(tecnico);

                try {
                    boolean asignado = servicioDAO.AsignarServiciosTecnico(servicio);
                    if (asignado == true) {
                        request.setAttribute("exito", "Servicio asignado exitosamente al tecnico seleccionado.");
                        request.getRequestDispatcher(listarActivosAdmin).forward(request, response);
                    } else {
                        request.setAttribute("error", "No se puede asignar el servicio al tecnico seleccionado.");
                        request.getRequestDispatcher(listarCientesxAdmin).forward(request, response);
                    }
                } catch (Exception e) {
                    System.err.print("No se puede asignar el servicio al tecnico usuario actual desde el Servlet. Excepcion: " + e);
                }
                request.getRequestDispatcher(listarActivosAdmin).forward(request, response);
                break;
            case "registrar_cli":
                //Captua de datos desde la Vista Solicitud del Cliente
                myid = Integer.parseInt(request.getParameter("RegistrarId"));
                servicio.setDescripcion(request.getParameter("RegistrarDescripcion"));
                //usuario.setIdUsuario(Integer.parseInt(request.getParameter("RegistrarId")));
                usuario.setIdUsuario(myid);
                tipoServicio.setIdTipoSer(Integer.parseInt(request.getParameter("RegistrarTipoServ")));
                equipo.setIdRegistro(Integer.parseInt(request.getParameter("RegistrarEquipo")));

                servicio.setIdEquipo(equipo);
                servicio.setIdUsuario(usuario);
                servicio.setIdTipoDelServ(tipoServicio);
                try {
                    boolean insertado = servicioDAO.AgregarServicios(servicio);
                    request.setAttribute("exito", "Servicio registrado. Seleccione 'Consultar Pendientes'");
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede registrar el servicio solicitado. Excepcion: " + e);
                    System.err.print("No se puede registrar el servicio solicitado desde el Servlet. Excepcion: " + e);
                }
                //
                response.sendRedirect("Servicio?accion=prelista_cli&id=" + myid);
                //request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);
                break;
            case "registrar_fecha":
                //Captua de datos desde la Vista Solicitud del Cliente
                myid = Integer.parseInt(request.getParameter("RegistrarId"));
                servicio.setFechaSolicitud(Date.valueOf(request.getParameter("RegistrarFecha")));
                servicio.setDescripcion(request.getParameter("RegistrarDescripcion"));
                //usuario.setIdUsuario(Integer.parseInt(request.getParameter("RegistrarId")));
                tipoServicio.setIdTipoSer(Integer.parseInt(request.getParameter("RegistrarTipoServ")));
                equipo.setIdRegistro(Integer.parseInt(request.getParameter("RegistrarEquipo")));

                servicio.setIdEquipo(equipo);
                servicio.setIdUsuario(usuario);
                servicio.setIdTipoDelServ(tipoServicio);
                try {
                    boolean insertado = servicioDAO.AgregarServicios(servicio);
                    request.setAttribute("exito", "Servicio registrado exitosamente.");
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede registrar el servicio solicitado. Excepcion: " + e);
                    System.err.print("No se puede registrar el servicio solicitado desde el Servlet. Excepcion: " + e);
                }
                response.sendRedirect("Servicio?accion=prelista_cli&id=" + myid);
                //request.getRequestDispatcher(listarCliente).forward(request, response);
                break;
            case "registrar_admin":
                //int idAdm= Integer.parseInt(request.getParameter("RegistrarId"));
                servicio.setFechaSolicitud(Date.valueOf(request.getParameter("RegistrarFecha")));
                //insertar tipo de Servicio
                tipoServicio.setIdTipoSer(Integer.parseInt(request.getParameter("RegistrarTipoServ")));
                //insertar el usuario
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("RegistrarUsuario")));
                //insertar el equipo
                equipo.setIdRegistro(Integer.parseInt(request.getParameter("RegistrarEquipo")));
                //Registrar la descripcion
                servicio.setDescripcion(request.getParameter("RegistrarDescripcion"));
                //Insertar el tecnico. Revisar(el dato no se esta recogiendo de la vista. Es un select)
                tecnico.setIdUsuTipo(Integer.parseInt(request.getParameter("RegistrarTecnico")));

                servicio.setIdTipoDelServ(tipoServicio);
                servicio.setIdUsuario(usuario);
                servicio.setIdEquipo(equipo);
                servicio.setIdTipoSer(tecnico);
                try {
                    boolean insertado = servicioDAO.AgregarServiciosAdmin(servicio);
                    request.setAttribute("exito", "Servicio registrado correctamente.");
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede registrar el servicio solicitado. Excepcion: " + e);
                    System.err.print("No se puede registrar el servicio solicitado desde el Servlet. Excepcion: " + e);
                }
                request.getRequestDispatcher(listarActivosAdmin).forward(request, response);
                break;
            case "cancela_cli": 
                myid = Integer.parseInt(request.getParameter("RegistrarId"));
                int idr = Integer.parseInt(request.getParameter("SeleccionarServicio"));
                servicio.setIdServicio(idr);
                try{
                    boolean cancelado=servicioDAO.CancelarServicio(idr);
                    
                    if(cancelado==true){
                        request.setAttribute("exito", "Servicio cancelado correctamente. ");
                    }
                    else{
                        request.setAttribute("error", "No se puede cancelar el servicio seleccionado.");
                    }
                }catch(Exception e){
                    request.setAttribute("error", "No se puede cancelar el servicio solicitado. Excepcion: " + e);
                    System.err.println("No se puede cancelar el servicio desde el Servlet.");
                } 
                response.sendRedirect("Servicio?accion=prelista_cli&id=" + myid);
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
