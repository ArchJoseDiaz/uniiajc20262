import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de elementos (N): ");
        int n = scanner.nextInt();

        double[] numeros = new double[n];
        double suma = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el número " + (i + 1) + ": ");
            numeros[i] = scanner.nextDouble();
            suma += numeros[i];
        }

        System.out.println("La suma total de los " + n + " números es: " + suma);
        scanner.close();
    }
}