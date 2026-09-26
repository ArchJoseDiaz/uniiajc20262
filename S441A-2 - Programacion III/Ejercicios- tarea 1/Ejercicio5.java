import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de productos distintos (N): ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] descripcion = new String[n];
        double[] pu = new double[n];
        int[] cc = new int[n];
        double[] tg = new double[n];

        double totalGeneral = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Producto " + (i + 1) + " ---");
            System.out.print("Descripción del producto: ");
            descripcion[i] = scanner.nextLine();
            System.out.print("Precio Unitario (PU): ");
            pu[i] = scanner.nextDouble();
            System.out.print("Cantidad Comprada (CC): ");
            cc[i] = scanner.nextInt();
            scanner.nextLine();

            tg[i] = pu[i] * cc[i];
            totalGeneral += tg[i];
        }

        int indiceMayorGasto = 0;
        for (int i = 1; i < n; i++) {
            if (tg[i] > tg[indiceMayorGasto]) {
                indiceMayorGasto = i;
            }
        }

        System.out.println("\n=== INFORME DE COMPRAS ===");
        for (int i = 0; i < n; i++) {
            System.out.println("Producto: " + descripcion[i] + " | Total Gastado: $" + tg[i]);
        }

        System.out.println("\nTotal General de todas las compras: $" + totalGeneral);
        System.out.println("Producto con mayor gasto: " + descripcion[indiceMayorGasto] + " (Total: $" + tg[indiceMayorGasto] + ")");

        scanner.close();
    }
}