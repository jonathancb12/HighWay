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
    private final String insert = "INSERT INTO pedido(rut, forma_pago, comprador, total, retiro) VALUES (?,?,?,?,?)";

    private final Conexion con = Conexion.instancia();
    PreparedStatement ps;
    ResultSet rs;

    public PedidoDAO() {
    }

    public int registrarPedido(Pedido p) {
        try {
            ps = con.getConnection().prepareStatement(insert);
            ps.setInt(1, p.getRut());
            ps.setString(2, p.getFormaPago());
            ps.setString(3, p.getComprador());
            ps.setInt(4, p.getTotal());
            ps.setString(5, p.getRetiro());
            ps.execute();
            return buscarUltimo();
        } catch (SQLException e) {
        }
        return 0;
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
                p.setComprador(rs.getString("comprador"));
                p.setTotal(rs.getInt("total"));
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
                p.setComprador(rs.getString("comprador"));
                p.setTotal(rs.getInt("total"));
                p.setFormaPago(rs.getString("forma_pago"));
                p.setRetiro(rs.getString("retiro"));
                lista.add(p);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    private int buscarUltimo() {
        try {
            String sql = "select max(id_pedido) from pedido order by id_pedido desc limit 1";
            ps = con.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_pedido");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }
}
