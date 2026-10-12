import java.util.Stack;

public class Problema9_3 {
    public static boolean verificarEquilibrio(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char car = expresion.charAt(i);

            // Si es de apertura, lo metemos a la pila
            if (car == '(' || car == '[' || car == '{') {
                pila.push(car);
            } 
            // Si es de cierre, verificamos la cúspide
            else if (car == ')' || car == ']' || car == '}') {
                if (pila.isEmpty()) return false; // Cierre sin apertura previa

                char apertura = pila.pop();
                if ((car == ')' && apertura != '(') ||
                    (car == ']' && apertura != '[') ||
                    (car == '}' && apertura != '{')) {
                    return false; // No coincide el tipo de agrupación
                }
            }
        }
        return pila.isEmpty(); // Si queda vacía, está perfectamente equilibrado
    }

    public static void main(String[] args) {
        System.out.println(verificarEquilibrio("((a+b)*5) - 7")); // true
        System.out.println(verificarEquilibrio("2*[(a+b)/2.5 + x - 7*y")); // false
    }
}
