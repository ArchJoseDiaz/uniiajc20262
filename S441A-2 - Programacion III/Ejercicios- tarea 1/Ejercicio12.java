import java.util.Arrays;

public class Ejercicio12 {
    public static void main(String[] args) {
        int[] A = {4, 6, 8, 2, 6, 9, 6, 1};
        int X = 6;

        int coincidencias = 0;
        for (int num : A) {
            if (num == X) {
                coincidencias++;
            }
        }

        int[] B = new int[coincidencias];
        int idxB = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == X) {
                B[idxB++] = i + 1; // Almacena la posición base 1 (humana)
            }
        }

        System.out.println("Arreglo A: " + Arrays.toString(A));
        System.out.println("Valor X buscado: " + X);
        System.out.println("Posiciones encontradas (Base 1): " + Arrays.toString(B));
    }
}
