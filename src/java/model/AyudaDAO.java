/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**
 *
 * @author David
 */
public class AyudaDAO {
    
    private static final String read_all = "SELECT * FROM ayuda";
    private static final String insert_ayuda = "INSERT INTO ayuda(nombre,apellido, mail,telefono, mensaje) VALUES(?,?,?,?,?)";

    private static final Conexion con = Conexion.instancia();
    static Logger log = Logger.getLogger(Conexion.class);
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean registrarAyuda(Ayuda a) {
        boolean confirmacion = false;
        try {
            ps = con.getConnection().prepareStatement(insert_ayuda);
            
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getMail());
            ps.setString(4,a.getTelefono());
            ps.setString(5, a.getMensaje());
            int i = ps.executeUpdate();
            if (i != 0) {
                confirmacion = true;
            }
            log.info("Datos de contacto recibidos correctamente");
        } catch (SQLException ex) {
            log.error(ex);
        }
        return confirmacion;
    }
}
