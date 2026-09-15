public class Ejercicio17 {
    public static void main(String[] args) {
        int[][] M = {
            {3, 5, 1},
            {2, 8, 4},
            {7, 6, 9}
        };

        int filas = M.length;
        int columnas = M[0].length;

        System.out.println("--- SUMA DE FILAS ---");
        for (int i = 0; i < filas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < columnas; j++) {
                sumaFila += M[i][j];
            }
            System.out.println("Fila " + i + ": " + sumaFila);
        }

        System.out.println("--- SUMA DE COLUMNAS ---");
        for (int j = 0; j < columnas; j++) {
            int sumaCol = 0;
            for (int i = 0; i < filas; i++) {
                sumaCol += M[i][j];
            }
            System.out.println("Columna " + j + ": " + sumaCol);
        }
    }
}
