public class testjugador {
    public static void main(String[] args) {
        Atacante atacante = new Atacante(5, 3, "Juan", 10, 12345, 2);
        Defensor defensor = new Defensor(4, 7, "Pedro", 5, 67890, 1);
        Portero portero = new Portero(8, "Luis", 1, 11223, 0);
        
        System.out.println("Valoración atacante: " + atacante.valoracionJugador());
        System.out.println("Valoración defensor: " + defensor.valoracionJugador());
        System.out.println("Valoración portero: " + portero.valoracionJugador());
    }
}

abstract class Jugador {
    public String nombre;
    public int numDorsal;
    public int ruc;
    public int numGoles;
    public int valorJugador;
    public int valorGoles;

    public Jugador(String nombre, int numDorsal, int ruc, int numGoles) {
        this.nombre = nombre;
        this.numDorsal = numDorsal;
        this.ruc = ruc;
        this.numGoles = numGoles;
    }
    
    public int valoracionGoles() {
        this.valorGoles = this.numGoles * 30;
        return this.valorGoles;
    }
    
    public abstract int valoracionJugador();
}

class Atacante extends Jugador {
    public int pases;
    public int recuperadas;

    public Atacante(int pases, int recuperadas, String nombre, int numDorsal, int ruc, int numGoles) {
        super(nombre, numDorsal, ruc, numGoles);
        this.pases = pases;
        this.recuperadas = recuperadas;
    }

    @Override
    public int valoracionGoles() {
        this.valorGoles = super.valoracionGoles() + (this.pases * 2) + (this.recuperadas * 3);
        return this.valorGoles;
    }

    @Override
    public int valoracionJugador() {
        this.valorJugador = this.valoracionGoles();
        return this.valorJugador;
    }
}

class Defensor extends Jugador {
    public int pases;
    public int recuperadas;

    public Defensor(int pases, int recuperadas, String nombre, int numDorsal, int ruc, int numGoles) {
        super(nombre, numDorsal, ruc, numGoles);
        this.pases = pases;
        this.recuperadas = recuperadas;
    }

    @Override
    public int valoracionGoles() {
        this.valorGoles = super.valoracionGoles() + (this.pases) + (this.recuperadas * 4);
        return this.valorGoles;
    }

    @Override
    public int valoracionJugador() {
        this.valorJugador = this.valoracionGoles();
        return this.valorJugador;
    }
}

class Portero extends Jugador {
    public int atajadas;

    public Portero(int atajadas, String nombre, int numDorsal, int ruc, int numGoles) {
        super(nombre, numDorsal, ruc, numGoles);
        this.atajadas = atajadas;
    }

    @Override
    public int valoracionGoles() {
        this.valorGoles = super.valoracionGoles() + (this.atajadas * 5);
        return this.valorGoles;
    }

    @Override
    public int valoracionJugador() {
        this.valorJugador = this.valoracionGoles();
        return this.valorJugador;
    }
}
