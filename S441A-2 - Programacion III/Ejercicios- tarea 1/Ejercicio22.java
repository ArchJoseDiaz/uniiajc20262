public class Ejercicio22 {
    public static void main(String[] args) {
        int[][] M = {
            {15, 42, 9},
            {8, 99, 23},
            {4, 17, 31}
        };

        int mayor = M[0][0], menor = M[0][0];
        int filaMayor = 0, colMayor = 0;
        int filaMenor = 0, colMenor = 0;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] > mayor) {
                    mayor = M[i][j];
                    filaMayor = i;
                    colMayor = j;
                }
                if (M[i][j] < menor) {
                    menor = M[i][j];
                    filaMenor = i;
                    colMenor = j;
                }
            }
        }

        System.out.println("Elemento Mayor: " + mayor + " en la casilla [" + filaMayor + "][" + colMayor + "]");
        System.out.println("Elemento Menor: " + menor + " en la casilla [" + filaMenor + "][" + colMenor + "]");
    }
}
