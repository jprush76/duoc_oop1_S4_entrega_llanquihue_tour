package cl.llanquihuetour.ui;

import cl.llanquihuetour.data.GestorDatos;
import cl.llanquihuetour.model.Tour;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Path ruta = Path.of("src/main/resources", "tours.txt");
        GestorDatos gd = new GestorDatos();

        try {
            ArrayList<Tour> tours = gd.cargarTours(ruta);
            System.out.println("\n------ LISTADO DE TOURS ------");
            System.out.println("TOURS EN LINEA: " + tours.size());

            System.out.println("\nA. TODOS LOS TOURS");
            for (Tour tour : tours) {
                System.out.println(tour);
            }

            // FILTRO: se imprimen solo los tours con precio > a $70.000
            System.out.println("\nB. TOURS CON PRECIOS x PERSONA MAYOR A 70000");
            boolean toursCaros = false;
            for (Tour tour : tours) {
                if (tour.getPrecio() > 70000) {
                    System.out.println(tour);
                    toursCaros = true;
                }
            }
            if (!toursCaros) {
                System.out.println("No existen tours con precios");
                System.out.println("superior a 70000 por persona.");
            }

        } catch (IOException e) {
            System.err.println("Error al abrir el archivo " + ruta.toAbsolutePath());
            System.err.println("Detalle: " + e.getMessage());
        }
    }
}