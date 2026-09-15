import java.util.Arrays;

public class Ejercicio10 {
    public static void main(String[] args) {
        int[] A = {8, 5, 3, 10, 2, 8, 1}; // N = 7
        int n = A.length;
        int tamB = (n + 1) / 2;
        int[] B = new int[tamB];

        int inicio = 0;
        int fin = n - 1;
        int idxB = 0;

        while (inicio <= fin) {
            if (inicio == fin) {
                B[idxB] = A[inicio]; // Elemento central en arreglos de tamaño impar
            } else {
                B[idxB] = A[inicio] + A[fin];
            }
            inicio++;
            fin--;
            idxB++;
        }

        System.out.println("Arreglo A: " + Arrays.toString(A));
        System.out.println("Arreglo B (Suma de opuestos): " + Arrays.toString(B));
    }
}
