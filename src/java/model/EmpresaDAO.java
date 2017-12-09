package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class EmpresaDAO {

    private final String read_all = "SELECT * FROM empresa";
    private final String read_one = "SELECT * FROM empresa WHERE rut = ?";
    private final String insert = "INSERT INTO `empresa`(`rut`, `nombre`, `direccion`) VALUES (?,?,?)";

    private final Conexion con = Conexion.instancia();
    PreparedStatement ps;
    ResultSet rs;

    public EmpresaDAO() {
    }

    public void registrarEmpresa(Empresa e) {
        try {
            if (buscar(e.getRut()) == null) {
                ps = con.getConnection().prepareStatement(insert);
                ps.setInt(1, e.getRut());
                ps.setString(2, e.getNombre());
                ps.setString(3, e.getDireccion());
                ps.execute();
            }
        } catch (SQLException ex) {
        }
    }

    public ArrayList<Empresa> listarTodas() {
        ArrayList<Empresa> lista = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(read_all);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa e = new Empresa();
                e.setRut(rs.getInt("rut"));
                e.setNombre(rs.getString("nombre"));
                e.setDireccion(rs.getString("direcion"));
                lista.add(e);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    public Empresa buscar(int rut) {
        Empresa e = null;
        try {
            ps = con.getConnection().prepareStatement(read_one);
            ps.setInt(1, rut);
            rs = ps.executeQuery();
            if (rs.next()) {
                e = new Empresa();
                e.setRut(rs.getInt("rut"));
                e.setNombre(rs.getString("nombre"));
                e.setDireccion(rs.getString("direcion"));
                return e;
            }
        } catch (SQLException ex) {
        }
        return e;
    }
}
