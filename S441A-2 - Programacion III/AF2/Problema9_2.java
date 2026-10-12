import java.util.Stack;

public class Problema9_2 {
    public static boolean esCadenaInversa(String entrada) {
        Stack<Character> pila = new Stack<>();
        int i = 0;

        // 1. Apilar los caracteres de X hasta encontrar el separador '&'
        while (i < entrada.length() && entrada.charAt(i) != '&') {
            pila.push(entrada.charAt(i));
            i++;
        }

        // Si terminó la cadena y no encontramos el '&', es incorrecto
        if (i == entrada.length()) return false;

        i++; // Saltamos el carácter '&'

        // 2. Comparar el resto de la cadena (Y) con los elementos de la pila
        while (i < entrada.length()) {
            if (pila.isEmpty() || entrada.charAt(i) != pila.pop()) {
                return false; // No coincide o Y es más larga que X
            }
            i++;
        }

        // Si la pila quedó vacía, significa que X e Y tenían el mismo tamaño
        return pila.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(esCadenaInversa("hola&aloh")); // true
        System.out.println(esCadenaInversa("abc&abc"));   // false
    }
}
