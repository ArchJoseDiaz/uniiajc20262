package modelo;

import estructuras.Cola;
import estructuras.ListaEnlazada;
import excepciones.ColaDeEsperaVaciaException;
import excepciones.CupoLlenoException;

/** Materia: pre-requisitos (lista enlazada), inscritos (lista enlazada) y cola de espera (cola). */
public class Materia {
    private final String codigo;
    private final String nombre;
    private final int creditos;
    private final int cupoMaximo;
    private final ListaEnlazada<Materia> preRequisitos = new ListaEnlazada<>();
    private final ListaEnlazada<Estudiante> inscritos = new ListaEnlazada<>();
    private final Cola<Estudiante> colaEspera = new Cola<>();
    private Horario horario; // puede ser null

    public Materia(String codigo, String nombre, int creditos, int cupoMaximo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCreditos() { return creditos; }
    public int getCupoMaximo() { return cupoMaximo; }
    public Horario getHorario() { return horario; }
    public void setHorario(Horario horario) { this.horario = horario; }
    public Iterable<Materia> getPreRequisitos() { return preRequisitos; }
    public Iterable<Estudiante> getInscritos() { return inscritos; }
    public Iterable<Estudiante> getColaEspera() { return colaEspera; }
    public int totalInscritos() { return inscritos.tamano(); }
    public int totalEnEspera() { return colaEspera.tamano(); }
    public int cuposRestantes() { return cupoMaximo - inscritos.tamano(); }

    public void agregarPreRequisito(Materia m) {
        if (m.equals(this)) throw new IllegalArgumentException("Una materia no puede ser pre-requisito de sí misma.");
        if (preRequisitos.contiene(m)) throw new IllegalArgumentException(m.codigo + " ya es pre-requisito de " + codigo);
        preRequisitos.agregarAlFinal(m);
    }

    /** @throws CupoLlenoException si ya no quedan cupos. */
    public void verificarCupo() throws CupoLlenoException {
        if (inscritos.tamano() >= cupoMaximo)
            throw new CupoLlenoException("Materia llena: " + codigo + " (" + cupoMaximo + "/" + cupoMaximo + ")");
    }

    public boolean estaInscrito(Estudiante e) { return inscritos.contiene(e); }
    public boolean estaEnCola(Estudiante e) { return colaEspera.contiene(e); }

    public void agregarInscrito(Estudiante e) { inscritos.agregarAlFinal(e); }
    public boolean retirarInscrito(Estudiante e) { return inscritos.eliminar(e); }
    public void encolar(Estudiante e) { colaEspera.encolar(e); }
    public void encolarAlFrente(Estudiante e) { colaEspera.encolarAlFrente(e); }
    public boolean retirarDeCola(Estudiante e) { return colaEspera.eliminar(e); }
    public int posicionEnCola(Estudiante e) { return colaEspera.posicionDe(e); }
    public void insertarEnCola(int pos, Estudiante e) { colaEspera.insertarEn(pos, e); }

    /** Pasa el primer estudiante de la cola a inscritos. */
    public Estudiante promoverSiguiente() throws ColaDeEsperaVaciaException {
        if (colaEspera.estaVacia())
            throw new ColaDeEsperaVaciaException("La cola de espera de " + codigo + " está vacía");
        Estudiante siguiente = colaEspera.desencolar();
        inscritos.agregarAlFinal(siguiente);
        return siguiente;
    }

    @Override
    public boolean equals(Object o) { return o instanceof Materia && codigo.equals(((Materia) o).codigo); }

    @Override
    public int hashCode() { return codigo.hashCode(); }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + creditos + " cr) [Inscritos: " + inscritos.tamano() + "/"
                + cupoMaximo + ", En espera: " + colaEspera.tamano() + "]"
                + (horario != null ? " | " + horario : "");
    }
}
