package Interfaz;

import Clases.Servicio;
import Extra.Obtener;
import Extra.Validar;
import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Gestionar_Servicio extends javax.swing.JDialog {
    
    static boolean añadir = false;
    static boolean modificar = false;
    static String codigoAntiguo;
    Validar v = new Validar();
    Obtener get = new Obtener();
    Servicio servicio = null;

    //CONSTRUCTOR AÑADIR
    public Gestionar_Servicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciarComponentes();
        setTitle("Ingresar Servicio - AutoTap");
        lbl_Nuevo.setVisible(true);
        añadir = true;
        
        Servicio serv = new Servicio();
        
        int ultimo = Integer.valueOf(serv.obtenerCodigo().getCodigo() ) + 1 ;
        String numero = get.largo(String.valueOf(ultimo));
        
        txt_Numero.setText(numero + ultimo);
    }
    
    //CONSTRUCTOR MODIFICAR
    public Gestionar_Servicio(java.awt.Frame parent, boolean modal, Servicio servicio) {
        super(parent, modal);
        initComponents();
        iniciarComponentes();
        setTitle("Modificar Servicio - AutoTap");
        lbl_Modificar.setVisible(true);
        modificar = true;
        
        txt_Codigo.setText(get.codigoServicio(servicio.getCodigo()));
        txt_Numero.setText(get.numeroServicio(servicio.getCodigo()));
        codigoAntiguo = txt_Codigo.getText() + "-" + txt_Numero.getText();
        txt_Servicio.setText(servicio.getServicio());
        txt_Precio.setText(String.valueOf(servicio.getPrecio()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Codigo = new javax.swing.JTextField();
        txt_Servicio = new javax.swing.JTextField();
        txt_Precio = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txt_Numero = new javax.swing.JTextField();
        btn_Limpiar = new javax.swing.JButton();
        btn_Volver = new javax.swing.JButton();
        btn_Aceptar = new javax.swing.JButton();
        lbl_Logo = new javax.swing.JLabel();
        lbl_Modificar = new javax.swing.JLabel();
        lbl_Nuevo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("*Código:");
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 60, 30));

        jLabel2.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("*Servicio:");
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 30));

        jLabel3.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("*Precio:");
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 30));

        txt_Codigo.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Codigo.setForeground(new java.awt.Color(255, 0, 0));
        txt_Codigo.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CodigoActionPerformed(evt);
            }
        });
        txt_Codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CodigoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CodigoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 60, 30));

        txt_Servicio.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Servicio.setForeground(new java.awt.Color(255, 0, 0));
        txt_Servicio.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Servicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ServicioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_ServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ServicioKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Servicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 310, 30));

        txt_Precio.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Precio.setForeground(new java.awt.Color(255, 0, 0));
        txt_Precio.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PrecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PrecioKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 120, 30));
        jLayeredPane1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 480, 10));
        jLayeredPane1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 480, 10));

        jLabel5.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("-");
        jLayeredPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 30, 30));

        txt_Numero.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Numero.setForeground(new java.awt.Color(255, 0, 0));
        txt_Numero.setSelectionColor(new java.awt.Color(255, 102, 51));
        jLayeredPane1.add(txt_Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 50, 30));

        btn_Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Limpiar.png"))); // NOI18N
        btn_Limpiar.setBorderPainted(false);
        btn_Limpiar.setContentAreaFilled(false);
        btn_Limpiar.setFocusPainted(false);
        btn_Limpiar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Limpiar Press.png"))); // NOI18N
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 40, 40));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 500, 300));

        btn_Volver.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver.png"))); // NOI18N
        btn_Volver.setBorderPainted(false);
        btn_Volver.setContentAreaFilled(false);
        btn_Volver.setFocusPainted(false);
        btn_Volver.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Press.png"))); // NOI18N
        btn_Volver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Volver Mouse.png"))); // NOI18N
        btn_Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VolverActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 60, 60));

        btn_Aceptar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Ac.png"))); // NOI18N
        btn_Aceptar.setBorderPainted(false);
        btn_Aceptar.setContentAreaFilled(false);
        btn_Aceptar.setFocusPainted(false);
        btn_Aceptar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Aceptar Mouse.png"))); // NOI18N
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 120, 60));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Icono2.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 110, -1));

        lbl_Modificar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Modificar.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Modificar.setText("Modificar");
        getContentPane().add(lbl_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        lbl_Nuevo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Nuevo.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Nuevo.setText("Nuevo");
        getContentPane().add(lbl_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/11.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_CodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CodigoActionPerformed

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        añadir = false;
        modificar = false;
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void txt_ServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ServicioKeyTyped
        if (!v.maximoLargo(txt_Servicio.getText(), 65))
            evt.consume();
    }//GEN-LAST:event_txt_ServicioKeyTyped

    private void txt_PrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioKeyTyped
        if (!v.maximoLargo(txt_Precio.getText(), 7))
            evt.consume();

        if(!Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_PrecioKeyTyped

    private void txt_ServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ServicioKeyReleased
        txt_Codigo.setText(get.generarCodigo(txt_Servicio.getText()));
    }//GEN-LAST:event_txt_ServicioKeyReleased

    private void txt_CodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CodigoKeyTyped
         if (!v.maximoLargo(txt_Codigo.getText(), 4))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_CodigoKeyTyped

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void txt_CodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CodigoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_CodigoKeyPressed

    private void txt_ServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ServicioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_ServicioKeyPressed

    private void txt_PrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_PrecioKeyPressed

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
            java.util.logging.Logger.getLogger(Gestionar_Servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Gestionar_Servicio dialog = new Gestionar_Servicio(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Modificar;
    private javax.swing.JLabel lbl_Nuevo;
    private javax.swing.JTextField txt_Codigo;
    private javax.swing.JTextField txt_Numero;
    private javax.swing.JTextField txt_Precio;
    private javax.swing.JTextField txt_Servicio;
    // End of variables declaration//GEN-END:variables

    private void iniciarComponentes() {
        this.setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Complementos/Icono.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(565, 520); 
        
        txt_Precio.setHorizontalAlignment(4);
        txt_Numero.setHorizontalAlignment(4);
        
        txt_Numero.setEditable(false);
        
        lbl_Modificar.setVisible(false);
        lbl_Nuevo.setVisible(false);
        
        txt_Servicio.requestFocus();
    }
    
    private void aceptar() {
        
        if (!camposVacios()) {
            if (Integer.valueOf(txt_Precio.getText()) > 10) {
                //servicio = cargarServicio();
        
                if (añadir) 
                    nuevoServicio();
                else if (modificar) 
                    modificarServicio();
            }
            else  
                JOptionPane.showMessageDialog(null, "Precio inválidao, favor ingresa un monto mayor","Monto inválido",JOptionPane.WARNING_MESSAGE);
        }
        else
            getFocus();
        
    }

    private void nuevoServicio() {
        Servicio serv = cargarServicio();
        if (serv.insertarServicio()) {
            JOptionPane.showMessageDialog(null, "El servicio "+serv.getServicio()+"\nfue ingresado correctamente","Servicio ingresado",JOptionPane.INFORMATION_MESSAGE);
                
            int ultimo = Integer.valueOf(serv.obtenerCodigo().getCodigo() ) + 1 ;
            String numero = get.largo(String.valueOf(ultimo));
            txt_Numero.setText(numero + ultimo);
            
            limpiar();
        }
    }

    private void modificarServicio() {
        Servicio serv = cargarServicio();
        
        if (serv.actualizarServicio()) {
            JOptionPane.showMessageDialog(null, "El servicio "+serv.getServicio()+"\nfue modificado correctamente","Servicio modificado",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Servicio cargarServicio() {
        Servicio servi = new Servicio();
        
        servi.setCodigo(txt_Codigo.getText().toUpperCase().trim() + "-" + txt_Numero.getText());
        servi.setCodigoAntiguo(codigoAntiguo);
        servi.setServicio(txt_Servicio.getText());
        servi.setPrecio(Integer.valueOf(txt_Precio.getText()));

        return servi;
    }

    private void limpiar() {
        txt_Codigo.setText("");
        txt_Servicio.setText("");
        txt_Precio.setText("");
        txt_Servicio.requestFocus();
    }

    private boolean camposVacios() {
        return txt_Codigo.getText().isEmpty() || txt_Servicio.getText().isEmpty() || txt_Precio.getText().isEmpty();
    }

    private void getFocus() {
        if (txt_Codigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El código se encuentra vacío","Código Vacío",JOptionPane.WARNING_MESSAGE);
            txt_Codigo.requestFocus();
        }
        else if (txt_Servicio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El servicio se encuentra vacío","Servicio Vacío",JOptionPane.WARNING_MESSAGE);
            txt_Servicio.requestFocus();
        }
        else if (txt_Precio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El precio se encuentra vacío","Precio sin valor",JOptionPane.WARNING_MESSAGE);
            txt_Precio.requestFocus();
        }
    }
}
