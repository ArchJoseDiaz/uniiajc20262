import java.util.LinkedList;

public class Bicola {
    private LinkedList<Integer> lista;

    public Bicola() {
        lista = new LinkedList<>();
    }

    // Insertar por el frente
    public void ponerFrente(int elemento) {
        lista.addFirst(elemento);
    }

    // Insertar por el final (Como una cola normal)
    public void ponerFinal(int elemento) {
        lista.addLast(elemento);
    }

    // Quitar por el frente (Como una cola normal)
    public Integer quitarFrente() {
        if (lista.isEmpty()) return null;
        return lista.removeFirst();
    }

    // Quitar por el final
    public Integer quitarFinal() {
        if (lista.isEmpty()) return null;
        return lista.removeLast();
    }
}
