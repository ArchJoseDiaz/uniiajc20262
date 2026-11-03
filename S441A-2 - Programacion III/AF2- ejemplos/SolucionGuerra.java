public class SolucionGuerra {

    public static int warOfNumbers(int[] arr) {
        int sumaPares = 0;
        int sumaImpares = 0;

        // Recorrer el arreglo para clasificar los números
        for (int num : arr) {
            if (num % 2 == 0) {
                sumaPares += num; // Es par
            } else {
                sumaImpares += num; // Es impar
            }
        }

        // Math.abs devuelve el valor absoluto de la resta (la diferencia positiva)
        return Math.abs(sumaPares - sumaImpares);
    }

    public static void main(String[] args) {
        // Casos de prueba basados en los ejemplos de la imagen
        int[] ejemplo1 = {2, 8, 7, 5};
        int[] ejemplo2 = {12, 90, 75};
        int[] ejemplo3 = {5, 9, 45, 6, 2, 7, 34, 8, 6, 90, 5, 243};

        System.out.println("Resultado Ejemplo 1: " + warOfNumbers(ejemplo1)); // Salida: 2
        System.out.println("Resultado Ejemplo 2: " + warOfNumbers(ejemplo2)); // Salida: 27
        System.out.println("Resultado Ejemplo 3: " + warOfNumbers(ejemplo3)); // Salida: 168
    }
}
