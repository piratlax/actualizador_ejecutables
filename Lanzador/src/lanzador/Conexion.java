package lanzador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private String db = "sistemas";
    private String url = "jdbc:mysql://localhost/" + db;
    private String usuario = "root";
    private String pass = "";

    public Conexion() {
    }

    public Connection conectar() {
        Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, pass);
        } catch (ClassNotFoundException | SQLException e) {
            //si quieres ver el error por que no se conecta "descomenta" la
            //siguiente linea:
            //JOptionPane.showMessageDialog(null, e);
            // si no nos conectamos a la BD corremos el programa
            try {
                //ruta y nombre del ejecutable
                String ejecutable = "calc.exe";
                Process p = Runtime.getRuntime().exec(ejecutable);
            } catch (Exception error) {
                System.out.println("no encuentro el ejecutable");
            }
        }
        return con;
    }
}
