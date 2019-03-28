/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Estudiante
 */
public class Ficha {
    
    private Pinta caraIzquierda;
    private Pinta caraDerecha;

    public Ficha(Pinta cara1, Pinta cara2) {
        this.caraIzquierda = cara1;
        this.caraDerecha = cara2;
    }

    public Pinta getCaraIzquierda() {
        return caraIzquierda;
    }

    public Pinta getCaraDerecha() {
        return caraDerecha;
    }
    
    
    
    
    
}
