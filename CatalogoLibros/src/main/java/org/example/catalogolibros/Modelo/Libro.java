package org.example.catalogolibros.Modelo;

/**
 * Representa la entidad Libro dentro del sistema.
 * Esta clase sirve como molde para los objetos que se guardan en el catálogo
 * y contiene toda su información básica.
 */
public class Libro {

    /** Identificador único del libro (ISBN/ID) */
    public String idLibro;

    /** Título de la obra */
    public String titulo;

    /** Nombre del autor o autores */
    public String autor;

    /** Año en el que fue publicado el libro */
    public int fechaDePublicacion;

    /** Categoria o genero (ej. Novela, Terror, Ciencia Ficción) */
    public String genero;

    /** Estado actual del libro: true si se puede prestar, false si no */
    public boolean disponible;

    /**
     * Constructor completo para crear una instancia de Libro con todos sus datos.
     */
    public Libro(String idLibro, String titulo, String autor, int fechaDePublicacion, String genero, boolean disponible) {
        this.idLibro = idLibro;
        this.titulo     = titulo;
        this.autor      = autor;
        this.fechaDePublicacion = fechaDePublicacion;
        this.genero     = genero;
        this.disponible = disponible;
    }

    // Metodos

    public String getIdLibro()  { return idLibro; }

    public String getTitulo()    { return titulo; }

    public String getAutor()     { return autor; }

    public int getFechaDePublicacion() { return fechaDePublicacion; }

    public String getGenero()    { return genero; }

    /** @return true si el libro está disponible */
    public boolean isDisponible()  { return disponible; }

    /** Método duplicado para obtener disponibilidad */
    public boolean getDisponible() { return disponible; }
}