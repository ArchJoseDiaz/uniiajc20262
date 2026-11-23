package modelo;

import excepciones.ColaDeEsperaVaciaException;
import excepciones.EstudianteNoEncontradoException;

/** Cancelación de inscripción. Guarda quién fue promovido desde la cola para poder revertirlo. */
public class OpCancelacion extends OperacionDeshacer {
    private final Estudiante estudiante;
    private final Materia materia;
    private final Estudiante promovido; // null si nadie estaba en espera
    private final boolean estabaEnCola;
    private final int posicionEnCola; // posición original si estaba en espera

    private OpCancelacion(Estudiante e, Materia m, Estudiante promovido, boolean estabaEnCola, int pos) {
        super("CANCELACION");
        this.estudiante = e;
        this.materia = m;
        this.promovido = promovido;
        this.estabaEnCola = estabaEnCola;
        this.posicionEnCola = pos;
    }

    /** Ejecuta la cancelación (asignando el cupo al primero de la cola) y devuelve la operación. */
    public static OpCancelacion ejecutar(Estudiante e, Materia m) throws EstudianteNoEncontradoException {
        if (m.estaInscrito(e)) {
            m.retirarInscrito(e);
            Estudiante promovido = null;
            try {
                promovido = m.promoverSiguiente();
            } catch (ColaDeEsperaVaciaException ex) {
                // Nadie en espera: el cupo simplemente queda libre.
            }
            return new OpCancelacion(e, m, promovido, false, -1);
        }
        if (m.estaEnCola(e)) {
            int pos = m.posicionEnCola(e);
            m.retirarDeCola(e);
            return new OpCancelacion(e, m, null, true, pos);
        }
        throw new EstudianteNoEncontradoException(e.getNombre() + " no está inscrito ni en espera en " + m.getCodigo());
    }

    public Estudiante getPromovido() { return promovido; }

    @Override
    public String deshacer() {
        if (estabaEnCola) {
            materia.insertarEnCola(posicionEnCola, estudiante); // recupera su posición original
        } else {
            if (promovido != null) {
                materia.retirarInscrito(promovido);
                materia.encolarAlFrente(promovido);
            }
            materia.agregarInscrito(estudiante);
        }
        return estudiante.getNombre().split(" ")[0] + " vuelve a estar en " + materia.getCodigo();
    }

    @Override
    public String rehacer() {
        if (estabaEnCola) {
            materia.retirarDeCola(estudiante);
        } else {
            materia.retirarInscrito(estudiante);
            try {
                materia.promoverSiguiente();
            } catch (ColaDeEsperaVaciaException ex) {
                // sin cola, nada que promover
            }
        }
        return estudiante.getNombre().split(" ")[0] + " queda fuera de " + materia.getCodigo();
    }
}
