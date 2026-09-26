// Clase sencilla para guardar los datos de cada película
public class Pelicula {
    private String nombre;
    private String idioma;
    private String tipo; // Guarda si es "35mm" o "3D"
    private int duracionMinutos;

    public Pelicula(String nombre, String idioma, String tipo, int duracionMinutos) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.tipo = tipo;
        this.duracionMinutos = duracionMinutos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    // Sirve para revisar después si la película es 3D y se puede poner en la Sala 3
    public String getTipo() {
        return tipo;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    // Para mostrar los datos de la película fácil en texto
    @Override
    public String toString() {
        return nombre + " | Idioma: " + idioma + " | Tipo: " + tipo + " | Duración: " + duracionMinutos + " min";
    }
}
