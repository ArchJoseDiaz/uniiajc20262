import java.util.Arrays;

public class Ejercicio11 {
    public static void main(String[] args) {
        int[] A = {-5, 0, 12, -2, 0, 8, 15, -9, 0, 3};

        int cNeg = 0, cCeros = 0, cPos = 0;
        for (int num : A) {
            if (num < 0) cNeg++;
            else if (num == 0) cCeros++;
            else cPos++;
        }

        int[] negativos = new int[cNeg];
        int[] ceros = new int[cCeros];
        int[] positivos = new int[cPos];
        int iN = 0, iC = 0, iP = 0;

        for (int num : A) {
            if (num < 0) negativos[iN++] = num;
            else if (num == 0) ceros[iC++] = num;
            else positivos[iP++] = num;
        }

        System.out.println("Original A: " + Arrays.toString(A));
        System.out.println("Negativos: " + Arrays.toString(negativos));
        System.out.println("Ceros: " + Arrays.toString(ceros));
        System.out.println("Positivos: " + Arrays.toString(positivos));
    }
}
