package excepciones;

/** Excepción de dominio: PreRequisitoNoAprobado. */
public class PreRequisitoNoAprobadoException extends Exception {
    public PreRequisitoNoAprobadoException(String mensaje) {
        super(mensaje);
    }
}
