import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Problema_1_Ejecutor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String atributos[] = {"Vida: ", "Mana: ", "Magia: ", "Fuerza: ", "Resistencia: ", "Destreza: ", "Velocidad: ", "Exp: ", "Nivel: ", "Puntos de nivel: "};
        ArrayList<Personaje> personajes = new ArrayList<>();
        JuegoRoles obj = new JuegoRoles(atributos);

        int n = 0;
        do {
            n = obj.menuPrincipal();
            switch (n) {
                case 1:
                    int tipoPersonaje;
                    do {
                        tipoPersonaje = obj.menuPersonaje();
                        switch (tipoPersonaje) {
                            case 1:
                                Guerrero guerrero = new Guerrero("Guerrero", 1000, 20, 20, 200, 200, 0, 40, 0, 1, 10);
                                personajes.add(guerrero);
                                break;
                            case 2:
                                Mago mago = new Mago("Mago", 1000, 20, 20, 200, 200, 0, 40, 0, 1, 10);
                                personajes.add(mago);
                                break;
                            case 3:
                                Arquero arquero = new Arquero("Arquero", 1000, 20, 20, 200, 200, 0, 40, 0, 1, 10);
                                personajes.add(arquero);
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, elige una opción del 1 al 3.");
                                break;
                        }
                    } while (tipoPersonaje != 4);
                    break;
                case 2:
                    System.out.println("2. Eliminar Personaje");
                    obj.listaPersonajes(personajes);
                    obj.EliminarPersonaje(personajes);
                    break;
                case 3:
                    System.out.println("3. Mejorar Personaje");
                    obj.listaPersonajes(personajes);
                    obj.mejorarPersonaje(personajes);
                    break;
                case 4:
                    System.out.println("4. Jugar");
                    if (personajes.isEmpty()) {
                        System.out.println("No hay personajes disponibles para jugar.");
                    } else {
                        Enemigo enemigo = obj.generarEnemigoAleatorio();
                        System.out.println("Enemigo generado:");
                        enemigo.EstadisticasPersonaje();
                        System.out.println("Seleccione el personaje con el que desea luchar:");
                        obj.listaPersonajes(personajes);
                        int indicePersonaje = scanner.nextInt();
                        if (indicePersonaje >= 0 && indicePersonaje < personajes.size()) {
                            Personaje personajeJugador = personajes.get(indicePersonaje);
                            boolean gano = obj.luchar(personajeJugador, enemigo);
                            if (gano) {
                                personajeJugador.exp += 50;
                                System.out.println("¡Ganaste! Tu personaje ha ganado 50 de experiencia.");
                                if (personajeJugador.exp >= 100) {
                                    personajeJugador.nivel++;
                                    personajeJugador.puntosNivel++;
                                    personajeJugador.exp -= 100;
                                    System.out.println("¡Felicidades! Tu personaje ha subido de nivel.");
                                }
                            } else {
                                System.out.println("Has perdido. Intenta de nuevo.");
                            }
                        } else {
                            System.out.println("Número de personaje no válido.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("0. Salir");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 0 al 4.");
                    break;
            }
        } while (n != 0);
    }
}

class JuegoRoles {
    Scanner scanner;
    Random random;

