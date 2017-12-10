package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Carretera;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SQuitarItem", urlPatterns = {"/SQuitarItem"})
public class SQuitarItem extends HttpServlet {

    ArrayList<Carretera> carreta;
    int[] can;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            carreta = (ArrayList<Carretera>) session.getAttribute("carreterasPedido");
            can = (int[]) session.getAttribute("cantidad");
            int id = Integer.parseInt(request.getParameter("id"));
            for (Carretera x : carreta) {
                if (x.getId() == id) {
                    carreta.remove(x);
                    can[x.getId()] = 0;
                }
            }
            session.setAttribute("carreterasPedido", carreta);
            session.setAttribute("cantidad", can);
            response.sendRedirect("STotal");
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