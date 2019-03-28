package DominoJuego;

import DominoJuego.Ficha;
import DominoJuego.Jugador;
import DominoJuego.Bot;
import java.util.Scanner;

public class Programa {
    
    public static void main(String[] args) {

        Scanner Leer = new Scanner(System.in);
        boolean FichaAceptada, pasoJugador, pasoBOT;
        char opcion;
        int numRonda = 1; 

       
        
       System.out.println("\n"+""+"\n");
       System.out.println("########:::'#######::'##::::'##:'####:'##::: ##::'#######:::::'####:\n" +
                            "##.... ##:'##.... ##: ###::'###:. ##:: ###:: ##:'##.... ##:::: ####:\n" +
                            "##:::: ##: ##:::: ##: ####'####:: ##:: ####: ##: ##:::: ##:::: ####:\n" +
                            "##:::: ##: ##:::: ##: ## ### ##:: ##:: ## ## ##: ##:::: ##::::: ##::\n" +
                            "##:::: ##: ##:::: ##: ##. #: ##:: ##:: ##. ####: ##:::: ##:::::..:::\n" +
                            "##:::: ##: ##:::: ##: ##:.:: ##:: ##:: ##:. ###: ##:::: ##::::'####:\n" +
                            "########::. #######:: ##:::: ##:'####: ##::. ##:. #######::::: ####:\n" +
                            "........::::.......:::..:::::..::....::..::::..:::.......::::::....::");
        Scanner LeerNom = new Scanner (System.in);
        System.out.println("Digite su nombre:");
        String userName = LeerNom.nextLine();
        Ficha ficha, fichaEXT;
        Jugador j1 = new Jugador(userName);
        Bot bot = new Bot("BOT", j1.getFichas());
       
       System.out.println("BOT <---- (vs)---->"+userName);
        bot.verfichas();
        System.out.println("¡El BOT Comienza!...");
        bot.iniciojuegobot(bot.getDobleFicha());
        System.out.println("¡ EL BOT HA PUESTO SU FICHA!");
        bot.verfichas();

        do {
            FichaAceptada =false; 
            pasoJugador =false;
            pasoBOT = false;
            System.out.println("MESA ACTUAL");
            System.out.println("RONDA NUMERO: "+numRonda);
            bot.mostrarmesa();
            System.out.println("Es tu turno:"+userName);
            j1.verFichas();
            System.out.print(userName+ " ¿DESEAS PASAR EL TURNO? [S/N]: ");
            opcion = Leer.next().charAt(0); 
            
            if (opcion == 'N' || opcion == 'n') {
                do {
                    System.out.print(userName+ " Elige la posicion de la ficha, recuerda desde 1 en adelante : ");
                    int pos = Leer.nextInt();
                    
                    
                    if (pos > j1.getNumFichas()) {
                        FichaAceptada = false;
                    } else {
                        ficha = j1.getFicha(pos - 1);
                        fichaEXT = bot.getExtremoIzq();
                        if (ficha.getNum1() == fichaEXT.getNum1()) {
                            bot.ponerFichaIzq(new Ficha(ficha.getNum2(), ficha.getNum1()));
                            
                            j1.quitarFicha(ficha);
                            FichaAceptada = true;
                        } else if (ficha.getNum2() == fichaEXT.getNum1()) {
                            
                            bot.ponerFichaIzq(new Ficha(ficha));
                            j1.quitarFicha(ficha);
                            FichaAceptada = true;
                        }

                        if (!FichaAceptada) {
                           
                            fichaEXT = bot.getExtremoDer();
                            if (ficha.getNum1() == fichaEXT.getNum2()) { 
                                bot.ponerFichaDer(new Ficha(ficha));
                                
                                j1.quitarFicha(ficha);
                                FichaAceptada = true;
                            } else if (ficha.getNum2() == fichaEXT.getNum2()) {
                                
                                bot.ponerFichaDer(new Ficha(ficha.getNum2(), ficha.getNum1()));
                                j1.quitarFicha(ficha);
                                FichaAceptada = true;
                            }
                        }

                        if (!FichaAceptada) {
                            System.out.println("Ficha Invalida por favor intenta nuevamente!");
                        }
                    }
                    if(pos > j1.getNumFichas() ){ System.out.println(userName+" Digitaste un numero incorrecto, intenta nuevamente..."); }
                } while (!FichaAceptada);
                System.out.println("MESA ACTUAL");
                bot.mostrarmesa();
            } else {
                pasoJugador = true;
            }

            if(j1.getNumFichas() != 0) {
                System.out.println("Turno del BOT ...");
                pasoBOT = bot.jugar();
                if (!pasoBOT) {
                    System.out.println("El BOT Pasó el Turno!...");
                    if (pasoJugador) {
                        break;
                    }
                } else {
                    System.out.println("El BOT ha jugado! :)");
                    bot.verfichas();
                }
            }
            System.out.println("\n-----------------------------------------------------------------------------\n");
            numRonda++;
        } while (j1.getNumFichas() > 0 && !bot.sinFichas()); 
        
        System.out.println("\n\n");
        System.out.println(".:JUEGO FINALIZADO!:.");
        int PintasJ1 = j1.contarpintas();
        int pintasBot = bot.sumapintas();
        if(PintasJ1 <= pintasBot) {
            
            System.out.println(userName+" HAS GANADO! BUENA PARTIDA");
        } else{
            System.out.println("EL BOT TE HA GANADO, jUEGA NUEVAMENTE :)!");
        }
    }
}