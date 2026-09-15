import java.util.Arrays;

public class Ejercicio21 {
    public static void main(String[] args) {
        int[][] M = {
            {4, 7, 2, 9},
            {1, 3, 5, 8},
            {6, 10, 12, 3},
            {11, 14, 15, 16}
        };

        int m = M.length;
        int[] B = new int[m];

        for (int i = 0; i < m; i++) {
            int sumaParesFila = 0;
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] % 2 == 0) {
                    sumaParesFila += M[i][j];
                }
            }
            B[i] = sumaParesFila;
        }

        System.out.println("Vector B (Suma de elementos pares por fila): " + Arrays.toString(B));
    }
}
