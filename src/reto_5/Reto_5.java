package reto_5;

import Controlador.Controlador;
import Modelo.Producto;
import Vista.Reto5;

public class Reto_5 {
    public static void main(String[] args) {
        Producto modelo = new Producto();
        Reto5 vista = new Reto5();
        
        Controlador ctrl = new Controlador(modelo,vista);
        ctrl.iniciar();
    }
    
}
