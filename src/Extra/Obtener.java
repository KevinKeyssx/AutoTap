package Extra;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class Obtener {
    
    public String fechaActual(String separador){
        Calendar cal = Calendar.getInstance();
        String dia = cal.get(Calendar.DATE) + "";
        String mes = (cal.get(Calendar.MONTH) + 1 ) + ""; 
        String año = cal.get(Calendar.YEAR) + "";
        return (dia+separador+mes+separador+año);
    }
    
    public String añoActual(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "";
    }
    
    public String rutReal(String rut){
        String RUT = "";
        
        for (int i = 0; i < rut.length()-2; i++) 
            RUT = RUT + rut.charAt(i) ;
        
        long Rut;
        
        try{
            Rut = Long.valueOf(RUT);
        }
        catch(Exception e){
            return null;
        }
        
        String dv = rut.charAt(rut.length()-2) + "" + rut.charAt(rut.length()-1);
        //JOptionPane.showMessageDialog(null, "RUT: "+(numerosConPuntos(Rut)+ dv),"",JOptionPane.INFORMATION_MESSAGE);   
        return numerosConPuntos(Rut) + dv;
    }
    
    public String numerosConPuntos (long numero){
        String palabreDeNumero = String.valueOf(numero);

        if (palabreDeNumero.length()>3) {          
            char letra;
            int suma = 0, pts = 0;
            String totalreal="";

            for (int i = palabreDeNumero.length()-1; i >=0; i--) {
                //Recorre cada latra
                letra = palabreDeNumero.charAt(i);
                //Crea la nueva palabra
                totalreal = letra+totalreal;
                //Contador de puntos
                suma++;
                //Compara los largos para no imprimir el punto al principio
                if (totalreal.length()-pts!=palabreDeNumero.length()) {
                    //Concatena el punto
                    if (suma==3) {
                          totalreal = "."+totalreal;
                          suma=0; //Reinicia la suma
                          pts++; //Suma los puntos
                    }   
                }     
            }
            
            return totalreal;
        }
        else
            return palabreDeNumero;
    }
    
    public String enteroReal (double entero){
        String numero = String.valueOf(entero), palabra="", palabra2="";
        int i = numero.length()-1; //Obtenemos el largo
        
        while (numero.charAt(i) != '.') { //Recorremos de reversa mientras no haya un "."
            palabra = numero.charAt(i) + palabra; //Creamos la palabra hasta antes del "."

            i--; //Restamos seguir recorriendo en reversa
        }
        
        if (palabra.equals("0")) { //Si la palabra teminó en "0"
            i = 0; //Reiniciamos el contador para recorredor normalmente
            
            while (numero.charAt(i) != '.') { //Recorremos normalmente
                palabra2 = palabra2 + numero.charAt(i); //Creamos la nueva palabra hasta antes del "."

                i++; //Sumamos para recorremos normalmente
            }
            numero = palabra2; //Igualamos para retornar
        }
        
        return numero; //Retornamos
    
    }
    
    public String reemplazo(String cadena, String reemplazar) {
        Pattern p = Pattern.compile("["+reemplazar+"]");
        Matcher m = p.matcher(cadena);
        
        String remplazado1 = "";
        
        if(m.find())
            remplazado1 = m.replaceAll("");

        return  remplazado1;
    }
    
    //        String cadena_a,cadena_b,cadena_c="GeekyTheory";
//        //Borramos los últimos caracteres a partir del sexto carácter.
//        cadena_a = cadena_c.substring(0,5);
//        //Borramos los primeros cinco caracteres.
//        cadena_b = cadena_c.substring(5);
//        //Imprimimos el resultado.
//        System.out.println(cadena_a+"\n"+cadena_b);

//                String aRemplazar="hola como estas";
//		String remplazado=aRemplazar.replace("a","o");
//		System.out.println(remplazado);//imprime hol como ests
//                
//                String aRemplazar1="hola como estas";
//                Pattern p= Pattern.compile("[ao]");
//		Matcher m= p.matcher(aRemplazar1);
//		if(m.find()){
//			String remplazado1 = m.replaceAll("");
//			System.out.println(remplazado1); //imprime hl cm ests
//		}
    
    public String cargarCMBEmpresa(String seleccion){
        switch (seleccion) {
            case "Empresa":
                return "id";
            
            case "Razón Social":
                return "nombre";
                
            case "Giro":
                return "giro";
                
            case "Rut":
                return "rut";
                
            case "Teléfono":
                return "telefono";
                
            case "Dirección":
                return "direccion";
                
            case "Activo":
                return "activo";
                
            default:
                return "";
        }
        
    }
    
    public String cargarCMBCliente(String seleccion){
        switch (seleccion) {
            case "Cliente":
                return "id";
            
            case "Teléfono":
                return "telefono";
                
            case "Dirección":
                return "direccion";
                
            default:
                return seleccion;
        }
        
    }
    
    public String codigoServicio(String codigo){
        int i = 0;
        String servicio = "";
        
        while (codigo.charAt(i) != '-'){ 
            servicio += codigo.charAt(i);
            i++;
        }
        return servicio;
    
    }
    
    public String numeroServicio(String codigo){
        int i = codigo.length() - 1;
        String servicio = "";
        
        while (codigo.charAt(i) != '-') {
            servicio = codigo.charAt(i) + servicio;
            i--;
        }
        
        return servicio;
    }
    
    public String matematicas(String mate){
        switch (mate) {
            case "Igual":
                
            return "=";
            
            case "Mayor que":
                
            return ">=";
            
            case "Menor que":
                
            return "<=";
            
            case "Distinto que":
                
            return "!=";
            
            default:
                return "";
        }
    }
    
    public String generarCodigo(String servicio){
        String codigo = "SAT";
        int pos = 0, largo = 0;
        boolean aux = true;
        boolean concat = true;
        
        for (int i = 0; i < servicio.length(); i++) {
            //Si en ingreso es un espacio
            if (servicio.charAt(i) != ' ') {
                //Este nos reiniciara cada vez que este vacio
                if (aux) {
                    codigo = "";
                    aux = false;
                }
                //Este nos indica el largo de cada palabra ingresada
                largo ++;
                //Sumamos un caracter cada vez que sea igual a '('
                if (servicio.charAt(pos) == '(') 
                    pos ++;
                //Validamos que solo concatene la inicial siempre cuendo sea el largo mayor a 3
                if (concat && largo > 3) {
                    codigo = codigo + servicio.charAt(pos);
                    concat = false;
                }
                
            }
            else{//Sino este es un espacio
                pos = i + 1;//Tomamos la inicial de la palabra
                concat = true;//reiniciamos el concatenador 
                largo = 0;//reiniciamos el largo para la nueva palabra
            }
        }      
        //Tomamos el codigo resultante y lo mostramos
        return codigo.toUpperCase();
    
    }
    
    public String largo(String num) {
        int largo = num.length();
        
        switch (largo) {
            case 1:
                return "00";
            
            case 2:
                return "0";
                
            default:
                return "";
        }
    }
}
