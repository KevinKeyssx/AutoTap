package Extra;

import Interfaz.Seleccion;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author keiss
 */
public class PDF {
    
     public void generarPDF (String rutag, String rutaf, String servicios[][], String vehiculo[], String[] para, String usuario, String total, String cotizacion, String fecha) throws DocumentException{           
        try {
            PdfPTable table = new PdfPTable(5);
            PdfPTable tabla = new PdfPTable(3);
            //Columnas por defecto
            table.addCell(carga("Código",           2, 1, 10));
            table.addCell(carga("Descripción",      2, 1, 10));
            table.addCell(carga("Cantidad",         2, 1, 10));
            table.addCell(carga("Precio Unitario",  2, 1, 10));
            table.addCell(carga("SubTotal",         2, 1, 10));
            //Cargar los servicios ingresdos
            for (String[] servicio : servicios) {
                //Recorre las columnas
                for (String servicio1 : servicio) {
                    table.addCell(carga(servicio1, 2, 1, 10));
                }
            }
            //Columnas por defecto del vehiculo
            tabla.addCell(carga("                                Marca",       2, 1, 10));
            tabla.addCell(carga("                                Modelo",      2, 1, 10));
            tabla.addCell(carga("                                Año",         2, 1, 10));
            //Cargar el vehiculo ingresado
            for (String vehiculo1 : vehiculo) {
                tabla.addCell(carga(vehiculo1, 2, 1, 10));
            }
            
            // Cree un objecto Cell y cambie su propiedad span
            PdfPCell celdaFinal = new PdfPCell(carga("Total:", 2, 1, 10));
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(4);
            table.addCell(celdaFinal);
            //Pequeña validación de diseño visual para que no haga un salto de linea cuando la venta sea mayor a 9 caracteres
            String es = "$           ";
            if (total.length() >= 9) 
                es = "$        ";
            //Cargamos el valor final
            table.addCell(carga(es+total, 2, 1, 10));
            //Asigna por defecto el tamaño de todas las columnas
            table.setWidthPercentage(100f);
            tabla.setWidthPercentage(100f);
            // ASIGNAS LAS MEDIDAS A LA TABLA (ANCHO)
            float[] medidaCeldas = {0.55f, 3.20f, 0.50f, 0.60f, 0.80f};
            table.setWidths(medidaCeldas);
            //varaiable donde se guarda nuestro pdf
            FileOutputStream archivo = new FileOutputStream(rutag);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            //Cargamos la imagen que tendra el pdf
            Image imagen = Image.getInstance(rutaf);
            imagen.scaleAbsolute(100, 50);//Tamaño que tendra por defecto
            imagen.setAlignment(Element.ALIGN_CENTER);//La alineamos al centro
            //Abrimos el PDF
            doc.open();
            //Agregamos la imagen
            doc.add(imagen);
            doc.add(tituloPDF(12));//Agremamos el titulo
            doc.add(carga(cotizacion+"                                                                                                  "+fecha, 5, 1, 9));
            //Agregamos la primera parte del PDF PARA y DE
            doc.add(carga(dePara(1, para, usuario), 1, 2, 10));//Para
            doc.add(carga(dePara(2, para, usuario), 1, 1, 9));//Vector de info del cotizante
            doc.add(carga(dePara(3, para, usuario), 1, 2, 10));//De
            doc.add(carga(dePara(4, para, usuario), 1, 1, 9));//Info del cotizador
            //Agregamos una linea
            doc.add(carga(lineaPDF(), 2, 1, 10));
            //Texto que indica lo que se cagara
            
            doc.add(carga("Vehículo:\n\n", 1, 1, 8));
            doc.add(tabla);
            doc.add(carga("De acuerdo a lo solicitado, envíamos la siguiente cotización\n\n", 1, 1, 8));
            doc.add(table);//Agregamos los servicios gracias a la tabla cargada
            
            //Agregamos las ultimas partes del footer
            doc.add(carga("\n", 1, 1, 8));//Agregamos un salto de linea
            doc.add(footerPDF(9));
            doc.add(carga(detalle(1), 1, 1, 8));
            doc.add(carga(detalle(2), 1, 2, 8));
            doc.add(carga(detalle(3), 8, 1, 10));
            doc.add(carga(detalle(4), 2, 1, 10));
            //Cerramos el PDF
            doc.close();
            //Abrimos el PDF
            abrirPDF(rutag);
        } catch (FileNotFoundException | DocumentException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al tratar de crear el formato PDF:\n"+e+"\nIntenta cerrando el archivo PDF abierto","Error!",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Obtener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private Paragraph tituloPDF(int size){
        String texto = "Tapicería para la Industria Automotriz\n\n"+largoPDF();
        return carga(texto, 2, 1, size);
    }
    
    private Paragraph footerPDF(int size){
        String texto = "CONDICIONES COMERCIALES:";
        return carga (texto, 1,4,size);
    }

    private String dePara(int num, String[] para, String empleado){
        emp = empleado;
        switch (num) {
            case 1:
                    return "Para:";
            case 2:
                    return "                                                                Rut               :        "+para[0]+"\n"+
                           "                                                                Empresa        :        "+para[1]+"\n"+
                           "                                                                Contacto       :        "+para[2]+"\n"+
                           "                                                                Teléfono       :        "+para[3]+"\n"+
                           "                                                                Mail              :        "+para[4]+"\n";
                case 3:
                return "De:";
                case 4:
                    return "                                                               "+empleado+"\n"+
                           "                                                                Teléfono: 22 2473183\n"+ 
                           "                                                                info@autotap.cl";
        }
        
        return "";
    }
    
    static String emp="";
    
    private String detalle(int num){
        switch (num) {
            case 1:
                return "                  -            PRECIOS INCLUYEN EL IMPUESTO AL VALOR AGREGADO (IVA)\n"+
               "                   -            CONDICIONES DE PAGO: EFECTIVO,  TARJETA DEBITO - BANCARIA, A CONVENIR\n"+
               "                   -            EMITIR ORDEN DE COMPRA Y PAGOS A: \n";

                case 2:
                    return"                               MARIA ISABEL CAMPOS GONZALEZ RUT: 6.041.239-1\n\n";
                case 3:
                    return emp+"\n9-1906523";
                 case 4:
                    return largoPDF()+"\nAv. Portugal 1136 - Santiago   Teléfono Fax: 2473183\n"+"www.autotap.cl";
        }
        return "";
    }
    
    private Paragraph carga (String texto, int aling, int font, int size){
        Paragraph p = new Paragraph();
        Chunk c= new Chunk();
        switch (aling) {
            case 1:
                p.setAlignment(Element.ALIGN_LEFT);
            break;
            case 2:
                p.setAlignment(Element.ALIGN_CENTER);
            break;
            case 3:
                p.setAlignment(Element.ALIGN_MIDDLE);
            break;
            case 4:
                p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            break;
            default:
                p.setAlignment(Element.ALIGN_RIGHT);
            break;
            
        }
        
        c.append(texto);
        
        switch (font) {
            case 1:
                c.setFont(new Font(Font.FontFamily.TIMES_ROMAN, size, Font.NORMAL));
            break;
            
            case 2:
                c.setFont(new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLD));
            break;
            
            case 3:
                c.setFont(new Font(Font.FontFamily.TIMES_ROMAN, size, Font.BOLDITALIC));//Cursiva
            break;
            
            case 4:
                c.setFont(new Font(Font.FontFamily.TIMES_ROMAN, size, Font.UNDERLINE));//Cursiva
            break;
        }

        p.add(c);
        return p;
    }
    
    public String largoPDF(){
        return "_______________________________________________________________________________________";
    } 

    public String lineaPDF(){
        return "________________________________________________________________________________________________________";
    } 
    /*Metodo que abre nuestro pdf*/
    private void abrirPDF(String ruta) {
        ProcessBuilder p = new ProcessBuilder();
        p.command("cmd.exe","/c", ruta);
        try {
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
