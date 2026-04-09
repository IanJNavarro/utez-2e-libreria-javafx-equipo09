package org.example.catalogolibros.Services;



import org.example.catalogolibros.Modelo.Libro;
import org.example.catalogolibros.Repositorio.LibroRepositorio;
import java.time.LocalDate;
import java.util.ArrayList;

public class LibroServicio {

    public LibroRepositorio repositorio;

    public LibroServicio() {
        repositorio = new LibroRepositorio();
    }

    public ArrayList<Libro> obtenerTodos() {
        return repositorio.listaLibros;
    }

    // Crud

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

    public void actualizar(String id_libro, String titulo, String autor,
                           String fechaTexto, String genero, boolean disponible) throws Exception {

        validarCampos(id_libro, titulo, autor, fechaTexto, genero);

        int fecha_pub = Integer.parseInt(fechaTexto);
        Libro editado = new Libro(id_libro, titulo, autor, fecha_pub, genero, disponible);
        repositorio.actualizar(editado);
    }

    public void eliminar(Libro libro) {
        repositorio.eliminar(libro);
    }

    public boolean exportarReporte() {
        return repositorio.exportarReporte();
    }

    // Validaciones

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