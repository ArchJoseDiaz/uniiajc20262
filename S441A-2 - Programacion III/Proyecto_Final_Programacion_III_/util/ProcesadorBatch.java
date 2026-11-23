package util;

import estructuras.Cola;
import excepciones.ArchivoInvalidoException;
import excepciones.InscripcionDuplicadaException;
import excepciones.PreRequisitoNoAprobadoException;
import modelo.Estudiante;
import modelo.Materia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/** Carga masiva desde CSV (idEstudiante,codigoMateria): se encola todo y luego se procesa en orden. */
public class ProcesadorBatch {

    private static class Solicitud {
        final String idEstudiante;
        final String codigoMateria;
        final String errorFormato; // != null si la línea era inválida

        Solicitud(String id, String codigo, String errorFormato) {
            this.idEstudiante = id;
            this.codigoMateria = codigo;
            this.errorFormato = errorFormato;
        }
    }

    private ProcesadorBatch() { }

    public static String procesar(String ruta, Map<String, Estudiante> estudiantes, Map<String, Materia> materias)
            throws ArchivoInvalidoException {
        Cola<Solicitud> cola = new Cola<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int n = 0;
            while ((linea = br.readLine()) != null) {
                n++;
                if (linea.trim().isEmpty() || linea.toLowerCase().startsWith("idestudiante")) continue;
                String[] p = linea.split(",");
                if (p.length != 2 || p[0].trim().isEmpty() || p[1].trim().isEmpty()) {
                    cola.encolar(new Solicitud(null, null, "línea " + n + " con formato inválido"));
                } else {
                    cola.encolar(new Solicitud(p[0].trim(), p[1].trim().toUpperCase(), null));
                }
            }
        } catch (IOException e) {
            throw new ArchivoInvalidoException("No se pudo leer el archivo '" + ruta + "': " + e.getMessage());
        }
        if (cola.estaVacia()) throw new ArchivoInvalidoException("El archivo no contiene solicitudes.");

        int total = cola.tamano();
        int exitosas = 0, enEspera = 0, fallidas = 0, i = 0;
        StringBuilder motivos = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("Se encolaron ").append(total).append(" solicitudes.\nProcesando cola...\n");

        while (!cola.estaVacia()) {
            Solicitud s = cola.desencolar();
            i++;
            String prefijo = "[" + i + "/" + total + "] ";
            String cabecera = s.errorFormato != null ? prefijo
                    : prefijo + s.idEstudiante + " \u2192 " + s.codigoMateria + " \u2192 ";
            String motivo;
            if (s.errorFormato != null) {
                motivo = s.errorFormato;
            } else {
                Estudiante e = estudiantes.get(s.idEstudiante);
                Materia m = materias.get(s.codigoMateria);
                if (e == null) {
                    motivo = "el estudiante no existe";
                } else if (m == null) {
                    motivo = "la materia no existe";
                } else {
                    try {
                        ServicioInscripcion.Resultado r = ServicioInscripcion.inscribir(e, m);
                        if (r.enEspera) {
                            sb.append(cabecera).append("En cola de espera (posicion ").append(r.posicionCola).append(")\n");
                            enEspera++;
                        } else {
                            sb.append(cabecera).append("Exitosa\n");
                            exitosas++;
                        }
                        continue;
                    } catch (PreRequisitoNoAprobadoException | InscripcionDuplicadaException ex) {
                        motivo = ex.getMessage();
                    }
                }
            }
            sb.append(cabecera).append("Fallida\n");
            fallidas++;
            motivos.append("  ").append(prefijo).append(motivo).append("\n");
        }
        sb.append("=== RESUMEN ===\nExitosas: ").append(exitosas);
        if (enEspera > 0) sb.append("\nEn cola de espera: ").append(enEspera);
        sb.append("\nFallidas: ").append(fallidas);
        if (fallidas > 0) sb.append("\nMotivos de las fallidas:\n").append(motivos.toString().stripTrailing());
        return sb.toString();
    }
}
