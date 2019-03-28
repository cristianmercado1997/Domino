
package DominoJuego;

public class Ficha {
    private int num1;
    private int num2;

    public Ficha(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    
    //Constructor de Copia
    public Ficha(Ficha fic) {
        this.num1 = fic.num1;
        this.num2 = fic.num2;
    }

    public int getNum1() {
        return num1;
    } 
    
    public int getNum2() {
        return num2;}

     public void setNum1(int num1) {
        this.num1 = num1;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.num1;
        hash = 17 * hash + this.num2;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        if (this.num1 != other.num1) {
            return false;
        }
        return this.num2 == other.num2;
    }
}
