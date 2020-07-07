package Clases;

import Persistencia.EmpleadoDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Empleado {
    
    private String id;
    private String nombre;
    private String paterno;
    private String materno;
    private String rut;
    private String contraseña;
    private String tipo;

    public Empleado(String id, String nombre, String paterno, String materno, String rut, String contraseña, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.rut = rut;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }
    
    public Empleado(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public boolean insertarEmpleado(){
        EmpleadoDAO sql = new EmpleadoDAO();//instancia ClienteDao
        return sql.InsertarEmpleadoDAO(this);
    }

    // llama al metodo Actualizar Cliente   
    public boolean actualizarEmpleado(){
        EmpleadoDAO empdao = new EmpleadoDAO();//instancia ClienteDao
        return empdao.actualizarEmpleadoDAO(this); 
    }
    
    //   llama al metodo Buscar Empleado por codigo y contraseña
    public Empleado buscarEmpleado(String codigo, String contraseña){
        EmpleadoDAO sqldao = new EmpleadoDAO();//instancia ClienteDao
        Empleado empl = sqldao.buscarEmpleadoRegistradoDAO(codigo, contraseña);
        
        if(empl != null)
            return empl;
        else
            return null;
    }
    
    public Empleado obtenerCodigo(){
        EmpleadoDAO sqldao = new EmpleadoDAO();//instancia ClienteDao
        Empleado empl = sqldao.obtenerCodigoDAO();
        
        if(empl != null){
            this.setRut(empl.getRut());
            return empl;
        }else
            return null;
    }
    
//    public boolean eliminarEmpleado(String id){
//        EmpleadoDAO empdao = new EmpleadoDAO();//instancia ClienteDao
//        return empdao.eliminarEmpleadoDao(id);
//    }
     
    public ArrayList<Empleado> buscarDatosEmpleado(boolean busqueda, String tabla, String dato){
        EmpleadoDAO empdao = new EmpleadoDAO();//instancia ClienteDao
        return empdao.buscarDatosEmpleadoDAO(busqueda, tabla, dato);
    }
    
}