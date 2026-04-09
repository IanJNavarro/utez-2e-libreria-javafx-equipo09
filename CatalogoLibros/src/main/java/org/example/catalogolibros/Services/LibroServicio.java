package org.example.catalogolibros.Services;

import org.example.catalogolibros.Modelo.Libro;
import org.example.catalogolibros.Repositorio.LibroRepositorio;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Aquí se aplica la lógica de negocio y las validaciones
 */
public class LibroServicio {

    /** Enlace directo con el repositorio para el manejo de datos */
    public LibroRepositorio repositorio;

    /**
     * Inicializa el servicio conectándolo con su repo correspondiente
     */
    public LibroServicio() {
        repositorio = new LibroRepositorio();
    }

    /**
     * Devuelve la lista completa de libros registrados
     * @return ArrayList con todos los libros en memoria
     */
    public ArrayList<Libro> obtenerTodos() {
        return repositorio.listaLibros;
    }

    /**
     * Procesa la creación de un nuevo libro. Valida los datos
     * @throws Exception Si algún campo falla las reglas de validación o el ID ya existe
     */
    public void agregar(String id_libro, String titulo, String autor,
                        String fechaTexto, String genero, boolean disponible) throws Exception {

        validarCampos(id_libro, titulo, autor, fechaTexto, genero);

        if (repositorio.existeId(id_libro)) {
            throw new Exception("Ya existe un libro con ese ID.");
        }

        int fecha_pub = Integer.parseInt(fechaTexto);
        Libro nuevo = new Libro(id_libro, titulo, autor, fecha_pub, genero, disponible);
        repositorio.agregar(nuevo);
    }

    /**
     * Gestiona la actualización de un libro existente tras validar los nuevos datos
     * @throws Exception Si los datos editados no cumplen con las reglas establecida
     */
    public void actualizar(String id_libro, String titulo, String autor,
                           String fechaTexto, String genero, boolean disponible) throws Exception {

        validarCampos(id_libro, titulo, autor, fechaTexto, genero);

        int fecha_pub = Integer.parseInt(fechaTexto);
        Libro editado = new Libro(id_libro, titulo, autor, fecha_pub, genero, disponible);
        repositorio.actualizar(editado);
    }

    /**
     * Solicita al repositorio la eliminación de un libro específico
     * @param libro El objeto libro que se desea borrar
     */
    public void eliminar(Libro libro) {
        repositorio.eliminar(libro);
    }

    /**
     * Llama a la función de exportación del repositorio para generar el archivo de reporte
     * @return true si el archivo se creó correctamente
     */
    public boolean exportarReporte() {
        return repositorio.exportarReporte();
    }

    // Validaciones

    /**
     * Revisa que todos los datos del libro cumplan con lo solicitado
     * campos no vacíos, longitud de texto coherente y fechas dentro de un rango lógico.
     * @throws Exception Con un mensaje del error encontrado.
     */
    private void validarCampos(String id_libro, String titulo, String autor,
                               String fechaTexto, String genero) throws Exception {

        if (id_libro.isBlank() || titulo.isBlank() || autor.isBlank() ||
                fechaTexto.isBlank() || genero.isBlank()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        if (titulo.length() < 3) {
            throw new Exception("El titulo debe tener minimo 3 caracteres.");
        }

        if (autor.length() < 3) {
            throw new Exception("El autor debe tener minimo 3 caracteres.");
        }

        int fecha_pub;
        try {
            fecha_pub = Integer.parseInt(fechaTexto);
        } catch (NumberFormatException e) {
            throw new Exception("La fecha de publicacion debe ser un numero.");
        }

        int anioActual = LocalDate.now().getYear();
        if (fecha_pub < 1500 || fecha_pub > anioActual) {
            throw new Exception("La fecha debe estar entre 1500 y " + anioActual + ".");
        }
    }
}