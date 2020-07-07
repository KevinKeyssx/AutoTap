package Interfaz;

import Clases.Cliente;
import Clases.Cotizacion;
import Clases.Empleado;
import Clases.Empresa;
import Clases.Servicio;
import Extra.Obtener;
import Extra.PDF;
import Extra.Validar;
import com.itextpdf.text.DocumentException;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * @author keiss
 */
public class Seleccion extends javax.swing.JFrame {
    Obtener get = new Obtener();
    PDF pdf = new PDF();
    Empleado emp = new Empleado();
    Validar v = new Validar();
    DefaultTableModel mCotizacion;
    DefaultTableModel mEmpleado;
    DefaultTableModel mEmpresa;
    DefaultTableModel mServicio;
    DefaultTableModel mCliente;
    
    static long neto = 0,total = 0;
    static double iva = 0;
     
    public Seleccion() {
        initComponents();
    }

    public Seleccion(Empleado emp) {
        initComponents();
        this.emp = emp;
        //CAMBIAR DESPUES
        btn_Auto.setVisible(false);
        btn_Guardar.setEnabled(false);

        setTitle("Selección - AutoTap");
        setLocationRelativeTo(null); //Centrar el JFrame al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/Complementos/Icono.png")).getImage()); // Icono del programa
        setResizable(false);
        setSize(1130, 710);
        //Bienvenida al empleaado logeado
        lbl_Usuario.setText("Hola "+emp.getNombre()+" "+emp.getPaterno()+" "+emp.getMaterno());
        //Habilitar solo al adm junto con la configuracion
        privilegiosDelPersonal();
        //Oculta paneles importantes
        ocultarEtiquetas();
        ocultarPaneles();
        desactivarDestellos();
        Destello_Home.setVisible(true);
        //Habilitaa lbl de inicio
        lbl_Home.setVisible(true);
        pnl_Home.setVisible(true);
        pnl_Menu1.setVisible(false);
        //Carga los combobox
        cargarFormaPago();//Carga el combobox forma de pago
        cargarCMBEmpleado();
        cargarCMBServicios();
        cargarCMBEmpleado();
        rdb_Cliente.setSelected(true);
        cargarCMBRut(rdb_Cliente.isSelected());
        //Autocompletar los cmb
        cmb_Servicios.setEditable(true);
        AutoCompleteDecorator.decorate(cmb_Servicios);//Auto Completa en el combobox
        cmb_Rut.setEditable(true);
        AutoCompleteDecorator.decorate(cmb_Rut);//Auto Completa en el combobox
        cmb_Marca.setEditable(true);
        AutoCompleteDecorator.decorate(cmb_Marca);//Auto Completa en el combobox
        cmb_Modelo.setEditable(true);
        AutoCompleteDecorator.decorate(cmb_Modelo);//Auto Completa en el combobox
            
        buscaPrecioServicio();
        //Deshabilitar campos importantes
        txt_NETO.setEditable(false);
        txt_NETO.setHorizontalAlignment(4);
        txt_IVA.setEditable(false);
        txt_IVA.setHorizontalAlignment(4);
        txt_Total.setEditable(false);
        txt_Total.setHorizontalAlignment(4);
        txt_Precio.setHorizontalAlignment(4);
        Cotizacion coti = new Cotizacion();
        
        //lbl_Codigo.setText(String.valueOf(Integer.valueOf(empre.obtenerCodigo().getId() ) + 1) );
        int cotizacion = coti.obtenerCodigo().getId() + 1 ;
        
        lbl_Cotizacion.setText("Cotización N° 00" +  cotizacion) ;//Obtener la ultima cotizacion + 1
        spr_Año.setValue(2016);//Definir por configuracion
        //Cambia el tamaño por defecto de las columnas de las tablas
        tb_Cotizacion.getColumnModel().getColumn(1).setPreferredWidth(450);
        //Ajustar tamañño EMPLEADO
        tb_Empleado.getColumnModel().getColumn(1).setPreferredWidth(450);
        //Ajustar tamañp AGENDA
        tb_Agenda.getColumnModel().getColumn(0).setPreferredWidth(200);
        //Ajustar tamaño SERVICIOS
        tb_Servicio.getColumnModel().getColumn(0).setPreferredWidth(140);//Codigo
        tb_Servicio.getColumnModel().getColumn(1).setPreferredWidth(500);//Servicio
        tb_Servicio.getColumnModel().getColumn(2).setPreferredWidth(140);//Precio
        //Ajustar tamaño EMPRESA
        tb_Empresa.getColumnModel().getColumn(0).setPreferredWidth(70);
        tb_Empresa.getColumnModel().getColumn(1).setPreferredWidth(200);
        tb_Empresa.getColumnModel().getColumn(2).setPreferredWidth(200);
        tb_Empresa.getColumnModel().getColumn(5).setPreferredWidth(200);
        tb_Empresa.getColumnModel().getColumn(6).setPreferredWidth(40);
        //Ajustar tamaño CLIENTE
        tb_Cliente.getColumnModel().getColumn(0).setPreferredWidth(40);
        tb_Cliente.getColumnModel().getColumn(1).setPreferredWidth(200);
        tb_Cliente.getColumnModel().getColumn(3).setPreferredWidth(100);
        tb_Cliente.getColumnModel().getColumn(5).setPreferredWidth(100);
        tb_Cliente.getColumnModel().getColumn(6).setPreferredWidth(20);
        //Modificamos el alto de cada todas las tablas
        setFilaAlto(30);
        
        //Cargar tablas de gestiones
        tablaEmpleado(false);
        tablaEmpresa(false);
        tablaServicio(false);
        tablaCliente(false);
        
        
//        String asiento = "<html><body>Cantidad de<br> Asientos: </body></html>";
//        lbl_CanditdadAsientos.setText(asiento);
//        String codera = "<html><body>Cantidad de<br> Coderas: </body></html>";
//        lbl_CanditdadCoderas.setText(codera);

        cerrar();
    }
    
