package edu.ijse.fx.orm.controller;

import edu.ijse.fx.orm.bo.BOFactory;
import edu.ijse.fx.orm.bo.custom.TherapistBO;
import edu.ijse.fx.orm.dto.TherapistDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TherapistController {

    @FXML
    private TextField txtTherapistId;
    @FXML
    private TextField txtProgramId;
    @FXML
    private TextField txtTherapistName;
    @FXML
    private TextField txtTherapistPhone;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    private TherapistBO therapistBO;

    public void initialize() {
        try {
            therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize Therapist Business Logic!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtTherapistId.getText();
        String programId = txtProgramId.getText();
        String name = txtTherapistName.getText();
        String phone = txtTherapistPhone.getText();

        if (id.isEmpty() || programId.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!").show();
            return;
        }

        try {
            TherapistDTO therapistDTO = new TherapistDTO(id, programId, name, phone);
            boolean isSaved = therapistBO.saveTherapist(therapistDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Saved Successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Therapist!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtTherapistId.getText();
        String programId = txtProgramId.getText();
        String name = txtTherapistName.getText();
        String phone = txtTherapistPhone.getText();

        if (id.isEmpty() || programId.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty for update!").show();
            return;
        }

        try {
            TherapistDTO therapistDTO = new TherapistDTO(id, programId, name, phone);
            boolean isUpdated = therapistBO.updateTherapist(therapistDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Updated Successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Therapist!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtTherapistId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter Therapist ID to delete!").show();
            return;
        }

        try {
            boolean isDeleted = therapistBO.deleteTherapist(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Deleted Successfully!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Therapist!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtTherapistId.clear();
        txtProgramId.clear();
        txtTherapistName.clear();
        txtTherapistPhone.clear();
    }
}