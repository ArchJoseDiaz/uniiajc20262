import java.util.EmptyStackException;
import java.util.Stack;

public class DeshacerEditor {
    private Stack<String> pilaAcciones;

    public DeshacerEditor() {
        pilaAcciones = new Stack<>();
    }

    // Método Apilar (Push): Guardar una nueva acción
    public void guardarAccion(String accion) {
        pilaAcciones.push(accion);
        System.out.println("Acción guardada: '" + accion + "'");
    }

    // Método Desapilar (Pop): Cancelar/Deshacer la última acción
    public String deshacer() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }
        return pilaAcciones.pop();
    }

    // Método Mirar Cúspide (Peek): Ver qué es lo próximo que se va a deshacer
    public String verProximaAccion() {
        if (estaVacia()) return null;
        return pilaAcciones.peek();
    }

    // Método Esta Vacía (IsEmpty): Verificar si no hay acciones guardadas
    public boolean estaVacia() {
        return pilaAcciones.isEmpty();
    }

    // Método Tamaño (Size): Ver cuántas acciones hay en el historial
    public int totalAcciones() {
        return pilaAcciones.size();
    }

    // Método Limpiar (Clear): Vaciar por completo el historial
    public void limpiarHistorial() {
        pilaAcciones.clear();
        System.out.println("Historial de acciones borrado.");
    }

    public static void main(String[] args) {
        DeshacerEditor editor = new DeshacerEditor();
        editor.guardarAccion("Escribir 'Hola Mundo'");
        editor.guardarAccion("Cambiar fuente a Arial");
        editor.guardarAccion("Borrar el párrafo 2");

        System.out.println("Próxima a deshacer (peek): " + editor.verProximaAccion());
        System.out.println("Deshaciendo acción (pop): " + editor.deshacer());
        System.out.println("Acciones restantes en el historial: " + editor.totalAcciones());
    }
}