    public JuegoRoles(String atributos[]) {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public int menuPrincipal() {
        System.out.println("Menú Principal:");
        System.out.println("1. Crear Personaje");
        System.out.println("2. Eliminar Personaje");
        System.out.println("3. Mejorar Personaje");
        System.out.println("4. Jugar");
        System.out.println("0. Salir");
        return scanner.nextInt();
    }

    public int menuPersonaje() {
        System.out.println("Seleccion de Personaje: ");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Arquero");
        System.out.println("4. Volver al menu principal");
        return scanner.nextInt();
    }

    public void listaPersonajes(ArrayList<Personaje> personajes) {
        for (int i = 0; i < personajes.size(); i++) {
            System.out.println(i + ": " + personajes.get(i).nombre);
        }
    }

    public void EliminarPersonaje(ArrayList<Personaje> personajes) {
        System.out.println("Ingrese el número del personaje que desea eliminar: ");
        int n = scanner.nextInt();
        if (n >= 0 && n < personajes.size()) {
            personajes.remove(n);
            System.out.println("Personaje eliminado");
        } else {
            System.out.println("Número no válido");
        }
    }

    public void mejorarPersonaje(ArrayList<Personaje> personajes) {
        System.out.println("Ingrese el número del personaje que desea mejorar: ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < personajes.size()) {
            personajes.get(indice).EstadisticasPersonaje();
            int habilidad = MenuHabilidades();
            personajes.get(indice).MejorarPersonaje(habilidad);
        } else {
            System.out.println("Número no válido");
        }
    }

    public int MenuHabilidades() {
        System.out.println("Elige el número de habilidad que deseas mejorar: ");
        return scanner.nextInt();
    }

    public Enemigo generarEnemigoAleatorio() {
        int tipo = random.nextInt(3) + 1;
        switch (tipo) {
            case 1:
                return new Enemigo("Guerrero Enemigo", 500, 10, 10, 100, 100, 0, 20, 0, 1, 5);
            case 2:
                return new Enemigo("Mago Enemigo", 500, 10, 10, 100, 100, 0, 20, 0, 1, 5);
            case 3:
                return new Enemigo("Arquero Enemigo", 500, 10, 10, 100, 100, 0, 20, 0, 1, 5);
            default:
                return new Enemigo("Guerrero Enemigo", 500, 10, 10, 100, 100, 0, 20, 0, 1, 5);
        }
    }

    public boolean luchar(Personaje jugador, Enemigo enemigo) {
        double poderJugador = jugador.fuerza + jugador.magia + jugador.destreza + jugador.velocidad;
        double poderEnemigo = enemigo.fuerza + enemigo.magia + enemigo.destreza + enemigo.velocidad;
        return poderJugador > poderEnemigo;
    }
}

abstract class Personaje {
    public String nombre;
    public double vida;
    public double mana;
    public double magia;
    public double fuerza;
    public double resistencia;
    public double destreza;
    public double velocidad;
    public double exp;
    public int nivel;
    public int puntosNivel;

    public Personaje(String nombre, double vida, double mana, double magia, double fuerza, double resistencia, double destreza, double velocidad, double exp, int nivel, int puntosNivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.mana = mana;
        this.magia = magia;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.velocidad = velocidad;
        this.exp = exp;
        this.nivel = nivel;
        this.puntosNivel = puntosNivel;
    }

    public abstract void EstadisticasPersonaje();
    public abstract void MejorarPersonaje(int habilidad);
}

class Guerrero extends Personaje {
    private double multiplicadorFuerza;
    private double multiplicadorVida;
    private double multiplicadorResistencia;

    public Guerrero(String nombre, double vida, double mana, double magia, double fuerza, double resistencia, double destreza, double velocidad, double exp, int nivel, int puntosNivel) {
        super(nombre, vida, mana, magia, fuerza, resistencia, destreza, velocidad, exp, nivel, puntosNivel);
    }

    @Override
    public void EstadisticasPersonaje() {
        System.out.println("\nNombre: " + nombre + "\n1. Vida: " + vida + "\n2. Mana: " + mana + "\n3. Magia: " + magia + "\n4. Fuerza: " + fuerza + "\n5. Resistencia: " + resistencia + "\n6. Destreza: " + destreza + "\n7. Velocidad: " + velocidad + "\n8. Exp: " + exp + "\n9. Nivel: " + nivel + "\n10. Puntos de nivel: " + puntosNivel);
    }

    @Override
    public void MejorarPersonaje(int habilidad) {
        switch (habilidad) {
            case 1:
                vida += 20 * (multiplicadorVida > 0 ? multiplicadorVida : 1);
                break;
            case 2:
                mana += 20;
                break;
            case 3:
                magia += 20;
                break;
            case 4:
                fuerza += 20 * (multiplicadorFuerza > 0 ? multiplicadorFuerza : 1);
                break;
            case 5:
                resistencia += 20 * (multiplicadorResistencia > 0 ? multiplicadorResistencia : 1);
                break;
            case 6:
                destreza += 20;
                break;
            case 7:
                velocidad += 20;
                break;
            default:
                System.out.println("Habilidad no válida");
                break;
        }
    }
}

class Mago extends Personaje {
    private double multiplicadorMana;
    private double multiplicadorMagia;

