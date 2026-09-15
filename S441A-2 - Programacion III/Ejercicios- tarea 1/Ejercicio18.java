public class Ejercicio18 {
    public static void main(String[] args) {
        int[][] tabla = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabla[i][j] = (i + 1) * (j + 1);
            }
        }

        System.out.println("=== TABLA DE MULTIPLICAR EN MATRIZ (10x10) ===");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%4d", tabla[i][j]);
            }
            System.out.println();
        }
    }
}
