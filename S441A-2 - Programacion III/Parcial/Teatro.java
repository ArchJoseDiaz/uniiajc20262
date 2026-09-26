// Controla todo el cine: guarda las películas, las salas y el horario de funciones
public class Teatro {
    private Pelicula[] repertorio;
    private int cantidadPeliculas;
    private Sala[] salas;
    
    // Matriz de funciones: [sala][franja] para saber qué película hay en cada horario
    private Funcion[][] funciones; 

    public Teatro() {
        this.repertorio = new Pelicula[10]; // Espacio para 10 películas
        this.cantidadPeliculas = 0;

        // Configuramos las 3 salas que pide el proyecto
        this.salas = new Sala[3];
        this.salas[0] = new Sala(1, false, 6, 12, 2, 9); // Sala 1 normal
        this.salas[1] = new Sala(2, false, 6, 12, 2, 9); // Sala 2 normal
        this.salas[2] = new Sala(3, true, 6, 12, 0, 0);  // Sala 3 (3D)

        // Matriz de 3 salas x 3 franjas de horario
        this.funciones = new Funcion[3][3];
    }

    public boolean registrarPelicula(String nombre, String idioma, String tipo, int duracion) {
        if (cantidadPeliculas >= repertorio.length) {
            System.out.println("❌ El repertorio está lleno.");
            return false;
        }
        repertorio[cantidadPeliculas] = new Pelicula(nombre, idioma, tipo, duracion);
        cantidadPeliculas++;
        System.out.println(" Película registrada exitosamente.");
        return true;
    }

    public void listarPeliculas() {
        System.out.println("\n=== REPERTORIO DE PELÍCULAS REGISTRADAS ===");
        if (cantidadPeliculas == 0) {
            System.out.println("No hay películas registradas en el sistema.");
            return;
        }
        for (int i = 0; i < cantidadPeliculas; i++) {
            System.out.println((i + 1) + ". " + repertorio[i].toString());
        }
    }

    // Pone una película en una sala y un horario específico
    public boolean asignarFuncion(int numSala, int franja, int idxPelicula) {
        if (numSala < 1 || numSala > 3 || franja < 1 || franja > 3) {
            System.out.println(" Sala o franja horaria inválida.");
            return false;
        }
        if (idxPelicula < 0 || idxPelicula >= cantidadPeliculas) {
            System.out.println(" Selección de película inválida.");
            return false;
        }

        Sala salaSeleccionada = salas[numSala - 1];
        Pelicula peliculaSeleccionada = repertorio[idxPelicula];

        // Validamos que las películas 3D solo vayan en la Sala 3
        if (salaSeleccionada.isEs3D() && !peliculaSeleccionada.getTipo().equalsIgnoreCase("3D")) {
            System.out.println(" RESTRICCIÓN: La Sala 3 sólo permite proyectar películas en 3D.");
            return false;
        }
        if (!salaSeleccionada.isEs3D() && peliculaSeleccionada.getTipo().equalsIgnoreCase("3D")) {
            System.out.println(" RESTRICCIÓN: Las Salas 1 y 2 NO pueden proyectar películas en 3D.");
            return false;
        }

        // Validamos que la sala no esté ocupada en ese mismo horario
        if (funciones[numSala - 1][franja - 1] != null) {
            System.out.println(" SOLAPAMIENTO: La Sala " + numSala + " ya tiene asignada una película en esa franja.");
            return false;
        }

        // Guardamos la función en la matriz
        funciones[numSala - 1][franja - 1] = new Funcion(peliculaSeleccionada, salaSeleccionada, franja);
        System.out.println(" Función asignada con éxito a la Sala " + numSala + " en la franja " + franja + ".");
        return true;
    }

    public Funcion getFuncion(int numSala, int franja) {
        if (numSala >= 1 && numSala <= 3 && franja >= 1 && franja <= 3) {
            return funciones[numSala - 1][franja - 1];
        }
        return null;
    }

    public int getCantidadPeliculas() {
        return cantidadPeliculas;
    }
}
