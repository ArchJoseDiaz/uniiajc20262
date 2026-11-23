package modelo;

/** Facultad de la universidad (se almacenan en un arreglo fijo Facultad[5]). */
public class Facultad {
    private final int id;
    private final String nombre;

    public Facultad(int id, String nombre) { this.id = id; this.nombre = nombre; }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() { return "[" + id + "] " + nombre; }
}
