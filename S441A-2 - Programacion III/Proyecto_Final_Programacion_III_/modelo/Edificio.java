package modelo;

/** Edificio del campus (nodo del grafo). */
public class Edificio {
    private final int id;
    private final String nombre;

    public Edificio(int id, String nombre) { this.id = id; this.nombre = nombre; }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() { return id + ": " + nombre; }
}
