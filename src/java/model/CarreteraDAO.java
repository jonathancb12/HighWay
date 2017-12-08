package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class CarreteraDAO {

    private final String read_all = "SELECT * FROM carretera";
    private final String read_one = "SELECT * FROM carretera WHERE id = ?";

    private final Conexion con = Conexion.instancia();
    PreparedStatement ps;
    ResultSet rs;

    public CarreteraDAO() {
    }

    public ArrayList<Carretera> listarTodas() {
        ArrayList<Carretera> lista = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(read_all);
            rs = ps.executeQuery();
            while (rs.next()) {
                Carretera c = new Carretera();
                c.setId(rs.getInt("id"));
                c.setCarretera(rs.getString("carretera"));
                c.setValor(rs.getInt("valor"));
                lista.add(c);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    public Carretera buscar(int id) {
        Carretera c = null;
        try {
            ps = con.getConnection().prepareStatement(read_one);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Carretera();
                c.setId(rs.getInt("id"));
                c.setCarretera(rs.getString("carretera"));
                c.setValor(rs.getInt("valor"));
                return c;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
