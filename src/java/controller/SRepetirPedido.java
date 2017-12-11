package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetallePedido;
import model.DetallePedidoDAO;
import model.Pedido;
import model.PedidoDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SRepetirPedido", urlPatterns = {"/SRepetirPedido"})
public class SRepetirPedido extends HttpServlet {

    static Logger log = Logger.getLogger(SRepetirPedido.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Crea DAOs y recibe datos
            DetallePedidoDAO dp = new DetallePedidoDAO();
            PedidoDAO pd = new PedidoDAO();
            int id = Integer.parseInt(request.getParameter("idPedido"));

            //Busca registros en la BD y registra con nuevo id
            Pedido p = pd.buscarPedidoId(id);
            pd.registrarPedido(p);
            p.setIdPedido(pd.buscarUltimoPedido(p.getRut()));
            p = pd.buscarPedidoId(p.getIdPedido());
            
            //Comienza insercción de registros según los datos recibidos 
            ArrayList<DetallePedido> listaDetalles = dp.listarDetalles(p.getRut());
            for (DetallePedido d : listaDetalles) {
                dp.registrarDetalle(p, d.getIdCarretera(), d.getCantidad());
            }
            response.sendRedirect("pedidosAnteriores.jsp");

        } catch (NumberFormatException ex) {
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
