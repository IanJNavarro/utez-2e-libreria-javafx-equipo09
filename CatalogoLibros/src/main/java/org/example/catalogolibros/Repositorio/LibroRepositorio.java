package org.example.catalogolibros.Repositorio;

import org.example.catalogolibros.Modelo.Libro;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestiona el almacenamiento y las operaciones principales del catalogo
 * Se encarga de que los cambios en la lista se actualicen en el archivo CSV
 */
public class LibroRepositorio {

    /** Lista donde se mantienen los libros cargados en memoria. */
    public ArrayList<Libro> listaLibros;

    /**
     * Inicializa el repositorio y carga los datos guardados
     */
    public LibroRepositorio() {
        listaLibros = new ArrayList<>();
        cargarDesdeArchivo();
    }

    // Crud

    /**
     * Añade un libro nuevo a la lista y actualiza el archivo
     * @param libro Objeto libro a registrar.
     */
    public void agregar(Libro libro) {
        listaLibros.add(libro);
        guardarEnArchivo();
    }

    /**
     * Busca un libro por su ID y reemplaza su información con los nuevos datos
     * @param libroEditado Libro con el ID a buscar y la información actualizada
     */
    public void actualizar(Libro libroEditado) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).idLibro.equals(libroEditado.idLibro)) {
                listaLibros.set(i, libroEditado);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Elimina el libro seleccionado y actualiza el archivo
     * @param libro El libro que se desea quitar del catálogo
     */
    public void eliminar(Libro libro) {
        listaLibros.remove(libro);
        guardarEnArchivo();
    }

    /**
     * Comprueba si un ID ya está registrado para evitar duplicados
     * @param id_libro El código identificador del libro
     * @return true si ya existe en la lista, o false
     */
    public boolean existeId(String id_libro) {
        for (Libro libro : listaLibros) {
            if (libro.idLibro.equalsIgnoreCase(id_libro)) {
                return true;
            }
        }
        return false;
    }

    // Lectura y escritura

    /**
     * Lee el archivo CSV y convierte cada línea en un objeto Libro.
     * Si no existe el archivo, simplemente inicia con la lista vacía.
     */
    private void cargarDesdeArchivo() {
        File archivo = new File("data/catalogo.csv");

        if (!archivo.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(archivo)) {
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
        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    /**
     * Escribe la lista de libros actual en el archivo "catalogo.csv".
     * Si la carpeta "data" no existe, la crea primero.
     */
    public void guardarEnArchivo() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new File("data/catalogo.csv"))) {
            for (Libro libro : listaLibros) {
                writer.println(
                        libro.idLibro + "|" +
                                libro.titulo  + "|" +
                                libro.autor   + "|" +
                                libro.fechaDePublicacion + "|" +
                                libro.genero  + "|" +
                                libro.disponible
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo CSV de reporte con formato de lectura sencillo
     * @return true si el proceso terminó correctamente
     */
    public boolean exportarReporte() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new File("data/reporte_catalogo.csv"))) {
            writer.println("ID Libro|Titulo|Autor|Fecha Publicacion|Genero|Disponible");

            for (Libro libro : listaLibros) {
                writer.println(
                        libro.idLibro + "|" +
                                libro.titulo  + "|" +
                                libro.autor   + "|" +
                                libro.fechaDePublicacion + "|" +
                                libro.genero  + "|" +
                                (libro.disponible ? "Si" : "No")
                );
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
            return false;
        }
    }
}