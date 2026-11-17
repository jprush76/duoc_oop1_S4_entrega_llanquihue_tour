package cl.llanquihuetour.data;

import cl.llanquihuetour.model.Tour;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Gestiona la carga de tours desde un archivo de texto.
 */
public class GestorDatos {

    public ArrayList<Tour> cargarTours(Path rutaArchivo) throws IOException {
        ArrayList<Tour> listaDeTours = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(rutaArchivo, StandardCharsets.UTF_8)) {
            String linea;
            int numLinea = 0;
            // Ejecutar mientras la línea tenga información
            while ((linea = reader.readLine()) != null) {
                numLinea++;
                if(linea.isBlank()) {
                    continue;
                }
                String[] campos = linea.split(";", -1);
                if(campos.length != 3) {
                    System.err.println("Error línea " + linea + ": Se omite linea debido a errores encontrados.");
                    continue;
                }

                try {
                    String nombre = campos[0].trim();
                    String duracion = campos[1].trim();
                    int precio = Integer.parseInt(campos[2].trim());

                    Tour tour = new Tour(nombre, duracion, precio);
                    listaDeTours.add(tour);

                } catch (IllegalArgumentException e) {
                    System.err.println("Error línea " + linea + ": " + e.getMessage());
                }
            }
        } catch (IOException ex) {
            System.err.println("Error al abrir el archivo " + rutaArchivo + ": " + ex.getMessage());
        }

        return listaDeTours;
    }
}
