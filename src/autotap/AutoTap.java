package autotap;

import Interfaz.Inicio;
import carga.PantallaCargandoMain;

/**
 * @author keiss
 */
public class AutoTap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PantallaCargandoMain();
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        
    }
    
}