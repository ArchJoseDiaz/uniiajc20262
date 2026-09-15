import java.util.Arrays;

public class Ejercicio09 {
    public static void main(String[] args) {
        int[] datos = {4, 7, 2, 7, 9, 7, 1, 5, 7, 3};
        int X = 7;
        int contador = 0;

        for (int num : datos) {
            if (num == X) {
                contador++;
            }
        }

        System.out.println("Arreglo de datos: " + Arrays.toString(datos));
        System.out.println("El número " + X + " aparece " + contador + " veces en el arreglo.");
    }
}
