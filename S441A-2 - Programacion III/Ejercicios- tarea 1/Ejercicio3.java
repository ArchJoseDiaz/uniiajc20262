import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de números del arreglo: ");
        int n = scanner.nextInt();

        int[] numeros = new int[n];
        int sumaPares = 0;
        int sumaImpares = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el número en la posición " + i + ": ");
            numeros[i] = scanner.nextInt();

            if (numeros[i] % 2 == 0) {
                sumaPares += numeros[i];
            } else {
                sumaImpares += numeros[i];
            }
        }

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Suma total de números pares: " + sumaPares);
        System.out.println("Suma total de números impares: " + sumaImpares);

        scanner.close();
    }
}