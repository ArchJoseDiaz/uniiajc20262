public class ColaCircular {
    private int[] cola;
    private int frente, atras, tamano, capacidad;

    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new int[capacidad];
        this.frente = 0;
        this.atras = 0;
        this.tamano = 0;
    }

    // Insertar elemento en la cola
    public boolean encolar(int elemento) {
        if (tamano == capacidad) {
            System.out.println("Error: Cola llena (Overflow)");
            return false;
        }
        cola[atras] = elemento;
        atras = (atras + 1) % capacidad; // Puntero circular con módulo
        tamano++;
        return true;
    }

    // Quitar elemento de la cola
    public Integer desencolar() {
        if (tamano == 0) {
            System.out.println("Error: Cola vacía (Underflow)");
            return null;
        }
        int elemento = cola[frente];
        frente = (frente + 1) % capacidad; // Puntero circular con módulo
        tamano--;
        return elemento;
    }
}
