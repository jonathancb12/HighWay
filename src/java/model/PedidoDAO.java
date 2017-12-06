package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class PedidoDAO {

    private final String read_all = "SELECT * FROM pedido";
    private final String read_filter = "SELECT * FROM pedido WHERE rut = ?";
    private final String insert = "INSERT INTO pedido( rut, forma_pago, retiro) VALUES (?,?,?)";

    private final Conexion con = Conexion.instancia();
    PreparedStatement ps;
    ResultSet rs;

    public PedidoDAO() {
    }

    public Pedido registrarPedido(Pedido p) {
        try {
            ps = con.getConnection().prepareStatement(insert);
            ps.setInt(1, p.getRut());
            ps.setString(2, p.getFormaPago());
            ps.setString(3, p.getRetiro());
            ps.execute();
            return buscarUltimo();
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(read_all);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setRut(rs.getInt("rut"));
                p.setFormaPago(rs.getString("forma_pago"));
                p.setRetiro(rs.getString("retiro"));
                lista.add(p);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    public ArrayList<Pedido> buscarPedidosEmpresa(int rut) {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(read_filter);
            ps.setInt(1, rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setRut(rs.getInt("rut"));
                p.setFormaPago(rs.getString("forma_pago"));
                p.setRetiro(rs.getString("retiro"));
                lista.add(p);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    private Pedido buscarUltimo() {
        try {
            ps = con.getConnection().prepareStatement("select * from pedido where max(id)");
            rs = ps.executeQuery();
            if (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setRut(rs.getInt("rut"));
                p.setFormaPago(rs.getString("forma_pago"));
                p.setRetiro(rs.getString("retiro"));
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
