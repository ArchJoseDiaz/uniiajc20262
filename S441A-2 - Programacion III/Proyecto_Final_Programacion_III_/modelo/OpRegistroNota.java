package modelo;

/** Registro de nota. Guarda la nota previa (null si no existía) para restaurarla. */
public class OpRegistroNota extends OperacionDeshacer {
    private final Estudiante estudiante;
    private final Materia materia;
    private final int semestre;
    private final Double notaPrevia;
    private final double notaNueva;

    private OpRegistroNota(Estudiante e, Materia m, int semestre, Double previa, double nueva) {
        super("NOTA");
        this.estudiante = e;
        this.materia = m;
        this.semestre = semestre;
        this.notaPrevia = previa;
        this.notaNueva = nueva;
    }

    public static OpRegistroNota ejecutar(Estudiante e, Materia m, int semestre, double nota) {
        Double previa = e.obtenerNota(semestre, m.getCodigo());
        e.registrarNota(semestre, m.getCodigo(), nota);
        e.agregarMateriaHistorial(m);
        return new OpRegistroNota(e, m, semestre, previa, nota);
    }

    @Override
    public String deshacer() {
        if (notaPrevia == null) {
            estudiante.eliminarNota(semestre, materia.getCodigo());
            estudiante.quitarMateriaHistorial(materia);
            return "nota de " + materia.getCodigo() + " eliminada para " + estudiante.getNombre();
        }
        estudiante.registrarNota(semestre, materia.getCodigo(), notaPrevia);
        return "nota de " + materia.getCodigo() + " restaurada a " + notaPrevia;
    }

    @Override
    public String rehacer() {
        estudiante.registrarNota(semestre, materia.getCodigo(), notaNueva);
        estudiante.agregarMateriaHistorial(materia);
        return "nota de " + materia.getCodigo() + " = " + notaNueva;
    }
}
