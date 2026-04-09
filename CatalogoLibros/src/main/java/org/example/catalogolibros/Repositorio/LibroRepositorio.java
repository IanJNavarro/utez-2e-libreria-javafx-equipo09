package org.example.catalogolibros.Repositorio;


import org.example.catalogolibros.Modelo.Libro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LibroRepositorio {

    public ArrayList<Libro> listaLibros;

    public LibroRepositorio() {
        listaLibros = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // Crud

    public void agregar(Libro libro) {
        listaLibros.add(libro);
        guardarEnArchivo();
    }

    public void actualizar(Libro libroEditado) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).idLibro.equals(libroEditado.idLibro)) {
                listaLibros.set(i, libroEditado);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void eliminar(Libro libro) {
        listaLibros.remove(libro);
        guardarEnArchivo();
    }

    public boolean existeId(String id_libro) {
        for (Libro libro : listaLibros) {
            if (libro.idLibro.equalsIgnoreCase(id_libro)) {
                return true;
            }
        }
        return false;
    }

    // Leer archivo

    private void cargarDesdeArchivo() {
        File archivo = new File("data/catalogo.csv");

        // Si el archivo no existe aún, inicia con lista vacia
        if (!archivo.exists()) {
            return;
        }

        try {
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();

                if (linea.isEmpty()) continue;

                String[] partes = linea.split("\\|");

                if (partes.length == 6) {
                    String id_libro    = partes[0].trim();
                    String titulo      = partes[1].trim();
                    String autor       = partes[2].trim();
                    int fecha_pub      = Integer.parseInt(partes[3].trim());
                    String genero      = partes[4].trim();
                    boolean disponible = Boolean.parseBoolean(partes[5].trim());

                    listaLibros.add(new Libro(id_libro, titulo, autor, fecha_pub, genero, disponible));
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Dato invalido en el archivo: " + e.getMessage());
        }
    }

    // Escribir archivo

    public void guardarEnArchivo() {
        // Crea la carpeta data/ si no existe
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try {
            PrintWriter writer = new PrintWriter(new File("data/catalogo.csv"));

            for (Libro libro : listaLibros) {
                writer.println(
                        libro.idLibro + "|" +
                                libro.titulo     + "|" +
                                libro.autor      + "|" +
                                libro.fechaDePublicacion + "|" +
                                libro.genero     + "|" +
                                libro.disponible
                );
            }

            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Exportar reporte

    public boolean exportarReporte() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try {
            PrintWriter writer = new PrintWriter(new File("data/reporte_catalogo.csv"));

            // Encabezado
            writer.println("ID Libro|Titulo|Autor|Fecha Publicacion|Genero|Disponible");

            for (Libro libro : listaLibros) {
                writer.println(
                        libro.idLibro + "|" +
                                libro.titulo    + "|" +
                                libro.autor     + "|" +
                                libro.fechaDePublicacion + "|" +
                                libro.genero    + "|" +
                                (libro.disponible ? "Si" : "No")
                );
            }

            writer.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Error al exportar: " + e.getMessage());
            return false;
        }
    }
}