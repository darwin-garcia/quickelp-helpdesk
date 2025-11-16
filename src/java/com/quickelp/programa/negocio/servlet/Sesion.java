/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.UsuarioDAO;
import com.quickelp.programa.persistencia.vo.RolVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Darwin Garcia
 */
@WebServlet(name = "Sesion", urlPatterns = {"/Sesion"})
public class Sesion extends HttpServlet {

    Connection conexion = null;
    UsuarioDAO usDAO = new UsuarioDAO();
    UsuarioVO usVO = new UsuarioVO();

    RolVO rolVO = new RolVO();

    boolean ingreso;
    boolean reseteado;

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
        processRequest(request, response);

        String action = request.getParameter("accion");//Accion desde el HTML
        switch (action) {
            case "Ingresar":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "Restablecer"://Accion para restablecer la contraseña
                request.getRequestDispatcher("views/guest/restablecer.jsp").forward(request, response);
                break;
            case "raiz"://Regresar al index al cerrar sesion
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "HomeAdministrador":
                request.getRequestDispatcher("views/administrador/home.jsp").forward(request, response);
                break;
            case "HomeFuncionario":
                request.getRequestDispatcher("views/funcionario/home.jsp").forward(request, response);
                break;
            case "HomeCliente":
                request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);
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

        String action = request.getParameter("Peticion");
        
        switch (action) {

            case "validar":
                //Datos que solicita para iniciar sesion (POST)
                String mail = request.getParameter("email");//Correo Electronico obtenido de la vista Login
                String pass = request.getParameter("clave");//Clave de Ingreso obtenido de la vista Login               
                //Datos que solicito al usuario se recogen de la base de datos
                usVO.setCorreo(mail);
                usVO.setClaveUsuario(pass);
                //Boolean de Ingreso con el metodo de Validar Usuario
                ingreso = usDAO.validarUsuario(usVO);//Acceso al DAO
                //Variable de Roles que capturo durante la validacion de acceso
                String nRoles = usVO.getIdRol().getNombreRol();
                try {

                    if (ingreso == true) {//Cuando falla. Revisar desde el DAO
                        //Estructura de sesiones
                        request.setAttribute("usu", usVO);

                        HttpSession miSesion = request.getSession(true);
                        UsuarioVO user = new UsuarioVO(mail);
                        miSesion.setAttribute("correo", user);
                        //Muestra Toda la informacion en el menu principal
                        request.getSession().setAttribute("correo", mail);
                        request.getSession().setAttribute("idUsu", usVO.getIdUsuario());
                        request.getSession().setAttribute("nombre", usVO.getNombre());
                        request.getSession().setAttribute("apellido", usVO.getApellido());
                        request.getSession().setAttribute("nombreRol", nRoles);

                        if (nRoles.equalsIgnoreCase("Administrador")) {
                            //Mostrar el mensaje de Acceso correcto
                            request.setAttribute("exito", "Bienvenido. Acceso Concedido. Estado de ingreso: " + ingreso);
                            //Redirecciona a la siguiente ruta para ir al dashboard
                            request.getRequestDispatcher("views/administrador/home.jsp").forward(request, response);
                        }
                        if (nRoles.equalsIgnoreCase("Tecnico")) {
                            //Mostrar el mensaje de Acceso correcto
                            request.setAttribute("exito", "Bienvenido. Acceso Concedido.");
                            //Redirecciona a la siguiente ruta para ir al dashboard
                            request.getRequestDispatcher("views/funcionario/home.jsp").forward(request, response);
                        }
                        if (nRoles.equalsIgnoreCase("Cliente")) {
                            //Mostrar el mensaje de Acceso correcto
                            request.setAttribute("exito", "Bienvenido. Acceso Concedido.");
                            //Redirecciona a la siguiente ruta para ir al dashboard
                            request.getRequestDispatcher("views/cliente/home.jsp").forward(request, response);
                        }

                    } else {
                        //Mensaje de error.

                        if (mail.equalsIgnoreCase("")) {
                            request.setAttribute("error", "El campo de Correo electronico esta vacio");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                        if (pass.equalsIgnoreCase("")) {
                            request.setAttribute("error", "El campo de Contraseña esta vacio ");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                        
                        if (pass.length()<6) {
                            request.setAttribute("error", "La contraseña debe tener mas de 6 digitos");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                        request.setAttribute("error", "Usuario y/o contraseña incorrecta. Estado de ingreso: " + ingreso);

                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    System.err.print("No hay conexion con la base de Datos. Por favor Intente de nuevo");
                }

                break;
            case "reset_pass":
                String mailreset = request.getParameter("email");//Correo Electronico
                String passreset = request.getParameter("claveNueva");//Clave de Ingreso
                String passconfirm = request.getParameter("claveConfirma");//Clave de Ingreso
                usVO.setCorreo(mailreset);
                usVO.setClaveUsuario(passreset);
                reseteado = usDAO.cambiarPassword(usVO);
                if (reseteado == true) {
                    HttpSession miSesion = request.getSession(true);
                    UsuarioVO user = new UsuarioVO(mailreset);
                    miSesion.setAttribute("email", user);

                    if (passreset.equals(passconfirm)) {
                        request.setAttribute("exito", "Contraseña Cambiada al usuario: " + mailreset);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        request.setAttribute("exito", "Compruebe sus datos. Las contraseñas no corresponden. ");
                    }
                }
                break;
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
