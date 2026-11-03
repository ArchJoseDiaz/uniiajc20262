// Punto 5: Encuentra el segundo número más grande en un arreglo numérico.
public class Punto5_SegundoMayor {

    public static int secondLargest(int[] nums) {
        // Inicializamos con el primer número del arreglo
        int mayor = nums[0];
        int segundoMayor = nums[0];

        // 1. Encontrar el número más grande
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > mayor) {
                mayor = nums[i];
            }
        }

        // 2. Encontrar el número más grande que sea menor que el mayor
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > segundoMayor && nums[i] < mayor) {
                segundoMayor = nums[i];
            }
        }

        return segundoMayor;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 40, 30, 20, 50};
        int[] arr2 = {25, 143, 89, 13, 105};

        System.out.println("Segundo mayor arr1: " + secondLargest(arr1)); // 40
        System.out.println("Segundo mayor arr2: " + secondLargest(arr2)); // 105
    }
}
