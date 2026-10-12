import java.util.Stack;
import java.util.Scanner;

public class Problema9_4_y_5 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        // Crear un arreglo para manejar las 5 pilas (P1 a P5)
        Stack<Integer>[] gulasPilas = new Stack[5];
        for (int m = 0; m < 5; m++) {
            gulasPilas[m] = new Stack<>();
        }

        System.out.println("Introduce tríos de datos (i, j, k). Usa i=0 para terminar.");
        while (true) {
            System.out.print("Ingrese i, j, k separados por espacio: ");
            int i = teclado.nextInt();
            int j = teclado.nextInt();
            int k = teclado.nextInt();

            if (i == 0) break; // Fin del proceso

            int indicePila = Math.abs(i) - 1;
            if (indicePila < 0 || indicePila >= 5) {
                System.out.println("Pila fuera de rango (debe ser de 1 a 5).");
                continue;
            }

            // Lógica del Problema 9.5 (Si k == -1, vaciar la pila)
            if (k == -1) {
                gulasPilas[indicePila].clear();
                System.out.println("Pila P" + Math.abs(i) + " vaciada por completo.");
            } else {
                // Lógica del Problema 9.4
                if (i > 0) {
                    gulasPilas[indicePila].push(j);
                    System.out.println("Insertado " + j + " en P" + i);
                } else {
                    if (!gulasPilas[indicePila].isEmpty()) {
                        int eliminado = gulasPilas[indicePila].pop();
                        System.out.println("Elemento superior " + eliminado + " eliminado de P" + Math.abs(i));
                    } else {
                        System.out.println("La pila P" + Math.abs(i) + " ya está vacía.");
                    }
                }
            }
        }

        // Mostrar contenido final de todas las pilas
        System.out.println("\n--- CONTENIDO FINAL DE LAS 5 PILAS ---");
        for (int m = 0; m < 5; m++) {
            System.out.println("Pila P" + (m + 1) + ": " + gulasPilas[m]);
        }
    }
}
