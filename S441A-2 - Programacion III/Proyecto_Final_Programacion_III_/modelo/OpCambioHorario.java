package modelo;

import excepciones.HorarioConflictivoException;

/** Reserva o cambio de horario (con o sin materia asociada). Guarda horario anterior y nuevo. */
public class OpCambioHorario extends OperacionDeshacer {
    private final Materia materia;   // puede ser null (reserva libre de aula)
    private final Horario anterior;  // puede ser null
    private final Horario nuevo;

    private OpCambioHorario(Materia materia, Horario anterior, Horario nuevo) {
        super("HORARIO");
        this.materia = materia;
        this.anterior = anterior;
        this.nuevo = nuevo;
    }

    /**
     * Libera el horario anterior de la materia (si lo tenía) y reserva el nuevo.
     * Si el nuevo conflictúa, restaura el anterior y relanza la excepción.
     */
    public static OpCambioHorario ejecutar(Materia m, Aula aula, int dia, int hora, int dur)
            throws HorarioConflictivoException {
        Horario ant = (m == null) ? null : m.getHorario();
        if (ant != null) ant.getAula().liberar(ant.getDia(), ant.getHora(), ant.getDuracion());
        try {
            aula.reservar(dia, hora, dur);
        } catch (HorarioConflictivoException | IllegalArgumentException ex) {
            if (ant != null) reservarSeguro(ant);
            throw ex;
        }
        Horario nuevo = new Horario(aula, dia, hora, dur);
        if (m != null) m.setHorario(nuevo);
        return new OpCambioHorario(m, ant, nuevo);
    }

    private static void reservarSeguro(Horario h) {
        try {
            h.getAula().reservar(h.getDia(), h.getHora(), h.getDuracion());
        } catch (HorarioConflictivoException e) {
            throw new IllegalStateException("Estado inconsistente al restaurar horario", e);
        }
    }

    @Override
    public String deshacer() {
        nuevo.getAula().liberar(nuevo.getDia(), nuevo.getHora(), nuevo.getDuracion());
        if (anterior != null) reservarSeguro(anterior);
        if (materia != null) materia.setHorario(anterior);
        return anterior == null ? "reserva liberada (" + nuevo + ")" : "horario restaurado a " + anterior;
    }

    @Override
    public String rehacer() {
        if (anterior != null) anterior.getAula().liberar(anterior.getDia(), anterior.getHora(), anterior.getDuracion());
        reservarSeguro(nuevo);
        if (materia != null) materia.setHorario(nuevo);
        return "reserva de " + nuevo;
    }
}
