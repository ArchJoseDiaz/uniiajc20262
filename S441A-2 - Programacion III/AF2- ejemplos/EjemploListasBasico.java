import java.util.ArrayList;
import java.util.List;

public class EjemploListasBasico {
    public static void main(String[] args) {
        // Declaración e instanciación usando la interfaz List
        List<String> tareas = new ArrayList<>();

        // 1. Agregar elementos al final
        tareas.add("Estudiar Java");
        tareas.add("Hacer taller de listas");
        tareas.add("Revisar proyectos");

        // 2. Insertar en una posición específica (desplaza el resto)
        tareas.add(1, "Preparar clase del 20 de Oct");

        System.out.println("Lista completa de tareas:");
        System.out.println(tareas);

        // 3. Consultar tamaño e ítem por índice
        System.out.println("\nTotal tareas (size): " + tareas.size());
        System.out.println("Primera tarea (get 0): " + tareas.get(0));

        // 4. Modificar un elemento existente
        tareas.set(2, "Hacer taller práctico de List");

        // 5. Verificar presencia y obtener posición
        if (tareas.contains("Estudiar Java")) {
            int pos = tareas.indexOf("Estudiar Java");
            System.out.println("\n'Estudiar Java' está en la posición: " + pos);
        }

        // 6. Recorrer la lista con bucle for-each
        System.out.println("\n--- TAREAS PENDIENTES ---");
        for (String tarea : tareas) {
            System.out.println("- " + tarea);
        }

        // 7. Eliminar elementos (por índice y por objeto)
        tareas.remove(0); 
        tareas.remove("Revisar proyectos"); 

        System.out.println("\nLista final tras eliminar:");
        System.out.println(tareas);
    }
}
