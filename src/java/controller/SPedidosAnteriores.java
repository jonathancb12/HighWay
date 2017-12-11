package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CarreteraDAO;
import model.DetallePedido;
import model.DetallePedidoDAO;
import model.Empresa;
import model.EmpresaDAO;
import model.Pedido;
import model.PedidoDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SPedidosAnteriores", urlPatterns = {"/SPedidosAnteriores"})
public class SPedidosAnteriores extends HttpServlet {

    static Logger log = Logger.getLogger(SPedidosAnteriores.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String mje = "";
            HttpSession session = request.getSession();
            session.setAttribute("pedidos", null);
            session.setAttribute("totales", null);
            session.setAttribute("cadenas", null);
            PedidoDAO pd = new PedidoDAO();
            DetallePedidoDAO dp = new DetallePedidoDAO();
            CarreteraDAO cd = new CarreteraDAO();

            String texto = "";
            ArrayList<String> cadenas = new ArrayList<>();
            ArrayList<Integer> totales = new ArrayList<>();
            String rut = request.getParameter("rut");
            if (!(rut.isEmpty())) {
                int key = Integer.parseInt(rut);
                EmpresaDAO ed = new EmpresaDAO();
                Empresa em = ed.buscar(key);
                if (em != null) {
                    ArrayList<DetallePedido> detalles = dp.listarDetalles(key);
                    ArrayList<Pedido> pedidos = pd.buscarPedidosEmpresa(key);
                    for (Pedido p : pedidos) {
                        for (DetallePedido d : detalles) {
                            if (p.getIdPedido() == d.getIdPedido()) {
                                texto += cd.buscar(d.getIdCarretera()).getCarretera() + " - ";
                            }
                        }

                        texto = texto.substring(0, texto.length() - 2);
                        cadenas.add(texto);
                        totales.add(p.getTotal());
                        texto = "";
                    }
                    log.info("Pedidos anteriores cargados en vista, con éxito.");
                    session.setAttribute("detalles", detalles);
                    session.setAttribute("pedidos", pedidos);
                    session.setAttribute("totales", totales);
                    session.setAttribute("cadenas", cadenas);

                    //Limpia datos para nuevo Pedido
                    session.setAttribute("total", 0);
                    session.setAttribute("carreterasPedido", null);
                    session.setAttribute("cantidad", null);
                } else {
                    log.info("Se buscó un rut que no existe en la Base de Datos.");
                    mje = "No hay registros asociados al rut ingresado";
                }
            } else {
                mje = "Se requiere el rut para buscar los pedidos...";
            }

            session.setAttribute("mensaje", mje);
            response.sendRedirect("pedidosAnteriores.jsp");
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
