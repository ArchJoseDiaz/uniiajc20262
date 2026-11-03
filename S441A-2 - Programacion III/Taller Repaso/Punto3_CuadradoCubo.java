// Punto 3: Comprueba si la raíz cuadrada del primer número es igual a la raíz cúbica del segundo.
public class Punto3_CuadradoCubo {

    public static boolean checkSquareAndCube(int[] arr) {
        // Raíz cuadrada del primer elemento (arr[0])
        double raizCuadrada = Math.sqrt(arr[0]);

        // Elevar la raíz al cubo (multiplicarla 3 veces) para comparar con arr[1]
        double cuboDeRaiz = raizCuadrada * raizCuadrada * raizCuadrada;

        if (cuboDeRaiz == arr[1]) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] par1 = {4, 8};   // sqrt(4)=2, 2*2*2=8 -> true
        int[] par2 = {16, 48}; // sqrt(16)=4, 4*4*4=64 != 48 -> false
        int[] par3 = {9, 27};  // sqrt(9)=3, 3*3*3=27 -> true

        System.out.println("Par {4, 8}: " + checkSquareAndCube(par1));   // true
        System.out.println("Par {16, 48}: " + checkSquareAndCube(par2)); // false
        System.out.println("Par {9, 27}: " + checkSquareAndCube(par3));  // true
    }
}
