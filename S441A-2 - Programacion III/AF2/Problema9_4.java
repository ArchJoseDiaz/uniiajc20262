import java.util.Stack;

public class Problema9_4 {
    public static void main(String[] args) {
        // 1. Guardar los 10 reales en un arreglo
        double[] arregloReales = {1.5, 2.3, 4.7, 8.9, 3.2, 5.5, 7.1, 9.0, 0.4, 6.8};
        Stack<Double> pila = new Stack<>();

        // 2. Pasar del arreglo a la pila
        for (double num : arregloReales) {
            pila.push(num);
        }

        // 3. Imprimir extrayendo (saldrán en orden inverso por la propiedad LIFO)
        System.out.print("Elementos extraídos de la pila: ");
        while (!pila.isEmpty()) {
            System.out.print(pila.pop() + " | ");
        }
    }
}
