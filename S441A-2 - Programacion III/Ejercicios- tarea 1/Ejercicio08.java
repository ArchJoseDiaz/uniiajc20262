import java.util.Arrays;
import java.util.Random;

public class Ejercicio08 {
    public static void main(String[] args) {
        int[] numeros = new int[30];
        Random rand = new Random();
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = rand.nextInt(50) + 1; // Genera valores entre 1 y 50
        }

        int mayor = numeros[0];
        int menor = numeros[0];
        for (int num : numeros) {
            if (num > mayor) mayor = num;
            if (num < menor) menor = num;
        }

        int repMayor = 0, repMenor = 0;
        for (int num : numeros) {
            if (num == mayor) repMayor++;
            if (num == menor) repMenor++;
        }

        System.out.println("Números generados: " + Arrays.toString(numeros));
        System.out.println("Número Mayor: " + mayor + " | Se repite: " + repMayor + " vez/veces");
        System.out.println("Número Menor: " + menor + " | Se repite: " + repMenor + " vez/veces");
    }
}
