import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

public class ColaImpresion {
    private Queue<String> filaDocumentos;

    public ColaImpresion() {
        filaDocumentos = new LinkedList<>();
    }

    // Método Encolar (Offer/Add): Poner un documento al final de la fila
    public void enviarDocumento(String nombreDoc) {
        filaDocumentos.offer(nombreDoc);
        System.out.println("Documento '" + nombreDoc + "' enviado a la fila.");
    }

    // Método Desencolar (Poll/Remove): Procesar e imprimir el primer documento
    public String procesarDocumento() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola de impresión está vacía.");
        }
        return filaDocumentos.poll();
    }

    // Método Frente (Peek): Ver qué documento va a salir primero sin sacarlo
    public String verSiguiente() {
        return filaDocumentos.peek();
    }

    // Método Esta Vacía (IsEmpty): Comprobar si hay impresiones pendientes
    public boolean estaVacia() {
        return filaDocumentos.isEmpty();
    }

    // Método Tamaño (Size): Saber cuántos archivos faltan por imprimirse
    public int totalPendientes() {
        return filaDocumentos.size();
    }

    public static void main(String[] args) {
        ColaImpresion impresora = new ColaImpresion();
        impresora.enviarDocumento("Reporte_Financiero.pdf");
        impresora.enviarDocumento("Contrato_Final.docx");

        System.out.println("Siguiente en la fila (peek): " + impresora.verSiguiente());
        System.out.println("Imprimiendo ahora (poll): " + impresora.procesarDocumento());
        System.out.println("Documentos en espera: " + impresora.totalPendientes());
    }
}
