package estructuras;

import modelo.Edificio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Grafo no dirigido del campus con matriz de adyacencia int[N][N] (0 = sin conexión). */
public class GrafoCampus {
    private final int n;
    private final int[][] matriz;
    private final List<Edificio> edificios = new ArrayList<>();

    public GrafoCampus(int numEdificios) {
        this.n = numEdificios;
        this.matriz = new int[n][n];
    }

    public void agregarEdificio(Edificio e) {
        if (edificios.size() >= n) throw new IllegalStateException("El grafo ya tiene " + n + " edificios.");
        edificios.add(e);
    }

    public List<Edificio> getEdificios() { return edificios; }

    /** Busca un edificio por nombre (sin distinguir mayúsculas) o por su ID numérico. */
    public int buscarEdificio(String texto) {
        for (Edificio e : edificios) {
            if (e.getNombre().equalsIgnoreCase(texto) || String.valueOf(e.getId()).equals(texto)) return e.getId();
        }
        throw new IllegalArgumentException("No existe el edificio '" + texto + "'");
    }

    public void agregarConexion(int origen, int destino, int metros) {
        if (origen < 0 || origen >= n || destino < 0 || destino >= n)
            throw new IllegalArgumentException("ID de edificio fuera de rango (0-" + (n - 1) + ").");
        if (origen == destino) throw new IllegalArgumentException("Un edificio no se conecta consigo mismo.");
        if (metros <= 0) throw new IllegalArgumentException("La distancia debe ser mayor que 0.");
        matriz[origen][destino] = metros;
        matriz[destino][origen] = metros;
    }

    /** Dijkstra sobre la matriz. Devuelve el texto con el camino completo y la distancia total. */
    public String calcularRutaMasCortaDijkstra(int origen, int destino) {
        if (origen < 0 || origen >= n || destino < 0 || destino >= n)
            throw new IllegalArgumentException("ID de edificio fuera de rango (0-" + (n - 1) + ").");

        int[] dist = new int[n];
        int[] previo = new int[n];
        boolean[] visitado = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(previo, -1);
        dist[origen] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int v = 0; v < n; v++) {
                if (!visitado[v] && dist[v] != Integer.MAX_VALUE && (u == -1 || dist[v] < dist[u])) u = v;
            }
            if (u == -1) break;
            visitado[u] = true;
            for (int v = 0; v < n; v++) {
                if (matriz[u][v] > 0 && !visitado[v] && dist[u] + matriz[u][v] < dist[v]) {
                    dist[v] = dist[u] + matriz[u][v];
                    previo[v] = u;
                }
            }
        }

        if (dist[destino] == Integer.MAX_VALUE) return "No existe ruta entre los edificios seleccionados.";

        List<Integer> camino = new ArrayList<>();
        for (int a = destino; a != -1; a = previo[a]) camino.add(0, a);

        StringBuilder sb = new StringBuilder("Ruta mas corta:\n");
        sb.append(edificios.get(camino.get(0)).getNombre());
        for (int i = 1; i < camino.size(); i++) {
            sb.append(" -> ").append(edificios.get(camino.get(i)).getNombre())
              .append(" (").append(matriz[camino.get(i - 1)][camino.get(i)]).append("m)");
        }
        sb.append("\nDistancia TOTAL: ").append(dist[destino]).append(" metros");
        return sb.toString();
    }
}
