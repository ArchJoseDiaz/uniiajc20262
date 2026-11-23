package estructuras;

import excepciones.PilaDeshacerVaciaException;
import modelo.OperacionDeshacer;

/** Dos pilas: pilaDeshacer y pilaRehacer. Cada elemento guarda el estado anterior de la operación. */
public class SistemaDeshacerRehacer {
    private final Pila<OperacionDeshacer> pilaDeshacer = new Pila<>();
    private final Pila<OperacionDeshacer> pilaRehacer = new Pila<>();

    /** Registra una operación nueva; invalida lo que hubiera para rehacer. */
    public void registrar(OperacionDeshacer op) {
        pilaDeshacer.push(op);
        pilaRehacer.vaciar();
    }

    /** Restaura el estado anterior de la última operación y la mueve a la pila de rehacer. */
    public String deshacer() throws PilaDeshacerVaciaException {
        if (pilaDeshacer.estaVacia()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para deshacer");
        }
        OperacionDeshacer op = pilaDeshacer.pop();
        String msg = op.deshacer();
        pilaRehacer.push(op);
        return msg;
    }

    /** Reaplica la última operación deshecha y la devuelve a la pila de deshacer. */
    public String rehacer() throws PilaDeshacerVaciaException {
        if (pilaRehacer.estaVacia()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para rehacer");
        }
        OperacionDeshacer op = pilaRehacer.pop();
        String msg = op.rehacer();
        pilaDeshacer.push(op);
        return msg;
    }

    /** Se usa tras operaciones no reversibles (p. ej. batch) para evitar estados inconsistentes. */
    public void limpiar() {
        pilaDeshacer.vaciar();
        pilaRehacer.vaciar();
    }
}
