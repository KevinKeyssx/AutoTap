package Interfaz;

import Extra.Obtener;

/**
 * @author keiss
 */
public class Vehiculo extends javax.swing.JDialog {

    Obtener get = new Obtener();
    public Vehiculo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmb_Modelo = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        cmb_Marca = new javax.swing.JComboBox<>();
        lbl_Puertas1 = new javax.swing.JLabel();
        spr_Año = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_GuardarTabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_Modelo.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        cmb_Modelo.setForeground(new java.awt.Color(255, 51, 51));
        cmb_Modelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cruze", "Aveo", "Trax", "Orlando", "Spark", "Freemont", "Doblò", "Punto", "Accord", "Jazz", "Civic", "Ix20", "I10", "Santa Fe", "Veloster", "I40", "Elantra", "I30", "Picanto", "Rio", "Sportage", "Venga", "Optima" }));
        cmb_Modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ModeloActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_Modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 110, 30));

        jLabel30.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 0));
        jLabel30.setText("*Marca:");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 50, 30));

        cmb_Marca.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        cmb_Marca.setForeground(new java.awt.Color(255, 51, 51));
        cmb_Marca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHEVROLET", "HYUNDAI", "HONDA", "KIA", "NISSAN", "PEUGEOT", "SUBARU", "SUZUKI", "TOYOTA", "VOLVO" }));
        getContentPane().add(cmb_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 130, 30));

        lbl_Puertas1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Puertas1.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Puertas1.setText("Año:");
        getContentPane().add(lbl_Puertas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, 30));

        spr_Año.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Año.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_AñoStateChanged(evt);
            }
        });
        getContentPane().add(spr_Año, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 60, 30));

        jLabel31.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 0));
        jLabel31.setText("*Modelo:");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 60, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Año"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 620, 210));

        btn_GuardarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Añadir.png"))); // NOI18N
        btn_GuardarTabla.setBorderPainted(false);
        btn_GuardarTabla.setContentAreaFilled(false);
        btn_GuardarTabla.setFocusPainted(false);
        btn_GuardarTabla.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Añadir Press.png"))); // NOI18N
        btn_GuardarTabla.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Añadir Mouse.png"))); // NOI18N
        btn_GuardarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarTablaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_GuardarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 30, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_ModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_ModeloActionPerformed

    private void spr_AñoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_AñoStateChanged
        if (spr_Año.getValue().toString().equals("1899"))
        spr_Año.setValue(1900);
        else if (spr_Año.getValue().toString().equals((Integer.valueOf(get.añoActual()) + 1) + ""))
        spr_Año.setValue(Integer.valueOf(get.añoActual()));
    }//GEN-LAST:event_spr_AñoStateChanged

    private void btn_GuardarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarTablaActionPerformed
//        if (!cmb_Servicios.getSelectedItem().toString().isEmpty()) {
//            tablaCotizacion();
//        }
//        else
//        JOptionPane.showMessageDialog(null, "Favor ingresa un servicio para poder cargarlo en al tabla.","Servicio vacío!",JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_btn_GuardarTablaActionPerformed

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
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Vehiculo dialog = new Vehiculo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_GuardarTabla;
    private javax.swing.JComboBox<String> cmb_Marca;
    private javax.swing.JComboBox<String> cmb_Modelo;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_Puertas1;
    private javax.swing.JSpinner spr_Año;
    // End of variables declaration//GEN-END:variables
}
