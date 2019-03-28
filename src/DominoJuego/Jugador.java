

package DominoJuego;

import java.util.LinkedList;
import java.util.Random;

public class Jugador {

    private final String jugador;
    private final LinkedList<Ficha> fichas;

    public Jugador(String jugador) {
        this.jugador = jugador;
        this.fichas = new LinkedList<>();
        darfichas();
    }

    private void darfichas() {
        Random random = new Random();
        int num1, num2;
        for (int i = 0; i < 7; i++) {
            Ficha ficha, fichaVol;
            do {
                num1 = random.nextInt(7);
                num2 = random.nextInt(7);
                ficha = new Ficha(num1, num2);
                fichaVol = new Ficha(num2, num1); 
            } while (fichas.contains(ficha) || fichas.contains(fichaVol));
            fichas.add(ficha);
        }
    }

    public void verFichas() {
        System.out.println("Fichas de  " + jugador + "\n\n");
        if (!fichas.isEmpty()) {
            int a=1;
            for (Ficha f : fichas) {
                System.out.print(+a+"->(|" + f.getNum1() + " - " + f.getNum2() + "|)\n");
           a++; }
            System.out.println("\n");
        } else {
            System.out.println("El " + jugador + " Se quedo sin fichas!");
        }
    }
    

    public int contarpintas() {
        int contador = 0;
        for (Ficha f : fichas) {
            contador += f.getNum1() + f.getNum2();
        }
        return contador;
    }
 
    public Ficha getFicha(int posic) {
        return this.fichas.get(posic);
    }

    public void quitarFicha(Ficha ficha) {
        this.fichas.remove(ficha);
    }

    
    
       public LinkedList<Ficha> getFichas() {
        return new LinkedList<>(this.fichas); 
    }

    public int getNumFichas() {
        return this.fichas.size();
    }
}