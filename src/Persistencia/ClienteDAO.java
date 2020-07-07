package Persistencia;

import Clases.Cliente;
import Conexión.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class ClienteDAO {
    private Conexion con = null;
    
    public ClienteDAO(){
        con = new Conexion();//instancia la conexion
    }
    
    public Cliente buscarClienteUnicoDAO(String tabla, String dato){
        Cliente clie = null;
        String query="SELECT * FROM Cliente WHERE "+tabla+" = '"+dato+"'";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            if (rs.next()) {
                clie = new Cliente();
                clie.setId(rs.getString("id"));
                clie.setRut(rs.getString("rut"));
                clie.setNombre(rs.getString("nombre"));
                clie.setPaterno(rs.getString("paterno"));
                clie.setMaterno(rs.getString("materno"));
                clie.setCorreo(rs.getString("correo"));
                clie.setTelefono(rs.getString("telefono"));
                clie.setDireccion(rs.getString("direccion"));
                clie.setActivo(rs.getInt("activo"));
                
                return clie;
            }else
                return null;            
        } 
        catch (SQLException ex) {
            return null;
        }finally{
            con.closeConnection();//cierra la conexion  
        }  
    }
    
    public boolean InsertarClienteDAO(Cliente clie){
        try{
            String query = "INSERT INTO Cliente VALUES ('"+clie.getRut()+"','"+clie.getNombre()+"','"+
            clie.getPaterno()+"','"+clie.getMaterno()+"','"+clie.getCorreo()+"', '"+clie.getTelefono()+"','"+clie.getDireccion()+
            "', "+clie.getActivo()+")";
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
    
    public boolean actualizarClienteDAO(Cliente clie){
        try{
            String query = "UPDATE Cliente SET  Nombre = '"+clie.getNombre()+"', Paterno = '"+clie.getPaterno()+"', Materno = '"+clie.getMaterno()
                    +"', Correo = '"+clie.getCorreo()+"', Telefono = '"+clie.getTelefono() + "', Direccion = '"+clie.getDireccion()+"', "
                    +" Activo = " + clie.getActivo() + " WHERE id = '"+clie.getId()+"';";
            
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
    
    public Cliente obtenerCodigoDAO (){
        Cliente emp = null;
        String query = "SELECT MAX(id) as Total FROM Cliente";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        
        try {
            if (rs.next()) {
                emp = new Cliente();
                emp.setRut(rs.getString(1));

                return emp;
            }else
                return null;            
        } 
        catch (SQLException ex) {
            return null;
        }finally{
            con.closeConnection();//cierra la conexion  
        }  
    }
    
//    public boolean eliminarEmpleadoDao(String id){
//        try{
//            String query ="DELETE FROM Empleado WHERE Id = "+id+";"; 
//             boolean flag = con.ejecutaSQLinstruccion(query);
//            con.closeConnection();//cierra la conexion
//            return flag;
//        }
//        catch(Exception e){
//            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+e.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        finally{
//            con.closeConnection(); //cierra la conexion 
//        } 
//    }
    
    public ArrayList<Cliente> buscarDatosClienteDAO(boolean busqueda, String tabla, String dato){
        String query;

        if (busqueda)
            if (!tabla.equals("correo")) 
                query = "Select * from Cliente where "+tabla+" like '"+dato+"%'";
            else
                query = "Select * from Cliente where "+tabla+" like '%"+dato+"%'";
            
        else
            query = "Select * from Cliente";
        
        ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
        
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            while(rs.next()) {
                Cliente clie = new Cliente();//instancia clase cliente
                clie = new Cliente();
                clie.setId(rs.getString("id"));
                clie.setRut(rs.getString("rut"));
                clie.setNombre(rs.getString("nombre"));
                clie.setPaterno(rs.getString("paterno"));
                clie.setMaterno(rs.getString("materno"));
                clie.setCorreo(rs.getString("correo"));
                clie.setTelefono(rs.getString("telefono"));
                clie.setDireccion(rs.getString("direccion"));
                clie.setActivo(rs.getInt("activo"));
                listaCliente.add(clie);
            }
            con.closeConnection();//cierra la conexion
            return listaCliente;  
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error! "+ex.getMessage(),"Error de conexión",JOptionPane.ERROR_MESSAGE);
            return null;
        } 
        finally {
            con.closeConnection();  //cierra la conexion
        }
    }  
}