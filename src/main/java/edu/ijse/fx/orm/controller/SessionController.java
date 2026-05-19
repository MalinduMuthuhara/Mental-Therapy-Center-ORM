package edu.ijse.fx.orm.controller;

import edu.ijse.fx.orm.bo.BOFactory;
import edu.ijse.fx.orm.bo.custom.SessionBO;
import edu.ijse.fx.orm.dto.SessionDTO;
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

public class SessionController {

    @FXML
    private TextField txtSessionId;
    @FXML
    private TextField txtTherapistId;
    @FXML
    private TextField txtPatientId;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtTime;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    @FXML
    private TableView<SessionDTO> tblSession;
    @FXML
    private TableColumn<SessionDTO, String> colSessionId;
    @FXML
    private TableColumn<SessionDTO, String> colTherapistId;
    @FXML
    private TableColumn<SessionDTO, String> colPatientId;
    @FXML
    private TableColumn<SessionDTO, String> colDate;
    @FXML
    private TableColumn<SessionDTO, String> colTime;

    private SessionBO sessionBO;

    public void initialize() {
        try {
            sessionBO = (SessionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SESSION);

            colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
            colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
            colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

            loadAllSessions();

            tblSession.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtSessionId.setText(newValue.getSessionId());
                    txtTherapistId.setText(newValue.getTherapistId());
                    txtPatientId.setText(newValue.getPatientId());
                    txtDate.setText(newValue.getDate());
                    txtTime.setText(newValue.getTime());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize Session Controller!").show();
        }
    }

    private void loadAllSessions() {
        try {
            ArrayList<SessionDTO> allSessions = sessionBO.getAllSessions();
            ObservableList<SessionDTO> observableList = FXCollections.observableArrayList(allSessions);
            tblSession.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load sessions to table: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String sessionId = txtSessionId.getText();
        String therapistId = txtTherapistId.getText();
        String patientId = txtPatientId.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();

        if (sessionId.isEmpty() || therapistId.isEmpty() || patientId.isEmpty() || date.isEmpty() || time.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!").show();
            return;
        }

        try {
            SessionDTO sessionDTO = new SessionDTO(sessionId, therapistId, patientId, date, time);
            boolean isSaved = sessionBO.saveSession(sessionDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Session Saved Successfully!").show();
                clearFields();
                loadAllSessions();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Session!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String sessionId = txtSessionId.getText();
        String therapistId = txtTherapistId.getText();
        String patientId = txtPatientId.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();

        if (sessionId.isEmpty() || therapistId.isEmpty() || patientId.isEmpty() || date.isEmpty() || time.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty for update!").show();
            return;
        }

        try {
            SessionDTO sessionDTO = new SessionDTO(sessionId, therapistId, patientId, date, time);
            boolean isUpdated = sessionBO.updateSession(sessionDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Session Updated Successfully!").show();
                clearFields();
                loadAllSessions();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Session!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSessionId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter Session ID to delete!").show();
            return;
        }

        try {
            boolean isDeleted = sessionBO.deleteSession(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Session Deleted Successfully!").show();
                clearFields();
                loadAllSessions();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Session!").show();
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
        txtSessionId.clear();
        txtTherapistId.clear();
        txtPatientId.clear();
        txtDate.clear();
        txtTime.clear();
        tblSession.getSelectionModel().clearSelection();
    }
}