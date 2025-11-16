/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.RolDAO;
import com.quickelp.programa.persistencia.dao.TipoDocumentoDAO;
import com.quickelp.programa.persistencia.dao.UsuarioDAO;
import com.quickelp.programa.persistencia.dao.UsuarioTipoServicioDAO;
import com.quickelp.programa.persistencia.vo.RolVO;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import com.quickelp.programa.persistencia.vo.Usuario_TipoServicioVO;
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

    //Links de Usuario (Solo Administrador)
    String listar = "views/administrador/usuario/lista.jsp";
    String agregar = "views/administrador/usuario/registro.jsp";
    String editar = "views/administrador/usuario/actualizar.jsp";
    //Links de Perfil de Usuario (Tecnico)
    String editarTecnico = "views/funcionario/actualizarFunc.jsp";
    String verTecnico = "views/funcionario/perfil.jsp";
    //Links de Perfil de Usuario (Cliente)
    String editarCliente = "views/cliente/actualizarCliente.jsp";
    String verCliente = "views/cliente/perfil.jsp";
    //Links de Invitado para registro
    String agregarTecnico = "views/guest/tecnico.jsp";
    String agregarCliente = "views/guest/registro.jsp";
    String restablecerClave = "views/guest/restablecer.jsp";
    String restablecerClaveDesdeAdmin = "views/administrador/usuario/restablecer.jsp";
    //Links de Roles (Solo Administrador)
    String listarRol = "views/administrador/rol/lista.jsp";
    String agregarRol = "views/administrador/rol/registro.jsp";
    String editarRol = "views/administrador/rol/editar.jsp";
    //Links de Tipo de Documento (solo Administrador)
    String listarTipoDoc = "views/administrador/tipoDocumento/lista.jsp";
    String agregarTipoDoc = "views/administrador/tipoDocumento/registro.jsp";
    String editarTipoDoc = "views/administrador/tipoDocumento/editar.jsp";
    //Links de Busqueda (Solo Administrador)
    String buscarModificar = "views/administrador/usuario/buscarModificar.jsp";
    String buscarEliminar = "views/administrador/usuario/buscarEliminar.jsp";
    String buscarCedula = "views/administrador/usuario/buscarCedula.jsp";

    String agregarEspecialidad = "views/administrador/usuario/agregarEsp.jsp";

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
        UsuarioVO usVO = new UsuarioVO();
        UsuarioDAO usDAO = new UsuarioDAO();

        RolVO rolVO = new RolVO();
        RolDAO rolDAO = new RolDAO();
        TipoDocumentoVO tipoVO = new TipoDocumentoVO();
        TipoDocumentoDAO tipoDAO = new TipoDocumentoDAO();

        String action = request.getParameter("accion");//Cambiar por RutaAdmin o Administrador
        switch (action) {
            //Menu del administrador para ver la lista de usuarios registrados
            case "listar_usuarios":
                request.getRequestDispatcher(listar).forward(request, response);
                break;
            //Para registrar una especialidad del Tecnico
            case "agregar_esp":
                request.getRequestDispatcher(agregarEspecialidad).forward(request, response);
                break;
            //Para registrar un usuario nuevo
            case "nuevo_registro":
                request.getRequestDispatcher(agregarCliente).forward(request, response);
                break;
            //Para registrar un nuevo tecnico
            case "nuevo_tecnico":
                request.getRequestDispatcher(agregarTecnico).forward(request, response);
                break;
            //Menu del administrador para registrar un usuario nuevo de cualquier rol.
            case "registrar_usuarios":
                request.getRequestDispatcher(agregar).forward(request, response);
                break;
            //Menu para Buscar un usuario a modificar por cedula
            case "buscar_editar":
                request.getRequestDispatcher(buscarModificar).forward(request, response);
                break;
            //Menu para Buscar un usuario a eliminar por cedula
            case "buscar_eliminar":
                request.getRequestDispatcher(buscarEliminar).forward(request, response);
                break;
            //Menu del administrador para editar la informacion del usuario
            case "editar_usuarios":
                try {
                    //Llamo la consulta antes de pasar a modificar
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    //Obtengo el parametro de la vista y lo paso al UsuarioVO
                    request.setAttribute("usuario", usVO);//Pertenece al usu de la vista actualizar.jsp. Debo obtener la informacion en la vista.
                    //Me dirije a Editar
                    request.getRequestDispatcher(editar).forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede editar la informacion del usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede editar la informacion de este usuario. Excepcion: " + e);
                }
                break;

            case "editar_infotecnico"://Modificar la Informacion del Tecnico en su Rol
                try {
                    //Llamo la consulta antes de pasar a modificar
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    //Obtengo el parametro de la vista y lo paso al UsuarioVO
                    request.setAttribute("usuario", usVO);//Pertenece al usu de la vista actualizar.jsp. Debo obtener la informacion en la vista.
                    //Me dirije a Editar
                    request.getRequestDispatcher(editarTecnico).forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede editar la informacion del usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede editar la informacion de este usuario. Excepcion: " + e);
                }
                break;
            case "ver_infotecnico"://Mostrar la Informacion del Tecnico en su Rol
                try {
                    //Llamo la consulta antes de pasar a modificar
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    //Obtengo el parametro de la vista y lo paso al UsuarioVO
                    request.setAttribute("usuario", usVO);//Pertenece al usu de la vista actualizar.jsp. Debo obtener la informacion en la vista.
                    //Me dirije a Editar
                    request.getRequestDispatcher(verTecnico).forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede editar la informacion del usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede editar la informacion de este usuario. Excepcion: " + e);
                }
                break;
            case "editar_infocliente"://Modificar la Informacion del Cliente en su Rol
                try {
                    //Llamo la consulta antes de pasar a modificar
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    //Obtengo el parametro de la vista y lo paso al UsuarioVO
                    request.setAttribute("usuario", usVO);//Pertenece al usu de la vista actualizar.jsp. Debo obtener la informacion en la vista.
                    //Me dirije a Editar
                    request.getRequestDispatcher(editarCliente).forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede editar la informacion del usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede editar la informacion de este usuario. Excepcion: " + e);
                }
                break;

            case "ver_infocliente"://Mostrar la Informacion del Cliente en su Rol
                try {
                    //Llamo la consulta antes de pasar a modificar
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    //Obtengo el parametro de la vista y lo paso al UsuarioVO
                    request.setAttribute("usuario", usVO);//Pertenece al usu de la vista actualizar.jsp. Debo obtener la informacion en la vista.
                    //Me dirije a Editar
                    request.getRequestDispatcher(verCliente).forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede editar la informacion del usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede editar la informacion de este usuario. Excepcion: " + e);
                }
                break;
            //Eliminar el usuario creado
            case "eliminar_usuarios":
                int idr = Integer.parseInt(request.getParameter("id"));
                try {
                    usVO.setIdUsuario(idr);//Recibo la ID.Usuario desde el UsuarioVO
                    boolean eliminado = usDAO.EliminarUsuario(idr);

                    if (eliminado == true) {
                        request.setAttribute("exito", "Usuario eliminado.");//Mensaje Posterior
                        request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                    } else {
                        request.setAttribute("error", "No se puede eliminar el equipo: " + idr);
                        request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                    }
                    processRequest(request, response);
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede borrar el usuario seleccionado de la base de datos. Excepcion: " + e);
                    System.err.print("No se puede borrar el usuario seleccionado desde el Servlet. Excepcion: " + e);
                }
                break;
            case "restablecer_clave":
                request.getRequestDispatcher(restablecerClave).forward(request, response);
                break;
            //Restablcer la clave de usuario desde el Menu del Administrador
            case "restablecer_usuarios":
                try {
                    usVO = usDAO.consultaUsuario(Integer.parseInt(request.getParameter("id")));
                    request.setAttribute("usuario", usVO);
                    request.getRequestDispatcher(restablecerClaveDesdeAdmin).forward(request, response);
                } catch (Exception e) {
                    System.err.print("No se puede restablecer la contraseña de este usuario desde el Servlet. Excepcion: " + e);
                }
                break;
            case "buscar_cedula":
                request.getRequestDispatcher(buscarCedula).forward(request, response);
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
        RolDAO rolDAO = new RolDAO();
        TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
        TipoDocumentoDAO tipoDocDAO = new TipoDocumentoDAO();
        UsuarioVO usuario = new UsuarioVO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario_TipoServicioVO usTipoServ = new Usuario_TipoServicioVO();
        UsuarioTipoServicioDAO usTipoServDAO = new UsuarioTipoServicioDAO();
        
        TipoServicioVO tipoServ = new TipoServicioVO();
        
        
        //Se obtiene el dato o el valor del input hidden despues del Form Action=post
        String url = request.getParameter("Peticion");//Parametro que recibe quel input hidden
        //Navegacion POST
        switch (url) {
            /*----------------------------------Usuario-----------------------------------*/
            //Cuando el administrador desea registrar un usuario nuevo
            case "registrar":
                roles.setIdRol(Integer.parseInt(request.getParameter("RegistrarIdRol")));
                tipoDocumento.setIdTipoDocumento(Integer.parseInt(request.getParameter("RegistrarTipoDoc")));
                usuario.setNumeroIdentificacion(Long.parseLong(request.getParameter("RegistrarNumeroIdentificacion")));
                usuario.setNombre(request.getParameter("RegistrarNombre"));
                usuario.setApellido(request.getParameter("RegistrarApellido"));
                usuario.setTelefono(Long.parseLong(request.getParameter("RegistrarTelefono")));
                usuario.setCorreo(request.getParameter("RegistrarCorreo"));
                usuario.setDireccion(request.getParameter("RegistrarDireccion"));
                usuario.setClaveUsuario(request.getParameter("RegistrarClaveUsuario"));
                usuario.setConfirmarClaveUsuario(request.getParameter("ValidarClaveUsuario"));
                //Incluir la informacion de las llaves foraneas a la tabla Usuario
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(roles);
                try {
                    if (usuario.getClaveUsuario().equals(usuario.getConfirmarClaveUsuario())) {
                        //Metodo de Agregar Usuario 
                        boolean insertado = usuarioDAO.AgregarUsuario(usuario);//Variable que determina la insercion de datos de la tabla Usuario                    
                        if (insertado == true) {
                            request.setAttribute("exito", "Usuario registrado exitosamente.");//Mensaje Posterior
                            request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                        } else {
                            request.setAttribute("error", "No se admite informacion vacia o este usuario ya existe.");
                            request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                        }
                    } else {
                        if (!usuario.getClaveUsuario().equals(usuario.getConfirmarClaveUsuario())) {
                            request.setAttribute("error", "Las claves no coinciden. Compruebe de nuevo.");
                            request.getRequestDispatcher(listar).forward(request, response);
                            break;
                        }
                    }

                } catch (Exception a) {
                    request.setAttribute("error", "No se puede registrar el usuario en la base de datos. Excepcion: " + a);
                    System.err.print("No se puede registrar la informacion del nuevo tecnico desde el Servlet. Excepcion: " + a);
                }
                request.getRequestDispatcher(listar).forward(request, response);
                break;
            //Cuando se registra un cliente desde el Login.
            case "nuevo_cliente":
                roles.setIdRol(Integer.parseInt(request.getParameter("NuevoIdRol")));
                tipoDocumento.setIdTipoDocumento(Integer.parseInt(request.getParameter("NuevoTipoDoc")));
                usuario.setNumeroIdentificacion(Long.parseLong(request.getParameter("NuevoNumeroIdentificacion")));
                usuario.setNombre(request.getParameter("NuevoNombre"));
                usuario.setApellido(request.getParameter("NuevoApellido"));
                usuario.setTelefono(Long.parseLong(request.getParameter("NuevoTelefono")));
                usuario.setCorreo(request.getParameter("NuevoCorreo"));
                usuario.setDireccion(request.getParameter("NuevoDireccion"));
                usuario.setClaveUsuario(request.getParameter("NuevoClaveUsuario"));
                //Incluir la informacion de las llaves foraneas a la tabla Usuario
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(roles);
                try {
                    //Metodo de Agregar Usuario Cliente                    
                    boolean insertado = usuarioDAO.AgregarUsuario(usuario);//Variable que determina la insercion de datos de la tabla Usuario
                    //Mensaje posterior a mostrar
                    request.setAttribute("exito", "Bienvenido. Cliente registrado.");
                    processRequest(request, response);
                } catch (Exception a) {
                    System.err.print("No se puede registrar la informacion del nuevo cliente desde el Servlet. Excepcion: " + a);
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            //Cuando el administrador modifica la informacion del usuario registrado
            case "actualizar_usuario":
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                //roles.setIdRol(Integer.parseInt(request.getParameter("ModificarIdRol")));
                //tipoDocumento.setIdTipoDocumento(Integer.parseInt(request.getParameter("ModificarTipoDoc")));
                //usuario.setNumeroIdentificacion(Long.parseLong(request.getParameter("ModificarNumeroIdentificacion")));
                usuario.setNombre(request.getParameter("ModificarNombre"));
                usuario.setApellido(request.getParameter("ModificarApellido"));
                usuario.setTelefono(Long.parseLong(request.getParameter("ModificarTelefono")));
                usuario.setCorreo(request.getParameter("ModificarCorreo"));
                usuario.setDireccion(request.getParameter("ModificarDireccion"));
                //usuario.setTipoIdentificacion(tipoDocumento); 
                //usuario.setIdRol(roles);
                try {
                    boolean modificado = usuarioDAO.ModificarUsuario(usuario);
                    if (modificado == true) {
                        request.setAttribute("exito", "Usuario modificado exitosamente.");//Mensaje Posterior
                        request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                    } else {
                        request.setAttribute("error", "No se puede modificar el Usuario seleccionado.");
                        request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                    }
                } catch (Exception m) {
                    request.setAttribute("error", "No se puede modificar el usuario actual de la base de datos. Excepcion: " + m);
                    System.err.print("No se puede modificar la informacion del usuario actual desde el Servlet. Excepcion: " + m);
                }
                request.getRequestDispatcher(listar).forward(request, response);
                break;
            // Actualizar la infomacion del Cliente 
            case "actualizar_idusucli":
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                usuario.setTelefono(Long.parseLong(request.getParameter("ModificarTelefono")));
                usuario.setDireccion(request.getParameter("ModificarDireccion"));
                usuario.setClaveUsuario(request.getParameter("CompruebaClaveUsuario"));
                //usuario.setConfirmarClaveUsuario(request.getParameter("ValidarClaveUsuario"));
                try {
                    boolean modificado = usuarioDAO.ModificarUsuarioRegistrado(usuario);
                    if (modificado == true) {
                        //Incluir el cambio de contraseña

                        usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                        usuario.setClaveUsuario(request.getParameter("ModificarClaveUsuario"));
                        try {
                            boolean cambiado = usuarioDAO.borrarPassword(usuario);
                            if (cambiado == true) {
                                request.setAttribute("exito", "Contraseña Modificada.");//Mensaje Posterior
                                //request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                            } else {
                                request.setAttribute("error", "No se puede realizar la peticion de cambio contraseña ");
                                //request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                            }
                        } catch (Exception c) {
                            request.setAttribute("error", "No se puede cambiar la contraseña del usuario con la ID seleccionada en su rol " + c);
                            System.err.print("No se puede cambiar la contraseña del usuario con la ID seleccionada en su rol desde el Servlet. Excepcion: " + c);
                        }

                        request.setAttribute("exito", "Usuario modificado exitosamente.");//Mensaje Posterior
                        request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);//Redirecciona a la lista de equipos registrados
                    } else {
                        request.setAttribute("error", "No se puede modificar el Usuario seleccionado.");
                        request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);//Redirecciona a la lista de equipos registrados
                    }
                } catch (Exception m) {
                    request.setAttribute("error", "No se puede modificar el usuario actual de la base de datos. Excepcion: " + m);
                    System.err.print("No se puede modificar la informacion del usuario actual desde el Servlet. Excepcion: " + m);
                }
                request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);
                break;
            //Actualizar la informacion del tecnico
            case "actualizar_idusutec":
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                usuario.setTelefono(Long.parseLong(request.getParameter("ModificarTelefono")));
                usuario.setDireccion(request.getParameter("ModificarDireccion"));
                usuario.setClaveUsuario(request.getParameter("CompruebaClaveUsuario"));
                //usuario.setConfirmarClaveUsuario(request.getParameter("ValidarClaveUsuario"));
                try {
                    boolean modificado = usuarioDAO.ModificarUsuarioRegistrado(usuario);
                    if (modificado == true) {
                        //Incluir el cambio de contraseña

                        usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                        usuario.setClaveUsuario(request.getParameter("ModificarClaveUsuario"));
                        try {
                            boolean cambiado = usuarioDAO.borrarPassword(usuario);
                            if (cambiado == true) {
                                request.setAttribute("exito", "Contraseña Modificada.");//Mensaje Posterior
                                //request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                            } else {
                                request.setAttribute("error", "No se puede realizar la peticion de cambio contraseña ");
                                //request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                            }
                        } catch (Exception c) {
                            request.setAttribute("error", "No se puede cambiar la contraseña del usuario con la ID seleccionada en su rol " + c);
                            System.err.print("No se puede cambiar la contraseña del usuario con la ID seleccionada en su rol desde el Servlet. Excepcion: " + c);
                        }

                        request.setAttribute("exito", "Usuario modificado exitosamente.");//Mensaje Posterior
                        request.getRequestDispatcher("views/funcionario/home.jsp").forward(request, response);//Redirecciona a la lista de equipos registrados
                    } else {
                        request.setAttribute("error", "No se puede modificar el Usuario seleccionado.");
                        request.getRequestDispatcher("views/funcionario/home.jsp").forward(request, response);//Redirecciona a la lista de equipos registrados
                    }
                } catch (Exception m) {
                    request.setAttribute("error", "No se puede modificar el usuario actual de la base de datos. Excepcion: " + m);
                    System.err.print("No se puede modificar la informacion del usuario actual desde el Servlet. Excepcion: " + m);
                }
                request.getRequestDispatcher("views/funcionario/home.jsp").forward(request, response);
                break;
            //Cuando el administrador restablece la clave de Usuario desde su Menu
            case "restablecer":
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                usuario.setClaveUsuario(request.getParameter("ModificarClaveUsuario"));
                try {
                    boolean cambiado = usuarioDAO.borrarPassword(usuario);
                    if (cambiado == true) {
                        request.setAttribute("exito", "Contraseña Modificada.");//Mensaje Posterior
                        request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                    } else {
                        request.setAttribute("error", "No se puede realizar la peticion de cambio contraseña ");
                        request.getRequestDispatcher(listar).forward(request, response);//Redirecciona a la lista de equipos registrados
                    }
                } catch (Exception r) {
                    System.err.print("No se puede modificar la informacion del usuario actual desde el Servlet. Excepcion: " + r);
                }
                request.getRequestDispatcher(listar).forward(request, response);//Regresa a la lista de Usuarios
                break;
            //Cuando el usuario no puede ingresar la clave
            case "reset_clave":
                usuario.setCorreo(request.getParameter("Correo"));
                usuario.setClaveUsuario(request.getParameter("ClaveUsuario"));
                try {
                    //usuarioDAO.ModificarUsuario(usuario);
                    boolean cambiado = usuarioDAO.cambiarPassword(usuario);
                    request.setAttribute("exito", "Contraseña Restablecida. Puede ingresar nuevamente.");
                    processRequest(request, response);
                } catch (Exception r) {
                    request.setAttribute("error", "No se puede restablecer la clave. No esta registrado o existe inconvenientes con la base de datos. ");
                    System.err.print("No se puede modificar la informacion del usuario actual desde el Servlet. Excepcion: " + r);
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "consulta_ced":
                tipoDocumento.setIdTipoDocumento(Integer.parseInt(request.getParameter("BuscarIdTipoDoc")));
                usuario.setNumeroIdentificacion(Long.parseLong(request.getParameter("RegistrarNumeroIdentificacion")));
                try {
                    boolean consultado = usuarioDAO.consultaporNumId(usuario);
                } catch (Exception e) {

                }
                break;
            case "agregar_especialidades":
                usuario.setIdUsuario(Integer.parseInt(request.getParameter("RegistrarTecnico")));
                tipoServ.setIdTipoSer(Integer.parseInt(request.getParameter("RegistrarTipoServ")));
                usTipoServ.setIdUsuario(usuario);
                usTipoServ.setIdTipoSer(tipoServ);                
                try{
                    boolean insertado= usTipoServDAO.agregarTipo(usTipoServ);
                    request.setAttribute("exito", "Especialidad registrada en el tecnico.");
                }
                catch(Exception e){
                    request.setAttribute("error", "No se puede registrar la especialidad del tecnico ");
                    System.err.print("No se puede registrar la especialidad del tecnico desde el Controller Usuario. Excepcion: " + e);
                }
                request.getRequestDispatcher("views/administrador/home.jsp").forward(request, response);
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
