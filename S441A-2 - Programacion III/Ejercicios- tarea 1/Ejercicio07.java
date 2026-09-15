import java.util.Arrays;

public class Ejercicio07 {
    public static void main(String[] args) {
        int[] A = {12, 5, 8, 19, 4, 7, 22, 15, 30, 3};

        int contPares = 0, contImpares = 0;
        for (int num : A) {
            if (num % 2 == 0) contPares++;
            else contImpares++;
        }

        int[] pares = new int[contPares];
        int[] impares = new int[contImpares];
        int idxP = 0, idxI = 0;
        for (int num : A) {
            if (num % 2 == 0) pares[idxP++] = num;
            else impares[idxI++] = num;
        }

        System.out.println("Arreglo Original A: " + Arrays.toString(A));
        System.out.println("Arreglo Pares: " + Arrays.toString(pares));
        System.out.println("Arreglo Impares: " + Arrays.toString(impares));
    }
}
