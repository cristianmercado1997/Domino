
package DominoJuego;

import java.util.LinkedList;
import java.util.Random;

public class Bot {

    private final String botNom;
    private final LinkedList<Ficha> fichas; 
    private final LinkedList<Ficha> mesa; 

    public Bot(String Nom, LinkedList<Ficha> fichasJ) {
        this.botNom = Nom;
        this.fichas = new LinkedList<>();
        this.mesa = new LinkedList<>();
        darfichas(fichasJ);
    }

    private void darfichas(LinkedList<Ficha> fichasJugador) {
        Random random = new Random();
        int num1, num2;
        for (int i = 0; i < 7; i++) {
            Ficha ficha, fichaVol;
            do {
                num1 = random.nextInt(7);
                num2 = random.nextInt(7);
                ficha = new Ficha(num1, num2);
                fichaVol = new Ficha(num2, num1);
                
            } while (fichas.contains(ficha) || fichas.contains(fichaVol) || fichasJugador.contains(ficha) || fichasJugador.contains(fichaVol));
            
            fichas.add(ficha);
        }
    }

    public void verfichas() {
        System.out.println("Fichas de la " + botNom + "\n\n");
        if(!fichas.isEmpty()) {
            for (Ficha f : fichas) {
                System.out.print("[" + f.getNum1() + " - " + f.getNum2() + "]");
            }
            System.out.println("\n\n");
        } else{
            System.out.println("No Hay Fichas!");
        }
    }

    public boolean sinFichas() {
        return this.fichas.isEmpty();
    }

    public void iniciojuegobot(Ficha ficha) {
        ponerFichaIzq(ficha);
        this.fichas.remove(ficha);
    }

        public Ficha getDobleFicha() {
        int num1, num2;
        
        for (Ficha f : fichas) {
            num1 = f.getNum1();
            num2 = f.getNum2();
            if (num1 == num2 ) {
                return f;
            }
        }
        for (Ficha x : fichas) {
            num1 = x.getNum1();
            num2 = x.getNum2();
            if (num1 != num2 ) {
                return x;
            }
        }
        return null;
        }
        
      
        
    public boolean jugar() {
        int tope = 0;
        boolean fichaValida;
        Random random = new Random();
        int rdPos;
        Ficha ficha, fichaAyu;
        do {
            tope++;
            fichaValida = false;
            rdPos = random.nextInt(this.fichas.size());
             ficha = this.fichas.get(rdPos);
            fichaAyu = getExtremoIzq();
            if (ficha.getNum1() == fichaAyu.getNum1()) {
                ponerFichaIzq(new Ficha(ficha.getNum2(), ficha.getNum1()));
                this.fichas.remove(ficha);
                fichaValida = true;
            } else if (ficha.getNum2() == fichaAyu.getNum1()) {
                ponerFichaIzq(new Ficha(ficha));
                this.fichas.remove(ficha);
                fichaValida = true;
            }

            if (!fichaValida) {
                fichaAyu = getExtremoDer();
                if (ficha.getNum1() == fichaAyu.getNum2()) {
                    ponerFichaDer(new Ficha(ficha));
                    this.fichas.remove(ficha);
                    fichaValida = true;
                } else if (ficha.getNum2() == fichaAyu.getNum2()) {
                    ponerFichaDer(new Ficha(ficha.getNum2(), ficha.getNum1()));
                    this.fichas.remove(ficha);
                    fichaValida = true;
                }
            }
            
            if(tope > this.fichas.size() && !fichaValida) {
                
                return false;
            }
        } while (!fichaValida);
        
        return true; 
    }
    
    public int sumapintas() {
        int contador = 0;
        for(Ficha f : fichas) {
            contador += f.getNum1() + f.getNum2();
        }
        return contador;
    }

    public void ponerFichaIzq(Ficha ficha) {
        this.mesa.addFirst(ficha);
    }
    
    public void ponerFichaDer(Ficha ficha) {
        this.mesa.addLast(ficha);
    }

    public void mostrarmesa() {
        for (Ficha f : mesa) {
            System.out.print("[" + f.getNum1() + " - " + f.getNum2() + "]");
        }
        System.out.println("\n\n");
    }

    public Ficha getExtremoIzq() {
        return this.mesa.getFirst();
    }

    public Ficha getExtremoDer() {
        return this.mesa.getLast();
    }
}