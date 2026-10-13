import java.util.Stack;

public class Problema9_2 {
    public static Stack<Integer> copiarPila(Stack<Integer> origen) {
        Stack<Integer> aux = new Stack<>();
        Stack<Integer> destino = new Stack<>();

        // Paso 1: Origen -> Auxiliar (se invierte el orden)
        while (!origen.isEmpty()) {
            aux.push(origen.pop());
        }

        // Paso 2: Auxiliar -> Destino y Origen (vuelve al orden original)
        while (!aux.isEmpty()) {
            int elemento = aux.pop();
            destino.push(elemento);
            origen.push(elemento); // Se devuelve a la original para no destruirla
        }
        return destino;
    }
}
