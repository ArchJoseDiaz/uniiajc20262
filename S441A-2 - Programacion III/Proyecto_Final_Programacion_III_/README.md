# Sistema de Gestión Académica - Programación III

## Compilar y ejecutar (Java 17+)
```
javac -encoding UTF-8 -d out $(find . -name "*.java")
java -Dfile.encoding=UTF-8 -cp out Main
```
En Windows (PowerShell): `javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java).FullName`

## Estructura
- `modelo/` entidades y operaciones deshacibles · `estructuras/` lista, pila, cola, grafo, deshacer · `excepciones/` · `util/` batch e inscripción
- `inscripciones.csv` ejemplo para la opción 21 · `JUSTIFICACION_ESTRUCTURAS.md`

## Datos de prueba
Estudiantes 2025001-2025006 (los IDs 2024xxx de los ejemplos del enunciado quedan libres para registrarlos desde el menu), materias CALC101 (3 cupos), CALC102, FIS101, FIS102 (pre-req FIS101), PROG101, PROG201 (pre-req PROG101), MATDIS; aulas 101, 102, 201; 5 edificios.

Si la consola muestra mal las flechas o tildes en Windows, ejecute antes `chcp 65001`.
