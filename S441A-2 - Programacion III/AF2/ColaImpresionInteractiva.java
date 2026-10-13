import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColaImpresionInteractiva {
    private Queue<String> filaDocumentos;

    public ColaImpresionInteractiva() {
        filaDocumentos = new LinkedList<>();
    }

    public void enviarDocumento(String nombreDoc) {
        filaDocumentos.offer(nombreDoc);
        System.out.println("Documento '" + nombreDoc + "' enviado a la fila.");
    }

    public String procesarDocumento() {
        if (estaVacia()) {
            System.out.println("Error: La cola de impresion esta vacia.");
            return null;
        }
        return filaDocumentos.poll();
    }

    public String verSiguiente() {
        return filaDocumentos.peek();
    }

    public boolean estaVacia() {
        return filaDocumentos.isEmpty();
    }

    public int totalPendientes() {
        return filaDocumentos.size();
    }

    public static void main(String[] args) {
        ColaImpresionInteractiva impresora = new ColaImpresionInteractiva();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- MENU DE COLA DE IMPRESION ---");
            System.out.println("1. Enviar un documento a la fila (Encolar)");
            System.out.println("2. Imprimir siguiente documento (Desencolar)");
            System.out.println("3. Ver que documento sigue (Peek)");
            System.out.println("4. Ver total de documentos en espera");
            System.out.println("5. Salir del programa");
            System.out.print("Seleccione una opcion: ");
            
            opcion = teclado.nextInt();
            teclado.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del documento (ej. Reporte.pdf): ");
                    String nombreDoc = teclado.nextLine();
                    impresora.enviarDocumento(nombreDoc);
                    break;

                case 2:
                    String procesado = impresora.procesarDocumento();
                    if (procesado != null) {
                        System.out.println("Imprimiendo ahora mismo: " + procesado);
                    }
                    break;

                case 3:
                    if (impresora.estaVacia()) {
                        System.out.println("No hay documentos en espera.");
                    } else {
                        System.out.println("El siguiente documento en salir sera: " + impresora.verSiguiente());
                    }
                    break;

                case 4:
                    System.out.println("Total de documentos pendientes por imprimir: " + impresora.totalPendientes());
                    break;

                case 5:
                    System.out.println("Saliendo del sistema de impresion...");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 5);

        teclado.close();
}

}
