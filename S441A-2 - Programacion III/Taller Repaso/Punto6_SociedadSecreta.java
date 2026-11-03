// Punto 6: Genera el nombre de la sociedad secreta ordenando las iniciales alfabéticamente (Método Burbuja).
public class Punto6_SociedadSecreta {

    public static String societyName(String[] nombres) {
        String[] iniciales = new String[nombres.length];

        // Extraer la primera letra de cada nombre
        for (int i = 0; i < nombres.length; i++) {
            iniciales[i] = nombres[i].substring(0, 1).toUpperCase();
        }

        // Ordenamiento por método Burbuja (sin librerías avanzadas)
        for (int i = 0; i < iniciales.length - 1; i++) {
            for (int j = 0; j < iniciales.length - 1 - i; j++) {
                if (iniciales[j].charAt(0) > iniciales[j + 1].charAt(0)) {
                    String aux = iniciales[j];
                    iniciales[j] = iniciales[j + 1];
                    iniciales[j + 1] = aux;
                }
            }
        }

        // Concatenar el resultado
        String resultado = "";
        for (int i = 0; i < iniciales.length; i++) {
            resultado = resultado + iniciales[i];
        }

        return resultado;
    }

    public static void main(String[] args) {
        String[] g1 = {"Adam", "Sarah", "Malcolm"};
        String[] g2 = {"Harry", "Newt", "Luna", "Cho"};

        System.out.println("Sociedad 1: " + societyName(g1)); // "AMS"
        System.out.println("Sociedad 2: " + societyName(g2)); // "CHLN"
    }
}