    private void cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e){
                    ejecucion();
                }
            });
            
            this.setVisible(true);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void ejecucion(){
        Object[] options = { "Cerrar sesión", "Salir", "Cancelar" };
        int seleccion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea salir de AutoTap?", "Aviso!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
   
        switch (seleccion) {
            case 1:
                System.exit(0);
            break;
                
            case 0:
                cerrarSesion();
            break;            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoCliente = new javax.swing.ButtonGroup();
        pnl_Menu = new javax.swing.JPanel();
        btn_ConfiguracionMenu = new javax.swing.JButton();
        btn_HomeMenu = new javax.swing.JButton();
        btn_HistorialMenu = new javax.swing.JButton();
        btn_CotizacionMenu = new javax.swing.JButton();
        btn_AgendaMenu = new javax.swing.JButton();
        Destello_Historial = new javax.swing.JLabel();
        Destello_Home = new javax.swing.JLabel();
        Destello_Configuracion = new javax.swing.JLabel();
        Destello_Agenda = new javax.swing.JLabel();
        Destello_Cotizacion = new javax.swing.JLabel();
        pnl_Menu1 = new javax.swing.JPanel();
        btn_ServicioMenu = new javax.swing.JButton();
        btn_EmpleadoMenu = new javax.swing.JButton();
        btn_EmpresaMenu = new javax.swing.JButton();
        Destello_Empresa = new javax.swing.JLabel();
        Destello_Productos = new javax.swing.JLabel();
        btn_ClienteMenu = new javax.swing.JButton();
        Destello_Empleado = new javax.swing.JLabel();
        Destello_Cliente = new javax.swing.JLabel();
        lbl_Logo = new javax.swing.JLabel();
        btn_Menu = new javax.swing.JButton();
        lbl_Home = new javax.swing.JLabel();
        lbl_Agenda = new javax.swing.JLabel();
        pnl_Home = new javax.swing.JLayeredPane();
        btn_Inicio = new javax.swing.JButton();
        btn_Agenda = new javax.swing.JButton();
        btn_Configuracion = new javax.swing.JButton();
        btn_Empleados = new javax.swing.JButton();
        btnCotizacion = new javax.swing.JButton();
        btn_Servicios = new javax.swing.JButton();
        btn_Empresa = new javax.swing.JButton();
        btn_Historial = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ckb_Sonido = new javax.swing.JCheckBox();
        btn_ApagarSesion = new javax.swing.JButton();
        lbl_Usuario = new javax.swing.JLabel();
        btn_Cliente = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        lbl_Cotizacion = new javax.swing.JLabel();
        pnl_Cotizacion = new javax.swing.JLayeredPane();
        lbl_Contacto = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Cotizacion = new javax.swing.JTable();
        txt_Fono = new javax.swing.JTextField();
        lbl_Fono = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btn_GuardarTabla = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txt_NETO = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_IVA = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_Total = new javax.swing.JTextField();
        btn_Cancelar = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();
        btn_Realizar = new javax.swing.JButton();
        cmb_FormaPago = new javax.swing.JComboBox<>();
        cmb_Servicios = new javax.swing.JComboBox<>();
        txt_Correo = new javax.swing.JTextField();
        cmb_Modelo = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cmb_Marca = new javax.swing.JComboBox<>();
        lbl_Puertas1 = new javax.swing.JLabel();
        spr_Año = new javax.swing.JSpinner();
        spr_Cantidad = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        btn_QuitarServicio = new javax.swing.JButton();
        cmb_Rut = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txt_Precio = new javax.swing.JTextField();
        ckb_Nuevo = new javax.swing.JCheckBox();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        rdb_Cliente = new javax.swing.JRadioButton();
        rdb_Empresa = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        txt_Contacto = new javax.swing.JTextField();
        btn_Auto = new javax.swing.JButton();
        pnl_Empleado = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Empleado = new javax.swing.JTable();
        cmb_BusEmpleado = new javax.swing.JComboBox<>();
        txt_BuscarEmpleado = new javax.swing.JTextField();
        lbl_Bus = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btn_Agregar = new javax.swing.JButton();
        btn_ModificarEmpleado = new javax.swing.JButton();
        btn_InhabilitarEmpleado = new javax.swing.JButton();
        lbl_Empleado = new javax.swing.JLabel();
        pnl_Empresa = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Empresa = new javax.swing.JTable();
        cmb_BusEmpresa = new javax.swing.JComboBox<>();
        txt_BuscarEmpresa = new javax.swing.JTextField();
        lbl_Bus1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btn_AgregarEmpresa = new javax.swing.JButton();
        btn_ModificarEmpresa = new javax.swing.JButton();
        btn_InhabilitarEmpresa = new javax.swing.JButton();
        pnl_Agenda = new javax.swing.JLayeredPane();
        lbl_DetalleAgenda = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        cal_Agenda = new com.toedter.calendar.JCalendar();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Agenda = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_Monto = new javax.swing.JTextField();
        spr_HoraAgen = new com.toedter.components.JSpinField();
        spr_MinAgen = new com.toedter.components.JSpinField();
        pnl_Servicios = new javax.swing.JLayeredPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Servicio = new javax.swing.JTable();
        cmb_BusServicio = new javax.swing.JComboBox<>();
        txt_BuscarServicio = new javax.swing.JTextField();
        lbl_Bus2 = new javax.swing.JLabel();
        lbl_ServicioIgual = new javax.swing.JLabel();
        btn_AgregarServicio = new javax.swing.JButton();
        btn_ModificarServicio = new javax.swing.JButton();
        btn_InhabilitarServicio = new javax.swing.JButton();
        cmb_PrecioServicio = new javax.swing.JComboBox<>();
        lbl_Servicios = new javax.swing.JLabel();
        lbl_Empresa = new javax.swing.JLabel();
        pnl_Historial = new javax.swing.JLayeredPane();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_Historial = new javax.swing.JTable();
        txt_BusHistorial = new javax.swing.JTextField();
        jdc_Historial = new com.toedter.calendar.JDateChooser();
        ckb_Dia = new javax.swing.JCheckBox();
        ckb_Mes = new javax.swing.JCheckBox();
        ckb_Año = new javax.swing.JCheckBox();
        cmb_BusHistorial = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        cmb_Total = new javax.swing.JComboBox<>();
        lbl_Historial = new javax.swing.JLabel();
        pnl_Cliente = new javax.swing.JLayeredPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_Cliente = new javax.swing.JTable();
        cmb_Cliente = new javax.swing.JComboBox<>();
        txt_Cliente = new javax.swing.JTextField();
        lbl_Bus3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btn_Agregar1 = new javax.swing.JButton();
        btn_ModificarEmpleado1 = new javax.swing.JButton();
        btn_InhabilitarEmpleado1 = new javax.swing.JButton();
        btn_Menu2 = new javax.swing.JButton();
        lbl_Cliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_Home = new javax.swing.JMenuItem();
        menu_Cotizacion = new javax.swing.JMenuItem();
        menu_Agenda = new javax.swing.JMenuItem();
        menu_Historial = new javax.swing.JMenuItem();
        menu_Configuracion = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menu_CerrarSesion = new javax.swing.JMenuItem();
        menu_Salir = new javax.swing.JMenuItem();
        menu = new javax.swing.JMenu();
        menu_Empleado = new javax.swing.JMenuItem();
        menu_Cliente = new javax.swing.JMenuItem();
        menu_Empresa = new javax.swing.JMenuItem();
        menu_Servicio = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menu_NuevoEmpreado = new javax.swing.JMenuItem();
        menu_NuevoCliente = new javax.swing.JMenuItem();
        menu_NuevaEmpresa = new javax.swing.JMenuItem();
        menu_NuevoServicio = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_Menu.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ConfiguracionMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Configuración Menu.png"))); // NOI18N
        btn_ConfiguracionMenu.setBorderPainted(false);
        btn_ConfiguracionMenu.setContentAreaFilled(false);
        btn_ConfiguracionMenu.setFocusPainted(false);
        btn_ConfiguracionMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Configuración Menu Mouse.png"))); // NOI18N
        btn_ConfiguracionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfiguracionMenuActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_ConfiguracionMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 60, 50));

        btn_HomeMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Home Menu.png"))); // NOI18N
        btn_HomeMenu.setBorderPainted(false);
        btn_HomeMenu.setContentAreaFilled(false);
        btn_HomeMenu.setFocusPainted(false);
        btn_HomeMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Home Menu Mouse.png"))); // NOI18N
        btn_HomeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HomeMenuActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_HomeMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 50));

        btn_HistorialMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Historial Menu.png"))); // NOI18N
        btn_HistorialMenu.setBorderPainted(false);
        btn_HistorialMenu.setContentAreaFilled(false);
        btn_HistorialMenu.setFocusPainted(false);
        btn_HistorialMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Historial Menu Mouse.png"))); // NOI18N
        btn_HistorialMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HistorialMenuActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_HistorialMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 60, 50));

        btn_CotizacionMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cotización Menu.png"))); // NOI18N
        btn_CotizacionMenu.setBorderPainted(false);
        btn_CotizacionMenu.setContentAreaFilled(false);
        btn_CotizacionMenu.setFocusPainted(false);
        btn_CotizacionMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cotización Mouse Menu.png"))); // NOI18N
        btn_CotizacionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CotizacionMenuActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_CotizacionMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 60, 50));

        btn_AgendaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Calendario Menu.png"))); // NOI18N
        btn_AgendaMenu.setBorderPainted(false);
        btn_AgendaMenu.setContentAreaFilled(false);
        btn_AgendaMenu.setFocusPainted(false);
        btn_AgendaMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Calendario Menu Mouse.png"))); // NOI18N
        btn_AgendaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgendaMenuActionPerformed(evt);
            }
        });
        pnl_Menu.add(btn_AgendaMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 60, 50));

        Destello_Historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu.add(Destello_Historial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 100, 90));

        Destello_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu.add(Destello_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 90));

        Destello_Configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu.add(Destello_Configuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 100, 100));

        Destello_Agenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu.add(Destello_Agenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 100, 90));

        Destello_Cotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu.add(Destello_Cotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 100, 90));

        getContentPane().add(pnl_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 420));

        pnl_Menu1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Menu1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_Menu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ServicioMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Productos Menu.png"))); // NOI18N
        btn_ServicioMenu.setBorderPainted(false);
        btn_ServicioMenu.setContentAreaFilled(false);
        btn_ServicioMenu.setFocusPainted(false);
        btn_ServicioMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Productos Menu Mouse.png"))); // NOI18N
        btn_ServicioMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ServicioMenuActionPerformed(evt);
            }
        });
        pnl_Menu1.add(btn_ServicioMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 60, 50));

        btn_EmpleadoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empleado Menu.png"))); // NOI18N
        btn_EmpleadoMenu.setBorderPainted(false);
        btn_EmpleadoMenu.setContentAreaFilled(false);
        btn_EmpleadoMenu.setFocusPainted(false);
        btn_EmpleadoMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empleado Mouse Menu.png"))); // NOI18N
        btn_EmpleadoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmpleadoMenuActionPerformed(evt);
            }
        });
        pnl_Menu1.add(btn_EmpleadoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, 50));

        btn_EmpresaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empresa Menu.png"))); // NOI18N
        btn_EmpresaMenu.setBorderPainted(false);
        btn_EmpresaMenu.setContentAreaFilled(false);
        btn_EmpresaMenu.setFocusPainted(false);
        btn_EmpresaMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empresa Menu Mouse.png"))); // NOI18N
        btn_EmpresaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmpresaMenuActionPerformed(evt);
            }
        });
        pnl_Menu1.add(btn_EmpresaMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 60, 50));

        Destello_Empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu1.add(Destello_Empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 100, 90));

        Destello_Productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu1.add(Destello_Productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 100, 90));

        btn_ClienteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cliente Menu.png"))); // NOI18N
        btn_ClienteMenu.setBorderPainted(false);
        btn_ClienteMenu.setContentAreaFilled(false);
        btn_ClienteMenu.setFocusPainted(false);
        btn_ClienteMenu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cliente Menu Mouse.png"))); // NOI18N
        btn_ClienteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClienteMenuActionPerformed(evt);
            }
        });
        pnl_Menu1.add(btn_ClienteMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 60, 50));

        Destello_Empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu1.add(Destello_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 90));

        Destello_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Destello.png"))); // NOI18N
        pnl_Menu1.add(Destello_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 100, 90));

        getContentPane().add(pnl_Menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 80, 100, 330));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Icono2.png"))); // NOI18N
        getContentPane().add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 140, -1));

        btn_Menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Menú1.png"))); // NOI18N
        btn_Menu.setBorderPainted(false);
        btn_Menu.setContentAreaFilled(false);
        btn_Menu.setFocusPainted(false);
        btn_Menu.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Menú1 Press.png"))); // NOI18N
        btn_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MenuActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 40, 40));

        lbl_Home.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Home.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Home.setText("Home");
        getContentPane().add(lbl_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, 40));

        lbl_Agenda.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Agenda.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Agenda.setText("Agenda");
        getContentPane().add(lbl_Agenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 60));

        pnl_Home.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_Inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Home1.png"))); // NOI18N
        btn_Inicio.setBorderPainted(false);
        btn_Inicio.setContentAreaFilled(false);
        btn_Inicio.setFocusPainted(false);
        btn_Inicio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Home Mouse.png"))); // NOI18N
        btn_Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_InicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_InicioMouseEntered(evt);
            }
        });
        btn_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InicioActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Inicio);
        btn_Inicio.setBounds(80, 100, 120, 120);

        btn_Agenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Calendario.png"))); // NOI18N
        btn_Agenda.setBorderPainted(false);
        btn_Agenda.setContentAreaFilled(false);
        btn_Agenda.setFocusPainted(false);
        btn_Agenda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Calendario Mouse.png"))); // NOI18N
        btn_Agenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_AgendaMouseEntered(evt);
            }
        });
        btn_Agenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgendaActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Agenda);
        btn_Agenda.setBounds(480, 100, 120, 120);

        btn_Configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Configuración.png"))); // NOI18N
        btn_Configuracion.setBorderPainted(false);
        btn_Configuracion.setContentAreaFilled(false);
        btn_Configuracion.setFocusPainted(false);
        btn_Configuracion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Configuración Mousse.png"))); // NOI18N
        btn_Configuracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ConfiguracionMouseEntered(evt);
            }
        });
        btn_Configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConfiguracionActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Configuracion);
        btn_Configuracion.setBounds(670, 290, 110, 110);

        btn_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empleado.png"))); // NOI18N
        btn_Empleados.setBorderPainted(false);
        btn_Empleados.setContentAreaFilled(false);
        btn_Empleados.setFocusPainted(false);
        btn_Empleados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empleado Mouse.png"))); // NOI18N
        btn_Empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EmpleadosMouseEntered(evt);
            }
        });
        btn_Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmpleadosActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Empleados);
        btn_Empleados.setBounds(80, 270, 120, 130);

        btnCotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cotización.png"))); // NOI18N
        btnCotizacion.setBorderPainted(false);
        btnCotizacion.setContentAreaFilled(false);
        btnCotizacion.setFocusPainted(false);
        btnCotizacion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cotización Mouse.png"))); // NOI18N
        btnCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCotizacionMouseEntered(evt);
            }
        });
        btnCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizacionActionPerformed(evt);
            }
        });
        pnl_Home.add(btnCotizacion);
        btnCotizacion.setBounds(280, 100, 120, 120);

        btn_Servicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Productos.png"))); // NOI18N
        btn_Servicios.setBorderPainted(false);
        btn_Servicios.setContentAreaFilled(false);
        btn_Servicios.setFocusPainted(false);
        btn_Servicios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Productos Mouse.png"))); // NOI18N
        btn_Servicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ServiciosMouseEntered(evt);
            }
        });
        btn_Servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ServiciosActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Servicios);
        btn_Servicios.setBounds(660, 100, 130, 120);

        btn_Empresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empresa.png"))); // NOI18N
        btn_Empresa.setBorderPainted(false);
        btn_Empresa.setContentAreaFilled(false);
        btn_Empresa.setFocusPainted(false);
        btn_Empresa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Empresa Mouse.png"))); // NOI18N
        btn_Empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EmpresaMouseEntered(evt);
            }
        });
        btn_Empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmpresaActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Empresa);
        btn_Empresa.setBounds(280, 270, 120, 130);

        btn_Historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Historial.png"))); // NOI18N
        btn_Historial.setBorderPainted(false);
        btn_Historial.setContentAreaFilled(false);
        btn_Historial.setFocusPainted(false);
        btn_Historial.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Historial Mouse.png"))); // NOI18N
        btn_Historial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_HistorialMouseEntered(evt);
            }
        });
        btn_Historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HistorialActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Historial);
        btn_Historial.setBounds(480, 270, 120, 130);

        jLabel3.setBackground(new java.awt.Color(51, 102, 255));
        jLabel3.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Home");
        pnl_Home.add(jLabel3);
        jLabel3.setBounds(120, 230, 70, 30);

        jLabel7.setBackground(new java.awt.Color(51, 102, 255));
        jLabel7.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Cotización");
        pnl_Home.add(jLabel7);
        jLabel7.setBounds(300, 230, 100, 30);

        jLabel8.setBackground(new java.awt.Color(51, 102, 255));
        jLabel8.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Ver Historial");
        pnl_Home.add(jLabel8);
        jLabel8.setBounds(500, 410, 110, 30);

        jLabel9.setBackground(new java.awt.Color(51, 102, 255));
        jLabel9.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Configuración");
        pnl_Home.add(jLabel9);
        jLabel9.setBounds(680, 400, 120, 50);

        jLabel10.setBackground(new java.awt.Color(51, 102, 255));
        jLabel10.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Gestionar Clientes");
        pnl_Home.add(jLabel10);
        jLabel10.setBounds(840, 230, 160, 30);

        jLabel12.setBackground(new java.awt.Color(51, 102, 255));
        jLabel12.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Gestionar Servicios");
        pnl_Home.add(jLabel12);
        jLabel12.setBounds(660, 230, 160, 30);

        jLabel13.setBackground(new java.awt.Color(51, 102, 255));
        jLabel13.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Ver Agenda");
        pnl_Home.add(jLabel13);
        jLabel13.setBounds(500, 230, 130, 30);

        jLabel14.setBackground(new java.awt.Color(51, 102, 255));
        jLabel14.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Getionar Empleado");
        pnl_Home.add(jLabel14);
        jLabel14.setBounds(70, 410, 150, 30);

        ckb_Sonido.setBackground(new java.awt.Color(51, 102, 255));
        ckb_Sonido.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        ckb_Sonido.setForeground(new java.awt.Color(255, 0, 0));
        ckb_Sonido.setSelected(true);
        ckb_Sonido.setText("Sonido");
        ckb_Sonido.setContentAreaFilled(false);
        pnl_Home.add(ckb_Sonido);
        ckb_Sonido.setBounds(900, 480, 110, 27);

        btn_ApagarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Off.png"))); // NOI18N
        btn_ApagarSesion.setBorderPainted(false);
        btn_ApagarSesion.setContentAreaFilled(false);
        btn_ApagarSesion.setFocusPainted(false);
        btn_ApagarSesion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Off Mouse.png"))); // NOI18N
        btn_ApagarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ApagarSesionActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_ApagarSesion);
        btn_ApagarSesion.setBounds(20, 450, 80, 62);

        lbl_Usuario.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 24)); // NOI18N
        lbl_Usuario.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Usuario.setText("--------");
        pnl_Home.add(lbl_Usuario);
        lbl_Usuario.setBounds(0, 10, 1040, 60);

        btn_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cliente.png"))); // NOI18N
        btn_Cliente.setBorderPainted(false);
        btn_Cliente.setContentAreaFilled(false);
        btn_Cliente.setFocusPainted(false);
        btn_Cliente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Cliente Mouse.png"))); // NOI18N
        btn_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ClienteMouseEntered(evt);
            }
        });
        btn_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClienteActionPerformed(evt);
            }
        });
        pnl_Home.add(btn_Cliente);
        btn_Cliente.setBounds(840, 100, 140, 120);

        jLabel22.setBackground(new java.awt.Color(51, 102, 255));
        jLabel22.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("Gestionar Empresas");
        pnl_Home.add(jLabel22);
        jLabel22.setBounds(270, 410, 160, 30);

        getContentPane().add(pnl_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1040, 530));

        lbl_Cotizacion.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Cotizacion.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Cotizacion.setText("Cotización");
        getContentPane().add(lbl_Cotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 500, 60));

        pnl_Cotizacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_Cotizacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Contacto.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Contacto.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Contacto.setText("*Contacto:");
        pnl_Cotizacion.add(lbl_Contacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, 30));

        txt_Nombre.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Nombre.setForeground(new java.awt.Color(255, 0, 0));
        txt_Nombre.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_NombreKeyTyped(evt);
            }
        });
        pnl_Cotizacion.add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 140, 30));

        jLabel19.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 0));
        jLabel19.setText("*Rut:");
        pnl_Cotizacion.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, -1, 30));

        jLabel20.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 0));
        jLabel20.setText("*Servicio:");
        pnl_Cotizacion.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 70, 30));

        tb_Cotizacion.setForeground(new java.awt.Color(255, 51, 51));
        tb_Cotizacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Servicio", "Cantidad", "Precio Unidad", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Cotizacion.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tb_Cotizacion.setSelectionForeground(new java.awt.Color(255, 51, 51));
        tb_Cotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_CotizacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Cotizacion);

        pnl_Cotizacion.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 820, 210));

        txt_Fono.setForeground(new java.awt.Color(255, 0, 0));
        txt_Fono.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Fono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_FonoKeyTyped(evt);
            }
        });
        pnl_Cotizacion.add(txt_Fono, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 150, 30));

        lbl_Fono.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Fono.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Fono.setText("Fono:");
        pnl_Cotizacion.add(lbl_Fono, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 50, 30));

        jLabel23.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 0));
        jLabel23.setText("Mail:");
        pnl_Cotizacion.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, -1, 30));

        jLabel24.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 0));
        jLabel24.setText("Precio:");
        pnl_Cotizacion.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 50, 30));

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
        pnl_Cotizacion.add(btn_GuardarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 190, 30, 30));

        jLabel26.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 51, 0));
        jLabel26.setText("NETO:");
        pnl_Cotizacion.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 240, -1, 30));

        txt_NETO.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_NETO.setForeground(new java.awt.Color(255, 51, 51));
        txt_NETO.setSelectionColor(new java.awt.Color(255, 102, 51));
        pnl_Cotizacion.add(txt_NETO, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 270, 110, 30));

        jLabel27.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 51, 0));
        jLabel27.setText("IVA:");
        pnl_Cotizacion.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 310, -1, 30));

        txt_IVA.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_IVA.setForeground(new java.awt.Color(255, 51, 51));
        txt_IVA.setSelectionColor(new java.awt.Color(255, 102, 51));
        pnl_Cotizacion.add(txt_IVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, 110, 30));

        jLabel28.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 51, 0));
        jLabel28.setText("Total:");
        pnl_Cotizacion.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 380, 40, 30));

        txt_Total.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Total.setForeground(new java.awt.Color(255, 51, 51));
        txt_Total.setSelectionColor(new java.awt.Color(255, 102, 51));
        pnl_Cotizacion.add(txt_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 410, 110, 30));

        btn_Cancelar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Cancelar.setForeground(new java.awt.Color(255, 51, 51));
        btn_Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Inhabilitar.png"))); // NOI18N
        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.setFocusPainted(false);
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });
        pnl_Cotizacion.add(btn_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, -1, 40));

        btn_Guardar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Guardar.setForeground(new java.awt.Color(255, 153, 51));
        btn_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Agendar.png"))); // NOI18N
        btn_Guardar.setText("Agendar");
        btn_Guardar.setFocusPainted(false);
        pnl_Cotizacion.add(btn_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 470, 120, 40));

        btn_Realizar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Realizar.setForeground(new java.awt.Color(51, 204, 0));
        btn_Realizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Habilitar.png"))); // NOI18N
        btn_Realizar.setText("Realizar");
        btn_Realizar.setFocusPainted(false);
        btn_Realizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RealizarActionPerformed(evt);
            }
        });
        pnl_Cotizacion.add(btn_Realizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 120, 40));

        cmb_FormaPago.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        cmb_FormaPago.setForeground(new java.awt.Color(255, 51, 51));
        cmb_FormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_FormaPago.setAutoscrolls(true);
        pnl_Cotizacion.add(cmb_FormaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 120, 30));

        cmb_Servicios.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        cmb_Servicios.setForeground(new java.awt.Color(255, 51, 51));
        cmb_Servicios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnl_Cotizacion.add(cmb_Servicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 330, 30));

        txt_Correo.setForeground(new java.awt.Color(255, 0, 0));
        txt_Correo.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CorreoActionPerformed(evt);
            }
        });
        pnl_Cotizacion.add(txt_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 150, 30));

        cmb_Modelo.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        cmb_Modelo.setForeground(new java.awt.Color(255, 51, 51));
        cmb_Modelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cruze", "Aveo", "Trax", "Orlando", "Spark", "Freemont", "Doblò", "Punto", "Accord", "Jazz", "Civic", "Ix20", "I10", "Santa Fe", "Veloster", "I40", "Elantra", "I30", "Picanto", "Rio", "Sportage", "Venga", "Optima" }));
        cmb_Modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ModeloActionPerformed(evt);
            }
        });
        pnl_Cotizacion.add(cmb_Modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 150, 30));

        jLabel29.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 51, 0));
        jLabel29.setText("*Pago:");
        pnl_Cotizacion.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 50, 30));

        jLabel30.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 0));
        jLabel30.setText("*Marca:");
        pnl_Cotizacion.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 50, 30));

        cmb_Marca.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        cmb_Marca.setForeground(new java.awt.Color(255, 51, 51));
        cmb_Marca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CHEVROLET", "HYUNDAI", "HONDA", "KIA", "NISSAN", "PEUGEOT", "SUBARU", "SUZUKI", "TOYOTA", "VOLVO" }));
        pnl_Cotizacion.add(cmb_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 140, 30));

        lbl_Puertas1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Puertas1.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Puertas1.setText("Año:");
        pnl_Cotizacion.add(lbl_Puertas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, 50, 30));

        spr_Año.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Año.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_AñoStateChanged(evt);
            }
        });
        pnl_Cotizacion.add(spr_Año, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 70, 30));

        spr_Cantidad.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        spr_Cantidad.setValue(1);
        spr_Cantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spr_CantidadStateChanged(evt);
            }
        });
        pnl_Cotizacion.add(spr_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 60, 30));

        jLabel31.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 51, 0));
        jLabel31.setText("*Modelo:");
        pnl_Cotizacion.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 60, 30));

        btn_QuitarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Quitar.png"))); // NOI18N
        btn_QuitarServicio.setBorderPainted(false);
        btn_QuitarServicio.setContentAreaFilled(false);
        btn_QuitarServicio.setFocusPainted(false);
        btn_QuitarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuitarServicioActionPerformed(evt);
            }
        });
        pnl_Cotizacion.add(btn_QuitarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 190, 30, 30));

        cmb_Rut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmb_Rut.setForeground(new java.awt.Color(255, 51, 51));
        cmb_Rut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_RutActionPerformed(evt);
            }
        });
        pnl_Cotizacion.add(cmb_Rut, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 120, 30));

        jLabel41.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 51, 0));
        jLabel41.setText("*Cantidad:");
        pnl_Cotizacion.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 80, 30));

        txt_Precio.setFont(new java.awt.Font("DS-Digital", 0, 18)); // NOI18N
        txt_Precio.setForeground(new java.awt.Color(255, 0, 51));
        txt_Precio.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PrecioKeyTyped(evt);
            }
        });
        pnl_Cotizacion.add(txt_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, 80, 30));

        ckb_Nuevo.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        ckb_Nuevo.setForeground(new java.awt.Color(255, 0, 0));
        ckb_Nuevo.setContentAreaFilled(false);
        ckb_Nuevo.setFocusPainted(false);
        pnl_Cotizacion.add(ckb_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 20, 30));

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        grupoCliente.add(rdb_Cliente);
        rdb_Cliente.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        rdb_Cliente.setForeground(new java.awt.Color(255, 0, 0));
        rdb_Cliente.setText("Cliente");
        rdb_Cliente.setContentAreaFilled(false);
        rdb_Cliente.setFocusPainted(false);
        rdb_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_ClienteMouseClicked(evt);
            }
        });
        rdb_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_ClienteActionPerformed(evt);
            }
        });
        jLayeredPane1.add(rdb_Cliente);
        rdb_Cliente.setBounds(10, 10, 80, 24);
        rdb_Cliente.getAccessibleContext().setAccessibleDescription("");

        grupoCliente.add(rdb_Empresa);
        rdb_Empresa.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        rdb_Empresa.setForeground(new java.awt.Color(255, 0, 0));
        rdb_Empresa.setText("Empresa");
        rdb_Empresa.setContentAreaFilled(false);
        rdb_Empresa.setFocusPainted(false);
        rdb_Empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_EmpresaMouseClicked(evt);
            }
        });
        rdb_Empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_EmpresaActionPerformed(evt);
            }
        });
        jLayeredPane1.add(rdb_Empresa);
        rdb_Empresa.setBounds(10, 40, 78, 30);

        pnl_Cotizacion.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 70, 100, 80));

        jLabel21.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 0));
        jLabel21.setText("*Nombre:");
        pnl_Cotizacion.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, 30));

        txt_Contacto.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Contacto.setForeground(new java.awt.Color(255, 0, 0));
        txt_Contacto.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Contacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ContactoKeyTyped(evt);
            }
        });
        pnl_Cotizacion.add(txt_Contacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 140, 30));

        btn_Auto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Vehiculo.png"))); // NOI18N
        btn_Auto.setBorderPainted(false);
        btn_Auto.setContentAreaFilled(false);
        btn_Auto.setFocusPainted(false);
        btn_Auto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Vehiculo Press.png"))); // NOI18N
        pnl_Cotizacion.add(btn_Auto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 60, 30));

        getContentPane().add(pnl_Cotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1050, 530));

        pnl_Empleado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_Empleado.setForeground(new java.awt.Color(255, 51, 51));
        tb_Empleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Usuario", "Nombre", "Rut", "Contraseña", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Empleado.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tb_Empleado.setSelectionForeground(new java.awt.Color(255, 51, 51));
        tb_Empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_EmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Empleado);

        pnl_Empleado.add(jScrollPane1);
        jScrollPane1.setBounds(50, 130, 960, 290);

        cmb_BusEmpleado.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_BusEmpleado.setForeground(new java.awt.Color(255, 102, 102));
        cmb_BusEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnl_Empleado.add(cmb_BusEmpleado);
        cmb_BusEmpleado.setBounds(240, 60, 170, 30);

        txt_BuscarEmpleado.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_BuscarEmpleado.setForeground(new java.awt.Color(255, 0, 0));
        txt_BuscarEmpleado.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_BuscarEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BuscarEmpleadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarEmpleadoKeyReleased(evt);
            }
        });
        pnl_Empleado.add(txt_BuscarEmpleado);
        txt_BuscarEmpleado.setBounds(460, 60, 400, 30);

        lbl_Bus.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Bus.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Bus.setText("Buscar:");
        pnl_Empleado.add(lbl_Bus);
        lbl_Bus.setBounds(160, 60, 50, 30);

        jLabel15.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("=");
        pnl_Empleado.add(jLabel15);
        jLabel15.setBounds(430, 60, 20, 30);

        btn_Agregar.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Agregar.setForeground(new java.awt.Color(0, 204, 204));
        btn_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Agregar.png"))); // NOI18N
        btn_Agregar.setText("Nuevo");
        btn_Agregar.setFocusPainted(false);
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });
        pnl_Empleado.add(btn_Agregar);
        btn_Agregar.setBounds(180, 450, 120, 40);

        btn_ModificarEmpleado.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_ModificarEmpleado.setForeground(new java.awt.Color(0, 204, 204));
        btn_ModificarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Editar.png"))); // NOI18N
        btn_ModificarEmpleado.setText("Modificar");
        btn_ModificarEmpleado.setFocusPainted(false);
        btn_ModificarEmpleado.setFocusable(false);
        btn_ModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarEmpleadoActionPerformed(evt);
            }
        });
        pnl_Empleado.add(btn_ModificarEmpleado);
        btn_ModificarEmpleado.setBounds(460, 450, 130, 40);

        btn_InhabilitarEmpleado.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_InhabilitarEmpleado.setForeground(new java.awt.Color(255, 102, 102));
        btn_InhabilitarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Inhabilitar.png"))); // NOI18N
        btn_InhabilitarEmpleado.setText("Inhabilitar");
        btn_InhabilitarEmpleado.setFocusPainted(false);
        btn_InhabilitarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InhabilitarEmpleadoActionPerformed(evt);
            }
        });
        pnl_Empleado.add(btn_InhabilitarEmpleado);
        btn_InhabilitarEmpleado.setBounds(750, 450, 140, 40);

        getContentPane().add(pnl_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1060, 530));

        lbl_Empleado.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Empleado.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Empleado.setText("Gestionar Empleado");
        getContentPane().add(lbl_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 390, 40));

        pnl_Empresa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_Empresa.setForeground(new java.awt.Color(255, 51, 51));
        tb_Empresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empresa", "Razón Social", "Giro", "Rut", "Teléfono", "Dirección", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Empresa.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tb_Empresa.setSelectionForeground(new java.awt.Color(255, 51, 51));
        tb_Empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_EmpresaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_Empresa);

        pnl_Empresa.add(jScrollPane3);
        jScrollPane3.setBounds(50, 130, 960, 290);

        cmb_BusEmpresa.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_BusEmpresa.setForeground(new java.awt.Color(255, 102, 102));
        cmb_BusEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empresa", "Razón Social", "Giro", "Rut", "Teléfono", "Dirección", "Activo" }));
        pnl_Empresa.add(cmb_BusEmpresa);
        cmb_BusEmpresa.setBounds(240, 60, 170, 30);

        txt_BuscarEmpresa.setForeground(new java.awt.Color(255, 0, 0));
        txt_BuscarEmpresa.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_BuscarEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BuscarEmpresaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarEmpresaKeyReleased(evt);
            }
        });
        pnl_Empresa.add(txt_BuscarEmpresa);
        txt_BuscarEmpresa.setBounds(460, 60, 400, 30);

        lbl_Bus1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Bus1.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Bus1.setText("Buscar:");
        pnl_Empresa.add(lbl_Bus1);
        lbl_Bus1.setBounds(160, 60, 50, 30);

        jLabel16.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("=");
        pnl_Empresa.add(jLabel16);
        jLabel16.setBounds(430, 60, 20, 30);

        btn_AgregarEmpresa.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_AgregarEmpresa.setForeground(new java.awt.Color(0, 204, 204));
        btn_AgregarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Agregar.png"))); // NOI18N
        btn_AgregarEmpresa.setText("Nuevo");
        btn_AgregarEmpresa.setFocusPainted(false);
        btn_AgregarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarEmpresaActionPerformed(evt);
            }
        });
        pnl_Empresa.add(btn_AgregarEmpresa);
        btn_AgregarEmpresa.setBounds(180, 450, 120, 40);

        btn_ModificarEmpresa.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_ModificarEmpresa.setForeground(new java.awt.Color(0, 204, 204));
        btn_ModificarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Editar.png"))); // NOI18N
        btn_ModificarEmpresa.setText("Modificar");
        btn_ModificarEmpresa.setFocusPainted(false);
        btn_ModificarEmpresa.setFocusable(false);
        btn_ModificarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarEmpresaActionPerformed(evt);
            }
        });
        pnl_Empresa.add(btn_ModificarEmpresa);
        btn_ModificarEmpresa.setBounds(460, 450, 130, 40);

        btn_InhabilitarEmpresa.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_InhabilitarEmpresa.setForeground(new java.awt.Color(255, 102, 102));
        btn_InhabilitarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Inhabilitar.png"))); // NOI18N
        btn_InhabilitarEmpresa.setText("Inactivar");
        btn_InhabilitarEmpresa.setFocusPainted(false);
        btn_InhabilitarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InhabilitarEmpresaActionPerformed(evt);
            }
        });
        pnl_Empresa.add(btn_InhabilitarEmpresa);
        btn_InhabilitarEmpresa.setBounds(750, 450, 140, 40);

        getContentPane().add(pnl_Empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1070, 530));

        pnl_Agenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl_DetalleAgenda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_DetalleAgenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_Agenda.add(lbl_DetalleAgenda);
        lbl_DetalleAgenda.setBounds(710, 280, 320, 140);

        jButton2.setText("Agendar");
        pnl_Agenda.add(jButton2);
        jButton2.setBounds(920, 80, 90, 30);

        cal_Agenda.setDoubleBuffered(false);
        cal_Agenda.setWeekOfYearVisible(false);
        cal_Agenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cal_AgendaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cal_AgendaMouseExited(evt);
            }
        });
        pnl_Agenda.add(cal_Agenda);
        cal_Agenda.setBounds(710, 80, 190, 140);

        jLabel4.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Detalle:");
        pnl_Agenda.add(jLabel4);
        jLabel4.setBounds(40, 40, 80, 30);

        jLabel5.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText(":");
        pnl_Agenda.add(jLabel5);
        jLabel5.setBounds(820, 230, 10, 30);

        tb_Agenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Hora", "Código Cotización", "Abono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tb_Agenda);

        pnl_Agenda.add(jScrollPane4);
        jScrollPane4.setBounds(30, 80, 650, 410);

        jButton4.setText("Aceptar");
        pnl_Agenda.add(jButton4);
        jButton4.setBounds(900, 450, 90, 40);

        jButton6.setText("Cancelar");
        pnl_Agenda.add(jButton6);
        jButton6.setBounds(760, 450, 90, 40);

        jLabel6.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Selecciona:");
        pnl_Agenda.add(jLabel6);
        jLabel6.setBounds(720, 40, 80, 30);

        jLabel11.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("Abono:");
        pnl_Agenda.add(jLabel11);
        jLabel11.setBounds(920, 120, 90, 30);

        jLabel17.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("Hora:");
        pnl_Agenda.add(jLabel17);
        jLabel17.setBounds(720, 230, 40, 30);

        txt_Monto.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_MontoKeyTyped(evt);
            }
        });
        pnl_Agenda.add(txt_Monto);
        txt_Monto.setBounds(920, 160, 90, 30);

        spr_HoraAgen.setMaximum(23);
        spr_HoraAgen.setMinimum(0);
        spr_HoraAgen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spr_HoraAgenKeyTyped(evt);
            }
        });
        pnl_Agenda.add(spr_HoraAgen);
        spr_HoraAgen.setBounds(770, 230, 40, 30);

        spr_MinAgen.setMaximum(59);
        spr_MinAgen.setMinimum(0);
        pnl_Agenda.add(spr_MinAgen);
        spr_MinAgen.setBounds(830, 230, 40, 30);

        getContentPane().add(pnl_Agenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1080, 530));

        pnl_Servicios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_Servicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tb_Servicio.setForeground(new java.awt.Color(255, 51, 51));
        tb_Servicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Servicio", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        tb_Servicio.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tb_Servicio.setSelectionForeground(new java.awt.Color(255, 51, 51));
        tb_Servicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ServicioMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_Servicio);

        pnl_Servicios.add(jScrollPane5);
        jScrollPane5.setBounds(50, 130, 960, 290);

        cmb_BusServicio.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_BusServicio.setForeground(new java.awt.Color(255, 102, 102));
        cmb_BusServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servicio", "Código", "Precio" }));
        cmb_BusServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_BusServicioActionPerformed(evt);
            }
        });
        pnl_Servicios.add(cmb_BusServicio);
        cmb_BusServicio.setBounds(240, 60, 170, 30);

        txt_BuscarServicio.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_BuscarServicio.setForeground(new java.awt.Color(255, 0, 0));
        txt_BuscarServicio.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_BuscarServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_BuscarServicioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_BuscarServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_BuscarServicioKeyTyped(evt);
            }
        });
        pnl_Servicios.add(txt_BuscarServicio);
        txt_BuscarServicio.setBounds(550, 60, 380, 30);

        lbl_Bus2.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Bus2.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Bus2.setText("Buscar:");
        pnl_Servicios.add(lbl_Bus2);
        lbl_Bus2.setBounds(160, 60, 50, 30);

        lbl_ServicioIgual.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_ServicioIgual.setForeground(new java.awt.Color(255, 0, 0));
        lbl_ServicioIgual.setText("=");
        pnl_Servicios.add(lbl_ServicioIgual);
        lbl_ServicioIgual.setBounds(470, 60, 20, 30);

        btn_AgregarServicio.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_AgregarServicio.setForeground(new java.awt.Color(0, 204, 204));
        btn_AgregarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Agregar.png"))); // NOI18N
        btn_AgregarServicio.setText("Nuevo");
        btn_AgregarServicio.setFocusPainted(false);
        btn_AgregarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarServicioActionPerformed(evt);
            }
        });
        pnl_Servicios.add(btn_AgregarServicio);
        btn_AgregarServicio.setBounds(180, 450, 120, 40);

        btn_ModificarServicio.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_ModificarServicio.setForeground(new java.awt.Color(0, 204, 204));
        btn_ModificarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Editar.png"))); // NOI18N
        btn_ModificarServicio.setText("Modificar");
        btn_ModificarServicio.setFocusPainted(false);
        btn_ModificarServicio.setFocusable(false);
        btn_ModificarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarServicioActionPerformed(evt);
            }
        });
        pnl_Servicios.add(btn_ModificarServicio);
        btn_ModificarServicio.setBounds(460, 450, 130, 40);

        btn_InhabilitarServicio.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_InhabilitarServicio.setForeground(new java.awt.Color(255, 102, 102));
        btn_InhabilitarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Inhabilitar.png"))); // NOI18N
        btn_InhabilitarServicio.setText("Eliminar");
        btn_InhabilitarServicio.setFocusPainted(false);
        btn_InhabilitarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InhabilitarServicioActionPerformed(evt);
            }
        });
        pnl_Servicios.add(btn_InhabilitarServicio);
        btn_InhabilitarServicio.setBounds(750, 450, 140, 40);

        cmb_PrecioServicio.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_PrecioServicio.setForeground(new java.awt.Color(255, 102, 102));
        cmb_PrecioServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Igual", "Mayor que", "Menor que", "Distinto que" }));
        cmb_PrecioServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_PrecioServicioActionPerformed(evt);
            }
        });
        pnl_Servicios.add(cmb_PrecioServicio);
        cmb_PrecioServicio.setBounds(430, 60, 100, 30);

        getContentPane().add(pnl_Servicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1090, 530));

        lbl_Servicios.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Servicios.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Servicios.setText("Gestionar Servicios");
        getContentPane().add(lbl_Servicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 40));

        lbl_Empresa.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Empresa.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Empresa.setText("Gestionar Empresa");
        getContentPane().add(lbl_Empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, 40));

        pnl_Historial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_Historial.add(jSeparator1);
        jSeparator1.setBounds(70, 390, 910, 10);

        jLabel25.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("Entradas:");
        pnl_Historial.add(jLabel25);
        jLabel25.setBounds(530, 410, 60, 10);

        jLabel32.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 0));
        jLabel32.setText("Servicios Realizados:");
        pnl_Historial.add(jLabel32);
        jLabel32.setBounds(80, 440, 130, 16);

        jLabel33.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 0, 0));
        jLabel33.setText("Servicio Más Vendido:");
        pnl_Historial.add(jLabel33);
        jLabel33.setBounds(70, 470, 140, 16);

        jLabel34.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("Venta Más Alta:");
        pnl_Historial.add(jLabel34);
        jLabel34.setBounds(310, 410, 100, 16);

        jLabel35.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 0, 0));
        jLabel35.setText("Venta Más Baja:");
        pnl_Historial.add(jLabel35);
        jLabel35.setBounds(310, 440, 100, 16);

        jLabel36.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 0, 0));
        jLabel36.setText("Venta Promedio:");
        pnl_Historial.add(jLabel36);
        jLabel36.setBounds(310, 470, 110, 16);

        tb_Historial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Cotización", "Servicio", "Contacto", "Empresa", "Total", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tb_Historial);

        pnl_Historial.add(jScrollPane6);
        jScrollPane6.setBounds(60, 120, 930, 260);

        txt_BusHistorial.setSelectionColor(new java.awt.Color(255, 102, 51));
        pnl_Historial.add(txt_BusHistorial);
        txt_BusHistorial.setBounds(450, 60, 250, 30);
        pnl_Historial.add(jdc_Historial);
        jdc_Historial.setBounds(720, 60, 100, 30);

        ckb_Dia.setFont(new java.awt.Font("Merriweather Sans Light", 0, 12)); // NOI18N
        ckb_Dia.setText("Dia");
        ckb_Dia.setContentAreaFilled(false);
        pnl_Historial.add(ckb_Dia);
        ckb_Dia.setBounds(840, 60, 50, 30);

        ckb_Mes.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        ckb_Mes.setText("Mes");
        ckb_Mes.setContentAreaFilled(false);
        pnl_Historial.add(ckb_Mes);
        ckb_Mes.setBounds(890, 60, 50, 30);

        ckb_Año.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        ckb_Año.setText("Año");
        ckb_Año.setContentAreaFilled(false);
        pnl_Historial.add(ckb_Año);
        ckb_Año.setBounds(940, 60, 60, 30);

        cmb_BusHistorial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnl_Historial.add(cmb_BusHistorial);
        cmb_BusHistorial.setBounds(170, 60, 130, 30);

        jLabel37.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 51, 51));
        jLabel37.setText("Buscar:");
        pnl_Historial.add(jLabel37);
        jLabel37.setBounds(100, 60, 50, 30);

        jButton1.setText("Buscar");
        pnl_Historial.add(jButton1);
        jButton1.setBounds(900, 460, 90, 40);

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Total Ventas:");
        pnl_Historial.add(jLabel38);
        jLabel38.setBounds(120, 410, 100, 20);

        jLabel39.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 0, 0));
        jLabel39.setText("Total Neto:");
        pnl_Historial.add(jLabel39);
        jLabel39.setBounds(520, 440, 80, 16);

        jLabel40.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 0, 0));
        jLabel40.setText("Total IVA:");
        pnl_Historial.add(jLabel40);
        jLabel40.setBounds(530, 470, 70, 16);

        jButton3.setText("Resultados");
        pnl_Historial.add(jButton3);
        jButton3.setBounds(890, 410, 100, 40);

        cmb_Total.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Igual", "Mayor que", "Menor que", "Distinto que" }));
        pnl_Historial.add(cmb_Total);
        cmb_Total.setBounds(320, 60, 110, 30);

        getContentPane().add(pnl_Historial, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1100, 530));

        lbl_Historial.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Historial.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Historial.setText("Historial");
        getContentPane().add(lbl_Historial, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        pnl_Cliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_Cliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_Cliente.setForeground(new java.awt.Color(255, 51, 51));
        tb_Cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Nombre", "Rut", "Correo", "Teléfono", "Dirección", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Cliente.setSelectionBackground(new java.awt.Color(255, 204, 153));
        tb_Cliente.setSelectionForeground(new java.awt.Color(255, 51, 51));
        tb_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ClienteMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_Cliente);

        pnl_Cliente.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 970, 270));

        cmb_Cliente.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        cmb_Cliente.setForeground(new java.awt.Color(255, 102, 102));
        cmb_Cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Rut", "Nombre", "Correo", "Teléfono", "Dirección" }));
        cmb_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ClienteActionPerformed(evt);
            }
        });
        pnl_Cliente.add(cmb_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 60, 180, 30));

        txt_Cliente.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        txt_Cliente.setForeground(new java.awt.Color(255, 0, 0));
        txt_Cliente.setSelectionColor(new java.awt.Color(255, 102, 51));
        txt_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_ClienteKeyReleased(evt);
            }
        });
        pnl_Cliente.add(txt_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 450, 30));

        lbl_Bus3.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        lbl_Bus3.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Bus3.setText("Buscar:");
        pnl_Cliente.add(lbl_Bus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, 30));

        jLabel18.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("=");
        pnl_Cliente.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        btn_Agregar1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_Agregar1.setForeground(new java.awt.Color(0, 204, 204));
        btn_Agregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Agregar.png"))); // NOI18N
        btn_Agregar1.setText("Nuevo");
        btn_Agregar1.setFocusPainted(false);
        btn_Agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Agregar1ActionPerformed(evt);
            }
        });
        pnl_Cliente.add(btn_Agregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, -1, 40));

        btn_ModificarEmpleado1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_ModificarEmpleado1.setForeground(new java.awt.Color(0, 204, 204));
        btn_ModificarEmpleado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Editar.png"))); // NOI18N
        btn_ModificarEmpleado1.setText("Modificar");
        btn_ModificarEmpleado1.setFocusPainted(false);
        btn_ModificarEmpleado1.setFocusable(false);
        btn_ModificarEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarEmpleado1ActionPerformed(evt);
            }
        });
        pnl_Cliente.add(btn_ModificarEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, -1, 40));

        btn_InhabilitarEmpleado1.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 12)); // NOI18N
        btn_InhabilitarEmpleado1.setForeground(new java.awt.Color(255, 102, 102));
        btn_InhabilitarEmpleado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Inhabilitar.png"))); // NOI18N
        btn_InhabilitarEmpleado1.setText("Inhabilitar");
        btn_InhabilitarEmpleado1.setFocusPainted(false);
        btn_InhabilitarEmpleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InhabilitarEmpleado1ActionPerformed(evt);
            }
        });
        pnl_Cliente.add(btn_InhabilitarEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, -1, 40));

        getContentPane().add(pnl_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1110, 530));

        btn_Menu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Menú1.png"))); // NOI18N
        btn_Menu2.setBorderPainted(false);
        btn_Menu2.setContentAreaFilled(false);
        btn_Menu2.setFocusPainted(false);
        btn_Menu2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/Boton Menú1 Press.png"))); // NOI18N
        btn_Menu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Menu2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Menu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 40, 40));

        lbl_Cliente.setFont(new java.awt.Font("Merriweather Sans ExtraBold", 0, 36)); // NOI18N
        lbl_Cliente.setForeground(new java.awt.Color(255, 51, 0));
        lbl_Cliente.setText("Gestionar Cliente");
        getContentPane().add(lbl_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 390, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Complementos/11.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 660));

        jMenu1.setText("Archivo");
        jMenu1.setToolTipText("");
        jMenu1.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N

        menu_Home.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menu_Home.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Home.setText("Home");
        menu_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_HomeActionPerformed(evt);
            }
        });
        jMenu1.add(menu_Home);

        menu_Cotizacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menu_Cotizacion.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Cotizacion.setText("Cotización");
        menu_Cotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_CotizacionActionPerformed(evt);
            }
        });
        jMenu1.add(menu_Cotizacion);

        menu_Agenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        menu_Agenda.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Agenda.setText("Agenda");
        menu_Agenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_AgendaActionPerformed(evt);
            }
        });
        jMenu1.add(menu_Agenda);

        menu_Historial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menu_Historial.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Historial.setText("Historial");
        menu_Historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_HistorialActionPerformed(evt);
            }
        });
        jMenu1.add(menu_Historial);

        menu_Configuracion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menu_Configuracion.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Configuracion.setText("Configuración");
        jMenu1.add(menu_Configuracion);
        jMenu1.add(jSeparator2);

        menu_CerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menu_CerrarSesion.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_CerrarSesion.setText("Cerrar sesión");
        menu_CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_CerrarSesionActionPerformed(evt);
            }
        });
        jMenu1.add(menu_CerrarSesion);

        menu_Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        menu_Salir.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Salir.setText("Salir");
        menu_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_SalirActionPerformed(evt);
            }
        });
        jMenu1.add(menu_Salir);

        jMenuBar1.add(jMenu1);

        menu.setText("Gestiones");
        menu.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N

        menu_Empleado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menu_Empleado.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Empleado.setText("Gestionar Empleado");
        menu_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_EmpleadoActionPerformed(evt);
            }
        });
        menu.add(menu_Empleado);

        menu_Cliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menu_Cliente.setText("Gestionar Cliente");
        menu.add(menu_Cliente);

        menu_Empresa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menu_Empresa.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Empresa.setText("Gestionar Empresa");
        menu_Empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_EmpresaActionPerformed(evt);
            }
        });
        menu.add(menu_Empresa);

        menu_Servicio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menu_Servicio.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_Servicio.setText("Gestionar Servicio");
        menu_Servicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_ServicioActionPerformed(evt);
            }
        });
        menu.add(menu_Servicio);

        jMenuBar1.add(menu);

        jMenu2.setText("Nuevo");
        jMenu2.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N

        menu_NuevoEmpreado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_MASK));
        menu_NuevoEmpreado.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_NuevoEmpreado.setText("Nuevo Empleado");
        menu_NuevoEmpreado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_NuevoEmpreadoActionPerformed(evt);
            }
        });
        jMenu2.add(menu_NuevoEmpreado);

        menu_NuevoCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_MASK));
        menu_NuevoCliente.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_NuevoCliente.setText("Nuevo Cliente");
        menu_NuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_NuevoClienteActionPerformed(evt);
            }
        });
        jMenu2.add(menu_NuevoCliente);

        menu_NuevaEmpresa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        menu_NuevaEmpresa.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_NuevaEmpresa.setText("Nueva Empresa");
        menu_NuevaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_NuevaEmpresaActionPerformed(evt);
            }
        });
        jMenu2.add(menu_NuevaEmpresa);

        menu_NuevoServicio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        menu_NuevoServicio.setFont(new java.awt.Font("Merriweather Sans", 0, 12)); // NOI18N
        menu_NuevoServicio.setText("Nuevo Servicio");
        menu_NuevoServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_NuevoServicioActionPerformed(evt);
            }
        });
        jMenu2.add(menu_NuevoServicio);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MenuActionPerformed
        if (pnl_Menu.isVisible())
            pnl_Menu.setVisible(false);
        else
            pnl_Menu.setVisible(true);
    }//GEN-LAST:event_btn_MenuActionPerformed

    private void btnCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotizacionActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarCotizacion();
    }//GEN-LAST:event_btnCotizacionActionPerformed

    private void btn_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InicioActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarHome();
    }//GEN-LAST:event_btn_InicioActionPerformed

    private void btn_InicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InicioMouseEntered
        reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_InicioMouseEntered

    private void btn_InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InicioMouseClicked
        
    }//GEN-LAST:event_btn_InicioMouseClicked

    private void btn_ConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ConfiguracionMouseEntered
        if (btn_Configuracion.isEnabled()) 
            reproducirEntrada(ckb_Sonido.isSelected());  
    }//GEN-LAST:event_btn_ConfiguracionMouseEntered

    private void btn_ConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfiguracionActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_ConfiguracionActionPerformed

    private void btnCotizacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCotizacionMouseEntered
        reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btnCotizacionMouseEntered

    private void btn_AgendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AgendaMouseEntered
        reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_AgendaMouseEntered

    private void btn_AgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgendaActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarAgenda();
    }//GEN-LAST:event_btn_AgendaActionPerformed

    private void btn_ServiciosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ServiciosMouseEntered
        reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_ServiciosMouseEntered

    private void btn_ServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ServiciosActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarServicio();
    }//GEN-LAST:event_btn_ServiciosActionPerformed

    private void btn_EmpleadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EmpleadosMouseEntered
        if (btn_Empleados.isEnabled()) 
            reproducirEntrada(ckb_Sonido.isSelected());
        
        
    }//GEN-LAST:event_btn_EmpleadosMouseEntered

    private void btn_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmpleadosActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarEmpleado();
    }//GEN-LAST:event_btn_EmpleadosActionPerformed

    private void btn_EmpresaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EmpresaMouseEntered
        if (btn_Empresa.isEnabled()) 
            reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_EmpresaMouseEntered

    private void btn_EmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmpresaActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarEmpresa();
    }//GEN-LAST:event_btn_EmpresaActionPerformed

    private void btn_HistorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HistorialMouseEntered
        if (btn_Historial.isEnabled()) 
            reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_HistorialMouseEntered

    private void btn_HistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HistorialActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarHistorial();
    }//GEN-LAST:event_btn_HistorialActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        
        nuevoEmpleado();
        
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_ModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarEmpleadoActionPerformed
        int filaseleccionada = tb_Empleado.getSelectedRow();

        if (filaseleccionada >= 0) 
            modificarEmpleado();        
        else
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún empleado.","Seleccione empleado",JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_btn_ModificarEmpleadoActionPerformed

    private void btn_InhabilitarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InhabilitarEmpleadoActionPerformed
