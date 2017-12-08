package controller;

import java.io.IOException;
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
    Integer[] cantidad1;

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
                int total = Integer.parseInt(session.getAttribute("totalPedido").toString());

                //Instancia objetos DAO
                EmpresaDAO ed = new EmpresaDAO();
                PedidoDAO pd = new PedidoDAO();
                DetallePedidoDAO dp = new DetallePedidoDAO();

                //Crea y registra Empresa
                Empresa e = new Empresa();
                e.setRut(rut);
                e.setNombre(nombre);
                e.setDireccion(direccion);
                ed.registrarEmpresa(e);

                //Crea y registra Pedido
                Pedido p = new Pedido();
                p.setRut(rut);
                p.setTotal(total);
                p.setFormaPago(pago);
                p.setComprador(comprador);
                p.setRetiro(retiro);
                p.setIdPedido(pd.registrarPedido(p));

                //
                DetallePedido d = new DetallePedido();
                d.setIdPedido(p.getIdPedido());

                Object[][] car = {{1, 0}, {2, 0}, {3, 0}, {4, 0}};
                for (int i = 0; i < carreteras.size(); i++) {
                    switch (carreteras.get(i).getId()) {
                        case 0:
                            car[0][1] = Integer.parseInt(car[0][1].toString()) + 1;
                            break;
                        case 1:
                            car[1][1] = Integer.parseInt(car[1][1].toString()) + 1;
                            break;
                        case 2:
                            car[2][1] = Integer.parseInt(car[2][1].toString()) + 1;
                            break;
                        case 3:
                            car[3][1] = Integer.parseInt(car[3][1].toString()) + 1;
                            break;
                    }
                }

                for (int i = 0; i < car.length; i++) {
                    if (Integer.parseInt(car[i][1].toString()) != 0) {
                    dp.registrarDetalle(p, (i + 1), Integer.parseInt(car[i][1].toString()));
                    }
                }
                //Prepara datos para voucher
                cantidad1 = cantidad;
                session.setAttribute("cantidad1", cantidad1);
                session.setAttribute("totalPedido", total);

                //Limpia listas de pedido anterior
                session.setAttribute("total", null);
                session.setAttribute("carreterasPedido", null);
                session.setAttribute("cantidad", null);

                //Redirecciona al voucher
                session.setAttribute("pedido", p);
                response.sendRedirect("voucher.jsp");
            } else {
                String mje = "Todos los campos son requeridos...";
                session.setAttribute("mensaje", mje);
                response.sendRedirect("principal.jsp");
            }
        } catch (IOException | NumberFormatException ex) {

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
