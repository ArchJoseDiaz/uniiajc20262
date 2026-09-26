// Maneja todo lo que pasa en una función (película, horario, mapa de sillas y precios)
public class Funcion {
    private Pelicula pelicula;
    private Sala sala;
    private int franjaHoraria; // 1, 2 o 3
    
    // Usamos dos matrices de booleanos: false es libre (_) y true es ocupada (X)
    private boolean[][] ocupacionGeneral;
    private boolean[][] ocupacionPreferencial;

    public Funcion(Pelicula pelicula, Sala sala, int franjaHoraria) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.franjaHoraria = franjaHoraria;

        // Le damos el tamaño a las matrices según cómo sea la sala
        this.ocupacionGeneral = new boolean[sala.getFilasGeneral()][sala.getSillasGeneralPorFila()];
        
        if (sala.tienePreferencial()) {
            this.ocupacionPreferencial = new boolean[sala.getFilasPreferencial()][sala.getSillasPreferencialPorFila()];
        } else {
            // Si la sala no tiene preferencial (como la 3), dejamos la matriz vacía
            this.ocupacionPreferencial = new boolean[0][0];
        }
    }

    public Pelicula getPelicula() { return pelicula; }
    public Sala getSala() { return sala; }
    public int getFranjaHoraria() { return franjaHoraria; }

    public String getNombreFranja() {
        switch (franjaHoraria) {
            case 1: return "14:00 - 16:30";
            case 2: return "16:30 - 19:00";
            case 3: return "19:00 - 21:00";
            default: return "Franja Desconocida";
        }
    }

    // Imprime el dibujito de la sala con las sillas ocupadas y libres
    public void mostrarEsquemaSillas() {
        System.out.println("\n======== ESQUEMA DE SILLAS DE LA SALA " + sala.getNumero() + " ========");
        System.out.println("Película: " + pelicula.getNombre() + " (" + getNombreFranja() + ")");
        System.out.println("Convención: '_' = Disponible | 'X' = Ocupada\n");

        // Imprimimos primero la sección Preferencial si existe (filas H y G)
        if (sala.tienePreferencial()) {
            System.out.println("--- SECCIÓN PREFERENCIAL ($12.000) ---");
            char letraPref = 'H';
            for (int i = sala.getFilasPreferencial() - 1; i >= 0; i--) {
                char letraFila = (char) (letraPref - (sala.getFilasPreferencial() - 1 - i));
                System.out.print(letraFila + "   ");
                for (int j = 0; j < sala.getSillasPreferencialPorFila(); j++) {
                    char estado = ocupacionPreferencial[i][j] ? 'X' : '_';
                    System.out.print(" " + estado + " ");
                }
                System.out.println();
            }
            System.out.println("----------------------------------------");
        }

        // Imprimimos la sección General (recorremos al revés para que la fila A quede abajo cerca a la pantalla)
        System.out.println("--- SECCIÓN GENERAL (" + (sala.isEs3D() ? "$10.000" : "$8.000") + ") ---");
        for (int i = sala.getFilasGeneral() - 1; i >= 0; i--) {
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + "   ");
            for (int j = 0; j < sala.getSillasGeneralPorFila(); j++) {
                char estado = ocupacionGeneral[i][j] ? 'X' : '_';
                System.out.print(" " + estado + " ");
            }
            System.out.println();
        }

        System.out.println("     ----------------------------------");
        System.out.println("                | PANTALLA |           \n");
    }

    // Cuenta cuántas sillas quedan desocupadas en total
    public int contarSillasDisponibles() {
        int disponibles = 0;
        for (int i = 0; i < ocupacionGeneral.length; i++) {
            for (int j = 0; j < ocupacionGeneral[i].length; j++) {
                if (!ocupacionGeneral[i][j]) disponibles++;
            }
        }
        for (int i = 0; i < ocupacionPreferencial.length; i++) {
            for (int j = 0; j < ocupacionPreferencial[i].length; j++) {
                if (!ocupacionPreferencial[i][j]) disponibles++;
            }
        }
        return disponibles;
    }

    // Función para comprar la silla pasando un texto como "A3" o "G2"
    public double comprarSilla(String codigoSilla) {
        codigoSilla = codigoSilla.trim().toUpperCase();
        if (codigoSilla.length() < 2) return -1;

        char letraFila = codigoSilla.charAt(0);
        int numeroSilla;
        try {
            // Sacamos el número de la silla (ej: de "A12" saca el 12)
            numeroSilla = Integer.parseInt(codigoSilla.substring(1));
        } catch (NumberFormatException e) {
            return -1; // Si escriben algo raro
        }

        // Si la silla es Preferencial (Filas G u H)
        if (sala.tienePreferencial() && (letraFila == 'G' || letraFila == 'H')) {
            int filaIdx = letraFila - 'G'; // 'G' pasa a ser 0 y 'H' pasa a ser 1
            int colIdx = numeroSilla - 1;   // Le restamos 1 porque la matriz empieza en 0

            if (filaIdx >= 0 && filaIdx < sala.getFilasPreferencial() && colIdx >= 0 && colIdx < sala.getSillasPreferencialPorFila()) {
                if (ocupacionPreferencial[filaIdx][colIdx]) {
                    System.out.println(" La silla " + codigoSilla + " ya se encuentra OCUPADA.");
                    return -2; // Código para indicar que ya estaba ocupada
                }
                ocupacionPreferencial[filaIdx][colIdx] = true; // La marcamos como ocupada
                return 12000.0; // Precio Preferencial
            }
        } 
        // Si la silla es General (Filas A a F)
        else if (letraFila >= 'A' && letraFila <= 'F') {
            int filaIdx = letraFila - 'A'; // 'A' pasa a ser 0, 'B' a 1, etc.
            int colIdx = numeroSilla - 1;

            if (filaIdx >= 0 && filaIdx < sala.getFilasGeneral() && colIdx >= 0 && colIdx < sala.getSillasGeneralPorFila()) {
                if (ocupacionGeneral[filaIdx][colIdx]) {
                    System.out.println("⚠️ La silla " + codigoSilla + " ya se encuentra OCUPADA.");
                    return -2;
                }
                ocupacionGeneral[filaIdx][colIdx] = true;
                // Si la sala es 3D cuesta $10.000, si no cuesta $8.000
                return sala.isEs3D() ? 10000.0 : 8000.0;
            }
        }

        System.out.println(" La silla " + codigoSilla + " NO existe en la Sala " + sala.getNumero() + ".");
        return -1;
    }
}
