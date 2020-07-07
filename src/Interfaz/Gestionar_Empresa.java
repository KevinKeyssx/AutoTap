package Interfaz;

import Clases.Empresa;
import Extra.Obtener;
import Extra.Validar;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Gestionar_Empresa extends javax.swing.JDialog {

    static boolean añadir;
    static boolean modificar;
    Validar v = new Validar();
    Obtener get = new Obtener();
    //CONSTRUCTOR MODIFICAR
    public Gestionar_Empresa(java.awt.Frame parent, boolean modal, Empresa emp) {
        super(parent, modal);
        initComponents();
        iniciarComponentes();
        setTitle("Ingresar Empresa - AutoTap");
        lbl_Modificar.setVisible(true);
        modificar = true;      
        
        int activo = 0;
        
        if (emp.getActivo() == 0) 
            activo = 1;
        
        txt_Rut.setEditable(false);
        
        lbl_Codigo.setText(emp.getId());
        txt_Nombre.setText(emp.getNombre());
        txt_Giro.setText(emp.getGiro());
        txt_Rut.setText(emp.getRut());
        txt_Direccion.setText(emp.getDireccion());
        txt_Telefono.setText(emp.getTelefono());
        cmb_Activo.setSelectedIndex(activo);
    }
    //CONSTRUCTOR INGRESAR
    public Gestionar_Empresa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciarComponentes();
        setTitle("Modificar Empresa - AutoTap");
        añadir = true;
        
        lbl_Nuevo.setVisible(true);
        
        Empresa empre = new Empresa();

        lbl_Codigo.setText(String.valueOf(Integer.valueOf(empre.obtenerCodigo().getId() ) + 1) );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        txt_Giro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Telefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Rut = new javax.swing.JTextField();
        cmb_Activo = new javax.swing.JComboBox<>();
        btn_Limpiar = new javax.swing.JButton();
        lbl_Codigo = new javax.swing.JLabel();
        txt_Direccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_Correo = new javax.swing.JTextField();
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
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*Razón social:");
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 100, 30));

        jLabel3.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("  Giro:");
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

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
        jLayeredPane1.add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 143, 30));

        txt_Giro.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Giro.setForeground(new java.awt.Color(255, 51, 51));
        txt_Giro.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Giro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_GiroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_GiroKeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Giro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 143, 30));

        jLabel4.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("E-Mail:");
        jLayeredPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 60, 30));

        jLabel5.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("Dirección:");
        jLayeredPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, 40));

        jLabel6.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("*Activo:");
        jLayeredPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, 30));

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
        jLayeredPane1.add(txt_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 140, 30));

        jLabel8.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText(" *Rut:");
        jLayeredPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 50, -1));

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
        jLayeredPane1.add(txt_Rut, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 143, 30));

        cmb_Activo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_Activo.setForeground(new java.awt.Color(255, 102, 102));
        cmb_Activo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 (Activo)", "0 (Inactivo)" }));
        jLayeredPane1.add(cmb_Activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 140, 30));

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
        jLayeredPane1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 40, 40));

        lbl_Codigo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        lbl_Codigo.setForeground(new java.awt.Color(255, 51, 51));
        lbl_Codigo.setText("-----");
        jLayeredPane1.add(lbl_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

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
        jLayeredPane1.add(txt_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 140, 30));

        jLabel9.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText(" Teléfono:");
        jLayeredPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, 70, 40));

        txt_Correo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Correo.setForeground(new java.awt.Color(255, 51, 51));
        txt_Correo.setSelectionColor(new java.awt.Color(255, 102, 51));
        jLayeredPane1.add(txt_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 140, 30));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 750, 280));

        lbl_Modificar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Modificar.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Modificar.setText("Modificar Empresa");
        getContentPane().add(lbl_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 360, -1));

        lbl_Nuevo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Nuevo.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Nuevo.setText("Ingresar Empresa");
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
        getContentPane().add(btn_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 120, 60));

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
        getContentPane().add(btn_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, 60, 60));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Icono2.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 110, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/11.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VolverActionPerformed
        añadir = false;
        modificar = false;
        this.setVisible(false);
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (!v.maximoLargo(txt_Nombre.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_GiroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GiroKeyTyped
        if (!v.maximoLargo(txt_Giro.getText(), 20))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_GiroKeyTyped

    private void txt_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyTyped
        if (!v.maximoLargo(txt_Telefono.getText(), 12))
            evt.consume();

        if(!Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_TelefonoKeyTyped

    private void txt_DireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyTyped
        if (!v.maximoLargo(txt_Direccion.getText(), 40))
            evt.consume();
    }//GEN-LAST:event_txt_DireccionKeyTyped

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_NombreKeyPressed

    private void txt_GiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_GiroKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_GiroKeyPressed

    private void txt_RutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_RutKeyPressed

    private void txt_TelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TelefonoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_TelefonoKeyPressed

    private void txt_DireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_DireccionKeyPressed

    private void txt_RutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyTyped
        if (!v.maximoLargo(txt_Giro.getText(), 10))
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
            java.util.logging.Logger.getLogger(Gestionar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Empresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Gestionar_Empresa dialog = new Gestionar_Empresa(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField txt_Giro;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Rut;
    private javax.swing.JTextField txt_Telefono;
    // End of variables declaration//GEN-END:variables

    private void iniciarComponentes() {
        this.setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Complementos/Icono.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(824, 510); 
        
        lbl_Modificar.setVisible(false);
        lbl_Nuevo.setVisible(false);
        
        txt_Nombre.requestFocus();
    }

    private void nuevaEmpresa() {
        Empresa empresa = cargarEmpresa();
        
        if (empresa.insertarEmpresa()) {
            JOptionPane.showMessageDialog(null, "La empresa "+empresa.getNombre()+" fue agregada correctamente.","Empresa agregada",JOptionPane.INFORMATION_MESSAGE);
            lbl_Codigo.setText(String.valueOf(Integer.valueOf(empresa.obtenerCodigo().getId() ) + 1) ); //Actualiza el lblId
            limpiarCampos();
        }
    }

    private void modificarEmpresa() {
        Empresa empresa = cargarEmpresa();
        
        if (empresa.actualizarEmpresa()) {
            JOptionPane.showMessageDialog(null, "La empresa "+empresa.getNombre()+" fue modificada correctamente.","Empresa modificada",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Empresa cargarEmpresa() {
        Empresa empresa = new Empresa();
        
        int activo = 1;
        
        if (cmb_Activo.getSelectedIndex() == 1) 
            activo = 0;
        
        empresa.setId(lbl_Codigo.getText());
        empresa.setNombre(txt_Nombre.getText());
        empresa.setGiro(txt_Giro.getText());
        empresa.setRut(txt_Rut.getText());
        empresa.setTelefono(txt_Telefono.getText());
        empresa.setDireccion(txt_Direccion.getText());
        empresa.setActivo(activo);
        empresa.setCorreo(txt_Correo.getText());
        //JOptionPane.showMessageDialog(null, "ID "+empresa.getId()+" fue modificada correctamente.","Empresa modificada",JOptionPane.INFORMATION_MESSAGE);
        
        return empresa;
    }

    private void limpiarCampos() {
        txt_Nombre.setText("");
        txt_Giro.setText("");
        txt_Rut.setText("");
        txt_Telefono.setText("");
        txt_Direccion.setText("");
        txt_Correo.setText("");     
        cmb_Activo.setSelectedIndex(0);
    }

    private void getFocus() {
        if (txt_Nombre.getText().isEmpty()) 
            txt_Nombre.requestFocus();
        
        if (txt_Rut.getText().isEmpty()) 
            txt_Rut.requestFocus();
    }

    private void aceptar() {
        if (!txt_Nombre.getText().isEmpty() && !txt_Rut.getText().isEmpty()) {
            
            if (añadir) {
                if (v.rut(txt_Rut.getText() ) != null) 
                    nuevaEmpresa();
                else{
                    JOptionPane.showMessageDialog(null, "El rut ingresado es inválido","Rut inválido",JOptionPane.WARNING_MESSAGE);
                    txt_Rut.requestFocus();
                }
            }
            else if (modificar) {
                if (v.rut(get.reemplazo(txt_Rut.getText(), ".")  ) != null) 
                    modificarEmpresa();
                else{
                    JOptionPane.showMessageDialog(null, "El rut ingresado es inválido","Rut inválido",JOptionPane.WARNING_MESSAGE);
                    txt_Rut.requestFocus();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Algunos campos están vacíos","Campos vacíos",JOptionPane.WARNING_MESSAGE);
            getFocus();
        }
    }
}
