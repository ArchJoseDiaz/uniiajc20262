import java.util.Arrays;

public class Ejercicio13 {
    public static void main(String[] args) {
        int[] A = {14, 25, 8, 30, 19, 11, 42, 5};

        int suma = 0;
        for (int num : A) suma += num;
        double media = (double) suma / A.length;

        int cantMayores = 0, cantMenores = 0;
        for (int num : A) {
            if (num > media) cantMayores++;
            else if (num < media) cantMenores++;
        }

        int[] mayores = new int[cantMayores];
        int[] menores = new int[cantMenores];
        int idxMay = 0, idxMen = 0;

        for (int num : A) {
            if (num > media) mayores[idxMay++] = num;
            else if (num < media) menores[idxMen++] = num;
        }

        System.out.println("Arreglo A: " + Arrays.toString(A));
        System.out.printf("Media (Promedio): %.2f%n", media);
        System.out.println("Elementos mayores a la media: " + Arrays.toString(mayores));
        System.out.println("Elementos menores a la media: " + Arrays.toString(menores));
    }
}
