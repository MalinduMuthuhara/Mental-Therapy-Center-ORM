module edu.ijse.fx.orm {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ijse.fx.orm to javafx.fxml;
    exports edu.ijse.fx.orm;
}