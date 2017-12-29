package lanzador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lanzador {

   static Conexion con = new Conexion();
  
    ResultSet rs;
    Statement st;
    PreparedStatement ps; 
    
    
    //nombre de programa
    private static String programa="apetatitlan";
    
    public static void main(String[] args) {
        
        
        //iniciamosla la variable de la version actual
        Double actual = 0D;
        //leemos desde la bd si hay nueva version
        Double nuevo = nuevo();

        //pasamos a valor double el archivo de texto
        try {
            actual = Double.valueOf(version());

        } catch (IOException ex) {
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println ("el nuevo es "+nuevo+" el actual "+actual);
        //con los valores comparamos
        if (nuevo > actual) {
           
            //se encontro nueva version, se procede a descargar y actualizar
            Actualizador actualizador=new Actualizador(programa);
            actualizador.setVisible(true);

        } else {
            //como no hay nueva version ejecutamos nuestro sistema
            Ejecutable ejecuta=new Ejecutable();
           ejecuta.lanzar();
            
        }

    }

    public static Double nuevo() {

        String c_actual = "0";
        Double actual = 0D;
        Connection cn = con.conectar();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT version FROM sistema WHERE nombre='"+programa+"'");
            while (rs.next()) {
                c_actual = rs.getString("version");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        }
        actual = Double.valueOf(c_actual);
        return actual;
    }

    public static String version() throws FileNotFoundException, IOException {
        String cadena;
        String version = null;
        FileReader f = new FileReader("version.txt");
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            version = cadena;
        }
        b.close();
        return version;
    } 
}
