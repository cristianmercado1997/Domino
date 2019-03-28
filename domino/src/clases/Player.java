/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * @author Estudiante
 */
public class Player {
    private ArrayList<Ficha>mano;
    private String id;

    public Player(String nombre_jugador) {
        this.mano = new ArrayList<Ficha>();
        this.id = nombre_jugador;
    }

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public String getId() {
        return id;
    }
    
    public boolean mirarMano(Pinta cara_izquierda, Pinta cara_derecha){
        int contador = 0;
        for(Ficha ficha : this.getMano()){
            if(cara_izquierda == ficha.getCaraIzquierda() || cara_izquierda == ficha.getCaraDerecha()){
                contador+=1;
            }
            if(cara_derecha == ficha.getCaraIzquierda() || cara_derecha == ficha.getCaraDerecha()){
                contador+=1;
            }
        }
        if(contador != 0){
            return true;
        }else{
           return false; 
        }
        
    }
    
    public void imprimirMano(){
        int i = 1;
        for(Ficha ficha: this.mano){
            System.out.print(i+".["+ficha.getCaraIzquierda()+", "+ficha.getCaraDerecha()+"]");
            i++;
        }
        System.out.println("");
    }
    
    public boolean removerFicha(Ficha eliminada){
        boolean retorno = false;
        for(Ficha ficha : this.getMano()){
            if(this.comparar(ficha, eliminada)){
                eliminada = ficha;
                retorno = true;
            }
        }
        this.getMano().remove(eliminada);
        return retorno;
    }
    
    public boolean comparar(Ficha ficha1, Ficha ficha2){
        
        if((ficha1.getCaraIzquierda() == ficha2.getCaraIzquierda()) && (ficha1.getCaraDerecha() == ficha2.getCaraDerecha())){
            return true;
        }else{
            return false;
        }
        
    }
    
    public boolean poner(Deque<Ficha> juego, Ficha ficha, String posicion){
        boolean retorno = false;
        if(posicion == "derecha"){
            if(juego.getLast().getCaraDerecha() == ficha.getCaraIzquierda()){
                juego.addLast(ficha);
                this.removerFicha(ficha);
                retorno = true;
            }else if(juego.getLast().getCaraDerecha() == ficha.getCaraDerecha()){
                juego.addLast(new Ficha(ficha.getCaraDerecha(),ficha.getCaraIzquierda()));
                this.removerFicha(ficha);
                retorno = true;
            }else{
                System.out.println("Esta ficha no puede puede ir en esa posicion");
                retorno = false;
            }
        }else if(posicion == "izquierda"){
            if(juego.getFirst().getCaraIzquierda() == ficha.getCaraIzquierda()){
                juego.addFirst(new Ficha(ficha.getCaraDerecha(),ficha.getCaraIzquierda()));
                this.removerFicha(ficha);
                retorno = true;
            }else if(juego.getFirst().getCaraIzquierda() == ficha.getCaraDerecha()){
                juego.addFirst(ficha);
                this.removerFicha(ficha);
                retorno = true;
            }else{
                System.out.println("Esta ficha no puede puede ir en esa posicion");
                retorno = false;
            }
        }
        return retorno;
    }
    
}