package estructuras;

import modelo.ReporteAcademico;

/** Navegación "atrás/adelante" entre reportes vistos, usando dos pilas. */
public class NavegadorReportes {
    private final Pila<ReporteAcademico> atras = new Pila<>();
    private final Pila<ReporteAcademico> adelante = new Pila<>();
    private ReporteAcademico actual;

    public void verReporte(ReporteAcademico r) {
        if (actual != null) atras.push(actual);
        actual = r;
        adelante.vaciar();
    }

    /** @return el reporte anterior o null si no hay. */
    public ReporteAcademico irAtras() {
        if (atras.estaVacia()) return null;
        adelante.push(actual);
        actual = atras.pop();
        return actual;
    }

    /** @return el reporte siguiente o null si no hay. */
    public ReporteAcademico irAdelante() {
        if (adelante.estaVacia()) return null;
        atras.push(actual);
        actual = adelante.pop();
        return actual;
    }

    public ReporteAcademico getActual() { return actual; }
}
