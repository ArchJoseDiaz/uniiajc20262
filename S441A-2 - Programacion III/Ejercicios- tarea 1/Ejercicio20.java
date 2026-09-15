public class Ejercicio20 {
    public static void main(String[] args) {
        int[][] M = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j >= i) { // Diagonal principal y elementos superiores
                    M[i][j] = 0;
                } else {
                    M[i][j] = (i + 1) * 2; // Ejemplo de valores por debajo
                }
            }
        }

        System.out.println("Matriz 10x10 con ceros sobre la diagonal principal:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%4d", M[i][j]);
            }
            System.out.println();
        }
    }
}
