package excepciones;

/** Excepción de dominio: EstudianteNoEncontrado. */
public class EstudianteNoEncontradoException extends Exception {
    public EstudianteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
