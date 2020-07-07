package Clases;

import Persistencia.CotizacionDAO;

/**
 * @author keiss
 */
public class Cotizacion {
    
    private int id;
    private String nombre;
    private String rut;
    private String contacto;
    private int total;

    public Cotizacion() {
    }

    public Cotizacion(int id, String nombre, String rut, String contacto, int total) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.contacto = contacto;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public boolean insertarCotizacion(){
        CotizacionDAO sql = new CotizacionDAO();//instancia ClienteDao
        return sql.InsertarCotizacionDAO(this);
    }
    
    public Cotizacion obtenerCodigo(){
        CotizacionDAO sqldao = new CotizacionDAO();//instancia ClienteDao
        Cotizacion coti = sqldao.obtenerCodigoDAO();
        
        if(coti != null)
            return coti;
        else
            return null;
    }
}
