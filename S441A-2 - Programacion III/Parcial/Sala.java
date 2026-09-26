// Guarda cómo está construida cada sala del cine
public class Sala {
    private int numero;
    private boolean es3D; // true si es la Sala 3
    private int filasGeneral;         // Filas A a F
    private int sillasGeneralPorFila; // 12 sillas
    private int filasPreferencial;    // Filas G y H (solo salas 1 y 2)
    private int sillasPreferencialPorFila; // 9 sillas

    public Sala(int numero, boolean es3D, int filasGeneral, int sillasGeneralPorFila, int filasPreferencial, int sillasPreferencialPorFila) {
        this.numero = numero;
        this.es3D = es3D;
        this.filasGeneral = filasGeneral;
        this.sillasGeneralPorFila = sillasGeneralPorFila;
        this.filasPreferencial = filasPreferencial;
        this.sillasPreferencialPorFila = sillasPreferencialPorFila;
    }

    public int getNumero() { return numero; }
    public boolean isEs3D() { return es3D; }
    public int getFilasGeneral() { return filasGeneral; }
    public int getSillasGeneralPorFila() { return sillasGeneralPorFila; }
    public int getFilasPreferencial() { return filasPreferencial; }
    public int getSillasPreferencialPorFila() { return sillasPreferencialPorFila; }

    // Ayuda a saber si la sala tiene zona VIP para no crear matrices vacías de más
    public boolean tienePreferencial() {
        return filasPreferencial > 0;
    }
}
