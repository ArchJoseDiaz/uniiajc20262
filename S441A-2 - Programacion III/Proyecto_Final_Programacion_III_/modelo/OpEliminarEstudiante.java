package modelo;

import estructuras.ListaEnlazada;
import excepciones.EstudianteNoEncontradoException;
import java.util.Collection;
import java.util.Map;

/**
 * Baja definitiva de un estudiante. También lo retira de las materias (con promoción desde la cola),
 * reutilizando OpCancelacion para poder revertirlo todo.
 */
public class OpEliminarEstudiante extends OperacionDeshacer {
    private final Map<String, Estudiante> indice;
    private final Estudiante estudiante;
    private final ListaEnlazada<OpCancelacion> cancelaciones;

    private OpEliminarEstudiante(Map<String, Estudiante> indice, Estudiante e, ListaEnlazada<OpCancelacion> c) {
        super("ELIMINAR_ESTUDIANTE");
        this.indice = indice;
        this.estudiante = e;
        this.cancelaciones = c;
    }

    public static OpEliminarEstudiante ejecutar(Map<String, Estudiante> indice, Estudiante e,
                                                Collection<Materia> materias) {
        indice.remove(e.getId());
        ListaEnlazada<OpCancelacion> ops = new ListaEnlazada<>();
        for (Materia m : materias) {
            if (m.estaInscrito(e) || m.estaEnCola(e)) {
                try {
                    ops.agregarAlFinal(OpCancelacion.ejecutar(e, m));
                } catch (EstudianteNoEncontradoException ex) {
                    throw new IllegalStateException(ex); // imposible: se verificó antes
                }
            }
        }
        return new OpEliminarEstudiante(indice, e, ops);
    }

    @Override
    public String deshacer() {
        indice.put(estudiante.getId(), estudiante);
        for (int i = cancelaciones.tamano() - 1; i >= 0; i--) cancelaciones.obtener(i).deshacer();
        return "estudiante " + estudiante.getNombre() + " (ID " + estudiante.getId() + ") restaurado";
    }

    @Override
    public String rehacer() {
        indice.remove(estudiante.getId());
        for (OpCancelacion c : cancelaciones) c.rehacer();
        return "estudiante " + estudiante.getNombre() + " eliminado de nuevo";
    }
}
