package lanzador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.SwingWorker;

public class Actualizador extends javax.swing.JFrame {

    public String nombre;
    private ActionListener al;
    String version, cambios, ruta;
    int valor = 1;
    int maximo = 0;
    long Porciento = 0;
    Thread barra = new Thread() {
        public void run() {
            jProgreso.setValue((int) Porciento);
        }
    };

    public Actualizador(String programa) {
        nombre = programa;
        initComponents();
        this.setLocationRelativeTo(null);
        //buscamos en la bd sus valores
        asignarValores();
    }

    private Actualizador() {
        throw new UnsupportedOperationException("No soportado todavia"); //To change body of generated methods, choose Tools | Templates.
    }

    private void asignarValores() {
        //Obtenemos el nombre

        String sql = "SELECT * FROM sistema WHERE nombre='" + nombre + "'";
        //creamos conexion;
        Conexion con = new Conexion();
        Connection cn = con.conectar();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println(nombre);
            while (rs.next()) {
                version = rs.getString("version");
                cambios = rs.getString("cambios");
                ruta = rs.getString("ruta");
            }
            txtSistema.setText(nombre);
            txtVersion.setText(version);
            txtCambios.setText(cambios);
        } catch (SQLException ex) {
            Logger.getLogger(Actualizador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSistema = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtVersion = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCambios = new javax.swing.JTextArea();
        visor = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnMasTarde = new javax.swing.JButton();
        jProgreso = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nueva Version encontrada");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Se Encontro una nueva actualización");

        jLabel2.setText("Programa");

        txtSistema.setText(nombre);
        txtSistema.setText("_");

        jLabel3.setText("Versión:");

        txtVersion.setText("_");

        jLabel4.setText("Cambios:");

        txtCambios.setEditable(false);
        txtCambios.setColumns(20);
        txtCambios.setRows(5);
        jScrollPane1.setViewportView(txtCambios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSistema)
                    .addComponent(jLabel3)
                    .addComponent(txtVersion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        visor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnMasTarde.setText("Mas tarde");
        btnMasTarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasTardeActionPerformed(evt);
            }
        });

        jProgreso.setStringPainted(true);

        javax.swing.GroupLayout visorLayout = new javax.swing.GroupLayout(visor);
        visor.setLayout(visorLayout);
        visorLayout.setHorizontalGroup(
            visorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(visorLayout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMasTarde)))
                .addContainerGap())
        );
        visorLayout.setVerticalGroup(
            visorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(visorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnMasTarde))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(visor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMasTardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasTardeActionPerformed
        Ejecutable ejecutable = new Ejecutable();
        ejecutable.lanzar();
        System.exit(0);
    }//GEN-LAST:event_btnMasTardeActionPerformed
    public class hiloDescargar implements Runnable {

        String ar;

        public hiloDescargar(String ar) {

            this.ar = ar;
        }

        @Override
        public void run() {
            try {

                String name = "instalador.exe"; //nombre del archivo destino
                //Directorio destino para las descargas
                String folder = "descargas/";

//Crea el directorio de destino en caso de que no exista
                File dir = new File(folder);

                if (!dir.exists()) {
                    if (!dir.mkdir()) {
                        return; // no se pudo crear la carpeta de destino
                    }
                }
                File file = new File(folder + name);

               
                URL url = new URL(ar);
                URLConnection urlCon = url.openConnection();
                //Creamos un archivo para la descarga igual que el archivo remoto
                File file2 = new File(url.toString());
               
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    InputStream is = urlCon.getInputStream();
                    byte[] array = new byte[1000];
                    int leido = is.read(array);
                    //Aplicando valores a la barra de progreso
                    int maximo = urlCon.getContentLength();
                    jProgreso.setMinimum(0);
                    jProgreso.setMaximum(maximo);
                    jProgreso.setValue(0);
                    int actual = 0;
                    while (leido > 0) {
                        fos.write(array, 0, leido);
                        leido = is.read(array);
                        jProgreso.setValue(actual);
                        actual += leido;
                        jProgreso.setString("Descargando " + actual + "/" + maximo + " bytes");
                    }
                    fos.close();
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(hiloDescargar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(hiloDescargar.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                // directorio/ejecutable es el path del ejecutable y un nombre 
                Process p = Runtime.getRuntime().exec("descargas/instalador.exe");
                System.exit(0);
            } catch (IOException e) {
                //Se lanza una excepción si no se encuentra en ejecutable o el fichero no es ejecutable. 
                System.out.println("no se encontro el ejecutable");
            }
        }
    }
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        hiloDescargar h = new hiloDescargar(ruta);
        Thread t = new Thread(h);
        t.start();
    }//GEN-LAST:event_btnActualizarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Actualizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Actualizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Actualizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Actualizador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Actualizador().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnMasTarde;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgreso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtCambios;
    public static javax.swing.JLabel txtSistema;
    private javax.swing.JLabel txtVersion;
    private javax.swing.JPanel visor;
    // End of variables declaration//GEN-END:variables
}
