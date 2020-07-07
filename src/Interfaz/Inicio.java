package Interfaz;

import Clases.Empleado;
import Extra.Validar;
import carga.PantallaCargandoMain;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Inicio extends javax.swing.JFrame {
    
    Validar v = new Validar();
    Empleado emp = new Empleado();

    public Inicio() {
        initComponents();
        
        setTitle("Inicio - AutoTap");
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Complementos/Icono.png")).getImage()); // Icono del programa
        setResizable(false);
        setSize(600, 454);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        lbl_Contraseña = new javax.swing.JLabel();
        txt_Usuario = new javax.swing.JTextField();
        btn_Ingresar = new javax.swing.JButton();
        lbl_Logo = new javax.swing.JLabel();
        lbl_Bienvenido = new javax.swing.JLabel();
        ckb_Recuerdame = new javax.swing.JCheckBox();
        txtp_Contraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel20.setText("Usuario:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, 30));

        lbl_Contraseña.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Contraseña.setText("Contraseña:");
        getContentPane().add(lbl_Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, 30));

        txt_Usuario.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Usuario.setForeground(new java.awt.Color(255, 0, 0));
        txt_Usuario.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_UsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_UsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(txt_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 130, 30));

        btn_Ingresar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Ingresar.setForeground(new java.awt.Color(255, 0, 0));
        btn_Ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/btn_Ingresar.png"))); // NOI18N
        btn_Ingresar.setBorderPainted(false);
        btn_Ingresar.setContentAreaFilled(false);
        btn_Ingresar.setFocusPainted(false);
        btn_Ingresar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Ingresar Press.png"))); // NOI18N
        btn_Ingresar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Ingresar Mousse.png"))); // NOI18N
        btn_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 120, 40));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/LogoBlanco.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        lbl_Bienvenido.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 48)); // NOI18N
        lbl_Bienvenido.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Bienvenido.setText("Bienvenido");
        getContentPane().add(lbl_Bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        ckb_Recuerdame.setBackground(new java.awt.Color(255, 204, 255));
        ckb_Recuerdame.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        ckb_Recuerdame.setText("Recuérdame");
        ckb_Recuerdame.setContentAreaFilled(false);
        ckb_Recuerdame.setFocusPainted(false);
        getContentPane().add(ckb_Recuerdame, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, -1));

        txtp_Contraseña.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txtp_Contraseña.setForeground(new java.awt.Color(255, 0, 0));
        txtp_Contraseña.setSelectionColor(new java.awt.Color(255, 102, 51));
        txtp_Contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtp_ContraseñaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtp_ContraseñaKeyTyped(evt);
            }
        });
        getContentPane().add(txtp_Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 130, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Fondo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IngresarActionPerformed

        iniciarSesion();
    }//GEN-LAST:event_btn_IngresarActionPerformed

    private void txt_UsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UsuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            iniciarSesion();
    }//GEN-LAST:event_txt_UsuarioKeyPressed

    private void txtp_ContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtp_ContraseñaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            iniciarSesion();
    }//GEN-LAST:event_txtp_ContraseñaKeyPressed

    private void txt_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_UsuarioKeyTyped
        if (!v.maximoLargo(txt_Usuario.getText(), 8))
            evt.consume();
    }//GEN-LAST:event_txt_UsuarioKeyTyped

    private void txtp_ContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtp_ContraseñaKeyTyped
        if (!v.maximoLargo(txtp_Contraseña.getText(), 20))
            evt.consume();
    }//GEN-LAST:event_txtp_ContraseñaKeyTyped

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Ingresar;
    private javax.swing.JCheckBox ckb_Recuerdame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel lbl_Bienvenido;
    private javax.swing.JLabel lbl_Contraseña;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JTextField txt_Usuario;
    private javax.swing.JPasswordField txtp_Contraseña;
    // End of variables declaration//GEN-END:variables

    private void iniciarSesion() {
        //Validamos campos vacios
        if (!txt_Usuario.getText().isEmpty() && !txtp_Contraseña.getText().isEmpty()) {
            //Validamos si el usuario existe
            Empleado emp = this.emp.buscarEmpleado(txt_Usuario.getText().trim(), txtp_Contraseña.getText());
            
            if (emp != null) {
                if (!emp.buscarEmpleado(txt_Usuario.getText().trim(), txtp_Contraseña.getText()).getTipo().equals("Inhabilitado")) {
                    
                    Seleccion seleccion = new Seleccion(emp);
                    this.setVisible(false);
                    seleccion.setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "El usuario ingresado está inhabilitado, imposible iniciar la sesión.","Usuario inválido",JOptionPane.ERROR_MESSAGE);
                
            }
            else
                JOptionPane.showMessageDialog(null, "El usuario ingresado no existe!","Usuario inválido",JOptionPane.ERROR_MESSAGE);
        }
        else
            getFocus();
        
    }
    
    private void getFocus(){
        JOptionPane.showMessageDialog(null, "Algunos campos están vacíos.","Campos vacíos",JOptionPane.WARNING_MESSAGE);

        if (txt_Usuario.getText().isEmpty())
            txt_Usuario.requestFocus();
        else if (txtp_Contraseña.getText().isEmpty()) 
            txtp_Contraseña.requestFocus();
    }
}