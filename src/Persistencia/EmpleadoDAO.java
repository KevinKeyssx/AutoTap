package Persistencia;

import Clases.Empleado;
import Conexión.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class EmpleadoDAO {
    private Conexion con = null;
    
    public EmpleadoDAO(){
        con = new Conexion();//instancia la conexion
    }
    
    public Empleado buscarEmpleadoRegistradoDAO(String codigo, String contraseña){
        Empleado emp = null;
        String query="SELECT * FROM Empleado WHERE Id = '"+codigo+"' and contraseña = '"+contraseña+"'";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            if (rs.next()) {
                emp = new Empleado();
                emp.setId(rs.getString("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setPaterno(rs.getString("paterno"));
                emp.setMaterno(rs.getString("materno"));
                emp.setContraseña(rs.getString("contraseña"));
                emp.setRut(rs.getString("rut"));
                emp.setTipo(rs.getString("tipo"));
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
    
    public boolean InsertarEmpleadoDAO(Empleado empleado){
        try{
            String query = "INSERT INTO Empleado VALUES ('"+empleado.getNombre()+"','"+empleado.getPaterno()+"','"+
            empleado.getMaterno()+"','"+empleado.getRut()+"','"+empleado.getContraseña()+"','"+empleado.getTipo()+"')";
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
    
    public boolean actualizarEmpleadoDAO(Empleado empleado){
        try{
            String query = "UPDATE Empleado SET  Nombre = '"+empleado.getNombre()+"', Paterno='"+empleado.getPaterno()
                    +"', Materno = '"+empleado.getMaterno()+"', Contraseña = '"+empleado.getContraseña()
                    +"', Tipo = '"+empleado.getTipo()+"' WHERE Id = '"+empleado.getId()+"';";
            
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
    
    public Empleado obtenerCodigoDAO (){
        Empleado emp = null;
        String query="SELECT MAX(Id) as Id FROM Empleado";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        
        try {
            if (rs.next()) {
                emp = new Empleado();
                emp.setId(rs.getString(1));

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
    
    public ArrayList<Empleado> buscarDatosEmpleadoDAO(boolean busqueda, String tabla, String dato){
        String query;

        if (busqueda){
            if (!tabla.equals("Nombre")) 
                query = "Select * from Empleado where "+tabla+" like '"+dato+"%'";
            else
                query = "Select * from Empleado where nombre+' '+paterno+' '+materno  like '%"+dato+"%'";
            
        }else
            query = "Select * from Empleado";
        
        ArrayList<Empleado> listaConsultaEmpleado = new ArrayList<Empleado>();
        
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            while(rs.next()) {
                Empleado emp = new Empleado();//instancia clase cliente
                emp.setId(rs.getString("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setPaterno(rs.getString("paterno"));
                emp.setMaterno(rs.getString("materno"));
                emp.setContraseña(rs.getString("contraseña"));
                emp.setRut(rs.getString("rut"));
                emp.setTipo(rs.getString("tipo"));
                
                listaConsultaEmpleado.add(emp);
            }
            con.closeConnection();//cierra la conexion
            return listaConsultaEmpleado;  
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