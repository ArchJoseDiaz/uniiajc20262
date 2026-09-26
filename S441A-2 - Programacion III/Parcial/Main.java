import java.util.Scanner;

// Programa principal con el menú para probar todo
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Teatro teatro = new Teatro();

        // Agregamos unas películas iniciales para no tener que escribirlas cada vez que probamos
        teatro.registrarPelicula("Avatar 2", "Español", "3D", 190);
        teatro.registrarPelicula("Batman", "Subtitulada", "35mm", 175);
        teatro.registrarPelicula("Minions", "Español", "35mm", 90);

        int opcion = 0;
        do {
            System.out.println("\n============================================");
            System.out.println("    SISTEMA DE TEATRO - CINEMASTAR CALI");
            System.out.println("============================================");
            System.out.println("1. Menú de Creación / Gestión de Películas");
            System.out.println("2. Menú de Asignación de Funciones");
            System.out.println("3. Módulo de Ventas de Boletas");
            System.out.println("4. Salir de la Aplicación");
            System.out.print("Seleccione una opción: ");

            // Usamos Integer.parseInt para evitar problemas con el Scanner al leer números y textos
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    menuPeliculas(scanner, teatro);
                    break;
                case 2:
                    menuAsignacion(scanner, teatro);
                    break;
                case 3:
                    moduloVentas(scanner, teatro);
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println(" Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void menuPeliculas(Scanner scanner, Teatro teatro) {
        int op = 0;
        do {
            System.out.println("\n--- MENÚ DE CREACIÓN DE PELÍCULAS ---");
            System.out.println("1. Ver películas en repertorio");
            System.out.println("2. Registrar nueva película");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Opción: ");

            try {
                op = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                op = -1;
            }

            if (op == 1) {
                teatro.listarPeliculas();
            } else if (op == 2) {
                System.out.print("Nombre de la película: ");
                String nombre = scanner.nextLine();
                System.out.print("Idioma: ");
                String idioma = scanner.nextLine();
                System.out.print("Tipo (1 para 35mm / 2 para 3D): ");
                String tipoSel = scanner.nextLine();
                String tipo = tipoSel.equals("2") ? "3D" : "35mm";
                System.out.print("Duración en minutos: ");
                int duracion = 120;
                try {
                    duracion = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {}

                teatro.registrarPelicula(nombre, idioma, tipo, duracion);
            }
        } while (op != 3);
    }

    private static void menuAsignacion(Scanner scanner, Teatro teatro) {
        if (teatro.getCantidadPeliculas() == 0) {
            System.out.println("❌ No hay películas registradas.");
            return;
        }

        System.out.println("\n--- ASIGNACIÓN DE FUNCIONES ---");
        teatro.listarPeliculas();

        System.out.print("\nSeleccione la película: ");
        int idxPel = Integer.parseInt(scanner.nextLine()) - 1;

        System.out.print("Seleccione Sala (1 - 3): ");
        int numSala = Integer.parseInt(scanner.nextLine());

        System.out.print("Seleccione Franja (1: 14:00-16:30, 2: 16:30-19:00, 3: 19:00-21:00): ");
        int franja = Integer.parseInt(scanner.nextLine());

        teatro.asignarFuncion(numSala, franja, idxPel);
    }

    private static void moduloVentas(Scanner scanner, Teatro teatro) {
        System.out.println("\n--- MÓDULO DE VENTAS DE ENTRADAS ---");
        System.out.print("Número de Sala (1 - 3): ");
        int numSala = Integer.parseInt(scanner.nextLine());

        System.out.print("Franja Horaria (1 - 3): ");
        int franja = Integer.parseInt(scanner.nextLine());

        Funcion funcion = teatro.getFuncion(numSala, franja);

        if (funcion == null) {
            System.out.println(" No hay película programada en esa sala y franja.");
            return;
        }

        boolean venderMas = true;
        while (venderMas) {
            funcion.mostrarEsquemaSillas();
            System.out.println("Sillas Disponibles: " + funcion.contarSillasDisponibles());
            
            System.out.print("Ingrese las sillas separadas por coma (Ej: A3, B8, G4) o '0' para salir: ");
            String entrada = scanner.nextLine();

            if (entrada.trim().equals("0")) break;

            // Separamos por coma por si quieren comprar varias sillas al tiempo
            String[] sillasPedidas = entrada.split(",");
            double totalCompra = 0;
            int compradas = 0;

            for (String codigoSilla : sillasPedidas) {
                double resultado = funcion.comprarSilla(codigoSilla);
                if (resultado > 0) {
                    totalCompra += resultado;
                    compradas++;
                }
            }

            if (compradas > 0) {
                System.out.println("\n============================================");
                System.out.println("  FCOMPRA REALIZADA CON ÉXITO ");
                System.out.println("Boletas compradas: " + compradas);
                System.out.println("VALOR TOTAL A PAGAR: $" + totalCompra);
                System.out.println("============================================");
            }

            System.out.print("\n¿Desea realizar otra compra en esta misma función? (S/N): ");
            if (!scanner.nextLine().equalsIgnoreCase("S")) venderMas = false;
        }
    }
}
