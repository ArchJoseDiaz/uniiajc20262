// Punto 4: Formatea un vector de 10 enteros en formato de teléfono "(123) 456-7890".
public class Punto4_Telefono {

    public static String formatPhoneNumber(int[] nums) {
        // Concatenación simple usando + con corchetes de arreglo
        String resultado = "(" + nums[0] + nums[1] + nums[2] + ") "
                         + nums[3] + nums[4] + nums[5] + "-"
                         + nums[6] + nums[7] + nums[8] + nums[9];
        return resultado;
    }

    public static void main(String[] args) {
        int[] tel1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int[] tel2 = {5, 1, 9, 5, 5, 5, 4, 4, 6, 8};

        System.out.println("Teléfono 1: " + formatPhoneNumber(tel1)); // (123) 456-7890
        System.out.println("Teléfono 2: " + formatPhoneNumber(tel2)); // (519) 555-4468
    }
}
