package org.example.catalogolibros.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.catalogolibros.Modelo.Libro;

public class DetailController {

    @FXML private Label lblId;
    @FXML private Label lblTitulo;
    @FXML private Label lblAutor;
    @FXML private Label lblAnio;
    @FXML private Label lblGenero;
    @FXML private Label lblDisponible;

    public void setLibro(Libro l) {
        lblId.setText(l.getIdLibro());
        lblTitulo.setText(l.getTitulo());
        lblAutor.setText(l.getAutor());
        lblAnio.setText(String.valueOf(l.getFechaDePublicacion()));
        lblGenero.setText(l.getGenero());
        lblDisponible.setText(l.isDisponible() ? "Sí" : "No");
    }

    @FXML
    private void onRegresar() {
        ((Stage) lblId.getScene().getWindow()).close();
    }
}
