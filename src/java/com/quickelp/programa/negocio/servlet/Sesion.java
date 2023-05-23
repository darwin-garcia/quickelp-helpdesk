/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import com.quickelp.programa.persistencia.dao.UsuarioDAO;
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
    Connection conexion=null;
    UsuarioDAO usDAO = new UsuarioDAO();
    UsuarioVO usVO = new UsuarioVO();
    boolean ingreso;
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
        //String numeroDoc="", tel="";
        String action=request.getParameter("accion");
        switch(action){
            case "Ingresar":
                request.getRequestDispatcher("login.jsp").forward(request, response);
                break;
            case "validar":
                //Datos que solicita para iniciar sesion (POST)
                String mail=request.getParameter("email");//Correo Electronico
                String pass=request.getParameter("clave");//Clave de Ingreso
                usVO.setCorreo(mail);//
                usVO.setClaveUsuario(pass);
                ingreso=usDAO.validarUsuario(usVO);//Acceso al DAO
                //Boolean Ingreso (if ingreso==true)
                if(ingreso==true){//Cuando falla. Revisar desde el DAO
                    //Estructura de sesiones
                    HttpSession miSesion = request.getSession(true);
                    UsuarioVO user = new UsuarioVO(mail);
                    miSesion.setAttribute("correo", user);
                    request.getSession().setAttribute("correo", mail);//Muestra el correo del usuario en el menu principal
                    request.getSession().setAttribute("NombreUsuario", user.getNombre());//Falta
                    request.getSession().setAttribute("rol", user.getIdRol());//Muestra el correo del usuario en el menu principal
                    //Mostrar datos del usuario en el menu Principal 
                    request.setAttribute("exito", "Bienvenido. Acceso Concedido. Estado de ingreso: "+ingreso);
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                }else{                   
                    
                    request.setAttribute("error", "Usuario y/o contraseña incorrecta. Estado de ingreso: "+ingreso);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                //
                break;
            case "Restablecer":
                
                
                request.getRequestDispatcher("views/usuario/restablecer.jsp").forward(request, response);
                break;
            case "raiz":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
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

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
