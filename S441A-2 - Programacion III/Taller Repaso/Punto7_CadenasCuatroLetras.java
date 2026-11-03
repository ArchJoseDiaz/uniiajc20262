import java.util.ArrayList;
import java.util.List;

// Punto 7: Retorna una lista con las palabras de 4 letras o menos.
public class Punto7_CadenasCuatroLetras {

    public static List<String> isFourLetters(String[] palabras) {
        List<String> resultado = new ArrayList<>();

        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].length() <= 4) {
                resultado.add(palabras[i]);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        String[] f1 = {"Tomate", "Papa", "Par"};
        String[] f2 = {"Ryan", "Kieran", "Jason", "Matt"};

        System.out.println("Resultado 1: " + isFourLetters(f1)); // [Papa, Par]
        System.out.println("Resultado 2: " + isFourLetters(f2)); // [Ryan, Matt]
    }
}
