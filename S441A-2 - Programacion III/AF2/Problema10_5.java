public class Problema10_5 {
    // Definimos el tamano fijo de 9 posiciones como indica el libro
    private static final int TAMANO = 9;
    private char[] arregloCircular;
    private int frente;
    private int fin;
    private int totalElementos;

    public Problema10_5() {
        arregloCircular = new char[TAMANO];
        // Estado inicial del problema
        frente = 5;
        fin = 7;
        
        // Asignamos los elementos iniciales: Bicola: A, C, E
        arregloCircular[5] = 'A';
        arregloCircular[6] = 'C';
        arregloCircular[7] = 'E';
        totalElementos = 3;
    }

    // Operacion para anadir por el final (avanza el puntero fin)
    public void anadirFinal(char elemento) {
        if (totalElementos == TAMANO) {
            System.out.println("ERROR al anadir '" + elemento + "' por el final: Bicola llena (Overflow)");
            return;
        }
        fin = (fin + 1) % TAMANO;
        arregloCircular[fin] = elemento;
        totalElementos++;
    }

    // Operacion para anadir por el frente (retrocede el puntero frente)
    public void anadirFrente(char elemento) {
        if (totalElementos == TAMANO) {
            System.out.println("ERROR al anadir '" + elemento + "' por el frente: Bicola llena (Overflow)");
            return;
        }
        frente = (frente - 1 + TAMANO) % TAMANO;
        arregloCircular[frente] = elemento;
        totalElementos++;
    }

    // Operacion para eliminar por el frente (avanza el puntero frente)
    public char eliminarFrente() {
        if (totalElementos == 0) {
            System.out.println("ERROR: Bicola vacia (Underflow)");
            return ' ';
        }
        char eliminado = arregloCircular[frente];
        arregloCircular[frente] = ' '; // Limpiamos la casilla visualmente
        frente = (frente + 1) % TAMANO;
        totalElementos--;
        return eliminado;
    }

    // Metodo para mostrar el estado actual de los extremos y los elementos
    public void mostrarEstado(String operacionEfectuada) {
        System.out.println("====== " + operacionEfectuada.toUpperCase() + " ======");
        System.out.println("Indices actuales: frente = " + frente + " | fin = " + fin);
        
        // Imprimir el vector interno tal y como reside en la memoria de la computadora
        System.out.print("Memoria interna: [ ");
        for (int i = 0; i < TAMANO; i++) {
            char c = arregloCircular[i];
            System.out.print((c == '\u0000' || c == ' ') ? "_" : c);
            System.out.print(" ");
        }
        System.out.println("]");

        // Imprimir los elementos logicos de la Bicola en su orden correcto (de frente a fin)
        System.out.print("Bicola en orden logico: ");
        if (totalElementos == 0) {
            System.out.println("(Vacia)");
        } else {
            int auxiliarFrente = frente;
            for (int i = 0; i < totalElementos; i++) {
                System.out.print(arregloCircular[auxiliarFrente]);
                if (i < totalElementos - 1) System.out.print(", ");
                auxiliarFrente = (auxiliarFrente + 1) % TAMANO;
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Problema10_5 simulacion = new Problema10_5();
        simulacion.mostrarEstado("Estado Inicial del Libro");

        // 1. Anadir los elementos F y K por el final de la bicola.
        simulacion.anadirFinal('F');
        simulacion.anadirFinal('K');
        simulacion.mostrarEstado("Anadir F y K por el final");

        // 2. Anadir los elementos R, W y V por el frente de la bicola.
        simulacion.anadirFrente('R');
        simulacion.anadirFrente('W');
        simulacion.anadirFrente('V');
        simulacion.mostrarEstado("Anadir R, W y V por el frente");

        // 3. Anadir el elemento M por el final de la bicola.
        simulacion.anadirFinal('M');
        simulacion.mostrarEstado("Anadir M por el final");

        // 4. Eliminar dos caracteres por el frente.
        char primero = simulacion.eliminarFrente();
        char segundo = simulacion.eliminarFrente();
        simulacion.mostrarEstado("Eliminar dos por el frente (Salieron: " + primero + ", " + segundo + ")");

        // 5. Anadir los elementos K y L por el final de la bicola.
        simulacion.anadirFinal('K');
        simulacion.anadirFinal('L');
        simulacion.mostrarEstado("Anadir K y L por el final");

        // 6. Anadir el elemento S por el frente de la bicola (Generara desbordamiento)
        simulacion.anadirFrente('S');
        simulacion.mostrarEstado("Intentar anadir S por el frente");
    }
}
