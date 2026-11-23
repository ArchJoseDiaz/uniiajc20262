package modelo;

/** Inscripción de un estudiante (quedó inscrito o en cola de espera). */
public class OpInscripcion extends OperacionDeshacer {
    private final Estudiante estudiante;
    private final Materia materia;
    private final boolean enEspera;

    public OpInscripcion(Estudiante estudiante, Materia materia, boolean enEspera) {
        super("INSCRIPCION");
        this.estudiante = estudiante;
        this.materia = materia;
        this.enEspera = enEspera;
    }

    private static String primerNombre(Estudiante e) { return e.getNombre().split(" ")[0]; }

    @Override
    public String deshacer() {
        if (enEspera) materia.retirarDeCola(estudiante); else materia.retirarInscrito(estudiante);
        return primerNombre(estudiante) + " ya NO esta inscrita en " + materia.getCodigo();
    }

    @Override
    public String rehacer() {
        if (enEspera) materia.encolar(estudiante); else materia.agregarInscrito(estudiante);
        return primerNombre(estudiante) + (enEspera ? " de nuevo en cola de espera de " : " inscrita en ") + materia.getCodigo();
    }
}
