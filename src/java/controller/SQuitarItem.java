package controller;

import static controller.SAgregarPedido.log;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Carretera;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SQuitarItem", urlPatterns = {"/SQuitarItem"})
public class SQuitarItem extends HttpServlet {

    ArrayList<Carretera> carreta;
    int[] can;

    static Logger log = Logger.getLogger(SQuitarItem.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            carreta = (ArrayList<Carretera>) session.getAttribute("carreterasPedido");
            can = (int[]) session.getAttribute("cantidad");
            int id = Integer.parseInt(request.getParameter("id"));
            boolean es = false;
            Carretera z = null;
            for (Carretera x : carreta) {
                if (x.getId() == id) {
                    if (can[x.getId()] > 0) {
                        can[x.getId()] = 0;
                    }
                    z = x;
                    es = true;
                }
            }

            if (es) {
                carreta.remove(z);
                z = null;
                log.info("Carretera eliminada del nuevo pedido.");
            }
            session.setAttribute("carreterasPedido", carreta);
            session.setAttribute("cantidad", can);
            response.sendRedirect("principal.jsp");
        } catch (IOException | NumberFormatException ex) {
            log.error(ex.getMessage());
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
