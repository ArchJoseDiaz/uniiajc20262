// Punto 8: Calcula la diferencia absoluta entre la suma de pares e impares.
public class Punto8_GuerraNumeros {

    public static int warOfNumbers(int[] nums) {
        int sumaPares = 0;
        int sumaImpares = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                sumaPares = sumaPares + nums[i];
            } else {
                sumaImpares = sumaImpares + nums[i];
            }
        }

        // Diferencia utilizando condicionales simples
        if (sumaPares > sumaImpares) {
            return sumaPares - sumaImpares;
        } else {
            return sumaImpares - sumaPares;
        }
    }

    public static void main(String[] args) {
        int[] g1 = {2, 8, 7, 5};   // Pares=10, Impares=12 -> Dif: 2
        int[] g2 = {12, 90, 75};   // Pares=102, Impares=75 -> Dif: 27

        System.out.println("Resultado Guerra 1: " + warOfNumbers(g1)); // 2
        System.out.println("Resultado Guerra 2: " + warOfNumbers(g2)); // 27
    }
}
