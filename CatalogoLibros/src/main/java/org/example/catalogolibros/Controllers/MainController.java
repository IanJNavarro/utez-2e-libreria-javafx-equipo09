package org.example.catalogolibros.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.catalogolibros.HelloApplication;
import org.example.catalogolibros.Modelo.Libro;
import org.example.catalogolibros.Services.LibroServicio;

import java.io.IOException;

public class MainController {

    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, String>  colId;
    @FXML private TableColumn<Libro, String>  colTitulo;
    @FXML private TableColumn<Libro, String>  colAutor;
    @FXML private TableColumn<Libro, Integer> colAnio;
    @FXML private TableColumn<Libro, String>  colGenero;
    @FXML private TableColumn<Libro, Boolean> colDisponible;
    @FXML private Label lblMensaje;

    private LibroServicio servicio;

    @FXML
    public void initialize() {
        servicio = new LibroServicio();

        colId.setCellValueFactory(new PropertyValueFactory<>("idLibro"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("fechaDePublicacion"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        cargarTabla();
    }

    @FXML
    private void onNuevo() {
        abrirFormulario(null);
    }

    @FXML
    private void onEditar() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un libro para editar.");
            return;
        }
        abrirFormulario(seleccionado);
    }

    @FXML
    private void onEliminar() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un libro para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Eliminar el libro?");
        confirmacion.setContentText("\"" + seleccionado.getTitulo() + "\" será eliminado permanentemente.");

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                servicio.eliminar(seleccionado);
                cargarTabla();
                lblMensaje.setText("Libro eliminado correctamente.");
            }
        });
    }

    @FXML
    private void onVerDetalle() {
        Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un libro para ver el detalle.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/detail-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Detalle del Libro");
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            DetailController controller = loader.getController();
            controller.setLibro(seleccionado);

            stage.showAndWait();
        } catch (IOException e) {
            lblMensaje.setText("Error al abrir detalle: " + e.getMessage());
        }
    }

    @FXML
    private void onExportar() {
        boolean exito = servicio.exportarReporte();
        if (exito) {
            lblMensaje.setText("Reporte exportado a data/reporte_catalogo.csv");
        } else {
            lblMensaje.setText("Error al exportar el reporte.");
        }
    }

    private void abrirFormulario(Libro libroEditar) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/form-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle(libroEditar == null ? "Nuevo Libro" : "Editar Libro");
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);

            FormController controller = loader.getController();
            controller.setServicio(servicio);
            if (libroEditar != null) {
                controller.setLibroEditar(libroEditar);
            }

            stage.showAndWait();
            cargarTabla();
            lblMensaje.setText(libroEditar == null ? "Libro agregado." : "Libro actualizado.");
        } catch (IOException e) {
            lblMensaje.setText("Error al abrir formulario: " + e.getMessage());
        }
    }

    private void cargarTabla() {
        ObservableList<Libro> lista = FXCollections.observableArrayList(servicio.obtenerTodos());
        tablaLibros.setItems(lista);
    }
}
