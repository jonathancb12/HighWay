/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class DetallePedidoDAO {

    private final String read_all = "SELECT id_detalle, id_pedido, id_carretera, cantidad  FROM detalle_pedido d inner join pedido p on d.id_pedido = p.id_pedido WHERE p.rut = ?";
    private final String insert = "INSERT INTO detalle_pedido(id_pedido, id_carretera, cantidad) VALUES (?,?,?)";

    private final Conexion con = Conexion.instancia();
    PreparedStatement ps;
    ResultSet rs;

    public void registrarDetalle(Pedido p, Carretera c, int cantidad) {
        try {
            ps = con.getConnection().prepareStatement(insert);
            ps.setInt(1, p.getIdPedido());
            ps.setInt(2, c.getId());
            ps.setInt(3, cantidad);
            ps.execute();
        } catch (SQLException ex) {
        }
    }

    public ArrayList<DetallePedido> listarDetalles(int rut) {
        ArrayList<DetallePedido> lista = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(read_all);
            ps.setInt(1, rut);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePedido d = new DetallePedido();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdPedido(rs.getInt("id_pedido"));
                d.setIdCarretera(rs.getInt("id_carretera"));
                d.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
        }
        return lista;
    }

}