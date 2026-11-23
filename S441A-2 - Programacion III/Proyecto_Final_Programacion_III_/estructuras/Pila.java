package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Pila (LIFO) implementada manualmente con nodos enlazados. */
public class Pila<T> implements Iterable<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> abajo;
        Nodo(T dato, Nodo<T> abajo) { this.dato = dato; this.abajo = abajo; }
    }

    private Nodo<T> tope;
    private int tamano;

    public void push(T dato) {
        tope = new Nodo<>(dato, tope);
        tamano++;
    }

    public T pop() {
        if (tope == null) throw new NoSuchElementException("Pila vacía");
        T d = tope.dato;
        tope = tope.abajo;
        tamano--;
        return d;
    }

    public T peek() {
        if (tope == null) throw new NoSuchElementException("Pila vacía");
        return tope.dato;
    }

    public boolean estaVacia() { return tope == null; }
    public int tamano() { return tamano; }
    public void vaciar() { tope = null; tamano = 0; }

    /** Recorre del tope hacia el fondo. */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = tope;
            public boolean hasNext() { return actual != null; }
            public T next() {
                T d = actual.dato;
                actual = actual.abajo;
                return d;
            }
        };
    }
}
