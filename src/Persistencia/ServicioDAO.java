package Persistencia;

import Clases.Servicio;
import Conexión.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class ServicioDAO {
    private Conexion con = null;
    
    public ServicioDAO(){
        con = new Conexion();//instancia la conexion
    }
    
    public Servicio buscarServicioUnicaDAO(String codigo){
        Servicio servicio = null;
        String query="SELECT * FROM Servicio WHERE codigo = '"+codigo+"'";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            if (rs.next()) {
                servicio = new Servicio();
                servicio.setCodigo(rs.getString("codigo"));
                servicio.setServicio(rs.getString("servicio"));
                servicio.setPrecio(rs.getInt("precio"));
                return servicio;
            }else
                return null;            
        } 
        catch (SQLException ex) {
            return null;
        }finally{
            con.closeConnection();//cierra la conexion  
        }  
    }
    
    public boolean InsertarServicioDAO(Servicio servicio){
        try{
            String query = "INSERT INTO Servicio VALUES ('"+servicio.getCodigo()+"'"
                                                        +",'"+servicio.getServicio()+"'"
                                                        +", "+servicio.getPrecio()+")";
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
    
    public boolean actualizarServicioDAO(Servicio servicio){
        try{
            String query = "UPDATE Servicio SET  Codigo = '"+servicio.getCodigo()+"', Servicio = '"+servicio.getServicio()+
                            "', Precio = "+servicio.getPrecio() + " WHERE Codigo = '"+servicio.getCodigoAntiguo()+"';";
            
            boolean flag = con.ejecutaSQLinstruccion(query);
            con.closeConnection();//cierra la conexion
            
            return flag;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            con.closeConnection();//cierra la conexion  
        }   
    }
    
    public Servicio obtenerCodigoDAO (){
        Servicio servicio = null;
        String query="SELECT COUNT(Codigo) as Codigo FROM Servicio";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        
        try {
            if (rs.next()) {
                servicio = new Servicio();
                servicio.setCodigo(rs.getString(1));

                return servicio;
            }else
                return null;            
        } 
        catch (SQLException ex) {
            return null;
        }finally{
            con.closeConnection();//cierra la conexion  
        }  
    }   
    
    public ArrayList<Servicio> buscarDatosServicioDAO(boolean busqueda, String tabla, String dato, String precio){
        String query;

        if (busqueda){
            
            if (!tabla.equals("Precio"))
                query = "Select * from Servicio where "+tabla+" like '"+dato+"%' order by servicio";
            else
                query = "Select * from Servicio where Precio "+precio+" "+dato +" order by servicio";
            
        }
        else
            query = "Select * from Servicio order by servicio";
        
        if (dato.isEmpty() && tabla.equals("Precio")) 
            query = "Select * from Servicio";

        
        ArrayList<Servicio> listaServicio = new ArrayList<Servicio>();
        
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            while(rs.next()) {
                Servicio servicio = new Servicio();//instancia clase cliente
                servicio.setCodigo(rs.getString("codigo"));
                servicio.setServicio(rs.getString("servicio"));
                servicio.setPrecio(rs.getInt("precio"));
                
                listaServicio.add(servicio);
            }
            con.closeConnection();//cierra la conexion
            return listaServicio;  
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+ex.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
            return null;
        } 
        finally {
            con.closeConnection();  //cierra la conexion
        }
    }  
    
        public boolean eliminarServicioDao(String codigo){
        try{
            String query ="DELETE FROM Servicio WHERE Codigo = '"+codigo+"';"; 
             boolean flag = con.ejecutaSQLinstruccion(query);
            con.closeConnection();//cierra la conexion
            return flag;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        finally{
            con.closeConnection(); //cierra la conexion 
        } 
    }
}
