package Extra;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Validar {
    
    public boolean maximoLargo(String cadena, int max){
        return cadena.length() < max;
    }
    
    public boolean formatoCorreo(String correo){
        Pattern pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        Matcher mat = pat.matcher(correo);
        
        return mat.find();
    }
    
    public boolean formatoRut(String rut, int index){
        switch (rut.charAt(index)) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
            case '-':
                return true;

            default:
                return false;
        }
    }
    
    public boolean conector(String codigo){
        
        switch (codigo.toLowerCase()) {
            case "la":
            case "el":
            case "de":
            case "para":
            case "desde":
            case "las":
            case "los":
            case "del":
            case "lo":
            case "cual":
            case "esos":
            case "esas":
            case "dentro":
            case "sobre":
            case "arriba":
            case "abajo":
            case "en":
            case "con":
                return true;

            default:
                return false;
        }
    }
    
    public boolean precioServicio(String precio){
        try {
            Integer.valueOf(precio);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Favor ingresa un precio","Error de conexión",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
    public String rut (String rut){
        boolean aux = false;
        
        for (int i = 0; i < rut.length(); i++) {
            if (rut.charAt(i) == '-') {
                aux = true;
                break; 
            }
        }
        
        if (!aux) 
            return null;
        
        String RUT="";
        
        //Obtener el DV
        char dv = rut.charAt(rut.length()-1);
        //Obtener rut antes del guión
        int i = 0;
        while (rut.charAt(i) != '-') {
            RUT = RUT+rut.charAt(i);//Coonstruye el rut
            i++;
        }
        
        rut = RUT;
        int DV=0;
        
        String digi = ""+dv;

        try{
            DV =Integer.parseInt(digi);
        }
        catch (Exception ex){
            if (dv == 'k' || dv == 'K')
                DV = 10;
        }

        int cont;
        int contar = 2;
        int acumulador = 0;
        int divisor;
        int dig;
        
        int Rut;
        try{
            Rut = Integer.parseInt(rut);
        }
        catch(Exception e){
            return null;
        }
        
        do{
            cont = ((Rut % 10) * contar);
            acumulador = acumulador + cont;
            Rut = (Rut / 10);
            contar++;
            if (contar == 8)
                contar = 2;
        } while (Rut != 0);

        divisor = acumulador % 11;

        if (divisor == 0)
            divisor = 11;

        dig = 11 - divisor;

        if (dig == DV)
            return RUT+"-"+dig;
        else
            return null;
    }
}
