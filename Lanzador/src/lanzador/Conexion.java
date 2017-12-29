package lanzador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    private String db="sistema";
    private String url="localhost"+db;
    private String usuario="root";
    private String pass="";
   
    public Conexion() {
    }
    
    public Connection conectar(){
     Connection con=null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con=DriverManager.getConnection(url,usuario,pass);
        } catch (ClassNotFoundException | SQLException e) {
            //JOptionPane.showMessageDialog(null, e);
            Ejecutable ejecutar=new Ejecutable();
            ejecutar.lanzar();
        }
    return con;
    }  
}