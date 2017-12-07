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
import model.CarreteraDAO;
import model.DetallePedido;
import model.DetallePedidoDAO;
import model.Pedido;
import model.PedidoDAO;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SPedidosAnteriores", urlPatterns = {"/SPedidosAnteriores"})
public class SPedidosAnteriores extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            PedidoDAO pd = new PedidoDAO();
            DetallePedidoDAO dp = new DetallePedidoDAO();
            CarreteraDAO cd = new CarreteraDAO();

            String mje = "";
            String texto = "";
            ArrayList<String> cadenas = new ArrayList<>();

            String rut = request.getParameter("rut");
            if (!(rut.isEmpty())) {
                int key = Integer.parseInt(rut);
                ArrayList<DetallePedido> detalles = dp.listarDetalles(key);
                ArrayList<Pedido> pedidos = pd.buscarPedidosEmpresa(key);
                for (Pedido p : pedidos) {
                    for (DetallePedido d : detalles) {
                        if (d.getIdPedido() == p.getIdPedido()) {
                            texto += cd.buscar(d.getIdCarretera()).getCarretera() + " ";
                        }
                    }
                    cadenas.add(texto);
                    texto = "";
                }
                session.setAttribute("pedidos", pedidos);
                session.setAttribute("detalles", detalles);
                session.setAttribute("cadenas", cadenas);
            } else {
                mje = "Se requiere el rut para buscar los pedidos...";
                session.setAttribute("mensaje", mje);
            }
            response.sendRedirect("pedidosAnteriores.jsp");
        } catch (Exception ex) {

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
