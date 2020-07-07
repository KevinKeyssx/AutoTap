package Persistencia;

import Clases.Empresa;
import Conexión.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class EmpresaDAO {
    private Conexion con = null;
    
    public EmpresaDAO(){
        con = new Conexion();//instancia la conexion
    }
    
    public Empresa buscarEmpresaUnicaDAO(String tabla,String dato){
        Empresa emp = null;
        String query="SELECT * FROM Empresa WHERE "+tabla +" = '"+dato+"'";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            if (rs.next()) {
                emp = new Empresa();
                emp.setId(rs.getString("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setGiro(rs.getString("giro"));
                emp.setRut(rs.getString("rut"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setActivo(rs.getInt("activo"));
                emp.setCorreo(rs.getString("correo"));
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
    
    public boolean InsertarEmpresaDAO(Empresa empresa){
        try{
            String query = "INSERT INTO Empresa VALUES ('"+empresa.getNombre()+"','"+empresa.getGiro()+"','"+
            empresa.getRut()+"','"+empresa.getTelefono()+"','"+empresa.getDireccion()+"', "+empresa.getActivo()+",'"+empresa.getCorreo()+"')";
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
    
    public boolean actualizarEmpresaDAO(Empresa empresa){
        try{
            String query = "UPDATE Empresa SET  Nombre = '"+empresa.getNombre()+"', Giro = '"+empresa.getGiro()+"', Telefono = '"+empresa.getTelefono()
                    +"', Direccion = '"+empresa.getDireccion()+"', Activo = "+empresa.getActivo() + ", Correo = '"+empresa.getCorreo()+"'"
                    +" WHERE Id = '"+empresa.getId()+"';";
            
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
    
    public Empresa obtenerCodigoDAO (){
        Empresa emp = null;
        String query="SELECT MAX(Id) as Id FROM Empresa";
        ResultSet rs = con.ejecutaConsultaQuery(query);
        
        try {
            if (rs.next()) {
                emp = new Empresa();
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
    
    public ArrayList<Empresa> buscarDatosEmpresaDAO(boolean busqueda, String tabla, String dato){
        String query;

        if (busqueda)
            query = "Select * from Empresa where "+tabla+" like '"+dato+"%'";
        else
            query = "Select * from Empresa";
        
        ArrayList<Empresa> listaEmpresa = new ArrayList<Empresa>();
        
        ResultSet rs = con.ejecutaConsultaQuery(query);
        try {
            while(rs.next()) {
                Empresa emp = new Empresa();//instancia clase cliente
                emp.setId(rs.getString("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setGiro(rs.getString("giro"));
                emp.setRut(rs.getString("rut"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setActivo(rs.getInt("activo"));
                emp.setCorreo(rs.getString("correo"));
                
                listaEmpresa.add(emp);
            }
            con.closeConnection();//cierra la conexion
            return listaEmpresa;  
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