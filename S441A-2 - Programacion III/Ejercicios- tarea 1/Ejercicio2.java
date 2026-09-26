import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de empleados (N): ");
        int n = scanner.nextInt();

        double[] aSueldos = new double[n];
        double[] bAsignaciones = new double[n];
        double[] cDeducciones = new double[n];
        double[] tNeto = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Empleado " + (i + 1) + " ---");
            System.out.print("Ingrese el sueldo básico: ");
            aSueldos[i] = scanner.nextDouble();
            System.out.print("Ingrese las asignaciones totales: ");
            bAsignaciones[i] = scanner.nextDouble();
            System.out.print("Ingrese las deducciones totales: ");
            cDeducciones[i] = scanner.nextDouble();

            tNeto[i] = aSueldos[i] + bAsignaciones[i] - cDeducciones[i];
        }

        System.out.println("\n=== RESUMEN DE NÓMINA DE NETOS A PAGAR ===");
        for (int i = 0; i < n; i++) {
            System.out.println("Empleado " + (i + 1) + " - Neto a pagar: $" + tNeto[i]);
        }

        scanner.close();
    }
}