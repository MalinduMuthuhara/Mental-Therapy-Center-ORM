package edu.ijse.fx.orm.controller;

import edu.ijse.fx.orm.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button btnPatient;
    @FXML
    private Button btnTherapist;
    @FXML
    private Button btnSession;
    @FXML
    private Button btnProgram;
    @FXML
    private Button btnPayment;
    @FXML
    private AnchorPane pneBody;

    public void initialize() {
        loadView("ManagePatient.fxml");
    }

    @FXML
    void btnPatientOnAction(ActionEvent event) {
        loadView("ManagePatient.fxml");
    }

    @FXML
    void btnTherapistOnAction(ActionEvent event) {
        loadView("ManageTherapist.fxml");
    }

    @FXML
    void btnSessionOnAction(ActionEvent event) {
        loadView("ManageSession.fxml");
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) {
        loadView("ManageProgram.fxml");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        loadView("ManagePayment.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/ijse/fx/orm/" + fxmlFile));
            pneBody.getChildren().clear();
            pneBody.getChildren().add(root);

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + fxmlFile).show();
        }
    }
}