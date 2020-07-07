package Interfaz;

import Clases.Cliente;
import Extra.Obtener;
import Extra.Validar;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Gestionar_Cliente extends javax.swing.JDialog {
    
    static boolean añadir;
    static boolean modificar;
    Validar v = new Validar();
    Cliente cli = new Cliente();
    Obtener get = new Obtener();
    
    //CONSTRUCTOR NUEVO CLIENTE
    public Gestionar_Cliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciarComponentes();
        lbl_Nuevo.setVisible(true);
        añadir = true;
        lbl_Codigo.setText(String.valueOf(Integer.valueOf(cli.obtenerCodigo().getRut()) + 1) );
    }
    
    //CONSTRUCTOR MODIFICAR CIENTE
    public Gestionar_Cliente(java.awt.Frame parent, boolean modal, Cliente cliente) {
        super(parent, modal);
        initComponents();
        iniciarComponentes();
        lbl_Modificar.setVisible(true);
        modificar = true;
        
        txt_Rut.setEditable(false);
        int activo = 0;
        
        if (cliente.getActivo() == 0) 
            activo = 1;
        //Cargamos los datos del cliente seleccionado
        lbl_Codigo.setText(cliente.getId());
        txt_Nombre.setText(cliente.getNombre());
        txt_Paterno.setText(cliente.getPaterno());
        txt_Materno.setText(cliente.getMaterno());
        txt_Rut.setText(cliente.getRut());
        txt_Correo.setText(cliente.getCorreo());
        txt_Telefono.setText(cliente.getTelefono());
        txt_Direccion.setText(cliente.getDireccion());
        cmb_Activo.setSelectedIndex(activo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        txt_Paterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Telefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Materno = new javax.swing.JTextField();
        cmb_Activo = new javax.swing.JComboBox<>();
        btn_Limpiar = new javax.swing.JButton();
        lbl_Codigo = new javax.swing.JLabel();
        txt_Direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_Rut = new javax.swing.JTextField();
        txt_Correo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbl_Modificar = new javax.swing.JLabel();
        lbl_Nuevo = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        btn_Volver = new javax.swing.JButton();
        lbl_Logo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Código:");
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, 30));

        jLabel2.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*Nombre:");
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 70, 30));

        jLabel3.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("*Apellido paterno:");
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, 30));

        txt_Nombre.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Nombre.setForeground(new java.awt.Color(255, 51, 51));
        txt_Nombre.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_NombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NombreKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 143, 30));

        txt_Paterno.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Paterno.setForeground(new java.awt.Color(255, 51, 51));
        txt_Paterno.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_PaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PaternoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Paterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 143, 30));

        jLabel4.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("*Rut:");
        jLayeredPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 60, 30));

        jLabel5.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Dirección:");
        jLayeredPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, 40));

        jLabel6.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("*Activo:");
        jLayeredPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, 30));

        txt_Telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Telefono.setForeground(new java.awt.Color(255, 51, 51));
        txt_Telefono.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_TelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_TelefonoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 140, 30));

        jLabel8.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("*Apellido materno:");
        jLayeredPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 140, 30));

        txt_Materno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Materno.setForeground(new java.awt.Color(255, 51, 51));
        txt_Materno.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_MaternoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_MaternoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Materno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 143, 30));

        cmb_Activo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_Activo.setForeground(new java.awt.Color(255, 102, 102));
        cmb_Activo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 (Activo)", "0 (Inactivo)" }));
        jLayeredPane1.add(cmb_Activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 140, 30));

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
        jLayeredPane1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 40, 40));

        lbl_Codigo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        lbl_Codigo.setForeground(new java.awt.Color(255, 51, 51));
        lbl_Codigo.setText("-----");
        jLayeredPane1.add(lbl_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 30));

        txt_Direccion.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Direccion.setForeground(new java.awt.Color(255, 51, 51));
        txt_Direccion.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_DireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DireccionKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 140, 30));

        jLabel9.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText(" Teléfono:");
        jLayeredPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 70, 40));

        txt_Rut.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Rut.setForeground(new java.awt.Color(255, 51, 51));
        txt_Rut.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RutKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_RutKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Rut, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 140, 30));

        txt_Correo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Correo.setForeground(new java.awt.Color(255, 51, 0));
        txt_Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CorreoKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 140, 30));

        jLabel10.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 0));
        jLabel10.setText("*Correo:");
        jLayeredPane1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, 30));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 750, 300));

        lbl_Modificar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Modificar.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Modificar.setText("Modificar Cliente");
        getContentPane().add(lbl_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 350, -1));

        lbl_Nuevo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Nuevo.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Nuevo.setText("Ingresar Cliente");
        getContentPane().add(lbl_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, 120, 60));

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
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, 60, 60));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Icono2.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 110, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/11.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_NombreKeyPressed

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (!v.maximoLargo(txt_Nombre.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_PaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PaternoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_PaternoKeyPressed

    private void txt_PaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PaternoKeyTyped
        if (!v.maximoLargo(txt_Paterno.getText(), 20))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txt_PaternoKeyTyped

    private void txt_TelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_TelefonoKeyPressed

    private void txt_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyTyped
        if (!v.maximoLargo(txt_Telefono.getText(), 12))
            evt.consume();

        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txt_TelefonoKeyTyped

    private void txt_MaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MaternoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_MaternoKeyPressed

    private void txt_MaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MaternoKeyTyped
        if (!v.maximoLargo(txt_Paterno.getText(), 10))
            evt.consume();
    }//GEN-LAST:event_txt_MaternoKeyTyped

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void txt_DireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_DireccionKeyPressed

    private void txt_DireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyTyped
        if (!v.maximoLargo(txt_Direccion.getText(), 40))
            evt.consume();
    }//GEN-LAST:event_txt_DireccionKeyTyped

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        añadir = false;
        modificar = false;
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void txt_CorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_CorreoKeyPressed

    private void txt_CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CorreoKeyTyped
        if (!v.maximoLargo(txt_Paterno.getText(), 50))
            evt.consume();
    }//GEN-LAST:event_txt_CorreoKeyTyped

    private void txt_RutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_RutKeyPressed

    private void txt_RutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyTyped
        if (!v.maximoLargo(txt_Paterno.getText(), 10))
            evt.consume();
    }//GEN-LAST:event_txt_RutKeyTyped

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
            java.util.logging.Logger.getLogger(Gestionar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Gestionar_Cliente dialog = new Gestionar_Cliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmb_Activo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lbl_Codigo;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Modificar;
    private javax.swing.JLabel lbl_Nuevo;
    private javax.swing.JTextField txt_Correo;
    private javax.swing.JTextField txt_Direccion;
    private javax.swing.JTextField txt_Materno;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Paterno;
    private javax.swing.JTextField txt_Rut;
    private javax.swing.JTextField txt_Telefono;
    // End of variables declaration//GEN-END:variables

    private void aceptar() {
        if (txt_Nombre.getText().isEmpty() || txt_Paterno.getText().isEmpty() || txt_Rut.getText().isEmpty() || txt_Telefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Algunos campos se encuentran vacíos","Campos vacíos",JOptionPane.WARNING_MESSAGE);
            obtenerFocus();
        }
        else {
            if (añadir)  {
                if (v.rut(txt_Rut.getText() ) != null) {
                    nuevoCliente();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El rut ingresado es inválido","Rut inválido",JOptionPane.WARNING_MESSAGE);
                    txt_Rut.requestFocus();
                }
            }
            else if (modificar)  {
                if (v.rut(get.reemplazo(txt_Rut.getText(), ".") ) != null) {
                    modificarCliente();
                }
                else{
                    JOptionPane.showMessageDialog(null, "El rut ingresado es inválido","Rut inválido",JOptionPane.WARNING_MESSAGE);
                    txt_Rut.requestFocus();
                }
            }            
        }
    }

    private void limpiarCampos() {
        txt_Nombre.setText("");
        txt_Paterno.setText("");
        txt_Materno.setText("");
        txt_Rut.setText("");
        txt_Rut.setEditable(true);
        txt_Correo.setText("");
        txt_Telefono.setText("");
        txt_Direccion.setText("");
        cmb_Activo.setSelectedIndex(0);
    }

    private void iniciarComponentes() {
        this.setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Complementos/Icono.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(824, 510); 
        lbl_Modificar.setVisible(false);
        lbl_Nuevo.setVisible(false);
        txt_Nombre.requestFocus();
    }

    private void nuevoCliente() {
        Cliente cliente = cargarCliente();
        
        if (cliente.insertarCliente()) {
            JOptionPane.showMessageDialog(null, "El cliente "+cliente.getNombre()+" fue ingresado correctamente.","Cliente ingresado",JOptionPane.INFORMATION_MESSAGE);
            lbl_Codigo.setText(String.valueOf(Integer.valueOf(cli.obtenerCodigo().getRut()) + 1) );
            limpiarCampos();
        }
    }

    private void modificarCliente() {
        Cliente cliente = cargarCliente();
        
        if (cliente.actualizarCliente()) 
            JOptionPane.showMessageDialog(null, "El cliente "+cliente.getNombre()+" fue modificado correctamente.","Cliente ingresado",JOptionPane.INFORMATION_MESSAGE);
    }

    private Cliente cargarCliente() {
        Cliente cliente = new Cliente();
        
        int activo = 1;
        if (cmb_Activo.getSelectedIndex() == 1) 
            activo = 0;
        
        cliente.setId(lbl_Codigo.getText());
        cliente.setNombre(txt_Nombre.getText());
        cliente.setPaterno(txt_Paterno.getText());
        cliente.setMaterno(txt_Materno.getText());
        cliente.setRut(txt_Rut.getText());
        cliente.setCorreo(txt_Correo.getText());
        cliente.setTelefono(txt_Telefono.getText());
        cliente.setDireccion(txt_Direccion.getText());
        cliente.setActivo(activo);
        
        return cliente;
    }

    private void obtenerFocus() {
        if (txt_Nombre.getText().isEmpty()) 
            txt_Nombre.requestFocus();
        else if (txt_Paterno.getText().isEmpty()) 
            txt_Paterno.requestFocus();
        else if (txt_Rut.getText().isEmpty()) 
            txt_Rut.requestFocus();
        else if (txt_Telefono.getText().isEmpty()) 
            txt_Telefono.requestFocus();
    }
}