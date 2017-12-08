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

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SAgregarPedido", urlPatterns = {"/SAgregarPedido"})
public class SAgregarPedido extends HttpServlet {

    ArrayList<Carretera> carreterasPedido;
    Integer[] cantidad = null;
    Carretera c;
    CarreteraDAO cd;
    DecimalFormat formatea = new DecimalFormat("###,###.##");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int total = 0;
            HttpSession session = request.getSession();

            if (session.getAttribute("carreterasPedido") == null) {
                carreterasPedido = new ArrayList<>();
            }
            if (session.getAttribute("cantidad") == null) {
                cantidad = new Integer[]{0, 0, 0, 0, 0};
            }

            cd = new CarreteraDAO();
            c = cd.buscar(Integer.parseInt(request.getParameter("carretera")));
            cantidad[c.getId()] += 1;
            carreterasPedido.add(c);
            for (Carretera car : carreterasPedido) {
                total += car.getValor();
            }

            session.setAttribute("total", formatea.format(total));
            session.setAttribute("carreterasPedido", carreterasPedido);
            session.setAttribute("cantidad", cantidad);
            session.setAttribute("totalPedido", total);

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
