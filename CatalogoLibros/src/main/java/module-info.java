module org.example.catalogolibros {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.catalogolibros to javafx.fxml;
    opens org.example.catalogolibros.Controllers to javafx.fxml;
    opens org.example.catalogolibros.Modelo to javafx.base;
    opens org.example.catalogolibros.Repositorio to javafx.base;
    opens org.example.catalogolibros.Services to javafx.base;

    exports org.example.catalogolibros;
}