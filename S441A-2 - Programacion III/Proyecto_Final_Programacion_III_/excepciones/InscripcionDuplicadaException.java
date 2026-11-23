package excepciones;

/** Excepción de dominio: InscripcionDuplicada. */
public class InscripcionDuplicadaException extends Exception {
    public InscripcionDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
