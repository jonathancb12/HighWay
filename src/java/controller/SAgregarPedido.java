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
@WebServlet(name = "SAgregarPedido", urlPatterns = {"/SAgregarPedido"})
public class SAgregarPedido extends HttpServlet {

    ArrayList<Carretera> carreterasPedido = new ArrayList<>();
    int[] cantidad = {0, 0, 0, 0, 0};
    DecimalFormat formatea = new DecimalFormat("###,###.##");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("mensaje", "");
            String carretera = request.getParameter("carretera");
            boolean existe = false;

            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String comprador = request.getParameter("comprador");

            Empresa e = new Empresa();
            e.setRut(rut.isEmpty() ? null : Integer.parseInt(rut));
            e.setNombre(nombre.isEmpty() ? null : nombre);
            e.setDireccion(direccion.isEmpty() ? null : direccion);
            comprador = comprador.isEmpty() ? null : comprador;

            for (Carretera c : carreterasPedido) {
                cantidad[c.getId()] = Integer.parseInt(request.getParameter(String.valueOf(c.getId())));
            }
            if (!(carretera.equals("Carreteras"))) {
                CarreteraDAO cd = new CarreteraDAO();
                Carretera c = cd.buscar(Integer.parseInt(carretera));
                for (Carretera x : carreterasPedido) {
                    if (c.getId() == x.getId()) {
                        existe = true;
                    }
                }
                if (!existe) {
                    carreterasPedido.add(c);
                }
                session.setAttribute("carreterasPedido", carreterasPedido);
                session.setAttribute("cantidad", cantidad);
                session.setAttribute("empresa", e);
                session.setAttribute("comprador", comprador);
            }
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
