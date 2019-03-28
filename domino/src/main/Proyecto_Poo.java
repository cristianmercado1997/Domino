/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import clases.Fichas;
import clases.Ficha;
import clases.Player;
import clases.Pinta;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Estudiante
 */
public class Proyecto_Poo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Fichas fichas = new Fichas();
        Player player1 = new Player("cachon1");
        Player player2 = new Player("cachon2");
        Player player3 = new Player("cachon3");
        Player player4 = new Player("cachon4");
        ArrayList <Player> jugadores = new ArrayList<Player>();
        fichas.repartir(player1, player2, player3, player4);
        Deque <Ficha> mesa = new LinkedList<Ficha>();
        Pinta cara_izquierda;
        int contador_pasa_turno = 0;
        Pinta cara_derecha;
        Player ganador = null;
        
        mesa = fichas.comenzar(jugadores, player1, player2, player3, player4);
        
        while(true){
            for(Player jugador_actual : jugadores){
                if(ganador != null){
                    break;
                }
                cara_izquierda = mesa.getFirst().getCaraIzquierda();
                cara_derecha = mesa.getLast().getCaraDerecha();
                int num_ficha = 0;
                int direccion = 0;
                System.out.println("turno del jugador " + jugador_actual.getId());
                System.out.println("Asi va el juego");
                int i=1;
                for(Ficha ficha: mesa){
                    System.out.print("["+ficha.getCaraIzquierda()+", "+ficha.getCaraDerecha()+"]");
                    i++;
                }
        System.out.println("");
                System.out.println("tu mano:");
                jugador_actual.imprimirMano();
                if(jugador_actual.mirarMano(cara_izquierda, cara_derecha)){
                    contador_pasa_turno = 0;
                    Scanner entrada = new Scanner(System.in);
                    while(true){
                        System.out.println("escoja el numero de la ficha a poner");
                        try{
                            num_ficha = entrada.nextInt();
                            if(num_ficha > jugador_actual.getMano().size()){
                                System.out.println("Este numero es mayor al numero de fichas que tienes");
                                continue;
                            }
                        }catch(Exception e){
                            System.out.println("Por favor introduzca un numero");
                            entrada.nextLine();
                            continue;
                        }
                        System.out.println("presione 1 para poner la ficha a la izquierda y 2 para ponerlo a la derecha");
                        //if(direccion == (int) direccion){
                            try{
                                direccion = entrada.nextInt();
                            if(direccion == 1){
                                if(jugador_actual.poner(mesa, jugador_actual.getMano().get(num_ficha-1), "izquierda")){
                                    if(jugador_actual.getMano().isEmpty()){
                                        ganador = jugador_actual;
                                    }
                                    break;
                                }
                            }else if(direccion == 2){
                                if(jugador_actual.poner(mesa, jugador_actual.getMano().get(num_ficha-1), "derecha")){
                                    if(jugador_actual.getMano().isEmpty()){
                                        ganador = jugador_actual;
                                    }
                                    break;
                                }
                            }else{
                                System.out.println("solo se aceptan los numeros 1 o 2 por favor vuelva a hacerlo");
                            }
                        }catch(Exception e){
                            
                            System.out.println("Por favor introduzca un numero");
                            entrada.nextLine();
                        }
                    }
                }else{
                    System.out.println("el jugador " + jugador_actual.getId() + " pasa el turno");
                    contador_pasa_turno++;
                } 
                System.out.println("-----------------------------------------------------------------------------------------");
            }
            /*for(Ficha ficha : juego){
                System.out.println(ficha.getCara1());
            }*/
            if(contador_pasa_turno >= 4){
                System.out.println("Ambos jugadores pasaron turno por eso se declara que el juego está cerrado");
                System.out.println("mano final del jugador: " + player1.getId());
                player1.getMano();
                System.out.println("mano final del jugador: " + player2.getId());
                player2.getMano();
                 System.out.println("mano final del jugador: " + player3.getId());
                player3.getMano();
                 System.out.println("mano final del jugador: " + player4.getId());
                player4.getMano();
                System.out.println("Juego finalizado");
                break;
            }
        }
    }
    
}
