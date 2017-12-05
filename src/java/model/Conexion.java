package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jonathan
 */
public class Conexion {

    private static Conexion instancia;
    private Connection con;

    private Conexion() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/highway";
        String usu = "root";
        String pass = "";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, usu, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            //Aqui iría un log
        }
    }

    public static Conexion instancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public void cerrar() {
        instancia = null;
    }

    public Connection getConnection() {
        return con;
    }
}