//        Empleado emp = new Empleado();
//
//        int filaseleccionada = tb_ConsultaEmpleado.getSelectedRow();
//
//        emp.setNombre(tb_ConsultaEmpleado.getValueAt(filaseleccionada, 1).toString()); //getValueAt ob tiene el valor de la fila
//
//        cambio = true;
//
//        if(JOptionPane.showConfirmDialog(null, "Seguro que quieres inhabilitar al empleado: "+emp.getNombre()+"?", "Inhabilitar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
//            emp = modificarEmpleado();
//
//            if (emp.actualizarEmpleado())
//            JOptionPane.showMessageDialog(null, "Empleado inhabilitado correctamente.","Empleado inhabilitado",JOptionPane.INFORMATION_MESSAGE);
//            else
//            JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar inhabilitar al cliente","Cliente NO inhabilitado",JOptionPane.ERROR_MESSAGE);
//
//            for (int i = tb_ConsultaEmpleado.getRowCount() - 1; i >=0 ; i--)
//            modelo2.removeRow(i); // El modelo limpia la tabla
//
//            tablaEmpleado(); // Volvemos a cargar la tabla
//        }
    }//GEN-LAST:event_btn_InhabilitarEmpleadoActionPerformed

    private void btn_AgendaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgendaMenuActionPerformed
        activarAgenda();
    }//GEN-LAST:event_btn_AgendaMenuActionPerformed

    private void btn_HomeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HomeMenuActionPerformed
        activarHome();
    }//GEN-LAST:event_btn_HomeMenuActionPerformed

    private void btn_GuardarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarTablaActionPerformed
        if (!cmb_Servicios.getSelectedItem().toString().isEmpty()) {
            
            if (v.rut(cmb_Rut.getSelectedItem().toString()) != null) {

                if (rdb_Cliente.isSelected()) {
                    Cliente cliente = new Cliente();

                    if (cliente.buscarCliente("rut", cmb_Rut.getSelectedItem().toString()) == null){
                        if(JOptionPane.showConfirmDialog(null, "Cliente no existe, ¿quieres ingresar este cliente\ncon los datos en los campos?", "Cuidado!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
                            crearNuevoCliente();
                            tablaCotizacion();  
                        }
                    }
                    else
                        tablaCotizacion();  
                }
                else if (rdb_Empresa.isSelected()) {
                    Empresa empresa = new Empresa();
                    if (empresa.buscarEmpresa("rut",cmb_Rut.getSelectedItem().toString()) == null) {
                        if(JOptionPane.showConfirmDialog(null, "Empresa no existe, ¿quieres ingresar esta empresa\ncon los datos en los campos?", "Cuidado!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
                            crearNuevaEmpresa();
                            tablaCotizacion();  
                        } 
                    }
                    else
                        tablaCotizacion();  
                }
                
                
                
            }
            else
                JOptionPane.showMessageDialog(null, "El rut ingresado no es válido.","Run inválido!",JOptionPane.WARNING_MESSAGE); 
        }
        else
            JOptionPane.showMessageDialog(null, "Favor ingresa un servicio para poder cargarlo en al tabla.","Servicio vacío!",JOptionPane.WARNING_MESSAGE); 
         
    }//GEN-LAST:event_btn_GuardarTablaActionPerformed

    private void btn_RealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RealizarActionPerformed
        try {
            String rut = cmb_Rut.getSelectedItem().toString();
            
            if (tb_Cotizacion.getRowCount() > 0) {
                try {
                    generarPDF();
                    
                    nuevaCotizacion();
                    
                } catch (DocumentException ex) {
                    Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Favor ingresa servicios para poder realizar la cotización.","Sin servicios!",JOptionPane.WARNING_MESSAGE); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El rut está vacío, favor ingresa un rut","Rut vacío!",JOptionPane.WARNING_MESSAGE); 
            cmb_Rut.requestFocus();
        }
    }//GEN-LAST:event_btn_RealizarActionPerformed

    private void btn_CotizacionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CotizacionMenuActionPerformed
        activarCotizacion();  
    }//GEN-LAST:event_btn_CotizacionMenuActionPerformed

    private void cmb_ModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_ModeloActionPerformed

    private void spr_AñoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_AñoStateChanged
        if (spr_Año.getValue().toString().equals("1899")) 
            spr_Año.setValue(1900);
        else if (spr_Año.getValue().toString().equals((Integer.valueOf(get.añoActual()) + 1) + "")) 
            spr_Año.setValue(Integer.valueOf(get.añoActual()));
    }//GEN-LAST:event_spr_AñoStateChanged

    private void spr_CantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spr_CantidadStateChanged
        if (spr_Cantidad.getValue().toString().equals("0"))
            spr_Cantidad.setValue(1);
        else if (spr_Cantidad.getValue().toString().equals("101"))
            spr_Cantidad.setValue(100);
    }//GEN-LAST:event_spr_CantidadStateChanged

    private void btn_AgregarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarEmpresaActionPerformed
        nuevaEmpresa();
    }//GEN-LAST:event_btn_AgregarEmpresaActionPerformed

    private void btn_ModificarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarEmpresaActionPerformed
        modificarEmpresa();
    }//GEN-LAST:event_btn_ModificarEmpresaActionPerformed

    private void btn_InhabilitarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InhabilitarEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_InhabilitarEmpresaActionPerformed

    private void cal_AgendaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal_AgendaMouseEntered
        getAgenda ();
    }//GEN-LAST:event_cal_AgendaMouseEntered

    private void cal_AgendaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal_AgendaMouseExited
        getAgenda ();
    }//GEN-LAST:event_cal_AgendaMouseExited

    private void txt_MontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_MontoKeyTyped
        if(!Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_MontoKeyTyped

    private void spr_HoraAgenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spr_HoraAgenKeyTyped
        if(!Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_spr_HoraAgenKeyTyped

    private void btn_ConfiguracionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConfiguracionMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ConfiguracionMenuActionPerformed

    private void btn_AgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarServicioActionPerformed
        nuevoServicio();
    }//GEN-LAST:event_btn_AgregarServicioActionPerformed

    private void btn_ModificarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarServicioActionPerformed
        modificarServicio();
    }//GEN-LAST:event_btn_ModificarServicioActionPerformed

    private void btn_InhabilitarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InhabilitarServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_InhabilitarServicioActionPerformed

    private void btn_HistorialMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HistorialMenuActionPerformed
        activarHistorial();
    }//GEN-LAST:event_btn_HistorialMenuActionPerformed

    private void txt_BuscarEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarEmpleadoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            tablaEmpleado(true);
    }//GEN-LAST:event_txt_BuscarEmpleadoKeyPressed

    private void txt_BuscarEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarEmpleadoKeyReleased
        tablaEmpleado(true);
    }//GEN-LAST:event_txt_BuscarEmpleadoKeyReleased

    private void btn_ApagarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ApagarSesionActionPerformed
        cerrarSesion();   
    }//GEN-LAST:event_btn_ApagarSesionActionPerformed

    private void tb_EmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EmpleadoMouseClicked
        //Empleado emp;
        //int filaseleccionada = tb_Empleado.getSelectedRow();
        if (evt.getClickCount() == 2) 
            modificarEmpleado();
        //else if (evt.getClickCount() == 1){
            
            
