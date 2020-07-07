package Clases;

import Persistencia.ServicioDAO;
import java.util.ArrayList;

/**
 * @author keiss
 */
public class Servicio {
    
    private String codigo;
    private String codigoAntiguo;
    private String servicio;
    private int precio;

    public Servicio() {}

    public Servicio(String codigo, String codigoAntiguo, String servicio, int precio) {
        this.codigo = codigo;
        this.codigoAntiguo = codigoAntiguo;
        this.servicio = servicio;
        this.precio = precio;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoAntiguo() {
        return codigoAntiguo;
    }

    public void setCodigoAntiguo(String codigoAntiguo) {
        this.codigoAntiguo = codigoAntiguo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public boolean insertarServicio(){
        ServicioDAO sql = new ServicioDAO();//instancia ClienteDao
        return sql.InsertarServicioDAO(this);
    }

    // llama al metodo Actualizar Cliente   
    public boolean actualizarServicio(){
        ServicioDAO servi = new ServicioDAO();//instancia ClienteDao
        return servi.actualizarServicioDAO(this); 
    }
    
    //   llama al metodo Buscar Empleado por codigo y contraseña
    public Servicio buscarServicio(String id){
        ServicioDAO sqldao = new ServicioDAO();//instancia ClienteDao
        Servicio servi = sqldao.buscarServicioUnicaDAO(id);
        
        if(servi != null)
            return servi;
        else
            return null;
    }
    
    public Servicio obtenerCodigo(){
        ServicioDAO sqldao = new ServicioDAO();//instancia ClienteDao
        Servicio servi = sqldao.obtenerCodigoDAO();
        
        if(servi != null)
            return servi;
        else
            return null;
    }
     
    public ArrayList<Servicio> buscarDatosServicio(boolean busqueda, String tabla, String dato, String precio){
        ServicioDAO servi = new ServicioDAO();//instancia ClienteDao
        return servi.buscarDatosServicioDAO(busqueda, tabla, dato, precio);
    }
    
    public boolean eliminarServicio(String codigo){
        ServicioDAO servi = new ServicioDAO();//instancia ClienteDao
        return servi.eliminarServicioDao(codigo);
    }
}
