package modelo;

import estructuras.ListaEnlazada;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Estudiante. Las notas viven en un arreglo nativo Double[10][20] (10 semestres x 20 materias);
 * un arreglo paralelo String[10][20] guarda el código de la materia de cada casilla.
 */
public class Estudiante extends Persona {
    public static final double NOTA_APROBATORIA = 3.0;

    private int semestreActual;
    private final Facultad facultad;
    private final Double[][] notas = new Double[10][20];
    private final String[][] codigos = new String[10][20];
    private final ListaEnlazada<Materia> historialMaterias = new ListaEnlazada<>();

    public Estudiante(String id, String nombre, String email, int semestreActual, Facultad facultad) {
        super(id, nombre, email);
        this.semestreActual = semestreActual;
        this.facultad = facultad;
    }

    public int getSemestreActual() { return semestreActual; }
    public void setSemestreActual(int s) { this.semestreActual = s; }
    public Facultad getFacultad() { return facultad; }
    public Iterable<Materia> getHistorialMaterias() { return historialMaterias; }

    public void agregarMateriaHistorial(Materia m) {
        if (!historialMaterias.contiene(m)) historialMaterias.agregarAlFinal(m);
    }

    public void quitarMateriaHistorial(Materia m) { historialMaterias.eliminar(m); }

    /** Registra (o reemplaza) la nota de una materia en un semestre (1-10). */
    public void registrarNota(int semestre, String codigoMateria, double nota) {
        validarSemestre(semestre);
        if (nota < 0.0 || nota > 5.0) throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        int fila = semestre - 1;
        int libre = -1;
        for (int j = 0; j < 20; j++) {
            if (codigoMateria.equals(codigos[fila][j])) { notas[fila][j] = nota; return; }
            if (libre == -1 && codigos[fila][j] == null) libre = j;
        }
        if (libre == -1) throw new IllegalArgumentException("El semestre " + semestre + " ya tiene 20 materias.");
        codigos[fila][libre] = codigoMateria;
        notas[fila][libre] = nota;
    }

    /** @return la nota registrada o null si no existe. */
    public Double obtenerNota(int semestre, String codigoMateria) {
        validarSemestre(semestre);
        for (int j = 0; j < 20; j++)
            if (codigoMateria.equals(codigos[semestre - 1][j])) return notas[semestre - 1][j];
        return null;
    }

    public void eliminarNota(int semestre, String codigoMateria) {
        validarSemestre(semestre);
        for (int j = 0; j < 20; j++) {
            if (codigoMateria.equals(codigos[semestre - 1][j])) {
                codigos[semestre - 1][j] = null;
                notas[semestre - 1][j] = null;
            }
        }
    }

    /** @return true si en algún semestre tiene nota >= 3.0 en esa materia. */
    public boolean haAprobado(String codigoMateria) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                if (codigoMateria.equals(codigos[i][j]) && notas[i][j] >= NOTA_APROBATORIA) return true;
        return false;
    }

    public double calcularPromedioSemestre(int semestre) {
        validarSemestre(semestre);
        double suma = 0;
        int n = 0;
        for (int j = 0; j < 20; j++) {
            if (notas[semestre - 1][j] != null) { suma += notas[semestre - 1][j]; n++; }
        }
        return n == 0 ? 0.0 : suma / n;
    }

    public double calcularPromedioAcumulado() {
        double suma = 0;
        int n = 0;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                if (notas[i][j] != null) { suma += notas[i][j]; n++; }
        return n == 0 ? 0.0 : suma / n;
    }

    public int contarAprobadas() { return contar(true); }
    public int contarReprobadas() { return contar(false); }

    private int contar(boolean aprobadas) {
        int c = 0;
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                if (notas[i][j] != null && (notas[i][j] >= NOTA_APROBATORIA) == aprobadas) c++;
        return c;
    }

    /** Materias con nota menor a 3.0 (escala 0-5). */
    public List<String> obtenerMateriasReprobadas() {
        List<String> r = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                if (notas[i][j] != null && notas[i][j] < NOTA_APROBATORIA)
                    r.add("Semestre " + (i + 1) + " - " + codigos[i][j] + ": " + fmt(notas[i][j], 1));
        return r;
    }

    /** Reporte con el formato del enunciado: notas por semestre, promedios y resumen. */
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiante: ").append(getNombre()).append(" (ID: ").append(getId()).append(")\n");
        boolean alguna = false;
        for (int i = 0; i < 10; i++) {
            boolean hay = false;
            for (int j = 0; j < 20; j++) if (notas[i][j] != null) { hay = true; break; }
            if (!hay) continue;
            alguna = true;
            sb.append("Semestre ").append(i + 1).append(":\n");
            for (int j = 0; j < 20; j++)
                if (notas[i][j] != null) sb.append(codigos[i][j]).append(": ").append(fmt(notas[i][j], 1)).append("\n");
            sb.append("Promedio: ").append(fmt(calcularPromedioSemestre(i + 1), 2)).append("\n");
        }
        if (!alguna) sb.append("(sin notas registradas)\n");
        sb.append("=== RESUMEN ===\n");
        sb.append("Promedio acumulado: ").append(fmt(calcularPromedioAcumulado(), 2)).append("\n");
        sb.append("Materias aprobadas: ").append(contarAprobadas()).append("\n");
        sb.append("Materias reprobadas: ").append(contarReprobadas());
        for (String r : obtenerMateriasReprobadas()) sb.append("\n  - ").append(r);
        return sb.toString();
    }

    private static String fmt(double v, int dec) { return String.format(Locale.US, "%." + dec + "f", v); }

    private void validarSemestre(int s) {
        if (s < 1 || s > 10) throw new IllegalArgumentException("Semestre fuera de rango (1-10).");
    }

    /** Sobrescritura del método abstracto de Persona (formato del ejemplo 1 del enunciado). */
    @Override
    public String mostrarInformacion() {
        boolean sinNotas = contarAprobadas() + contarReprobadas() == 0;
        return "ID: " + getId() + "\nNombre: " + getNombre() + "\nEmail: " + getEmail()
                + "\nSemestre: " + semestreActual
                + "\nPromedio acumulado: " + (sinNotas ? "0.0 (sin notas aun)" : fmt(calcularPromedioAcumulado(), 2));
    }
}
