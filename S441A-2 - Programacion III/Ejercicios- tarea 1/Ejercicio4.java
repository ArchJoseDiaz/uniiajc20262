import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el tamaño del primer grupo de edades: ");
        int n1 = scanner.nextInt();
        int[] grupo1 = new int[n1];

        System.out.println("--- Ingrese las edades del Grupo 1 ---");
        for (int i = 0; i < n1; i++) {
            System.out.print("Edad " + (i + 1) + ": ");
            grupo1[i] = scanner.nextInt();
        }

        System.out.print("Ingrese el tamaño del segundo grupo de edades: ");
        int n2 = scanner.nextInt();
        int[] grupo2 = new int[n2];

        System.out.println("--- Ingrese las edades del Grupo 2 ---");
        for (int i = 0; i < n2; i++) {
            System.out.print("Edad " + (i + 1) + ": ");
            grupo2[i] = scanner.nextInt();
        }

        int edadMayor = (n1 > 0) ? grupo1[0] : ((n2 > 0) ? grupo2[0] : 0);

        for (int i = 0; i < n1; i++) {
            if (grupo1[i] > edadMayor) {
                edadMayor = grupo1[i];
            }
        }

        for (int i = 0; i < n2; i++) {
            if (grupo2[i] > edadMayor) {
                edadMayor = grupo2[i];
            }
        }

        System.out.println("\nLa mayor edad encontrada entre ambos grupos es: " + edadMayor + " años.");
        scanner.close();
    }
}