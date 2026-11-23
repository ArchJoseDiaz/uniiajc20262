package modelo;

import excepciones.HorarioConflictivoException;

/** Aula con matriz nativa boolean[7][24]: true = reservado, false = libre. */
public class Aula implements Comparable<Aula> {
    private final String codigo;
    private final int capacidad;
    private final boolean[][] reservado = new boolean[7][24];

    public Aula(String codigo, int capacidad) {
        this.codigo = codigo;
        this.capacidad = capacidad;
    }

    public String getCodigo() { return codigo; }
    public int getCapacidad() { return capacidad; }

    /** @return true si esa hora está libre. */
    public boolean consultarDisponibilidad(int dia, int hora) {
        validar(dia, hora);
        return !reservado[dia][hora];
    }

    /**
     * Reserva [hora, hora+duracion) en el día dado. Es atómica: si alguna hora está ocupada
     * no se reserva ninguna.
     */
    public void reservar(int dia, int hora, int duracion) throws HorarioConflictivoException {
        validar(dia, hora);
        if (duracion <= 0 || hora + duracion > 24)
            throw new IllegalArgumentException("Duración inválida (la reserva debe terminar a las 24:00 como máximo).");
        for (int h = hora; h < hora + duracion; h++) {
            if (reservado[dia][h])
                throw new HorarioConflictivoException(Horario.DIAS[dia] + " " + h + ":00 ya esta reservado");
        }
        for (int h = hora; h < hora + duracion; h++) reservado[dia][h] = true;
    }

    /** Libera un rango reservado. */
    public void liberar(int dia, int hora, int duracion) {
        validar(dia, hora);
        if (duracion <= 0 || hora + duracion > 24) throw new IllegalArgumentException("Duración inválida.");
        for (int h = hora; h < hora + duracion; h++) reservado[dia][h] = false;
    }

    private void validar(int dia, int hora) {
        if (dia < 0 || dia > 6) throw new IllegalArgumentException("Día inválido (0=Domingo ... 6=Sábado).");
        if (hora < 0 || hora > 23) throw new IllegalArgumentException("Hora inválida (0-23).");
    }

    @Override
    public int compareTo(Aula otra) { return codigo.compareTo(otra.codigo); }

    @Override
    public String toString() { return "Aula " + codigo + " (capacidad " + capacidad + ")"; }
}
