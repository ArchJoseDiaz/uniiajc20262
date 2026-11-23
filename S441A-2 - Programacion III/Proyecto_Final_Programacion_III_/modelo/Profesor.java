package modelo;

/** Profesor: segunda hija de Persona. */
public class Profesor extends Persona {
    private final String departamento;
    private final double salario;

    public Profesor(String id, String nombre, String email, String departamento, double salario) {
        super(id, nombre, email);
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }

    @Override
    public String mostrarInformacion() {
        return "Profesor ID: " + getId() + " | Nombre: " + getNombre() + " | Depto: " + departamento;
    }
}
