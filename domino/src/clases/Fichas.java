/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Estudiante
 */
public class Fichas {
    private ArrayList<Ficha> domino;

    public Fichas() {
        this.domino = new ArrayList<Ficha>();
        this.llenarFichas();
    }
    
    public void llenarFichas(){
        int contador;
        this.domino.add(new Ficha(Pinta.CERO, Pinta.CERO));
        for(Pinta cara1 : Pinta.values()){
            for(Pinta cara2 : Pinta.values()){
                Ficha mientras1 = new Ficha(cara1, cara2);
                Ficha mientras2 = new Ficha(cara2, cara1);
                contador = 0;
                for(Ficha ficha : this.domino){
                    if(this.comparar(mientras1, ficha) || this.comparar(mientras2, ficha)){
                        contador+=1;
                    }
                }
                if(contador == 0){
                    this.domino.add(mientras1);
                }
            }
            
        }
    }
    
    public boolean comparar(Ficha ficha1, Ficha ficha2){
        
        if((ficha1.getCaraIzquierda() == ficha2.getCaraIzquierda()) && (ficha1.getCaraDerecha() == ficha2.getCaraDerecha())){
            //System.out.println("true");
            return true;
        }else{
            //System.out.println("false");
            return false;
        }
        
    }
    
    public void repartir(Player jugador_1, Player jugador_2, Player jugador_3, Player jugador_4){
        int i = 0;
        Collections.shuffle(this.domino);
        for(Ficha ficha : this.domino){
            if(i<7){
                jugador_1.getMano().add(ficha);
            }else if(i<14){
                jugador_2.getMano().add(ficha);
            }else if(i<21){
                jugador_3.getMano().add(ficha);
            }else{
                jugador_4.getMano().add(ficha);
            }
            i++;
        }
    }
    
    public Deque <Ficha> comenzar(ArrayList<Player> jugadores, Player jugador1, Player jugador2, Player jugador3, Player jugador4){
        Deque <Ficha> juego = new LinkedList<Ficha>();
        if(jugador1.removerFicha(new Ficha(Pinta.SEIS, Pinta.SEIS))){
            juego.add(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugadores.add(jugador2);
            jugadores.add(jugador3);
            jugadores.add(jugador4);
            jugadores.add(jugador1);
        }else if(jugador2.removerFicha(new Ficha(Pinta.SEIS, Pinta.SEIS))){
            juego.add(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugadores.add(jugador1);
            jugadores.add(jugador3);
            jugadores.add(jugador4);
            jugadores.add(jugador2);
        }else if(jugador3.removerFicha(new Ficha(Pinta.SEIS, Pinta.SEIS))){
            juego.add(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugadores.add(jugador1);
            jugadores.add(jugador2);
            jugadores.add(jugador4);
            jugadores.add(jugador3);
        }else{
            juego.add(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugador4.removerFicha(new Ficha(Pinta.SEIS, Pinta.SEIS));
            jugadores.add(jugador1);
            jugadores.add(jugador2);
            jugadores.add(jugador3);
            jugadores.add(jugador4);
        }
        
        return juego;
    }
    
    
}
