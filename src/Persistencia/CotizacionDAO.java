package Persistencia;

import Clases.Cotizacion;
import Conexión.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class CotizacionDAO {
    private Conexion con = null;

    public CotizacionDAO() {
        con = new Conexion();//instancia la conexion
    }
    
    public boolean InsertarCotizacionDAO(Cotizacion coti){
        try{
            String query = "INSERT INTO Cotizacion VALUES ('"+coti.getNombre()+"','"+coti.getRut()+"','"+
            coti.getContacto()+"',"+coti.getTotal()+")";
            
            boolean flag = con.ejecutaSQLinstruccion(query);//variable igualada a conexion consulta
            con.closeConnection();//cierra la conexion
            return flag;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection(); //cierra la conexion 
        } 
    }
    
    public Cotizacion obtenerCodigoDAO (){
        Cotizacion coti = null;
        String query = "SELECT COUNT(rut) as Total FROM Cotizacion";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        
        try {
            if (rs.next()) {
                coti = new Cotizacion();
                coti.setId(rs.getInt(1));
//                JOptionPane.showMessageDialog(null, "ID : "+coti.getRut(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
                return coti;
            }else
                return null;            
        } 
        catch (SQLException ex) {
            return null;
        }finally{
            con.closeConnection();//cierra la conexion  
        }  
    }
    
}
