package lanzador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lanzador {

    public static Conexion con = new Conexion();
    public static Connection cn = con.conectar();

    public static void main(String[] args) {
        //nombre de programa,
        String programa="apetatitlan";
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

        //con los valores comparamos
        if (nuevo > actual) {
            System.out.println("Actualizamos");
            //se encontro nueva version, se procede a descargar y actualizar
            descargar();
        } else {

            //no hay cambios se ejecuta el software
            try {
                //ruta y nombre del ejecutable
                String ejecutable = "calc.exe";
                Process p = Runtime.getRuntime().exec(ejecutable);
            } catch (Exception error) {
                System.out.println("no encuentro el ejecutable");
            }
        }

    }

    public static Double nuevo() {

        String c_actual = "0";
        Double actual = 0D;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT version FROM software WHERE programa='programa'");
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
        System.out.println(version);
        return version;
    }

    private static void descargar() {
        String url=null;
        try {
            //leemos el nombre y la direccion de archivo a descargar

            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery("SELECT origin FROM software");
            while (rs.next()){
                url=rs.getString("origin");
            }
            File file = new File ("actualiza.rar");
            try {
                URLConnection conn = new URL(url).openConnection();
                conn.connect();
                
                InputStream in = conn.getInputStream();
                OutputStream out=new FileOutputStream(file);
                int b=0;
                while (b!=-1){
                    b=in.read();
                    if (b!=-1)
                        out.write(b);
                }
                out.close();
                in.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
