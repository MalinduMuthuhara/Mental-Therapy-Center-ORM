package edu.ijse.fx.orm.controller;

import edu.ijse.fx.orm.bo.BOFactory;
import edu.ijse.fx.orm.bo.custom.PatientBO;
import edu.ijse.fx.orm.dto.PatientDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class PatientController {

    @FXML
    private TextField txtPatientId;
    @FXML
    private TextField txtPatientName;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtPatientPhone;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    // Table එක සහ Columns ටික සඳහා නව FX Components
    @FXML
    private TableView<PatientDTO> tblPatient;
    @FXML
    private TableColumn<PatientDTO, String> colPatientId;
    @FXML
    private TableColumn<PatientDTO, String> colPatientName;
    @FXML
    private TableColumn<PatientDTO, String> colGender;
    @FXML
    private TableColumn<PatientDTO, String> colPatientPhone;

    private PatientBO patientBO;

    public void initialize() {
        try {
            patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);


            colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colPatientPhone.setCellValueFactory(new PropertyValueFactory<>("patientPhone"));


            loadAllPatients();

            //Row click
            tblPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtPatientId.setText(newValue.getPatientId());
                    txtPatientName.setText(newValue.getPatientName());
                    txtGender.setText(newValue.getGender());
                    txtPatientPhone.setText(newValue.getPatientPhone());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize Patient Business Logic!").show();
        }
    }


    private void loadAllPatients() {
        try {
            ArrayList<PatientDTO> allPatients = patientBO.getAllPatients();
            ObservableList<PatientDTO> observableList = FXCollections.observableArrayList(allPatients);
            tblPatient.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load patients to table: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtPatientId.getText();
        String name = txtPatientName.getText();
        String gender = txtGender.getText();
        String phone = txtPatientPhone.getText();

        if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || phone.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!").show();
            return;
        }

        try {
            PatientDTO patientDTO = new PatientDTO(id, name, gender, phone);
            boolean isSaved = patientBO.savePatient(patientDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Saved Successfully!").show();
                clearFields();
                loadAllPatients();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Patient!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtPatientId.getText();
        String name = txtPatientName.getText();
        String gender = txtGender.getText();
        String phone = txtPatientPhone.getText();

        if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || phone.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty for update!").show();
            return;
        }

        try {
            PatientDTO patientDTO = new PatientDTO(id, name, gender, phone);
            boolean isUpdated = patientBO.updatePatient(patientDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Updated Successfully!").show();
                clearFields();
                loadAllPatients();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Patient!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPatientId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter Patient ID to delete!").show();
            return;
        }

        try {
            boolean isDeleted = patientBO.deletePatient(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Deleted Successfully!").show();
                clearFields();
                loadAllPatients();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Patient!").show();
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
        txtPatientId.clear();
        txtPatientName.clear();
        txtGender.clear();
        txtPatientPhone.clear();
        tblPatient.getSelectionModel().clearSelection();
    }
}