//            if (!tb_Empleado.getValueAt(filaseleccionada, 6).toString().equals("Inhabilitado")) {
//            
//                btn_InhabilitarEmpleado.setVisible(true);
//                btn_HabilitarEmpleado.setVisible(false);
//            }
//            else{
//                btn_HabilitarEmpleado.setVisible(true);
//                btn_InhabilitarEmpleado.setVisible(false); 
//            }
        //}
    }//GEN-LAST:event_tb_EmpleadoMouseClicked

    private void menu_CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_CerrarSesionActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_menu_CerrarSesionActionPerformed

    private void menu_CotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_CotizacionActionPerformed
        activarCotizacion();
    }//GEN-LAST:event_menu_CotizacionActionPerformed

    private void menu_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_EmpleadoActionPerformed
        activarEmpleado();
    }//GEN-LAST:event_menu_EmpleadoActionPerformed

    private void menu_EmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_EmpresaActionPerformed
        activarEmpresa();
    }//GEN-LAST:event_menu_EmpresaActionPerformed

    private void menu_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_HomeActionPerformed
        activarHome();
    }//GEN-LAST:event_menu_HomeActionPerformed

    private void menu_ServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_ServicioActionPerformed
        activarServicio();
    }//GEN-LAST:event_menu_ServicioActionPerformed

    private void menu_AgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_AgendaActionPerformed
        activarAgenda();
    }//GEN-LAST:event_menu_AgendaActionPerformed

    private void menu_HistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_HistorialActionPerformed
        activarHistorial();
    }//GEN-LAST:event_menu_HistorialActionPerformed

    private void menu_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menu_SalirActionPerformed

    private void txt_BuscarEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarEmpresaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            tablaEmpresa(true);
    }//GEN-LAST:event_txt_BuscarEmpresaKeyPressed

    private void txt_BuscarEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarEmpresaKeyReleased
        tablaEmpresa(true);
    }//GEN-LAST:event_txt_BuscarEmpresaKeyReleased

    private void txt_CorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CorreoActionPerformed

    private void btn_QuitarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuitarServicioActionPerformed
        int filaseleccionada = tb_Cotizacion.getSelectedRow();
        
        if(tb_Cotizacion.getRowCount() > 0){
            if (filaseleccionada >= 0) 
                quitarServicio();
            else
                JOptionPane.showMessageDialog(null, "No ha seleccionado ningún servicio.","Seleccione servicio",JOptionPane.WARNING_MESSAGE);   
        } 
    }//GEN-LAST:event_btn_QuitarServicioActionPerformed

    private void tb_CotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CotizacionMouseClicked
        if (evt.getClickCount() == 2)
            quitarServicio();
    }//GEN-LAST:event_tb_CotizacionMouseClicked

    private void tb_EmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EmpresaMouseClicked
        if (evt.getClickCount() == 2) 
            modificarEmpresa();
        else if (evt.getClickCount() == 1){}
    }//GEN-LAST:event_tb_EmpresaMouseClicked

    private void tb_ServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ServicioMouseClicked
        if (evt.getClickCount() == 2) 
            modificarServicio();
    }//GEN-LAST:event_tb_ServicioMouseClicked

    private void txt_BuscarServicioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarServicioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            tablaServicio(true);
    }//GEN-LAST:event_txt_BuscarServicioKeyPressed

    private void txt_BuscarServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarServicioKeyReleased
        tablaServicio(true);
    }//GEN-LAST:event_txt_BuscarServicioKeyReleased

    private void txt_BuscarServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_BuscarServicioKeyTyped
        
        if (cmb_BusServicio.getSelectedItem().equals("Precio")) {
            if (!v.maximoLargo(txt_BuscarServicio.getText(), 7))
                evt.consume();

            if(!Character.isDigit(evt.getKeyChar())) 
                evt.consume(); 
        }
        
    }//GEN-LAST:event_txt_BuscarServicioKeyTyped

    private void cmb_PrecioServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_PrecioServicioActionPerformed
        tablaServicio(true);
    }//GEN-LAST:event_cmb_PrecioServicioActionPerformed

    private void cmb_BusServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_BusServicioActionPerformed
        buscaPrecioServicio();
    }//GEN-LAST:event_cmb_BusServicioActionPerformed

    private void menu_NuevoEmpreadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_NuevoEmpreadoActionPerformed
        nuevoEmpleado();
    }//GEN-LAST:event_menu_NuevoEmpreadoActionPerformed

    private void menu_NuevoServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_NuevoServicioActionPerformed
        nuevoServicio();
    }//GEN-LAST:event_menu_NuevoServicioActionPerformed

    private void menu_NuevaEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_NuevaEmpresaActionPerformed
        nuevaEmpresa();
    }//GEN-LAST:event_menu_NuevaEmpresaActionPerformed

    private void txt_PrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioKeyTyped
        if (!v.maximoLargo(txt_Precio.getText(), 7))
            evt.consume();

        if(!Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_PrecioKeyTyped

    private void txt_NombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NombreKeyTyped
        if (!v.maximoLargo(txt_Nombre.getText(), 40))
            evt.consume();

        if(Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_NombreKeyTyped

    private void txt_FonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_FonoKeyTyped
        if (!v.maximoLargo(txt_Fono.getText(), 10))
            evt.consume();

        if(!Character.isDigit(evt.getKeyChar())) 
            evt.consume(); 
    }//GEN-LAST:event_txt_FonoKeyTyped

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        if (tb_Cotizacion.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres cancelar esta cotización?", "Cancelar cotización", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
                cmb_Rut.setSelectedItem("");
                txt_Nombre.setText("");
                txt_Correo.setText("");
                txt_Fono.setText("");
                cmb_FormaPago.setSelectedIndex(0);
                cmb_Marca.setSelectedIndex(0);
                cmb_Modelo.setSelectedIndex(0);
                spr_Año.setValue(0);//Este año
                cmb_Servicios.setSelectedItem("");
                spr_Cantidad.setValue(1);
                txt_Precio.setText("");

                removeAllService();
            }
        }
        
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private void rdb_EmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_EmpresaMouseClicked
        cargarCMBRut(rdb_Cliente.isSelected());
        cmb_Rut.setSelectedIndex(0);
    }//GEN-LAST:event_rdb_EmpresaMouseClicked

    private void rdb_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_ClienteMouseClicked
        cargarCMBRut(rdb_Cliente.isSelected());
    }//GEN-LAST:event_rdb_ClienteMouseClicked

    private void rdb_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_ClienteActionPerformed
        cargarCMBRut(rdb_Cliente.isSelected());
    }//GEN-LAST:event_rdb_ClienteActionPerformed

    private void rdb_EmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_EmpresaActionPerformed
        cargarCMBRut(rdb_Empresa.isSelected());
    }//GEN-LAST:event_rdb_EmpresaActionPerformed

    private void cmb_RutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_RutActionPerformed
        //Obtenemos a la empresa o cliente dependiendo de su rut
        if (rdb_Empresa.isSelected() && cmb_Rut.getComponentCount() >= 0 && cmb_Rut.getItemCount() > 0 && !cmb_Rut.getSelectedItem().toString().equals("")) {
            
            Empresa empre = new Empresa();
            ArrayList<Empresa> listaEmpresa = empre.buscarDatosEmpresa(true, "rut", cmb_Rut.getSelectedItem().toString());
            
            if (!listaEmpresa.isEmpty()) {
                txt_Nombre.setText(listaEmpresa.get(0).getNombre());
                txt_Fono.setText(listaEmpresa.get(0).getTelefono());
                txt_Correo.setText(listaEmpresa.get(0).getCorreo());
            }
            
        }
        else if (rdb_Cliente.isSelected() && cmb_Rut.getComponentCount() >= 0 && cmb_Rut.getItemCount() > 0 && !cmb_Rut.getSelectedItem().toString().equals("")) {
            
            Cliente cliente = new Cliente();
            ArrayList<Cliente> listaCliente = cliente.buscarDatosCliente(true, "rut", cmb_Rut.getSelectedItem().toString());
            
            if (!listaCliente.isEmpty()) {
                txt_Nombre.setText(listaCliente.get(0).getNombre() + " " + listaCliente.get(0).getPaterno() + " " + listaCliente.get(0).getMaterno());
                txt_Fono.setText(listaCliente.get(0).getTelefono());
                txt_Correo.setText(listaCliente.get(0).getCorreo());
            }
            
        }
    }//GEN-LAST:event_cmb_RutActionPerformed

    private void txt_ContactoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ContactoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ContactoKeyTyped

    private void tb_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClienteMouseClicked
        if (evt.getClickCount() == 2) 
            modificarCliente();
    }//GEN-LAST:event_tb_ClienteMouseClicked

    private void txt_ClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ClienteKeyPressed
        //        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        //        tablaEmpleado(true);
    }//GEN-LAST:event_txt_ClienteKeyPressed

    private void txt_ClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ClienteKeyReleased
        tablaCliente(true); 
    }//GEN-LAST:event_txt_ClienteKeyReleased

    private void btn_Agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Agregar1ActionPerformed
        
        nuevoCliente();
        
    }//GEN-LAST:event_btn_Agregar1ActionPerformed

    private void btn_ModificarEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarEmpleado1ActionPerformed
        //        int filaseleccionada = tb_Empleado.getSelectedRow();
        //
        //        if (filaseleccionada >= 0)
        ////        modificarEmpleado();
        //        else
        //        JOptionPane.showMessageDialog(null, "No ha seleccionado ningún empleado.","Seleccione empleado",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_ModificarEmpleado1ActionPerformed

    private void btn_InhabilitarEmpleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InhabilitarEmpleado1ActionPerformed
        //        Empleado emp = new Empleado();
        //
        //        int filaseleccionada = tb_ConsultaEmpleado.getSelectedRow();
        //
        //        emp.setNombre(tb_ConsultaEmpleado.getValueAt(filaseleccionada, 1).toString()); //getValueAt ob tiene el valor de la fila
        //
        //        cambio = true;
        //
        //        if(JOptionPane.showConfirmDialog(null, "Seguro que quieres inhabilitar al empleado: "+emp.getNombre()+"?", "Inhabilitar", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
            //            emp = modificarEmpleado();
            //
            //            if (emp.actualizarEmpleado())
            //            JOptionPane.showMessageDialog(null, "Empleado inhabilitado correctamente.","Empleado inhabilitado",JOptionPane.INFORMATION_MESSAGE);
            //            else
            //            JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar inhabilitar al cliente","Cliente NO inhabilitado",JOptionPane.ERROR_MESSAGE);
            //
            //            for (int i = tb_ConsultaEmpleado.getRowCount() - 1; i >=0 ; i--)
            //            modelo2.removeRow(i); // El modelo limpia la tabla
            //
            //            tablaEmpleado(); // Volvemos a cargar la tabla
            //        }
    }//GEN-LAST:event_btn_InhabilitarEmpleado1ActionPerformed

    private void btn_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClienteActionPerformed
        reproducirClick(ckb_Sonido.isSelected());
        activarCliente();
    }//GEN-LAST:event_btn_ClienteActionPerformed

    private void btn_ClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ClienteMouseEntered
        reproducirEntrada(ckb_Sonido.isSelected());
    }//GEN-LAST:event_btn_ClienteMouseEntered

    private void btn_Menu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Menu2ActionPerformed
         if (pnl_Menu1.isVisible())
            pnl_Menu1.setVisible(false);
        else
            pnl_Menu1.setVisible(true);
    }//GEN-LAST:event_btn_Menu2ActionPerformed

    private void btn_ServicioMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ServicioMenuActionPerformed
        activarServicio();
    }//GEN-LAST:event_btn_ServicioMenuActionPerformed

    private void btn_EmpleadoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmpleadoMenuActionPerformed
        activarEmpleado();
    }//GEN-LAST:event_btn_EmpleadoMenuActionPerformed

    private void btn_EmpresaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmpresaMenuActionPerformed
        activarEmpresa();
    }//GEN-LAST:event_btn_EmpresaMenuActionPerformed

    private void btn_ClienteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClienteMenuActionPerformed
        activarCliente();
    }//GEN-LAST:event_btn_ClienteMenuActionPerformed

    private void cmb_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ClienteActionPerformed
        tablaCliente(true);
    }//GEN-LAST:event_cmb_ClienteActionPerformed

    private void menu_NuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_NuevoClienteActionPerformed
        nuevoCliente();
    }//GEN-LAST:event_menu_NuevoClienteActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //new Seleccion();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Seleccion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Destello_Agenda;
    private javax.swing.JLabel Destello_Cliente;
    private javax.swing.JLabel Destello_Configuracion;
    private javax.swing.JLabel Destello_Cotizacion;
    private javax.swing.JLabel Destello_Empleado;
    private javax.swing.JLabel Destello_Empresa;
    private javax.swing.JLabel Destello_Historial;
    private javax.swing.JLabel Destello_Home;
    private javax.swing.JLabel Destello_Productos;
    private javax.swing.JButton btnCotizacion;
    private javax.swing.JButton btn_Agenda;
    private javax.swing.JButton btn_AgendaMenu;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Agregar1;
    private javax.swing.JButton btn_AgregarEmpresa;
    private javax.swing.JButton btn_AgregarServicio;
    private javax.swing.JButton btn_ApagarSesion;
    private javax.swing.JButton btn_Auto;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Cliente;
    private javax.swing.JButton btn_ClienteMenu;
    private javax.swing.JButton btn_Configuracion;
    private javax.swing.JButton btn_ConfiguracionMenu;
    private javax.swing.JButton btn_CotizacionMenu;
    private javax.swing.JButton btn_EmpleadoMenu;
    private javax.swing.JButton btn_Empleados;
    private javax.swing.JButton btn_Empresa;
    private javax.swing.JButton btn_EmpresaMenu;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton btn_GuardarTabla;
    private javax.swing.JButton btn_Historial;
    private javax.swing.JButton btn_HistorialMenu;
    private javax.swing.JButton btn_HomeMenu;
    private javax.swing.JButton btn_InhabilitarEmpleado;
    private javax.swing.JButton btn_InhabilitarEmpleado1;
    private javax.swing.JButton btn_InhabilitarEmpresa;
    private javax.swing.JButton btn_InhabilitarServicio;
    private javax.swing.JButton btn_Inicio;
    private javax.swing.JButton btn_Menu;
    private javax.swing.JButton btn_Menu2;
    private javax.swing.JButton btn_ModificarEmpleado;
    private javax.swing.JButton btn_ModificarEmpleado1;
    private javax.swing.JButton btn_ModificarEmpresa;
    private javax.swing.JButton btn_ModificarServicio;
    private javax.swing.JButton btn_QuitarServicio;
    private javax.swing.JButton btn_Realizar;
    private javax.swing.JButton btn_ServicioMenu;
    private javax.swing.JButton btn_Servicios;
    private com.toedter.calendar.JCalendar cal_Agenda;
    private javax.swing.JCheckBox ckb_Año;
    private javax.swing.JCheckBox ckb_Dia;
    private javax.swing.JCheckBox ckb_Mes;
    private javax.swing.JCheckBox ckb_Nuevo;
    private javax.swing.JCheckBox ckb_Sonido;
    private javax.swing.JComboBox<String> cmb_BusEmpleado;
    private javax.swing.JComboBox<String> cmb_BusEmpresa;
    private javax.swing.JComboBox<String> cmb_BusHistorial;
    private javax.swing.JComboBox<String> cmb_BusServicio;
    private javax.swing.JComboBox<String> cmb_Cliente;
    private javax.swing.JComboBox<String> cmb_FormaPago;
    private javax.swing.JComboBox<String> cmb_Marca;
    private javax.swing.JComboBox<String> cmb_Modelo;
    private javax.swing.JComboBox<String> cmb_PrecioServicio;
    private javax.swing.JComboBox<String> cmb_Rut;
    private javax.swing.JComboBox<String> cmb_Servicios;
    private javax.swing.JComboBox<String> cmb_Total;
    public static javax.swing.ButtonGroup grupoCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private com.toedter.calendar.JDateChooser jdc_Historial;
    private javax.swing.JLabel lbl_Agenda;
    private javax.swing.JLabel lbl_Bus;
    private javax.swing.JLabel lbl_Bus1;
    private javax.swing.JLabel lbl_Bus2;
    private javax.swing.JLabel lbl_Bus3;
    private javax.swing.JLabel lbl_Cliente;
    private javax.swing.JLabel lbl_Contacto;
    private javax.swing.JLabel lbl_Cotizacion;
    private javax.swing.JLabel lbl_DetalleAgenda;
    private javax.swing.JLabel lbl_Empleado;
    private javax.swing.JLabel lbl_Empresa;
    private javax.swing.JLabel lbl_Fono;
    private javax.swing.JLabel lbl_Historial;
    private javax.swing.JLabel lbl_Home;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Puertas1;
    private javax.swing.JLabel lbl_ServicioIgual;
    private javax.swing.JLabel lbl_Servicios;
    private javax.swing.JLabel lbl_Usuario;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menu_Agenda;
    private javax.swing.JMenuItem menu_CerrarSesion;
    private javax.swing.JMenuItem menu_Cliente;
    private javax.swing.JMenuItem menu_Configuracion;
    private javax.swing.JMenuItem menu_Cotizacion;
    private javax.swing.JMenuItem menu_Empleado;
    private javax.swing.JMenuItem menu_Empresa;
    private javax.swing.JMenuItem menu_Historial;
    private javax.swing.JMenuItem menu_Home;
    private javax.swing.JMenuItem menu_NuevaEmpresa;
    private javax.swing.JMenuItem menu_NuevoCliente;
    private javax.swing.JMenuItem menu_NuevoEmpreado;
    private javax.swing.JMenuItem menu_NuevoServicio;
    private javax.swing.JMenuItem menu_Salir;
    private javax.swing.JMenuItem menu_Servicio;
    private javax.swing.JLayeredPane pnl_Agenda;
    private javax.swing.JLayeredPane pnl_Cliente;
    private javax.swing.JLayeredPane pnl_Cotizacion;
    private javax.swing.JLayeredPane pnl_Empleado;
    private javax.swing.JLayeredPane pnl_Empresa;
    private javax.swing.JLayeredPane pnl_Historial;
    private javax.swing.JLayeredPane pnl_Home;
    private javax.swing.JPanel pnl_Menu;
    private javax.swing.JPanel pnl_Menu1;
    private javax.swing.JLayeredPane pnl_Servicios;
    private javax.swing.JRadioButton rdb_Cliente;
    private javax.swing.JRadioButton rdb_Empresa;
    private javax.swing.JSpinner spr_Año;
    private javax.swing.JSpinner spr_Cantidad;
    private com.toedter.components.JSpinField spr_HoraAgen;
    private com.toedter.components.JSpinField spr_MinAgen;
    private javax.swing.JTable tb_Agenda;
    private javax.swing.JTable tb_Cliente;
    private javax.swing.JTable tb_Cotizacion;
    private javax.swing.JTable tb_Empleado;
    private javax.swing.JTable tb_Empresa;
    private javax.swing.JTable tb_Historial;
    private javax.swing.JTable tb_Servicio;
    private javax.swing.JTextField txt_BusHistorial;
    private javax.swing.JTextField txt_BuscarEmpleado;
    private javax.swing.JTextField txt_BuscarEmpresa;
    private javax.swing.JTextField txt_BuscarServicio;
    private javax.swing.JTextField txt_Cliente;
    private javax.swing.JTextField txt_Contacto;
    private javax.swing.JTextField txt_Correo;
    private javax.swing.JTextField txt_Fono;
    private javax.swing.JTextField txt_IVA;
    private javax.swing.JTextField txt_Monto;
    private javax.swing.JTextField txt_NETO;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Precio;
    private javax.swing.JTextField txt_Total;
    // End of variables declaration//GEN-END:variables

    private void ocultarPaneles() {
        pnl_Menu.setVisible(false);
        pnl_Home.setVisible(false);
        pnl_Cotizacion.setVisible(false);
        pnl_Empleado.setVisible(false);
        pnl_Empresa.setVisible(false);
        pnl_Agenda.setVisible(false);
        pnl_Servicios.setVisible(false);
        pnl_Historial.setVisible(false);
        pnl_Cliente.setVisible(false);
        pnl_Menu1.setVisible(false);
    }

    private void ocultarEtiquetas() {
        lbl_Home.setVisible(false);
        lbl_Cotizacion.setVisible(false);
        lbl_Empleado.setVisible(false);
        lbl_Empresa.setVisible(false);
        lbl_Servicios.setVisible(false);
        lbl_Agenda.setVisible(false);
        lbl_Historial.setVisible(false);
        lbl_Cliente.setVisible(false);
    }
    
