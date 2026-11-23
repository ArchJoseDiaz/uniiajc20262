package excepciones;

/** Excepción de dominio: HorarioConflictivo. */
public class HorarioConflictivoException extends Exception {
    public HorarioConflictivoException(String mensaje) {
        super(mensaje);
    }
}
