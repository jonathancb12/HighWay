/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Carretera;
import model.CarreteraDAO;
import model.Empresa;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "STotal", urlPatterns = {"/STotal"})
public class STotal extends HttpServlet {

    ArrayList<Carretera> carreteras;
    int[] cantidad;

    DecimalFormat formatea = new DecimalFormat("###,###.##");
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int total = 0;
            HttpSession session = request.getSession();
            CarreteraDAO cd = new CarreteraDAO();

            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String comprador = request.getParameter("comprador");

            Empresa e = new Empresa();
            e.setRut(rut.isEmpty() ? null : Integer.parseInt(rut));
            e.setNombre(nombre.isEmpty() ? null : nombre);
            e.setDireccion(direccion.isEmpty() ? null : direccion);
            comprador = comprador.isEmpty() ? null : comprador;

            carreteras = (ArrayList<Carretera>) session.getAttribute("carreterasPedido");
            cantidad = (int[]) session.getAttribute("cantidad");

            for (Carretera c : carreteras) {
                cantidad[c.getId()] = Integer.parseInt(request.getParameter(String.valueOf(c.getId())));
            }
            
            int valor = 0;
            for (Carretera x : carreteras) {
                int i = cantidad[x.getId()];
                if (i > 0) {
                    valor = cd.buscar(x.getId()).getValor();
                    total += i * valor;
                }
            }

            session.setAttribute("cantidad", cantidad);
            session.setAttribute("total", formatea.format(total));
            session.setAttribute("totalPedido", total);
            session.setAttribute("empresa", e);
            session.setAttribute("comprador", comprador);
            response.sendRedirect("principal.jsp");
        } catch (Exception e) {
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
