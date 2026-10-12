public class Bicola {
    
    // Clase interna que define la estructura de cada Nodo
    private class Nodo {
        Object elemento;
        Nodo siguiente;
        Nodo anterior;

        public Nodo(Object elemento) {
            this.elemento = elemento;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    private Nodo frente;
    private Nodo fin;

    public Bicola() {
        this.frente = null;
        this.fin = null;
    }

    // Verificar si la bicola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // 1. Insertar por el Frente
    public void ponerFrente(Object elemento) {
        Nodo nuevoNode = new Nodo(elemento);
        if (estaVacia()) {
            frente = fin = nuevoNode;
        } else {
            nuevoNode.siguiente = frente;
            frente.anterior = nuevoNode;
            frente = nuevoNode;
        }
    }

    // 2. Insertar por el Final (Fin)
    public void ponerFinal(Object elemento) {
        Nodo nuevoNode = new Nodo(elemento);
        if (estaVacia()) {
            frente = fin = nuevoNode;
        } else {
            nuevoNode.anterior = fin;
            fin.siguiente = nuevoNode;
            fin = nuevoNode;
        }
    }

    // 3. Eliminar por el Frente
    public Object quitarFrente() {
        if (estaVacia()) {
            System.out.println("Error: Bicola vacía");
            return null;
        }
        Object valor = frente.elemento;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null; // La bicola se quedó totalmente vacía
        } else {
            frente.anterior = null;
        }
        return valor;
    }

    // 4. Eliminar por el Final (Fin)
    public Object quitarFinal() {
        if (estaVacia()) {
            System.out.println("Error: Bicola vacía");
            return null;
        }
        Object valor = fin.elemento;
        fin = fin.anterior;
        if (fin == null) {
            frente = null; // La bicola se quedó totalmente vacía
        } else {
            fin.siguiente = null;
        }
        return valor;
    }
}
