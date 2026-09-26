import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de viviendas (N): ");
        int n = scanner.nextInt();

        double[] alquileres = new double[n];
        double[] porcentajesGanancia = new double[n];
        double[] ganancias = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Vivienda " + (i + 1) + " ---");
            System.out.print("Ingrese el valor del alquiler mensual ($): ");
            alquileres[i] = scanner.nextDouble();
            System.out.print("Ingrese el porcentaje de ganancia (ej. 10 para 10%): ");
            porcentajesGanancia[i] = scanner.nextDouble();

            ganancias[i] = alquileres[i] * (porcentajesGanancia[i] / 100.0);
        }

        System.out.println("\n=== GANANCIAS POR CADA VIVIENDA ===");
        for (int i = 0; i < n; i++) {
            System.out.println("Vivienda " + (i + 1) + " - Alquiler: $" + alquileres[i] + " | Porcentaje: " + porcentajesGanancia[i] + "% | Ganancia: $" + ganancias[i]);
        }

        scanner.close();
    }
}