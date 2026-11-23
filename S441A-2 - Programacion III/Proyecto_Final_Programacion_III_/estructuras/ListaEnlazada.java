package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista enlazada simple implementada manualmente (nodos + referencias).
 * Se usa para pre-requisitos, historial de materias e inscritos.
 */
public class ListaEnlazada<T> implements Iterable<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    private Nodo<T> cabeza;
    private Nodo<T> ultimo;
    private int tamano;

    /** Agrega al final de la lista. O(1). */
    public void agregarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamano++;
    }

    /** Agrega al inicio de la lista. O(1). */
    public void agregarAlInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        if (ultimo == null) ultimo = nuevo;
        tamano++;
    }

    /** Elimina la primera ocurrencia (por equals). Devuelve true si la encontró. */
    public boolean eliminar(T dato) {
        Nodo<T> previo = null;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                if (previo == null) cabeza = actual.siguiente;
                else previo.siguiente = actual.siguiente;
                if (actual == ultimo) ultimo = previo;
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

    /** Devuelve el elemento en la posición indicada (base 0). */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamano) throw new IndexOutOfBoundsException("Índice: " + indice);
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) actual = actual.siguiente;
        return actual.dato;
    }

    public int tamano() { return tamano; }
    public boolean estaVacia() { return tamano == 0; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            public boolean hasNext() { return actual != null; }
            public T next() {
                if (actual == null) throw new NoSuchElementException();
                T d = actual.dato;
                actual = actual.siguiente;
                return d;
            }
        };
    }
}
