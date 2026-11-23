package modelo;

/** Reporte generado para un estudiante; se apila en el navegador de reportes. */
public class ReporteAcademico {
    private final String titulo;
    private final String contenido;

    public ReporteAcademico(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }

    @Override
    public String toString() { return titulo + "\n" + contenido; }
}
