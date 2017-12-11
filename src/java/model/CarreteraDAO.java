package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

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
    
    static Logger log = Logger.getLogger(CarreteraDAO.class);
    
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
            log.info("Carreteras obtenidas desde la Base de Datos.");
        } catch (SQLException ex) {
            log.error(ex.getMessage());
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
                log.info("Carretera encontrada en los registros.");
                return c;
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