//    private void fechaDefecto(){
//        try {
//            String Fecha = get.fechaActual();
//            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//            Date fechaDate = formato.parse(Fecha);
//            jdc_Fecha.setDate(fechaDate);
//        } 
//        catch (ParseException ex) {}     
//    }
    
    private void activarHome() {
        desactivarDestellos();
        Destello_Home.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Home.setVisible(true);
        lbl_Home.setVisible(true);
    }

    private void activarCotizacion() {
        desactivarDestellos();
        Destello_Cotizacion.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Cotizacion.setVisible(true);
        lbl_Cotizacion.setVisible(true);
        cmb_Rut.requestFocus();
    }

    private void activarEmpleado() {
        desactivarDestellos();
        Destello_Empleado.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Empleado.setVisible(true);
        lbl_Empleado.setVisible(true);
    }

    private void reproducirEntrada(boolean sonido) {
        if (sonido) {
            AudioClip audio = java.applet.Applet.newAudioClip(getClass().getResource("/Complementos/pop.wav"));
            audio.play();
        }
    }

    private void reproducirClick(boolean sonido) {
        if (sonido) {
            AudioClip audio = java.applet.Applet.newAudioClip(getClass().getResource("/Complementos/selected.wav"));
            audio.play();
        }
    }

    private void cargarFormaPago() {
        cmb_FormaPago.removeAllItems();
        cmb_FormaPago.addItem("Efectivo");
        cmb_FormaPago.addItem("Tarjeta Débito");
        cmb_FormaPago.addItem("Tarjeta Crédito");
    }

    private void cargarCMBEmpleado() {
        cmb_BusEmpleado.removeAllItems();
        cmb_BusEmpleado.addItem("Código");
        cmb_BusEmpleado.addItem("Nombre");
        cmb_BusEmpleado.addItem("Rut");
        cmb_BusEmpleado.addItem("Tipo");
        cmb_BusEmpleado.addItem("Contraseña");
    }   

    private void desactivarDestellos() {
        Destello_Home.setVisible(false);
        Destello_Cotizacion.setVisible(false);
        Destello_Empleado.setVisible(false);
        Destello_Productos.setVisible(false);
        Destello_Empresa.setVisible(false);
        Destello_Agenda.setVisible(false);
        Destello_Historial.setVisible(false);
        Destello_Configuracion.setVisible(false);
        Destello_Cliente.setVisible(false);
    }

    private void cargarCMBServicios() {
        cmb_Servicios.removeAllItems();
        
        Servicio serv = new Servicio();

        ArrayList<Servicio> listaServicio = serv.buscarDatosServicio(false, "Servicio", "", "");
        
        for (int i = 0; i < listaServicio.size(); i++) 
            cmb_Servicios.addItem(String.valueOf(listaServicio.get(i).getServicio()));    
    }

    private void tablaCotizacion() {
        mCotizacion = (DefaultTableModel)tb_Cotizacion.getModel();
        Object []dato = new Object[5];
        boolean nuevo = false, registrar = true, precio = false;
        ArrayList <Servicio> cargarDatosServicios = new ArrayList();
        Servicio servi = new Servicio();
        
        cargarDatosServicios = servi.buscarDatosServicio(true, "Servicio", cmb_Servicios.getSelectedItem().toString(), "");
        
        if (cargarDatosServicios == null || cargarDatosServicios.isEmpty()){
            if (!txt_Precio.getText().isEmpty()) {
                registrarServicioAutomatico();//Guardamos el nuevo servicio
                nuevo = true;
                cargarDatosServicios = servi.buscarDatosServicio(true, "Servicio", cmb_Servicios.getSelectedItem().toString(), "");//Volvemos a cargar
            }
            else{
                registrar = false;
                JOptionPane.showMessageDialog(null, "Este servicio no está registrado.\n Para registrarlo favor ingresa el precio","Servicio sin sin valor!",JOptionPane.WARNING_MESSAGE); 
                txt_Precio.requestFocus();
            }
        }
        
        if (ckb_Nuevo.isSelected()) {
            if (!txt_Precio.getText().isEmpty()) {
                if (Integer.valueOf(txt_Precio.getText()) !=  cargarDatosServicios.get(0).getPrecio()) {
                    if(JOptionPane.showConfirmDialog(null, "¿Quieres actualizar el precio de este servicio?", "Actualizar precio", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
                        servi.setCodigo(cargarDatosServicios.get(0).getCodigo());
                        servi.setServicio(cargarDatosServicios.get(0).getServicio());
                        servi.setPrecio(Integer.valueOf(txt_Precio.getText()));
                        servi.setCodigoAntiguo(cargarDatosServicios.get(0).getCodigo());
                        servi.actualizarServicio();
                        tablaServicio(false);
                        precio = true;
                    } 
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Para actualizar favor ingresa el precio","Sin valor!",JOptionPane.WARNING_MESSAGE); 
                registrar = false;
            }
        }

        if (registrar) {           
            DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
            Alinear.setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla 

            //Centra los datos de las columnas de la tabla
            for (int i = 0; i < dato.length; i++) 
                tb_Cotizacion.getColumnModel().getColumn(i).setCellRenderer(Alinear); 

            dato[0] = cargarDatosServicios.get(0).getCodigo();//Codigo
            dato[1] = cmb_Servicios.getSelectedItem();        //Servicio
            dato[2] = spr_Cantidad.getValue();                //Cantidad
            
            if (precio){ 
                dato[3] = txt_Precio.getText();
                dato[4] = get.numerosConPuntos(Integer.valueOf(spr_Cantidad.getValue().toString()) *  Integer.valueOf(txt_Precio.getText()) ) ;
            }else{
                dato[3] = get.numerosConPuntos(cargarDatosServicios.get(0).getPrecio()) ;//Precio
                dato[4] = get.numerosConPuntos(Integer.valueOf(spr_Cantidad.getValue().toString()) *  cargarDatosServicios.get(0).getPrecio()) ;
            }
            
            mCotizacion.addRow(dato);
            //Calculamos los valores
            calculaNeto();
            calculaIva();
            calculaTotal();
            //Una vez cargado todo actualizamos todo y dejamos selecionado el ingreso de servicio
            if (nuevo) {
                cargarCMBServicios();
                //Dejamos seleccionado por defecto el mismo servicio 
                for (int i = 0; i < cmb_Servicios.getItemCount() - 1; i++) {
                    if (cmb_Servicios.getItemAt(i).equals(dato[1]))//Obtenemos el contenido de texto con el index
                        cmb_Servicios.setSelectedIndex(i);
                }
            }
        }
    }

    private void calculaNeto() {  
        neto = 0;

        for (int i = 0; i < mCotizacion.getRowCount(); i++)
            neto = neto + Integer.valueOf(get.reemplazo(mCotizacion.getValueAt(i, 4).toString(), "."))   ;
        
        txt_NETO.setText(get.numerosConPuntos(neto));
    }

    private void calculaIva() {
        iva = 0;
        for (int i = 0; i < mCotizacion.getRowCount(); i++){ 
            double guarda = Integer.valueOf(get.reemplazo(mCotizacion.getValueAt(i, 4).toString(), "."));
            iva = (iva + guarda*0.19);
        }
        txt_IVA.setText(get.numerosConPuntos(Math.round(iva)));
    }

    private void calculaTotal() {
        txt_Total.setText(get.numerosConPuntos(neto + (Math.round(iva))));
    }

    private void generarPDF() throws DocumentException {
        String ruta = "C:\\Users\\keiss\\Desktop\\"+lbl_Cotizacion.getText()+" "+txt_Nombre.getText()+".pdf"; //Poner esta ruta  en configuracion
        String foto = "C:\\Users\\keiss\\Documents\\NetBeans\\AutoTap\\build\\classes\\Complementos\\LogoNegro.png";

        String [] para = new String [5];
        
        para[0] = get.rutReal(cmb_Rut.getSelectedItem().toString());     //Rut
        
        if (rdb_Cliente.isSelected()){ //Empresa
            para[1] = "-";
            para[2] = txt_Nombre.getText();
        }else{
            para[1] = txt_Nombre.getText();      
            para[2] = txt_Contacto.getText(); //Contacto
        }

        para[3] = txt_Fono.getText();   //Fono
        para[4] = txt_Correo.getText(); //Correo

        String nombre = emp.getNombre()+" "+emp.getPaterno()+" "+emp.getMaterno();

        pdf.generarPDF(ruta, foto, obtenerServicios(), obtenerVehiculo(), para, nombre, txt_Total.getText(), lbl_Cotizacion.getText(), get.fechaActual("-"));
    }
    
//     private String getFechaActual(){
//        try{
//            String formato = jdc_Fecha.getDateFormatString(); //agrega el formato dd-mm-yy
//            Date date = jdc_Fecha.getDate(); //Guarda en una variable de tipo DATE
//            SimpleDateFormat sdf = new SimpleDateFormat(formato); //Instacia con un formato
//            return String.valueOf(sdf.format(date));//Transformamos a string el formato
//        }
//        catch(Exception ex){
//           JOptionPane.showMessageDialog(null, "Ingresa una fecha válida.\n"+ex.getMessage(),"Fecha inválida",JOptionPane.WARNING_MESSAGE);  
//           return null;
//        }
//    }
    
    private String[][] obtenerServicios(){
        String servicio[][] = new String[mCotizacion.getRowCount()][mCotizacion.getColumnCount()];
        
        for (int i = 0; i < mCotizacion.getRowCount(); i++) {
            //Recorre las columnas
            for (int j = 0; j < mCotizacion.getColumnCount(); j++) { 
                String es = "$       ";
                
                if (j == 4)
                    es = "$            ";
                
                if (j == 3 || j == 4) 
                    servicio[i][j] = es + mCotizacion.getValueAt(i, j).toString();
                else
                    servicio[i][j] = mCotizacion.getValueAt(i, j).toString();
            }
        }
 
        return servicio;
    }
    
    private String[] obtenerVehiculo(){
        String vehiculo[]= new String[3];

        vehiculo[0] = cmb_Marca.getSelectedItem().toString() ;
        vehiculo[1] = cmb_Modelo.getSelectedItem().toString();
        vehiculo[2] = spr_Año.getValue().toString();
        
        return vehiculo;
    }   

    private void activarAgenda() {
        desactivarDestellos();
        Destello_Agenda.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Agenda.setVisible(true);
        lbl_Agenda.setVisible(true);
    }

    private void getAgenda (){
        try {
            String formato = "dd/MM/yyyy";
            //Formato
            Date date = cal_Agenda.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            String min = spr_MinAgen.getValue() + "";
            
            if (min.equals("0")) 
                min = "00";
            
            lbl_DetalleAgenda.setText(sdf.format(date) +"   "+ spr_HoraAgen.getValue()+":"+min);
       } catch(NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!\n"+ex, "Error!", JOptionPane.INFORMATION_MESSAGE);
       }
    }

    private void activarEmpresa() {
        desactivarDestellos();
        Destello_Empresa.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Empresa.setVisible(true);
        lbl_Empresa.setVisible(true);
    }

    private void activarServicio() {
        desactivarDestellos();
        Destello_Productos.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Servicios.setVisible(true);
        lbl_Servicios.setVisible(true);
    }

    private void activarHistorial() {
        desactivarDestellos();
        Destello_Historial.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Historial.setVisible(true);
        lbl_Historial.setVisible(true);
    }

    private void tablaEmpleado(boolean busqueda) {

        ArrayList <Empleado> cargarDatosEmpleado;
        mEmpleado = (DefaultTableModel)tb_Empleado.getModel();
        
        String tabla = cmb_BusEmpleado.getSelectedItem().toString();
        
        if (cmb_BusEmpleado.getSelectedIndex() == 0) 
            tabla = "id";       
        
        cargarDatosEmpleado = emp.buscarDatosEmpleado(busqueda, tabla, txt_BuscarEmpleado.getText());
        
        if (cargarDatosEmpleado == null || cargarDatosEmpleado.isEmpty() && busqueda) {
            //JOptionPane.showMessageDialog(null, "No se encontro ningún dato.","Información!",JOptionPane.INFORMATION_MESSAGE); 
            for (int i = tb_Empleado.getRowCount() -1; i >= 0; i--)
                    mEmpleado.removeRow(i); 
        }
        else{
            DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
            Alinear.setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //Borra todos los datos de la tabla
            for (int i = tb_Empleado.getRowCount() -1; i >= 0; i--)
                    mEmpleado.removeRow(i); 
            
            for (int i = 0; i < mEmpleado.getColumnCount(); i++) 
                tb_Empleado.getColumnModel().getColumn(i).setCellRenderer(Alinear); //llama a centrar los dtos de las columnas de la tabla
            
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mEmpleado.getColumnCount()];
            
            for (int i = 0; i <cargarDatosEmpleado.size(); i++) {

                fila[0]=cargarDatosEmpleado.get(i).getId();            
                fila[1]=cargarDatosEmpleado.get(i).getNombre() + " "+cargarDatosEmpleado.get(i).getPaterno()+ " "+ cargarDatosEmpleado.get(i).getMaterno();
                //fila[2]=cargarDatosEmpleado.get(i).getApellidoP();
                //fila[3]=cargarDatosEmpleado.get(i).getApellidoM();
                fila[2]=get.rutReal(cargarDatosEmpleado.get(i).getRut()) ;
                fila[3]=cargarDatosEmpleado.get(i).getContraseña();
                fila[4]=cargarDatosEmpleado.get(i).getTipo();

                mEmpleado.addRow(fila);
            }  
                
            tb_Empleado.setModel(mEmpleado);
        }
    }

    private void cerrarSesion() {
        Inicio ini = new Inicio();
        ini.setVisible(true);
        this.setVisible(false);
    }
    
    private void modificarEmpleado(){
        Empleado empe = cargarEmpleado();

        Gestionar_Empleado gEmpleado = new Gestionar_Empleado(this, true, empe);
        gEmpleado.setVisible(true);

        actualizarTablaEmpleado();
    }

    private Empleado cargarEmpleado() {
        Empleado empleado = new Empleado();
        
        int filaseleccionada = tb_Empleado.getSelectedRow();
        
        empleado.setId(tb_Empleado.getValueAt(filaseleccionada, 0).toString()); //getValueAt ob tiene el valor de la fila
        empleado.setRut(tb_Empleado.getValueAt(filaseleccionada, 2).toString());
        empleado.setContraseña(tb_Empleado.getValueAt(filaseleccionada, 3).toString());
        empleado.setTipo(tb_Empleado.getValueAt(filaseleccionada, 4).toString());
        empleado.setNombre(emp.buscarEmpleado(empleado.getId(), empleado.getContraseña()).getNombre());
        empleado.setPaterno(emp.buscarEmpleado(empleado.getId(), empleado.getContraseña()).getPaterno());
        empleado.setMaterno(emp.buscarEmpleado(empleado.getId(), empleado.getContraseña()).getMaterno());
        
        return empleado;
    }

    private void actualizarTablaEmpleado() {
        //Gestionar_Empleado ge = new Gestionar_Empleado(this, true);
        
        //if (ge.cambio()) {
            for (int i = tb_Empleado.getRowCount() - 1; i >=0 ; i--)
                mEmpleado.removeRow(i); // El modelo limpia la tabla

            tablaEmpleado(false); // Volvemos a cargar la tabla
        //}
    }

    private void tablaEmpresa(boolean busqueda) {
        ArrayList <Empresa> cargarDatosEmpresa;
        
        Empresa empresa = new Empresa();
        
        mEmpresa = (DefaultTableModel)tb_Empresa.getModel();
        
        String tabla = get.cargarCMBEmpresa(cmb_BusEmpresa.getSelectedItem().toString()) ;
        
        cargarDatosEmpresa = empresa.buscarDatosEmpresa(busqueda, tabla, txt_BuscarEmpresa.getText());
        
        if (cargarDatosEmpresa == null || cargarDatosEmpresa.isEmpty() && busqueda) {
            //JOptionPane.showMessageDialog(null, "No se encontro ningún dato.","Información!",JOptionPane.INFORMATION_MESSAGE); 
            for (int i = tb_Empresa.getRowCount() -1; i >= 0; i--)
                mEmpresa.removeRow(i); 
        }
        else{
            DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
            Alinear.setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //Borra todos los datos de la tabla
            for (int i = tb_Empresa.getRowCount() -1; i >= 0; i--)
                mEmpresa.removeRow(i); 
            
            for (int i = 0; i < mEmpresa.getColumnCount(); i++) 
                tb_Empresa.getColumnModel().getColumn(i).setCellRenderer(Alinear); //llama a centrar los dtos de las columnas de la tabla
            
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mEmpresa.getColumnCount()];
            
            for (int i = 0; i <cargarDatosEmpresa.size(); i++) {

                fila[0] = cargarDatosEmpresa.get(i).getId();            
                fila[1] = cargarDatosEmpresa.get(i).getNombre();
                fila[2] = cargarDatosEmpresa.get(i).getGiro();
                fila[3] = get.rutReal(cargarDatosEmpresa.get(i).getRut()) ;
                fila[4] = cargarDatosEmpresa.get(i).getTelefono();
                fila[5] = cargarDatosEmpresa.get(i).getDireccion();
                fila[6] = cargarDatosEmpresa.get(i).getActivo();

                mEmpresa.addRow(fila);
            }  
                
            tb_Empresa.setModel(mEmpresa);
        }
    }
    
    private void tablaCliente(boolean busqueda) {
        ArrayList <Cliente> cargarDatosCliente;
        
        Cliente cliente = new Cliente();
        
        mCliente = (DefaultTableModel)tb_Cliente.getModel();
        
        String tabla = get.cargarCMBCliente(cmb_Cliente.getSelectedItem().toString()) ;
        
        cargarDatosCliente = cliente.buscarDatosCliente(busqueda, tabla, txt_Cliente.getText());
        
        if (cargarDatosCliente == null || cargarDatosCliente.isEmpty() && busqueda) {
            //JOptionPane.showMessageDialog(null, "No se encontro ningún dato.","Información!",JOptionPane.INFORMATION_MESSAGE); 
            for (int i = tb_Cliente.getRowCount() -1; i >= 0; i--)
                mCliente.removeRow(i); 
        }
        else{
            DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
            Alinear.setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //Borra todos los datos de la tabla
            for (int i = tb_Cliente.getRowCount() -1; i >= 0; i--)
                mCliente.removeRow(i); 
            
            for (int i = 0; i < mCliente.getColumnCount(); i++) 
                tb_Cliente.getColumnModel().getColumn(i).setCellRenderer(Alinear); //llama a centrar los dtos de las columnas de la tabla
            
            //datos que se cargaran en la tabla
            Object[] fila = new Object[mCliente.getColumnCount()];
            
            for (int i = 0; i <cargarDatosCliente.size(); i++) {

                fila[0] = cargarDatosCliente.get(i).getId();            
                fila[1] = cargarDatosCliente.get(i).getNombre() + " " + cargarDatosCliente.get(i).getPaterno() + " " + cargarDatosCliente.get(i).getMaterno();
                fila[2] = get.rutReal(cargarDatosCliente.get(i).getRut()) ;
                fila[3] = cargarDatosCliente.get(i).getCorreo();
                fila[4] = cargarDatosCliente.get(i).getTelefono();
                fila[5] = cargarDatosCliente.get(i).getDireccion();
                fila[6] = cargarDatosCliente.get(i).getActivo();
                
                mCliente.addRow(fila);
            }  
                
            tb_Cliente.setModel(mCliente);
        }
    }

    private void quitarServicio() {
        int filaseleccionada = tb_Cotizacion.getSelectedRow();

        mCotizacion.removeRow(filaseleccionada); 
        
        calculaNeto();
        calculaIva();      
        calculaTotal();
    }

    private void privilegiosDelPersonal() {
        if (!emp.getTipo().equals("Administrador")) {
            //Inhabilitar botones del home
            btn_Empleados.setEnabled(false);
            btn_Empresa.setEnabled(false);
            btn_Historial.setEnabled(false);
            btn_Configuracion.setEnabled(false);
            btn_Cliente.setEnabled(false);
            //Inhabilitar boton del menu
            btn_EmpleadoMenu.setEnabled(false);
            btn_EmpresaMenu.setEnabled(false);
            btn_HistorialMenu.setEnabled(false);
            btn_ConfiguracionMenu.setEnabled(false);
            btn_ClienteMenu.setEnabled(false);
            //Inhabilitar accedos del panel
            menu_Empleado.setEnabled(false);
            menu_Empresa.setEnabled(false);
            menu_Historial.setEnabled(false);
            menu_Configuracion.setEnabled(false);
            menu_Cliente.setEnabled(false);
            
        }
    }

    private void actualizarTablaEmpresa() {
        for (int i = tb_Empresa.getRowCount() - 1; i >=0 ; i--)
            mEmpresa.removeRow(i); // El modelo limpia la tabla

        tablaEmpresa(false); // Volvemos a cargar la tabla
    }

    private void nuevaEmpresa() {
        Gestionar_Empresa gem = new Gestionar_Empresa(this, true);
        gem.setVisible(true);
        
        actualizarTablaEmpresa();
    }

    private void nuevoEmpleado() {
        Gestionar_Empleado gem = new Gestionar_Empleado(this, true);
        gem.setVisible(true);
        //Una vez cerrada la interfaz de empleado actualizamos la tabla
        actualizarTablaEmpleado();
    }

    private void setFilaAlto(int i) {
        tb_Empleado.setRowHeight(i);
        tb_Cotizacion.setRowHeight(i);
        tb_Empresa.setRowHeight(i);
        tb_Servicio.setRowHeight(i);
        tb_Cliente.setRowHeight(i);
    }

    private void modificarEmpresa() {
        Empresa empe = cargarEmpresa();

        Gestionar_Empresa ge = new Gestionar_Empresa(this, true, empe);
        ge.setVisible(true);

        actualizarTablaEmpresa();
    }

    private Empresa cargarEmpresa() {
        Empresa empresa = new Empresa();
        
        int filaseleccionada = tb_Empresa.getSelectedRow();
        
        empresa.setId(tb_Empresa.getValueAt(filaseleccionada, 0).toString()); //getValueAt ob tiene el valor de la fila
        empresa.setNombre(tb_Empresa.getValueAt(filaseleccionada, 1).toString());
        empresa.setGiro(tb_Empresa.getValueAt(filaseleccionada, 2).toString());
        empresa.setRut(tb_Empresa.getValueAt(filaseleccionada, 3).toString());
        empresa.setTelefono(tb_Empresa.getValueAt(filaseleccionada, 4).toString());
        empresa.setDireccion(tb_Empresa.getValueAt(filaseleccionada, 5).toString());
        empresa.setActivo(Integer.valueOf(tb_Empresa.getValueAt(filaseleccionada, 6).toString()));
        
        return empresa;
    }

    private void tablaServicio(boolean busqueda) {
        ArrayList <Servicio> cargarDatosServicio;
        
        Servicio servicio = new Servicio();
        
        mServicio = (DefaultTableModel)tb_Servicio.getModel();
        
        String tabla = cmb_BusServicio.getSelectedItem().toString() ;
        
        if (tabla.equals("Código")) 
            tabla = "Codigo";
        
        String mate = get.matematicas(cmb_PrecioServicio.getSelectedItem().toString());
            
        cargarDatosServicio = servicio.buscarDatosServicio(busqueda, tabla, txt_BuscarServicio.getText(), mate);

        if (cargarDatosServicio == null || cargarDatosServicio.isEmpty() && busqueda) {
            //JOptionPane.showMessageDialog(null, "No se encontro ningún dato.","Información!",JOptionPane.INFORMATION_MESSAGE); 
            for (int i = tb_Servicio.getRowCount() -1; i >= 0; i--)
                mServicio.removeRow(i); 
        }
        else{
            DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
            Alinear.setHorizontalAlignment(SwingConstants.CENTER);//alinea  en el centro datos de la tabla             
            //Borra todos los datos de la tabla
            for (int i = tb_Servicio.getRowCount() -1; i >= 0; i--)
                mServicio.removeRow(i); 

            for (int i = 0; i < mServicio.getColumnCount(); i++) 
                tb_Servicio.getColumnModel().getColumn(i).setCellRenderer(Alinear); //llama a centrar los dtos de las columnas de la tabla

            //datos que se cargaran en la tabla
            Object[] fila = new Object[mServicio.getColumnCount()];

            for (int i = 0; i <cargarDatosServicio.size(); i++) {

                fila[0] = cargarDatosServicio.get(i).getCodigo();            
                fila[1] = cargarDatosServicio.get(i).getServicio();
                fila[2] = get.numerosConPuntos(cargarDatosServicio.get(i).getPrecio());

                mServicio.addRow(fila);
            }  

            tb_Servicio.setModel(mServicio);
        }
    }

    private void modificarServicio() {
        Servicio servicio = cargarServicio();
        
        Gestionar_Servicio gs = new Gestionar_Servicio(this, true, servicio);
        gs.setVisible(true);
        
        actualizarTablaServicio();
        cargarCMBServicios();
    }

    private Servicio cargarServicio() {
        Servicio servicio = new Servicio();
        
        int filaseleccionada = tb_Servicio.getSelectedRow();
        
        servicio.setCodigo(tb_Servicio.getValueAt(filaseleccionada, 0).toString()); //Codigo
        servicio.setServicio(tb_Servicio.getValueAt(filaseleccionada, 1).toString());//Servicio
        servicio.setPrecio(Integer.valueOf(get.reemplazo(tb_Servicio.getValueAt(filaseleccionada, 2).toString(), ".")));//Precio
        
        return servicio;
    }

    private void buscaPrecioServicio() {
        if (cmb_BusServicio.getSelectedItem().equals("Precio")) {
            txt_BuscarServicio.setText("");
            lbl_ServicioIgual.setVisible(false);
            cmb_PrecioServicio.setVisible(true);
        }
        else{
            lbl_ServicioIgual.setVisible(true);
            cmb_PrecioServicio.setVisible(false);
        }
    }

    private void nuevoServicio() {
        Gestionar_Servicio gs = new Gestionar_Servicio(this, true);
        gs.setVisible(true);
        
        actualizarTablaServicio();
        cargarCMBServicios();
    }

    private void actualizarTablaServicio() {
        for (int i = tb_Servicio.getRowCount() - 1; i >=0 ; i--)
            mServicio.removeRow(i); // El modelo limpia la tabla

        tablaServicio(false); // Volvemos a cargar la tabla
    }

    private void removeAllService() {
        for (int i = tb_Cotizacion.getRowCount() - 1; i >= 0; i--) 
            mCotizacion.removeRow(i); 

        calculaNeto();
        calculaIva();      
        calculaTotal();
    }

    private void registrarServicioAutomatico() {
        Servicio serv = new Servicio();
        
        int ultimo = Integer.valueOf(serv.obtenerCodigo().getCodigo() ) + 1 ;
        String numero = get.largo(String.valueOf(ultimo)) + ultimo;

        serv.setServicio(cmb_Servicios.getSelectedItem().toString());
        serv.setCodigo(get.generarCodigo(serv.getServicio()) + "-" + numero);
        serv.setPrecio(Integer.valueOf(txt_Precio.getText()));
        
        if (serv.insertarServicio()) {
            //JOptionPane.showMessageDialog(null, "Código: "+serv.getCodigo()+"\nfue ingresado correctamente","Servicio ingresado",JOptionPane.INFORMATION_MESSAGE);
            actualizarTablaServicio();
        }
    }

    private void cargarCMBRut(boolean carga) {
        cmb_Rut.removeAllItems();
        
        Empresa empre = new Empresa();
        Cliente cliente = new Cliente();
        
        if (!carga) {
            txt_Contacto.setVisible(true);
            lbl_Contacto.setVisible(true);
            ArrayList<Empresa> listaEmpresa = empre.buscarDatosEmpresa(false, "", "");
            
            for (int i = 0; i < listaEmpresa.size(); i++) 
                cmb_Rut.addItem(String.valueOf(listaEmpresa.get(i).getRut()));
        }
        else{
            txt_Contacto.setVisible(false);
            lbl_Contacto.setVisible(false);
            ArrayList<Cliente> listaCliente = cliente.buscarDatosCliente(false, "", "");
        
            for (int i = 0; i < listaCliente.size(); i++) 
                cmb_Rut.addItem(String.valueOf(listaCliente.get(i).getRut()));
        }
    }

    private void activarCliente() {
        desactivarDestellos();
        Destello_Cliente.setVisible(true);
        ocultarPaneles();
        ocultarEtiquetas();
        pnl_Cliente.setVisible(true);
        lbl_Cliente.setVisible(true);   
    }

    private void modificarCliente() {
        Cliente cliente = cargarCliente();
        Gestionar_Cliente gc = new Gestionar_Cliente(this, true, cliente);
        gc.setVisible(true);
        actualizarTablaCliente();
    }

    private Cliente cargarCliente() {
        Cliente cliente = new Cliente();
        
        int filaseleccionada = tb_Cliente.getSelectedRow();
        
        cliente.setId(tb_Cliente.getValueAt(filaseleccionada, 0).toString());      //Codigo
        cliente.setNombre(cliente.buscarCliente("id", tb_Cliente.getValueAt(filaseleccionada, 0).toString()).getNombre());  //Nombre
        cliente.setPaterno(cliente.buscarCliente("id", tb_Cliente.getValueAt(filaseleccionada, 0).toString()).getPaterno());//Apellido Paterno
        cliente.setMaterno(cliente.buscarCliente("id", tb_Cliente.getValueAt(filaseleccionada, 0).toString()).getMaterno()); //Apellido Materno
        cliente.setRut(tb_Cliente.getValueAt(filaseleccionada, 2).toString());     //Rut
        cliente.setCorreo(tb_Cliente.getValueAt(filaseleccionada, 3).toString());  //Correo
        cliente.setTelefono(tb_Cliente.getValueAt(filaseleccionada, 4).toString());//Teléfono
        cliente.setDireccion(tb_Cliente.getValueAt(filaseleccionada, 5).toString());//Servicio
        cliente.setActivo(Integer.valueOf(tb_Cliente.getValueAt(filaseleccionada, 6).toString()));//Activo
        
        return cliente;
    }

    private void actualizarTablaCliente() {
        for (int i = tb_Cliente.getRowCount() - 1; i >=0 ; i--)
            mCliente.removeRow(i); // El modelo limpia la tabla

        tablaCliente(false); // Volvemos a cargar la tabla
    }

    private void crearNuevoCliente() {
        Cliente cliente = cargarCOTCliente();
        
        if (cliente.insertarCliente()) {
            JOptionPane.showMessageDialog(null, "Cliente ingresado correctamente","Cliente ingresado",JOptionPane.INFORMATION_MESSAGE);
            
            String guarda = cmb_Rut.getSelectedItem().toString();
            
            cargarCMBRut(rdb_Cliente.isSelected());
            
            ultimoRut(guarda);
            
            tablaCliente(false);
        }
    }

    private void crearNuevaEmpresa() {
        Empresa empresa = cargarCOTEmpresa();
        
        if (empresa.insertarEmpresa()) {
            JOptionPane.showMessageDialog(null, "Empresa ingresada correctamente","Empresa ingresada",JOptionPane.INFORMATION_MESSAGE);
            
            String guarda = cmb_Rut.getSelectedItem().toString();
            
            cargarCMBRut(rdb_Cliente.isSelected());
            
            ultimoRut(guarda);
            
            tablaEmpresa(false);
        }
    }

    private Cliente cargarCOTCliente() {
        Cliente cliente = new Cliente();
        
        cliente.setRut(cmb_Rut.getSelectedItem().toString());
        cliente.setNombre(txt_Nombre.getText());
        cliente.setPaterno(txt_Nombre.getText());
        cliente.setMaterno(txt_Nombre.getText());
        cliente.setCorreo(txt_Correo.getText());
        cliente.setTelefono(txt_Fono.getText());
        cliente.setDireccion("SIN DATOS");
        cliente.setActivo(1);
        
        return cliente;
    }

    private Empresa cargarCOTEmpresa() {
        Empresa empresa = new Empresa();
        
        empresa.setRut(cmb_Rut.getSelectedItem().toString());
        empresa.setNombre(txt_Nombre.getText());
        empresa.setGiro("");
        empresa.setCorreo(txt_Correo.getText());
        empresa.setTelefono(txt_Fono.getText());
        empresa.setDireccion("SIN DATOS");
        empresa.setActivo(1);
        
        return empresa;
    }

    private void ultimoRut(String guarda) {
        for (int i = 0; i < cmb_Rut.getItemCount(); i++) {
            if (cmb_Rut.getItemAt(i).equals(guarda)) {
                cmb_Rut.setSelectedIndex(i);
                break;
            }
        }
    }

    private void nuevaCotizacion() {
        Cotizacion coti = new Cotizacion();
        
        coti.setRut(cmb_Rut.getSelectedItem().toString());
        coti.setNombre(txt_Nombre.getText());
        coti.setContacto(txt_Contacto.getText());
        coti.setTotal(Integer.valueOf(get.reemplazo(txt_Total.getText(), ".")) );
        
        if (coti.insertarCotizacion()) {
            //JOptionPane.showMessageDialog(null, "Cotización ingresada correctamente","Cotización ingresada",JOptionPane.INFORMATION_MESSAGE);
            int cotizacion = coti.obtenerCodigo().getId() + 1 ;
        
            lbl_Cotizacion.setText("Cotización N° 00" +  cotizacion) ;//Obtener la ultima cotizacion + 1
        }
    }

    private void nuevoCliente() {
        Gestionar_Cliente gc = new Gestionar_Cliente(this, true);
        gc.setVisible(true);
        tablaCliente(false);
    }

}