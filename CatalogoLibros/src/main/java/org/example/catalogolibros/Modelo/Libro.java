package org.example.catalogolibros.Modelo;

public class Libro {

    public String idLibro;
    public String titulo;
    public String autor;
    public int fechaDePublicacion;
    public String genero;
    public boolean disponible;

    public Libro(String idLibro, String titulo, String autor, int fechaDePublicacion, String genero, boolean disponible) {
        this.idLibro = idLibro;
        this.titulo     = titulo;
        this.autor      = autor;
        this.fechaDePublicacion = fechaDePublicacion;
        this.genero     = genero;
        this.disponible = disponible;
    }

    public String getIdLibro()  { return idLibro; }
    public String getTitulo()    { return titulo; }
    public String getAutor()     { return autor; }
    public int getFechaDePublicacion() { return fechaDePublicacion; }
    public String getGenero()    { return genero; }
    public boolean isDisponible()  { return disponible; }
    public boolean getDisponible() { return disponible; }
}