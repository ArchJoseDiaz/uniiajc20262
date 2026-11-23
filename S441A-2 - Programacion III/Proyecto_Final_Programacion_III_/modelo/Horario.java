package modelo;

/** Horario asignado a una materia: aula + día + hora de inicio + duración. */
public class Horario {
    public static final String[] DIAS = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

    private final Aula aula;
    private final int dia;
    private final int hora;
    private final int duracion;

    public Horario(Aula aula, int dia, int hora, int duracion) {
        this.aula = aula;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
    }

    public Aula getAula() { return aula; }
    public int getDia() { return dia; }
    public int getHora() { return hora; }
    public int getDuracion() { return duracion; }

    @Override
    public String toString() {
        return "Aula " + aula.getCodigo() + ", " + DIAS[dia] + " " + hora + ":00 (" + duracion + "h)";
    }
}
