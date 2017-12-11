package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ayuda;
import model.AyudaDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author David
 */
@WebServlet(name = "SAyuda", urlPatterns = {"/SAyuda"})
public class SAyuda extends HttpServlet {

    static Logger log = Logger.getLogger(SAyuda.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AyudaDAO ad = new AyudaDAO();
            Ayuda a = new Ayuda();

            a.setNombre(request.getParameter("name"));
            a.setApellido(request.getParameter("apellido"));
            a.setMail(request.getParameter("mail"));
            a.setTelefono(request.getParameter("telephone"));
            a.setMensaje(request.getParameter("message"));
            ad.registrarAyuda(a);
            response.sendRedirect("index.jsp");
        } catch (IOException e) {
            log.error(e);
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
