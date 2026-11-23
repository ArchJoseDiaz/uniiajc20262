import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

import estructuras.GrafoCampus;
import estructuras.NavegadorReportes;
import estructuras.SistemaDeshacerRehacer;
import excepciones.ArchivoInvalidoException;
import excepciones.ColaDeEsperaVaciaException;
import excepciones.EstudianteNoEncontradoException;
import excepciones.HorarioConflictivoException;
import excepciones.InscripcionDuplicadaException;
import excepciones.PilaDeshacerVaciaException;
import excepciones.PreRequisitoNoAprobadoException;
import modelo.Aula;
import modelo.Edificio;
import modelo.Estudiante;
import modelo.Facultad;
import modelo.Horario;
import modelo.Materia;
import modelo.OpCambioHorario;
import modelo.OpCancelacion;
import modelo.OpEliminarEstudiante;
import modelo.OpRegistroNota;
import modelo.ReporteAcademico;
import util.ProcesadorBatch;
import util.ServicioInscripcion;

/** Clase principal: menú interactivo por consola con todas las funcionalidades (salida según el enunciado). */
public class Main {

    private static final String LINEA = "============================================================";

    // Mapas: índice rápido de estudiantes (HashMap) y aulas ordenadas por código (TreeMap)
    private static final Map<String, Estudiante> estudiantes = new HashMap<>();
    private static final TreeMap<String, Aula> aulas = new TreeMap<>();
    private static final Map<String, Materia> materias = new LinkedHashMap<>();
    private static final Facultad[] facultades = new Facultad[5]; // arreglo fijo
    private static GrafoCampus campus;
    private static final SistemaDeshacerRehacer deshacerRehacer = new SistemaDeshacerRehacer();
    private static final NavegadorReportes navegador = new NavegadorReportes();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));
        inicializarDatos();
        int opcion = 0;
        while (opcion != 22) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(leer("\nSeleccione una opcion: "));
                ejecutar(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un numero valido.");
            } catch (PreRequisitoNoAprobadoException | HorarioConflictivoException
                     | EstudianteNoEncontradoException | ColaDeEsperaVaciaException | PilaDeshacerVaciaException
                     | ArchivoInvalidoException | InscripcionDuplicadaException e) {
                System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: datos invalidos - " + e.getMessage());
            } catch (NoSuchElementException e) {
                break; // fin de la entrada estándar
            }
        }
        System.out.println("Hasta pronto!");
    }

    // ------------------------------------------------------------------ datos de prueba
    private static void inicializarDatos() {
        String[] nf = {"Ingenieria", "Ciencias de la Salud", "Ciencias Economicas", "Artes y Humanidades", "Derecho"};
        for (int i = 0; i < 5; i++) facultades[i] = new Facultad(i + 1, nf[i]);

        // IDs 2025xxx para no chocar con los ejemplos del enunciado (2024xxx), que se pueden registrar desde el menu
        String[][] est = {
            {"2025001", "Sofia Herrera", "sofia.herrera@universidad.edu", "3"},
            {"2025002", "Juan Perez", "juan.perez@universidad.edu", "2"},
            {"2025003", "Maria Lopez", "maria.lopez@universidad.edu", "2"},
            {"2025004", "Carlos Ruiz", "carlos.ruiz@universidad.edu", "1"},
            {"2025005", "Laura Diaz", "laura.diaz@universidad.edu", "1"},
            {"2025006", "Pablo Ortiz", "pablo.ortiz@universidad.edu", "1"}};
        for (String[] e : est)
            estudiantes.put(e[0], new Estudiante(e[0], e[1], e[2], Integer.parseInt(e[3]), facultades[0]));

        String[][] mat = {{"CALC101", "Calculo I", "4", "3"}, {"CALC102", "Calculo II", "4", "3"},
            {"FIS101", "Fisica I", "3", "30"}, {"FIS102", "Fisica II", "3", "30"},
            {"PROG101", "Programacion I", "3", "30"}, {"PROG201", "Programacion II", "3", "30"},
            {"MATDIS", "Matematicas Discretas", "3", "30"}};
        for (String[] m : mat)
            materias.put(m[0], new Materia(m[0], m[1], Integer.parseInt(m[2]), Integer.parseInt(m[3])));
        // Pre-requisitos de ejemplo (CALC102 queda libre para poder repetir el ejemplo 3 del enunciado)
        materias.get("FIS102").agregarPreRequisito(materias.get("FIS101"));
        materias.get("PROG201").agregarPreRequisito(materias.get("PROG101"));

        // Sofia ya aprobo Programacion I (permite inscribirla en PROG201)
        OpRegistroNota.ejecutar(estudiantes.get("2025001"), materias.get("PROG101"), 1, 4.2);

        for (String[] a : new String[][]{{"101", "30"}, {"102", "25"}, {"201", "40"}})
            aulas.put(a[0], new Aula(a[0], Integer.parseInt(a[1])));

        campus = new GrafoCampus(5);
        String[] ed = {"Ingenieria", "Biblioteca", "Cafeteria", "Rectoria", "Laboratorios"};
        for (int i = 0; i < ed.length; i++) campus.agregarEdificio(new Edificio(i, ed[i]));
        campus.agregarConexion(0, 1, 100);
        campus.agregarConexion(0, 2, 150);
        campus.agregarConexion(1, 3, 250);
        campus.agregarConexion(2, 3, 180);
        campus.agregarConexion(3, 4, 120);
    }

    // ------------------------------------------------------------------ menú (idéntico al enunciado)
    private static void mostrarMenu() {
        System.out.println(LINEA);
        System.out.println("PLANIFICACIÓN ACADÉMICA - SISTEMA UNIVERSITARIO");
        System.out.println(LINEA);
        System.out.println("=== GESTION DE ESTUDIANTES ===");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Buscar estudiante por ID");
        System.out.println("3. Listar todos los estudiantes");
        System.out.println("4. Eliminar estudiante");

        System.out.println("=== GESTION DE MATERIAS ===");
        System.out.println("5. Crear materia");
        System.out.println("6. Agregar pre-requisito");
        System.out.println("7. Mostrar pre-requisitos");
        System.out.println("8. Inscribir estudiante");
        System.out.println("9. Cancelar inscripcion");
        System.out.println("10. Mostrar cola de espera");

        System.out.println("=== GESTION DE HORARIOS ===");
        System.out.println("11. Reservar horario en aula");
        System.out.println("12. Liberar horario");
        System.out.println("13. Consultar disponibilidad");

        System.out.println("=== RUTAS ENTRE EDIFICIOS ===");
        System.out.println("14. Agregar conexion entre edificios");
        System.out.println("15. Calcular ruta mas corta");

        System.out.println("=== REPORTES ACADEMICOS ===");
        System.out.println("16. Registrar nota");
        System.out.println("17. Ver reporte academico");
        System.out.println("18. Navegador de reportes (atras/adelante)");

        System.out.println("=== SISTEMA DESHACER/REHACER ===");
        System.out.println("19. Deshacer última operacion");
        System.out.println("20. Rehacer ultima operacion");

        System.out.println("=== PROCESAMIENTO POR LOTES ===");
        System.out.println("21. Procesar archivo CSV");
        
        System.out.println("=== SALIR ===");
        System.out.println("22. Salir");
    }

    private static void ejecutar(int op) throws PreRequisitoNoAprobadoException, HorarioConflictivoException,
            EstudianteNoEncontradoException, ColaDeEsperaVaciaException, PilaDeshacerVaciaException,
            ArchivoInvalidoException, InscripcionDuplicadaException {
        switch (op) {
            case 1: registrarEstudiante(); break;
            case 2: buscarEstudianteMenu(); break;
            case 3: listarEstudiantes(); break;
            case 4: eliminarEstudiante(); break;
            case 5: crearMateria(); break;
            case 6: agregarPrerequisito(); break;
            case 7: mostrarPrerequisitos(); break;
            case 8: inscribir(); break;
            case 9: cancelarInscripcion(); break;
            case 10: mostrarCola(); break;
            case 11: reservarHorario(); break;
            case 12: liberarHorario(); break;
            case 13: consultarDisponibilidad(); break;
            case 14: agregarConexion(); break;
            case 15: calcularRuta(); break;
            case 16: registrarNota(); break;
            case 17: verReporte(); break;
            case 18: navegarReportes(); break;
            case 19: System.out.println("Operacion deshecha: " + deshacerRehacer.deshacer()); break;
            case 20: System.out.println("Operacion rehecha: " + deshacerRehacer.rehacer()); break;
            case 21: procesarBatch(); break;
            case 22: break;
            default: System.out.println("Opcion no valida.");
        }
    }

    // ------------------------------------------------------------------ estudiantes
    private static void registrarEstudiante() {
        System.out.println("--- REGISTRO DE ESTUDIANTE ---");
        String id = leer("ID: ");
        String nombre = leer("Nombre: ");
        String email = leer("Email: ");
        int sem = Integer.parseInt(leer("Semestre actual: "));
        if (id.isEmpty() || nombre.isEmpty()) throw new IllegalArgumentException("ID y nombre son obligatorios.");
        if (!email.contains("@")) throw new IllegalArgumentException("Email invalido.");
        if (sem < 1 || sem > 10) throw new IllegalArgumentException("Semestre fuera de rango (1-10).");
        if (estudiantes.containsKey(id)) throw new IllegalArgumentException("Ya existe un estudiante con ID " + id);
        estudiantes.put(id, new Estudiante(id, nombre, email, sem, null));
        System.out.println("Estudiante registrado exitosamente.");
    }

    private static Estudiante buscarEstudiante(String id) throws EstudianteNoEncontradoException {
        Estudiante e = estudiantes.get(id);
        if (e == null) throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        return e;
    }

    private static Materia buscarMateria(String codigo) {
        Materia m = materias.get(codigo.toUpperCase());
        if (m == null) throw new IllegalArgumentException("No existe la materia " + codigo);
        return m;
    }

    private static void buscarEstudianteMenu() throws EstudianteNoEncontradoException {
        System.out.println("--- BUSCAR ESTUDIANTE ---");
        Estudiante e = buscarEstudiante(leer("ID: "));
        System.out.println("Resultado encontrado:");
        System.out.println(e.mostrarInformacion());
    }

    private static void listarEstudiantes() {
        System.out.println("--- LISTA DE ESTUDIANTES (" + estudiantes.size() + ") ---");
        for (Estudiante e : new TreeMap<>(estudiantes).values())
            System.out.println(e.getId() + " | " + e.getNombre() + " | " + e.getEmail() + " | Semestre " + e.getSemestreActual());
    }

    private static void eliminarEstudiante() throws EstudianteNoEncontradoException {
        System.out.println("--- ELIMINAR ESTUDIANTE ---");
        Estudiante e = buscarEstudiante(leer("ID: "));
        deshacerRehacer.registrar(OpEliminarEstudiante.ejecutar(estudiantes, e, materias.values()));
        System.out.println("Estudiante eliminado: " + e.getNombre() + ". (Se puede deshacer)");
    }

    // ------------------------------------------------------------------ materias
    private static void crearMateria() {
        System.out.println("--- CREAR MATERIA ---");
        String cod = leer("Codigo: ").toUpperCase();
        String nom = leer("Nombre: ");
        int cupo = Integer.parseInt(leer("Cupos maximos: "));
        int cred = Integer.parseInt(leer("Creditos: "));
        if (cod.isEmpty() || nom.isEmpty()) throw new IllegalArgumentException("Codigo y nombre son obligatorios.");
        if (cred <= 0 || cupo <= 0) throw new IllegalArgumentException("Creditos y cupos deben ser positivos.");
        if (materias.containsKey(cod)) throw new IllegalArgumentException("Ya existe la materia " + cod);
        materias.put(cod, new Materia(cod, nom, cred, cupo));
        System.out.println("Materia creada exitosamente.");
    }

    private static void agregarPrerequisito() {
        System.out.println("--- AGREGAR PRE-REQUISITO ---");
        Materia m = buscarMateria(leer("Codigo materia: "));
        Materia req = buscarMateria(leer("Codigo pre-requisito: "));
        m.agregarPreRequisito(req);
        System.out.println("Pre-requisito agregado: " + req.getCodigo() + " -> " + m.getCodigo());
    }

    private static void mostrarPrerequisitos() {
        System.out.println("--- PRE-REQUISITOS ---");
        Materia m = buscarMateria(leer("Codigo materia: "));
        System.out.println("Materia: " + m.getCodigo() + " - " + m.getNombre());
        boolean alguno = false;
        for (Materia r : m.getPreRequisitos()) { System.out.println("- " + r.getCodigo() + " - " + r.getNombre()); alguno = true; }
        if (!alguno) System.out.println("(sin pre-requisitos)");
    }

    private static String primerNombre(Estudiante e) { return e.getNombre().split(" ")[0]; }

    private static void inscribir() throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException,
            InscripcionDuplicadaException {
        System.out.println("--- INSCRIBIR ESTUDIANTE ---");
        Estudiante e = buscarEstudiante(leer("ID: "));
        Materia m = buscarMateria(leer("Codigo materia: "));
        ServicioInscripcion.Resultado r = ServicioInscripcion.inscribir(e, m);
        deshacerRehacer.registrar(r.operacion);
        if (r.enEspera) {
            System.out.println(e.getNombre() + " \u2192 Materia llena. Agregada a COLA DE ESPERA");
        } else {
            System.out.println(e.getNombre() + " \u2192 Cupo disponible (cupos restantes: " + m.cuposRestantes() + ")");
            System.out.println("Inscripcion exitosa");
        }
    }

    private static void cancelarInscripcion() throws EstudianteNoEncontradoException {
        System.out.println("--- CANCELAR INSCRIPCION ---");
        Estudiante e = buscarEstudiante(leer("ID: "));
        Materia m = buscarMateria(leer("Codigo materia: "));
        OpCancelacion op = OpCancelacion.ejecutar(e, m);
        deshacerRehacer.registrar(op);
        System.out.println("--- Liberar cupo (cancelar inscripcion de " + e.getNombre() + ") ---");
        System.out.println("Cancelacion exitosa. Cupo liberado.");
        if (op.getPromovido() != null)
            System.out.println("Asignando cupo a " + op.getPromovido().getNombre() + " (primer estudiante en cola)");
    }

    private static void mostrarCola() throws ColaDeEsperaVaciaException {
        System.out.println("--- MOSTRAR COLA DE ESPERA ---");
        Materia m = buscarMateria(leer("Codigo materia: "));
        if (m.totalEnEspera() == 0)
            throw new ColaDeEsperaVaciaException("La cola de espera de " + m.getCodigo() + " esta vacia");
        int pos = 1;
        for (Estudiante e : m.getColaEspera()) System.out.println("Posicion " + pos++ + ": " + e.getNombre());
        System.out.println("Total en espera: " + m.totalEnEspera());
    }

    // ------------------------------------------------------------------ horarios
    private static Aula buscarAula(String codigo) {
        Aula a = aulas.get(codigo);
        if (a == null) throw new IllegalArgumentException("No existe el aula " + codigo + " (disponibles: " + aulas.keySet() + ")");
        return a;
    }

    private static String estadoHora(Aula aula, int dia, int hora) {
        return Horario.DIAS[dia] + " " + hora + ":00 \u2192 " + (aula.consultarDisponibilidad(dia, hora) ? "LIBRE" : "OCUPADO");
    }

    private static void reservarHorario() throws HorarioConflictivoException {
        System.out.println("--- RESERVAR HORARIO EN AULA ---");
        Aula aula = buscarAula(leer("Aula: "));
        int dia = Integer.parseInt(leer("Dia (1=Lunes): "));
        int hora = Integer.parseInt(leer("Hora: "));
        int dur = Integer.parseInt(leer("Duracion: "));

        // Se arma el detalle antes de reservar; solo se muestra si la reserva tiene exito
        StringBuilder detalle = new StringBuilder("Verificando disponibilidad...\n");
        for (int h = hora; h < hora + dur && h < 24; h++) detalle.append(estadoHora(aula, dia, h)).append("\n");

        deshacerRehacer.registrar(OpCambioHorario.ejecutar(null, aula, dia, hora, dur));
        System.out.print(detalle);
        System.out.println("Reserva exitosa.");
    }

    private static void liberarHorario() {
        System.out.println("--- LIBERAR HORARIO ---");
        Aula aula = buscarAula(leer("Aula: "));
        int dia = Integer.parseInt(leer("Dia: "));
        int hora = Integer.parseInt(leer("Hora: "));
        int dur = Integer.parseInt(leer("Duracion: "));
        aula.liberar(dia, hora, dur);
        System.out.println("Horario liberado.");
    }

    private static void consultarDisponibilidad() {
        System.out.println("--- CONSULTAR DISPONIBILIDAD ---");
        Aula aula = buscarAula(leer("Aula: "));
        int dia = Integer.parseInt(leer("Dia: "));
        int hora = Integer.parseInt(leer("Hora: "));
        System.out.println(estadoHora(aula, dia, hora));
    }

    // ------------------------------------------------------------------ rutas
    private static void mostrarEdificios() {
        System.out.println("Edificios registrados:");
        for (Edificio e : campus.getEdificios()) System.out.println(e);
    }

    private static void agregarConexion() {
        System.out.println("--- AGREGAR CONEXION ENTRE EDIFICIOS ---");
        mostrarEdificios();
        int o = campus.buscarEdificio(leer("Origen: "));
        int d = campus.buscarEdificio(leer("Destino: "));
        int m = Integer.parseInt(leer("Distancia (metros): "));
        campus.agregarConexion(o, d, m);
        System.out.println("Conexion agregada exitosamente.");
    }

    private static void calcularRuta() {
        System.out.println("--- CALCULAR RUTA MAS CORTA ---");
        mostrarEdificios();
        int o = campus.buscarEdificio(leer("Origen: "));
        int d = campus.buscarEdificio(leer("Destino: "));
        System.out.println("--- RESULTADO ---");
        System.out.println(campus.calcularRutaMasCortaDijkstra(o, d));
    }

    // ------------------------------------------------------------------ reportes
    private static void registrarNota() throws EstudianteNoEncontradoException {
        System.out.println("--- REGISTRAR NOTA ---");
        Estudiante e = buscarEstudiante(leer("ID: "));
        Materia m = buscarMateria(leer("Codigo materia: "));
        int sem = Integer.parseInt(leer("Semestre (1-10): "));
        double nota = Double.parseDouble(leer("Nota (0.0 - 5.0): "));
        deshacerRehacer.registrar(OpRegistroNota.ejecutar(e, m, sem, nota));
        System.out.println("Nota registrada: " + m.getCodigo() + " = " + nota);
    }

    private static void verReporte() throws EstudianteNoEncontradoException {
        Estudiante e = buscarEstudiante(leer("ID: "));
        ReporteAcademico r = new ReporteAcademico("--- REPORTE ACADEMICO ---", e.generarReporte());
        navegador.verReporte(r);
        System.out.println(r);
    }

    private static void navegarReportes() {
        System.out.println("--- NAVEGADOR DE REPORTES ---");
        if (navegador.getActual() == null) { System.out.println("Aun no ha visto ningun reporte (use la opcion 17)."); return; }
        while (true) {
            String s = leer("1. Atras | 2. Adelante | 0. Volver: ");
            if (s.equals("0")) return;
            if (!s.equals("1") && !s.equals("2")) { System.out.println("Opcion no valida."); continue; }
            ReporteAcademico r = s.equals("1") ? navegador.irAtras() : navegador.irAdelante();
            System.out.println(r == null ? "No hay mas reportes en esa direccion." : r.toString());
        }
    }

    // ------------------------------------------------------------------ batch
    private static void procesarBatch() throws ArchivoInvalidoException {
        String ruta = leer("Archivo CSV (ENTER = inscripciones.csv): ");
        if (ruta.isEmpty()) ruta = "inscripciones.csv";
        String resultado = ProcesadorBatch.procesar(ruta, estudiantes, materias);
        System.out.println("--- PROCESAMIENTO MASIVO (BATCH) ---");
        System.out.println("Archivo: " + ruta);
        System.out.println(resultado);
        deshacerRehacer.limpiar(); // el batch no es reversible: se reinicia el historial para evitar estados inconsistentes
    }

    private static String leer(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }
}
