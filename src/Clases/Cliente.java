package Clases;

import Persistencia.ClienteDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Cliente {
    
    private String id;
    private String rut;
    private String nombre;
    private String paterno;
    private String materno;
    private String correo;
    private String telefono;
    private String direccion;
    private int activo;

    //CONSTRUCTOR VACIO
    public Cliente() {}
    
    //CONSTRUCTOR LLENO
    public Cliente(String id, String rut, String nombre, String paterno, String materno, String correo, String telefono, String direccion, int activo) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = activo;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
    
    public boolean insertarCliente(){
        ClienteDAO sql = new ClienteDAO();//instancia ClienteDao
        return sql.InsertarClienteDAO(this);
    }

    // llama al metodo Actualizar Cliente   
    public boolean actualizarCliente(){
        ClienteDAO empdao = new ClienteDAO();//instancia ClienteDao
        return empdao.actualizarClienteDAO(this); 
    }
    
    //   llama al metodo Buscar Empleado por codigo y contraseña
    public Cliente buscarCliente(String tabla, String dato){
        ClienteDAO sqldao = new ClienteDAO();//instancia ClienteDao
        Cliente clie = sqldao.buscarClienteUnicoDAO(tabla, dato);
        
        if(clie != null)
            return clie;
        else
            return null;
    }
    
    public Cliente obtenerCodigo(){
        ClienteDAO sqldao = new ClienteDAO();//instancia ClienteDao
        Cliente clie = sqldao.obtenerCodigoDAO();
        
        if(clie != null){
            //this.setRut(empl.getRut());
            return clie;
        }else
            return null;
    }
    
//    public boolean eliminarCliente(String rut){
//        ClienteDAO clie = new ClienteDAO();//instancia ClienteDao
//        return clie.eliminarEmpleadoDao(id);
//    }
     
    public ArrayList<Cliente> buscarDatosCliente(boolean busqueda, String tabla, String dato){
        ClienteDAO clie = new ClienteDAO();//instancia ClienteDao
        return clie.buscarDatosClienteDAO(busqueda, tabla, dato);
    }
    
}