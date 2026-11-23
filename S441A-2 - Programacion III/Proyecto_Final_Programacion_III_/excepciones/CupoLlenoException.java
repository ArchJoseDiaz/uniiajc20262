package excepciones;

/** Excepción de dominio: CupoLleno. */
public class CupoLlenoException extends Exception {
    public CupoLlenoException(String mensaje) {
        super(mensaje);
    }
}
