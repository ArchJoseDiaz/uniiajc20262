// Punto 2: Compara si dos sándwiches tienen el mismo pan al inicio (índice 0) y al final (índice 2).
public class Punto2_PanSimilar {

    public static boolean hasSameBread(String[] sandwich1, String[] sandwich2) {
        // Uso de .equals() y operador lógico && (Y)
        if (sandwich1[0].equals(sandwich2[0]) && sandwich1[2].equals(sandwich2[2])) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String[] s1 = {"pan blanco", "lechuga", "pan blanco"};
        String[] s2 = {"pan blanco", "tomate", "pan blanco"};
        String[] s3 = {"pan integral", "pollo", "pan integral"};

        System.out.println("S1 vs S2: " + hasSameBread(s1, s2)); // true
        System.out.println("S1 vs S3: " + hasSameBread(s1, s3)); // false
    }
}
