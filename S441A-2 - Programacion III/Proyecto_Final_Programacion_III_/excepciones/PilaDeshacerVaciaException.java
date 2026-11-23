package excepciones;

/** Excepción de dominio: PilaDeshacerVacia. */
public class PilaDeshacerVaciaException extends Exception {
    public PilaDeshacerVaciaException(String mensaje) {
        super(mensaje);
    }
}