    public Mago(String nombre, double vida, double mana, double magia, double fuerza, double resistencia, double destreza, double velocidad, double exp, int nivel, int puntosNivel) {
        super(nombre, vida, mana, magia, fuerza, resistencia, destreza, velocidad, exp, nivel, puntosNivel);
    }

    @Override
    public void EstadisticasPersonaje() {
        System.out.println("\nNombre: " + nombre + "\n1. Vida: " + vida + "\n2. Mana: " + mana + "\n3. Magia: " + magia + "\n4. Fuerza: " + fuerza + "\n5. Resistencia: " + resistencia + "\n6. Destreza: " + destreza + "\n7. Velocidad: " + velocidad + "\n8. Exp: " + exp + "\n9. Nivel: " + nivel + "\n10. Puntos de nivel: " + puntosNivel);
    }

    @Override
    public void MejorarPersonaje(int habilidad) {
        switch (habilidad) {
            case 1:
                vida += 20;
                break;
            case 2:
                mana += 20 * (multiplicadorMana > 0 ? multiplicadorMana : 1);
                break;
            case 3:
                magia += 20 * (multiplicadorMagia > 0 ? multiplicadorMagia : 1);
                break;
            case 4:
                fuerza += 20;
                break;
            case 5:
                resistencia += 20;
                break;
            case 6:
                destreza += 20;
                break;
            case 7:
                velocidad += 20;
                break;
            default:
                System.out.println("Habilidad no válida");
                break;
        }
    }
}

class Arquero extends Personaje {
    private double multiplicadorDestreza;
    private double multiplicadorVelocidad;

    public Arquero(String nombre, double vida, double mana, double magia, double fuerza, double resistencia, double destreza, double velocidad, double exp, int nivel, int puntosNivel) {
        super(nombre, vida, mana, magia, fuerza, resistencia, destreza, velocidad, exp, nivel, puntosNivel);
    }

    @Override
    public void EstadisticasPersonaje() {
        System.out.println("\nNombre: " + nombre + "\n1. Vida: " + vida + "\n2. Mana: " + mana + "\n3. Magia: " + magia + "\n4. Fuerza: " + fuerza + "\n5. Resistencia: " + resistencia + "\n6. Destreza: " + destreza + "\n7. Velocidad: " + velocidad + "\n8. Exp: " + exp + "\n9. Nivel: " + nivel + "\n10. Puntos de nivel: " + puntosNivel);
    }

    @Override
    public void MejorarPersonaje(int habilidad) {
        switch (habilidad) {
            case 1:
                vida += 20;
                break;
            case 2:
                mana += 20;
                break;
            case 3:
                magia += 20;
                break;
            case 4:
                fuerza += 20;
                break;
            case 5:
                resistencia += 20;
                break;
            case 6:
                destreza += 20 * (multiplicadorDestreza > 0 ? multiplicadorDestreza : 1);
                break;
            case 7:
                velocidad += 20 * (multiplicadorVelocidad > 0 ? multiplicadorVelocidad : 1);
                break;
            default:
                System.out.println("Habilidad no válida");
                break;
        }
    }
}

class Enemigo extends Personaje {
    public Enemigo(String nombre, double vida, double mana, double magia, double fuerza, double resistencia, double destreza, double velocidad, double exp, int nivel, int puntosNivel) {
        super(nombre, vida, mana, magia, fuerza, resistencia, destreza, velocidad, exp, nivel, puntosNivel);
    }

    @Override
    public void EstadisticasPersonaje() {
        System.out.println("\nNombre: " + nombre + "\n1. Vida: " + vida + "\n2. Mana: " + mana + "\n3. Magia: " + magia + "\n4. Fuerza: " + fuerza + "\n5. Resistencia: " + resistencia + "\n6. Destreza: " + destreza + "\n7. Velocidad: " + velocidad + "\n8. Exp: " + exp + "\n9. Nivel: " + nivel + "\n10. Puntos de nivel: " + puntosNivel);
    }

    @Override
    public void MejorarPersonaje(int habilidad) {
    }
}