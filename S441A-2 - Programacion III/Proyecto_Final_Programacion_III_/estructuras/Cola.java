package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Cola (FIFO) implementada manualmente con nodos enlazados. */
public class Cola<T> implements Iterable<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    /** Agrega al final de la cola. */
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin == null) frente = nuevo;
        else fin.siguiente = nuevo;
        fin = nuevo;
        tamano++;
    }

    /** Reinserta al frente (usado al deshacer una promoción desde la cola de espera). */
    public void encolarAlFrente(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = frente;
        frente = nuevo;
        if (fin == null) fin = nuevo;
        tamano++;
    }

    public T desencolar() {
        if (frente == null) throw new NoSuchElementException("Cola vacía");
        T d = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamano--;
        return d;
    }

    public T verFrente() {
        if (frente == null) throw new NoSuchElementException("Cola vacía");
        return frente.dato;
    }

    /** Elimina un elemento cualquiera de la cola (por equals). */
    public boolean eliminar(T dato) {
        Nodo<T> previo = null;
        Nodo<T> actual = frente;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                if (previo == null) frente = actual.siguiente;
                else previo.siguiente = actual.siguiente;
                if (actual == fin) fin = previo;
                tamano--;
                return true;
            }
            previo = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean contiene(T dato) {
        for (T t : this) if (t.equals(dato)) return true;
        return false;
    }

    /** @return posición (base 0) del elemento en la cola o -1 si no está. */
    public int posicionDe(T dato) {
        int i = 0;
        for (T t : this) { if (t.equals(dato)) return i; i++; }
        return -1;
    }

    /** Inserta en una posición concreta (base 0); se usa al deshacer una salida de la cola. */
    public void insertarEn(int pos, T dato) {
        if (pos <= 0 || frente == null) { encolarAlFrente(dato); return; }
        if (pos >= tamano) { encolar(dato); return; }
        Nodo<T> previo = frente;
        for (int i = 0; i < pos - 1; i++) previo = previo.siguiente;
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = previo.siguiente;
        previo.siguiente = nuevo;
        tamano++;
    }

    public boolean estaVacia() { return frente == null; }
    public int tamano() { return tamano; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = frente;
            public boolean hasNext() { return actual != null; }
            public T next() {
                T d = actual.dato;
                actual = actual.siguiente;
                return d;
            }
        };
    }
}
