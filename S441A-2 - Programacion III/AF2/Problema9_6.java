import java.util.Stack;
import java.util.LinkedList; // Usamos LinkedList simulando el comportamiento circular

public class Problema9_6 {
    public static boolean esPalindromo(String frase) {
        // Limpiar la frase de espacios y pasar a minúsculas
        String limpia = frase.replaceAll("\\s+", "").toLowerCase();
        
        Stack<Character> pila = new Stack<>();
        LinkedList<Character> listaCircular = new LinkedList<>();

        // Añadir a las dos estructuras
        for (int i = 0; i < limpia.length(); i++) {
            char c = limpia.charAt(i);
            pila.push(c);               // Almacena en orden inverso (LIFO)
            listaCircular.addLast(c);   // Almacena en orden original por el final
        }

        // Extraer simultáneamente y comparar
        while (!pila.isEmpty()) {
            char dePila = pila.pop();
            char deLista = listaCircular.removeFirst(); // El primero de la lista circular

            if (dePila != deLista) {
                return false; // No es palíndromo
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(esPalindromo("Anita lava la tina")); // true
        System.out.println(esPalindromo("Estructuras"));        // false
    }
}
