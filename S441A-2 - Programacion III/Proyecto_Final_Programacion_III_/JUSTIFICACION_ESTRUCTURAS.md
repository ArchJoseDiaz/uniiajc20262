# Justificación de estructuras de datos

| Funcionalidad | Estructura | Implementación | Por qué |
|---|---|---|---|
| Disponibilidad de aulas | `boolean[7][24]` | Propia (arreglo nativo) | Tamaño fijo conocido; acceso O(1) por (día, hora). |
| Distancias entre edificios | `int[N][N]` | Propia (arreglo nativo) | Matriz de adyacencia; Dijkstra O(N²) es simple y suficiente para pocos edificios. |
| Notas por semestre | `Double[10][20]` (+ `String[10][20]` con el código) | Propia (arreglo nativo) | Límite explícito de 10 semestres y 20 materias; `null` = sin nota. |
| Facultades | `Facultad[5]` | Propia (arreglo nativo) | Lista fija. |
| Pre-requisitos, historial, inscritos | `ListaEnlazada<T>` | **Propia** (nodos) | Altas al final O(1), recorrido secuencial; se muestra el funcionamiento interno. |
| Cola de espera, cola batch | `Cola<T>` | **Propia** (nodos, frente/fin) | FIFO con encolar/desencolar O(1); `eliminar` e `insertarEn` permiten restaurar la posición al deshacer. |
| Deshacer/rehacer, navegación de reportes | `Pila<T>` | **Propia** (nodos) | LIFO natural para historial de operaciones. |
| Índice de estudiantes | `HashMap<String, Estudiante>` | Java | Búsqueda por ID en O(1). |
| Aulas ordenadas | `TreeMap<String, Aula>` | Java | Orden por código sin reordenar manualmente. |

**Decisión:** listas, pilas y colas se implementaron manualmente (suma puntos en la rúbrica y demuestra el funcionamiento interno).
Los mapas usan las clases de Java, como indica el enunciado.

**Deshacer/rehacer:** patrón *Command*. Cada operación (`OpInscripcion`, `OpCancelacion`, `OpCambioHorario`,
`OpRegistroNota`, `OpEliminarEstudiante`) hereda de `OperacionDeshacer` y guarda el estado anterior
(nota previa, horario previo, quién fue promovido desde la cola y su posición original).
