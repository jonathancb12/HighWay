/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Carretera;
import model.CarreteraDAO;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SAgregarPedido", urlPatterns = {"/SAgregarPedido"})
public class SAgregarPedido extends HttpServlet {

    ArrayList<Carretera> carreterasPedido = new ArrayList<>();
    ArrayList<Integer> cantidad = new ArrayList<>(asList(0, 0, 0, 0,0));
    Carretera c;
    CarreteraDAO cd;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int total = 0;
            HttpSession session = request.getSession();
            cd = new CarreteraDAO();
            c = cd.buscar(Integer.parseInt(request.getParameter("carretera")));
            carreterasPedido.add(c);
            for (Carretera car : carreterasPedido) {
                switch (car.getId()) {
                    case 1:
                        cantidad.add(1, (cantidad.get(1) + 1));
                        break;
                    case 2:
                        cantidad.add(2, (cantidad.get(2) + 1));
                        break;
                    case 3:
                        cantidad.add(3, (cantidad.get(3) + 1));
                        break;
                    case 4:
                        cantidad.add(4, (cantidad.get(4) + 1));
                        break;
                }
                total += car.getValor();
            }
            session.setAttribute("total", total);
            session.setAttribute("carreterasPedido", carreterasPedido);
            session.setAttribute("cantidad", cantidad);

            response.sendRedirect("principal.jsp");

        } catch (NumberFormatException ex) {

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
