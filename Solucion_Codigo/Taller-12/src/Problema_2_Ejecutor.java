public class Problema_2_Ejecutor {

    public static void main(String[] args) {
        MenuNinos menuNinos1 = new MenuNinos("Arroz Relleno", 8.5, 2.0, 3.0, 1.0);
        MenuNinos menuNinos2 = new MenuNinos("Arroz con Pollo", 9.0, 1.5, 2.5, 0.5);
        MenuEconomico menuEconomico = new MenuEconomico("omlet", 12.0, 3.0, 0.2);
        MenuDia menuDia = new MenuDia("Desayuno Completo", 10.0, 2.5, 2.0, 1.0);
        MenuCarta menuCarta = new MenuCarta("milanesa", 15.0, 5.0, 3.0, 2.0, 0.1);

        CuentaMenu cuenta = new CuentaMenu("Jose", 0.12);
        cuenta.menus = new Menu[]{menuNinos1, menuNinos2, menuEconomico, menuDia, menuCarta};
        cuenta.calcularValores();
        System.out.println(cuenta.toString());

    }
}
class CuentaMenu{ 
    public String nombreCliente;
    public Menu[] menus;
    public double valorTotal;
    public double valorSubtotal;
    public double iva;

    public CuentaMenu(String nombreCliente, double iva) {
        this.nombreCliente = nombreCliente;
        this.iva = iva;
    }

    public void calcularValores() {
        valorTotal = 0;
        for (Menu menu : menus) {
            valorTotal += menu.calcularTotal();
        }
        valorSubtotal = valorTotal / (1 + iva);
    }

    @Override
    public String toString() {
        String menuStr = "";
        for (Menu menu : menus) {
            menuStr += menu.toString() + "\n";
        }

        return "Cliente: " + nombreCliente
                + "\nSubtotal: " + valorSubtotal
                + "\nIVA: " + iva
                + "\nValor Total: " + valorTotal
                + "\nMenús:\n" + menuStr;
    }
}

abstract class Menu{
    public String nombrePlato;
    public double valorMenu;
    public double valorInicialMenu;

    public Menu(String nombrePlato, double valorMenu, double valorInicialMenu) {
        this.nombrePlato = nombrePlato;
        this.valorMenu = valorMenu;
        this.valorInicialMenu = valorInicialMenu;
    }

    public abstract double calcularTotal();

    @Override
    public String toString() {
        return "Nombre del plato: " + nombrePlato
                + "\nValor del menú: " + valorMenu
                + "\nValor inicial del menú: " + valorInicialMenu;
    }
}

class MenuCarta extends Menu{
      public double valorGuarnicion;
    public double valorBebida;
    public double servicioPorcentaje;

    public MenuCarta(String nombrePlato, double valorMenu, double valorInicialMenu, double valorGuarnicion,
                     double valorBebida, double servicioPorcentaje) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorGuarnicion = valorGuarnicion;
        this.valorBebida = valorBebida;
        this.servicioPorcentaje = servicioPorcentaje;
    }

    public double calcularTotal() {
        double subtotal = valorInicialMenu + valorGuarnicion + valorBebida;
        double servicio = subtotal * servicioPorcentaje;
        return subtotal + servicio;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nValor de porción de guarnición: " + valorGuarnicion +
                "\nValor de bebida: " + valorBebida +
                "\nPorcentaje adicional por servicio: " + servicioPorcentaje;
    }
}

class MenuDia extends Menu{
       public double valorPostre;
    public double valorBebida;

    public MenuDia(String nombrePlato, double valorMenu, double valorInicialMenu, double valorPostre,
                   double valorBebida) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    public double calcularTotal() {
        return valorInicialMenu + valorPostre + valorBebida;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nValor de postre: " + valorPostre +
                "\nValor de bebida: " + valorBebida;
    }
}

class MenuNinos extends Menu{
     public double valorPorcionHelado;
    public double valorPorcionPastel;

    public MenuNinos(String nombrePlato, double valorMenu, double valorInicialMenu, double valorPorcionHelado,
                      double valorPorcionPastel) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.valorPorcionHelado = valorPorcionHelado;
        this.valorPorcionPastel = valorPorcionPastel;
    }

    public double calcularTotal() {
        return valorInicialMenu + valorPorcionHelado + valorPorcionPastel;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nValor de porción de helado: " + valorPorcionHelado +
                "\nValor de porción de pastel: " + valorPorcionPastel;
    }
}

class MenuEconomico extends Menu{
    public double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorMenu, double valorInicialMenu, double porcentajeDescuento) {
        super(nombrePlato, valorMenu, valorInicialMenu);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double calcularTotal() {
        double descuento = valorInicialMenu * porcentajeDescuento;
        return valorInicialMenu - descuento;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPorcentaje de descuento: " + porcentajeDescuento;
    }
}