package org.example.catalogolibros.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.catalogolibros.Modelo.Libro;
import org.example.catalogolibros.Services.LibroServicio;

public class FormController {

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtAnio;
    @FXML private TextField txtGenero;
    @FXML private CheckBox  chkDisponible;
    @FXML private Label     lblError;

    private LibroServicio servicio;
    private Libro libroEditar;

    /**
     * Setter de servicio para este controller
     * @param servicio
     */
    public void setServicio(LibroServicio servicio) {
        this.servicio = servicio;
    }

    /**
     * Metodo para mostrar los detalles de un libro en el formulario
     * @param l -> default view del libro a editar
     */
    public void setLibroEditar(Libro l) {
        this.libroEditar = l;
        txtId.setText(l.getIdLibro());
        txtId.setDisable(true);
        txtTitulo.setText(l.getTitulo());
        txtAutor.setText(l.getAutor());
        txtAnio.setText(String.valueOf(l.getFechaDePublicacion()));
        txtGenero.setText(l.getGenero());
        chkDisponible.setSelected(l.isDisponible());
    }

    /**
     * Metodo para guardar un libro en el catalogo al presionar un boton
     */
    @FXML
    private void onGuardar() {
        lblError.setText("");
        try {
            if (libroEditar == null) {
                servicio.agregar(
                        txtId.getText().trim(),
                        txtTitulo.getText().trim(),
                        txtAutor.getText().trim(),
                        txtAnio.getText().trim(),
                        txtGenero.getText().trim(),
                        chkDisponible.isSelected()
                );
            } else {
                servicio.actualizar(
                        libroEditar.getIdLibro(),
                        txtTitulo.getText().trim(),
                        txtAutor.getText().trim(),
                        txtAnio.getText().trim(),
                        txtGenero.getText().trim(),
                        chkDisponible.isSelected()
                );
            }
            cerrar();
        } catch (Exception e) {
            lblError.setText(e.getMessage());
        }
    }

    /**
     * Metodo para cerrar una scene al presionar un boton.
     */
    @FXML
    private void onCancelar() {
        cerrar();
    }

    /**
     * Metodo para terminar una scene y regresar al main stage
     */
    private void cerrar() {
        ((Stage) txtId.getScene().getWindow()).close();
    }
}
