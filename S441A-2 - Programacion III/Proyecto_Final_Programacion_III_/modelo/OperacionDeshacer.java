package modelo;

/**
 * Operación reversible (patrón Command). Cada subclase guarda el estado anterior necesario
 * para deshacer y los datos para rehacer. Se almacena en la pila de deshacer/rehacer.
 */
public abstract class OperacionDeshacer {
    private final String tipo;

    protected OperacionDeshacer(String tipo) { this.tipo = tipo; }

    public String getTipo() { return tipo; }

    /** Restaura el estado anterior. @return mensaje para el usuario. */
    public abstract String deshacer();

    /** Reaplica la operación. @return mensaje para el usuario. */
    public abstract String rehacer();
}
