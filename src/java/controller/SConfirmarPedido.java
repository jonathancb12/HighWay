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
    int[] cantidad;
    DecimalFormat formatea = new DecimalFormat("###,###.##");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String mje = "";
            HttpSession session = request.getSession();

            //Recibe datos
            carreteras = (ArrayList<Carretera>) session.getAttribute("carreterasPedido");
            cantidad = (int[]) session.getAttribute("cantidad");
            String rut = request.getParameter("rut");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String comprador = request.getParameter("comprador");
            String pago = request.getParameter("pago");
            String retiro = request.getParameter("retiro");

            //Instancia DAO
            EmpresaDAO ed = new EmpresaDAO();
            PedidoDAO pd = new PedidoDAO();
            DetallePedidoDAO dd = new DetallePedidoDAO();

            //Valida contenido 
            if (!rut.isEmpty() && !nombre.isEmpty() && !direccion.isEmpty() && !comprador.isEmpty() && !pago.isEmpty() && !retiro.isEmpty()) {
                int total = (int) session.getAttribute("totalPedido");
                int totalVoucher = total;
                for (Carretera c : carreteras) {
                    cantidad[c.getId()] = Integer.parseInt(request.getParameter(String.valueOf(c.getId())));
                }
                Empresa e = new Empresa();
                e.setRut(Integer.parseInt(rut));
                e.setNombre(nombre);
                e.setDireccion(direccion);
                ed.registrarEmpresa(e);

                Pedido p = new Pedido();
                p.setRut(e.getRut());
                p.setFormaPago(pago);
                p.setComprador(comprador);
                p.setRetiro(retiro);
                p.setTotal(total);
                pd.registrarPedido(p);
                p.setIdPedido(pd.buscarUltimoPedido(p.getRut()));

                DetallePedido d = new DetallePedido();
                d.setIdPedido(p.getIdPedido());
                for (int i = 1; i < cantidad.length; i++) {
                    if (cantidad[i] > 0) {
                        dd.registrarDetalle(p, (i) + 1, cantidad[i]);
                    }
                }
                int cantidadV[] = cantidad;
                ArrayList<Carretera> carreterasV = carreteras;
                
                //Limpia para nuevo pedido
                session.setAttribute("total", null);
                session.setAttribute("carreterasPedido", null);
                session.setAttribute("cantidad", null);

                //Carga datos a la session y envía al voucher
                session.setAttribute("totalVoucher", formatea.format(totalVoucher));
                session.setAttribute("pedido", p);
                session.setAttribute("cantidad", cantidadV);
                session.setAttribute("carreterasV", carreterasV);
                response.sendRedirect("voucher.jsp");
            } else {
                mje = "Todos los campos son requeridos...";
                session.setAttribute("mensaje", mje);
                response.sendRedirect("principal.jsp");
            }

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
