package Interfaz;

import Clases.Empleado;
import Extra.Obtener;
import Extra.Validar;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Gestionar_Empleado extends javax.swing.JDialog {
    static boolean añadir = false;
    static boolean modificar = false;
    static boolean change = false;
    
    Obtener get = new Obtener();
    Validar v = new Validar(); 

    //CONSTRUCTOR DE MODIFICACION
    public Gestionar_Empleado(java.awt.Frame parent, boolean modal, Empleado emp) {
        super(parent, modal);
        initComponents();
        setTitle("Modificar Empleado - AutoTap");
        
        iniciarComponentes();
        modificar = true;
        añadir = false;
        
        lbl_Modificar.setVisible(true);
        txt_Rut.setEditable(false);
        //Cargar los txt con los valores entrantes
        lbl_Codigo.setText(emp.getId());
        txt_Nombre.setText(emp.getNombre());
        txt_Paterno.setText(emp.getPaterno());
        txt_Materno.setText(emp.getMaterno());
        txt_Rut.setText(emp.getRut());
        txt_Contraseña1.setText(emp.getContraseña());
        txt_Contraseña2.setText(emp.getContraseña());
        cmb_Tipo.setSelectedItem(emp.getTipo());
    }
    
    //CONSTRUCTOR DE NUEVO INGRESO
    public Gestionar_Empleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Ingresar Empleado - AutoTap");
        
        iniciarComponentes();
        añadir = true;
        lbl_Ingresar.setVisible(true);
        
        Empleado emp = new Empleado();
        
        //Cargar el nuevo id del usuario
        int id = Integer.valueOf(emp.obtenerCodigo().getId()) + 1 ; 
        lbl_Codigo.setText(id + "");
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
        txt_Rut = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Materno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_Contraseña2 = new javax.swing.JPasswordField();
        txt_Contraseña1 = new javax.swing.JPasswordField();
        cmb_Tipo = new javax.swing.JComboBox<>();
        btn_Limpiar = new javax.swing.JButton();
        lbl_Codigo = new javax.swing.JLabel();
        lbl_Modificar = new javax.swing.JLabel();
        lbl_Ingresar = new javax.swing.JLabel();
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
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*Nombre:");
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 70, 30));

        jLabel3.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("*Apellido paterno:");
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 120, 30));

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
        jLayeredPane1.add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 143, 30));

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
        jLayeredPane1.add(txt_Paterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 143, 30));

        jLabel4.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("*Rut:");
        jLayeredPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 40, 30));

        jLabel5.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("*Contraseña:");
        jLayeredPane1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, 30));

        jLabel6.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("*Tipo:");
        jLayeredPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, -1, 30));

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
        jLayeredPane1.add(txt_Rut, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 143, 30));

        jLabel8.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("*Apellido materno:");
        jLayeredPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, 30));

        txt_Materno.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
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
        jLayeredPane1.add(txt_Materno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 143, 30));

        jLabel9.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("*Contraseña:");
        jLayeredPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, 30));

        txt_Contraseña2.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Contraseña2.setForeground(new java.awt.Color(255, 51, 51));
        txt_Contraseña2.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Contraseña2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Contraseña2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Contraseña2KeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Contraseña2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 143, 30));

        txt_Contraseña1.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Contraseña1.setForeground(new java.awt.Color(255, 51, 51));
        txt_Contraseña1.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Contraseña1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Contraseña1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Contraseña1KeyTyped(evt);
            }
        });
        jLayeredPane1.add(txt_Contraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 143, 30));

        cmb_Tipo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_Tipo.setForeground(new java.awt.Color(255, 102, 102));
        cmb_Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLayeredPane1.add(cmb_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 143, 30));

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
        jLayeredPane1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 50, 40));

        lbl_Codigo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        lbl_Codigo.setForeground(new java.awt.Color(255, 51, 51));
        lbl_Codigo.setText("-----");
        jLayeredPane1.add(lbl_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 750, 280));

        lbl_Modificar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Modificar.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Modificar.setText("Modificar Empleado");
        getContentPane().add(lbl_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        lbl_Ingresar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Ingresar.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Ingresar.setText("Ingresar Empleado");
        getContentPane().add(lbl_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

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
        cambio();
    }//GEN-LAST:event_btn_VolverActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (!v.maximoLargo(txt_Nombre.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_PaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PaternoKeyTyped
        if (!v.maximoLargo(txt_Paterno.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_PaternoKeyTyped

    private void txt_MaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MaternoKeyTyped
        if (!v.maximoLargo(txt_Materno.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_MaternoKeyTyped

    private void txt_RutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyTyped
        if (!v.maximoLargo(txt_Rut.getText(), 10)){
            evt.consume(); }
    }//GEN-LAST:event_txt_RutKeyTyped

    private void txt_Contraseña1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Contraseña1KeyTyped
        if (!v.maximoLargo(txt_Contraseña1.getText(), 20))
            evt.consume();
    }//GEN-LAST:event_txt_Contraseña1KeyTyped

    private void txt_Contraseña2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Contraseña2KeyTyped
        if (!v.maximoLargo(txt_Contraseña2.getText(), 20))
            evt.consume();
    }//GEN-LAST:event_txt_Contraseña2KeyTyped

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void txt_NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_NombreKeyPressed

    private void txt_PaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PaternoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_PaternoKeyPressed

    private void txt_MaternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MaternoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_MaternoKeyPressed

    private void txt_RutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_RutKeyPressed

    private void txt_Contraseña1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Contraseña1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Contraseña1KeyPressed

    private void txt_Contraseña2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Contraseña2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_txt_Contraseña2KeyPressed

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
            java.util.logging.Logger.getLogger(Gestionar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Empleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gestionar_Empleado dialog = new Gestionar_Empleado(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmb_Tipo;
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
    private javax.swing.JLabel lbl_Ingresar;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Modificar;
    private javax.swing.JPasswordField txt_Contraseña1;
    private javax.swing.JPasswordField txt_Contraseña2;
    private javax.swing.JTextField txt_Materno;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Paterno;
    private javax.swing.JTextField txt_Rut;
    // End of variables declaration//GEN-END:variables

    private void iniciarComponentes() {
        this.setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Complementos/Icono.png")).getImage()); // Icono del programa
        this.setResizable(false);
        setSize(824, 510); 
        
        lbl_Modificar.setVisible(false);
        lbl_Ingresar.setVisible(false);
        
        txt_Nombre.requestFocus();
        
        cmb_Tipo.removeAllItems();
        cmb_Tipo.addItem("Empleado");
        cmb_Tipo.addItem("Administrador");
        cmb_Tipo.addItem("Tapicero");
        cmb_Tipo.addItem("Inhabilitado");
    }
    
    public boolean cambio(){
        return change;
    }

    private void ingresarEmpleado() {
        Empleado emp = cargarEmpleado();
        
        if (emp.insertarEmpleado()) {
            JOptionPane.showMessageDialog(null, "El empleado "+emp.getNombre()+" fue agregado correctamente.","Empleado ingresado",JOptionPane.INFORMATION_MESSAGE);
            lbl_Codigo.setText(String.valueOf(Integer.parseInt(this.lbl_Codigo.getText()) + 1)); //Actualiza el lblId
            limpiar();
            change = true;
        }
    }

    private void modificarEmpleado() {
        Empleado emp = cargarEmpleado();

        if (emp.actualizarEmpleado()) {
            JOptionPane.showMessageDialog(null, "El empleado "+emp.getNombre()+" fue modificado correctamente.","Empleado ingresado",JOptionPane.INFORMATION_MESSAGE);
            change = true;
        }
    }

    private Empleado cargarEmpleado() {
        Empleado emp = new Empleado();
        
        emp.setId(lbl_Codigo.getText());
        emp.setNombre(txt_Nombre.getText().trim());
        emp.setPaterno(txt_Paterno.getText().trim());
        emp.setMaterno(txt_Materno.getText().trim());
        emp.setRut(txt_Rut.getText().trim());
        emp.setContraseña(txt_Contraseña1.getText());
        emp.setTipo(cmb_Tipo.getSelectedItem().toString());
        
        return emp;        
    }

    private void limpiar() {
        txt_Nombre.setText("");
        txt_Paterno.setText("");
        txt_Materno.setText("");
        
        if (añadir) 
            txt_Rut.setText("");

        txt_Contraseña1.setText("");
        txt_Contraseña2.setText("");
        cmb_Tipo.setSelectedIndex(0);
    }

    private void aceptar() {
        if (!txt_Nombre.getText().isEmpty() && !txt_Paterno.getText().isEmpty() && !txt_Paterno.getText().isEmpty() && !txt_Contraseña1.getText().isEmpty() && !txt_Contraseña2.getText().isEmpty()) {
            
            if (txt_Contraseña1.getText().equals(txt_Contraseña2.getText())) {
                if (añadir)
                    if (v.rut(txt_Rut.getText() ) != null) 
                        ingresarEmpleado();
                    else{
                        JOptionPane.showMessageDialog(null, "El rut ingresado es inválido","Rut inválido",JOptionPane.WARNING_MESSAGE);
                        txt_Rut.requestFocus();
                    }
                
                else if (modificar){
                    if (v.rut(get.reemplazo(txt_Rut.getText(), ".") ) != null) 
                         modificarEmpleado();
                    else{
                        JOptionPane.showMessageDialog(null, "El rut ingresado es inválido","Rut inválido",JOptionPane.WARNING_MESSAGE);
                        txt_Rut.requestFocus();
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.","Precaución!",JOptionPane.WARNING_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacíos.","Precaución!",JOptionPane.WARNING_MESSAGE);
    }
}
