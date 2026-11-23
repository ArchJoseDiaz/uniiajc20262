package excepciones;

/** Excepción de dominio: ArchivoInvalido. */
public class ArchivoInvalidoException extends Exception {
    public ArchivoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
