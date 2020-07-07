package Clases;

import Persistencia.EmpresaDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Empresa {
    private String id;
    private String nombre;
    private String giro;
    private String rut;
    private String telefono;
    private String direccion;
    private int activo;
    private String correo;

    //CONSTRUCTOR VACIO
    public Empresa() {}
    
    //CONSTRUCTOR LLENO
    public Empresa(String id, String nombre, String giro, String rut, String telefono, String direccion, int activo, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.giro = giro;
        this.rut = rut;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = activo;
        this.correo = correo;
    }

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

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
 
    public boolean insertarEmpresa(){
        EmpresaDAO sql = new EmpresaDAO();//instancia ClienteDao
        return sql.InsertarEmpresaDAO(this);
    }

    // llama al metodo Actualizar Cliente   
    public boolean actualizarEmpresa(){
        EmpresaDAO empdao = new EmpresaDAO();//instancia ClienteDao
        return empdao.actualizarEmpresaDAO(this); 
    }
    
    //   llama al metodo Buscar Empleado por codigo y contraseña
    public Empresa buscarEmpresa(String tabla, String dato){
        EmpresaDAO sqldao = new EmpresaDAO();//instancia ClienteDao
        Empresa empl = sqldao.buscarEmpresaUnicaDAO(tabla, dato);
        
        if(empl != null)
            return empl;
        else
            return null;
    }
    
    public Empresa obtenerCodigo(){
        EmpresaDAO sqldao = new EmpresaDAO();//instancia ClienteDao
        Empresa empl = sqldao.obtenerCodigoDAO();
        
        if(empl != null){
            //this.setRut(empl.getRut());
            return empl;
        }else
            return null;
    }
    
//    public boolean eliminarEmpleado(String id){
//        EmpleadoDAO empdao = new EmpleadoDAO();//instancia ClienteDao
//        return empdao.eliminarEmpleadoDao(id);
//    }
     
    public ArrayList<Empresa> buscarDatosEmpresa(boolean busqueda, String tabla, String dato){
        EmpresaDAO empdao = new EmpresaDAO();//instancia ClienteDao
        return empdao.buscarDatosEmpresaDAO(busqueda, tabla, dato);
    }
    
}