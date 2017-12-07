package controller;

import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Carretera;
import model.DetallePedido;
import model.DetallePedidoDAO;
import model.Empresa;
import model.EmpresaDAO;
import model.Pedido;
import model.PedidoDAO;

/**
 *
 * @author Jonathan
 */
@WebServlet(name = "SConfirmarPedido", urlPatterns = {"/SConfirmarPedido"})
public class SConfirmarPedido extends HttpServlet {

    ArrayList<Carretera> carreteras;
    Integer[] cantidad;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            int rut = Integer.parseInt(request.getParameter("rut"));
            String pago = request.getParameter("pago");
            String retiro = request.getParameter("retiro");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String comprador = request.getParameter("comprador");
            if (rut != 0 && !pago.isEmpty() && !retiro.isEmpty() && !nombre.isEmpty() && !direccion.isEmpty() && !comprador.isEmpty()) {
                carreteras = (ArrayList<Carretera>) session.getAttribute("carreterasPedido");
                cantidad = (Integer[]) session.getAttribute("cantidad");

                //Instancia objetos DAO
                EmpresaDAO ed = new EmpresaDAO();
                PedidoDAO pd = new PedidoDAO();
                DetallePedidoDAO dp = new DetallePedidoDAO();

                //Crea y registra Empresa
                Empresa e = new Empresa();
                e.setRut(rut);
                e.setNombre(nombre);
                e.setDireccion(direccion);
                e.setComprador(comprador);
                ed.registrarEmpresa(e);

                //Crea y registra Pedido
                Pedido p = new Pedido();
                p.setRut(rut);
                p.setFormaPago(pago);
                p.setRetiro(retiro);
                p = pd.registrarPedido(p);

                //
                DetallePedido d = new DetallePedido();
                d.setIdPedido(p.getIdPedido());

                for (int i = 0; i < carreteras.size(); i++) {
                    dp.registrarDetalle(p, carreteras.get(i), cantidad[i]);
                }

                session.setAttribute("pedido", p);
                response.sendRedirect("voucher.jsp");
            } else {
                String mje = "Todos los campos son requeridos...";
                session.setAttribute("mensaje", mje);
                response.sendRedirect("principal.jsp");
            }
        } catch (IOException | NumberFormatException ex) {
            out.print("Error");
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
