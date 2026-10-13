import java.util.Stack;

public class Problema9_6 {
    public static double evaluarPostfija(String[] tokens) {
        Stack<Double> pila = new Stack<>();

        for (String token : tokens) {
            // Comprobar si el token es un número (positivo o negativo, entero o decimal)
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                pila.push(Double.parseDouble(token));
            } else {
                // Al ser operador, sacamos los dos últimos números de la pila
                double operando2 = pila.pop();
                double operando1 = pila.pop();
                
                switch (token) {
                    case "+": pila.push(operando1 + operando2); break;
                    case "-": pila.push(operando1 - operando2); break;
                    case "*": pila.push(operando1 * operando2); break;
                    case "/": pila.push(operando1 / operando2); break;
                    case "^": pila.push(Math.pow(operando1, operando2)); break;
                }
            }
        }
        return pila.pop(); // El último elemento es el resultado final
    }

    public static void main(String[] args) {
        // Ejemplo numérico de prueba: (10-4)/(1+2) que en postfija es ["10", "4", "-", "1", "2", "+", "/"]
        String[] expresion = {"10", "4", "-", "1", "2", "+", "/"};
        System.out.println("Resultado de la evaluación: " + evaluarPostfija(expresion)); // Da como resultado 2.0
    }
}
