package carga;

import javax.swing.ImageIcon;

public class PantallaCargandoMain {

    PantallaCargando screen;

    public PantallaCargandoMain() {
        inicioPantalla();
        screen.velocidadDeCarga();
    }

    private void inicioPantalla() {
        //ImageIcon myImage = new ImageIcon("C:\\Users\\keiss\\Documents\\NetBeans\\Carga\\src\\Imagen\\Icono.png");
        //C:\Users\keiss\Desktop\AutoTap
        ImageIcon myImage = new ImageIcon("C:\\Users\\Autotap Asus\\Desktop\\AutoTap\\Icono.png");
        screen = new PantallaCargando(myImage);
        screen.setLocationRelativeTo(null);
        screen.setProgresoMax(100);
        screen.setVisible(true);
    }

    public static void main(String[] args){
        new PantallaCargandoMain();
    }
}