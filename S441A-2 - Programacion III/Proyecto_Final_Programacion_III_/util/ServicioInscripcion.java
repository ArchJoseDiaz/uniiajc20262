package util;

import excepciones.CupoLlenoException;
import excepciones.InscripcionDuplicadaException;
import excepciones.PreRequisitoNoAprobadoException;
import modelo.Estudiante;
import modelo.Materia;
import modelo.OpInscripcion;

/** Lógica única de inscripción, compartida por el menú y por el procesamiento batch. */
public class ServicioInscripcion {

    /** Resultado de una inscripción: inscrito directo o en cola de espera. */
    public static class Resultado {
        public final boolean enEspera;
        public final int posicionCola;
        public final OpInscripcion operacion;

        Resultado(boolean enEspera, int posicionCola, OpInscripcion operacion) {
            this.enEspera = enEspera;
            this.posicionCola = posicionCola;
            this.operacion = operacion;
        }
    }

    private ServicioInscripcion() { }

    /**
     * Inscribe al estudiante si cumple los pre-requisitos (nota >= 3.0). Si no hay cupo,
     * lo agrega a la cola de espera.
     */
    public static Resultado inscribir(Estudiante e, Materia m)
            throws PreRequisitoNoAprobadoException, InscripcionDuplicadaException {
        if (m.estaInscrito(e) || m.estaEnCola(e))
            throw new InscripcionDuplicadaException(e.getNombre() + " ya esta inscrito o en espera en " + m.getCodigo());
        if (e.haAprobado(m.getCodigo()))
            throw new InscripcionDuplicadaException(e.getNombre() + " ya aprobo " + m.getCodigo());
        for (Materia req : m.getPreRequisitos()) {
            if (!e.haAprobado(req.getCodigo()))
                throw new PreRequisitoNoAprobadoException("No ha aprobado el pre-requisito " + req.getCodigo());
        }
        try {
            m.verificarCupo();
            m.agregarInscrito(e);
            return new Resultado(false, 0, new OpInscripcion(e, m, false));
        } catch (CupoLlenoException ex) {
            m.encolar(e);
            int pos = m.totalEnEspera();
            return new Resultado(true, pos, new OpInscripcion(e, m, true));
        }
    }
}
