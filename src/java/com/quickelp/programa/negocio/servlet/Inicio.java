/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.negocio.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darwin Garcia
 */
@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

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

        String action = request.getParameter("accion");
        switch (action) {
            case "SobreNosotros":
                request.getRequestDispatcher("views/principal/SobreNosotros.jsp").forward(request, response);
                break;
            case "QuienesSomos":
                request.getRequestDispatcher("views/principal/QuienesSomos.jsp").forward(request, response);
                break;
            case "Contactenos":
                request.getRequestDispatcher("views/principal/contacto.jsp").forward(request, response);
                break;
            case "Experiencias":
                request.getRequestDispatcher("views/principal/experiences.jsp").forward(request, response);
                break;
            case "Mantenimiento":
                request.getRequestDispatcher("views/principal/mantenimiento.jsp").forward(request, response);
                break;
            case "Configuracion":
                request.getRequestDispatcher("views/principal/configuracion.jsp").forward(request, response);
                break;
            case "Reparacion":
                request.getRequestDispatcher("views/principal/reparacion.jsp").forward(request, response);
                break;
            case "SoporteTecnico":
                request.getRequestDispatcher("views/principal/soporte.jsp").forward(request, response);
                break;
            case "Preguntas":
                request.getRequestDispatcher("views/principal/faq.jsp").forward(request, response);
                break;
            case "NuestrosTecnicos":
                request.getRequestDispatcher("views/principal/NuestrosTecnicos.jsp").forward(request, response);
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
