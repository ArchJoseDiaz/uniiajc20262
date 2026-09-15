public class Ejercicio24 {
    public static void main(String[] args) {
        double[] cosechaMeses = {120.5, 98.0, 145.2, 160.0, 210.8, 250.4,
                300.0, 280.6, 195.0, 150.2, 110.0, 85.5};
        String[] nombresMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        double sumaTotal = 0;
        for (double prod : cosechaMeses) sumaTotal += prod;
        double promedioAnual = sumaTotal / cosechaMeses.length;

        int mesesSuperiores = 0;
        int mesesInferiores = 0;
        int mesMaxIdx = 0;
        double maxProduccion = cosechaMeses[0];

        for (int i = 0; i < cosechaMeses.length; i++) {
            if (cosechaMeses[i] > promedioAnual) mesesSuperiores++;
            if (cosechaMeses[i] < promedioAnual) mesesInferiores++;
            if (cosechaMeses[i] > maxProduccion) {
                maxProduccion = cosechaMeses[i];
                mesMaxIdx = i;
            }
        }

        System.out.printf("a. Promedio anual de cosechas: %.2f toneladas%n", promedioAnual);
        System.out.println("b. Meses por encima del promedio: " + mesesSuperiores);
        System.out.println("c. Meses por debajo del promedio: " + mesesInferiores);
        System.out.println("d. Mes de mayor producción: " + nombresMeses[mesMaxIdx] + " (" + maxProduccion + " toneladas)");
    }
}
