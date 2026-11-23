package modelo;

/** Clase base abstracta de la jerarquía de herencia (Persona -> Estudiante / Profesor). */
public abstract class Persona {
    private final String id;
    private String nombre;
    private String email;

    protected Persona(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }

    /** Polimorfismo: cada subclase describe su información. */
    public abstract String mostrarInformacion();

    @Override
    public boolean equals(Object o) {
        return o instanceof Persona && id.equals(((Persona) o).id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}
