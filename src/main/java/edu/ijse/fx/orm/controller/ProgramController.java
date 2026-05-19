package edu.ijse.fx.orm.controller;

import edu.ijse.fx.orm.bo.BOFactory;
import edu.ijse.fx.orm.bo.custom.ProgramBO;
import edu.ijse.fx.orm.dto.ProgramDTO;
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

public class ProgramController {

    @FXML
    private TextField txtProgramId;
    @FXML
    private TextField txtProgramName;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    @FXML
    private TableView<ProgramDTO> tblProgram;
    @FXML
    private TableColumn<ProgramDTO, String> colProgramId;
    @FXML
    private TableColumn<ProgramDTO, String> colProgramName;

    private ProgramBO programBO;

    public void initialize() {
        try {
            programBO = (ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);

            colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
            colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));

            loadAllPrograms();

            tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtProgramId.setText(newValue.getProgramId());
                    txtProgramName.setText(newValue.getProgramName());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize Program Business Logic!").show();
        }
    }

    private void loadAllPrograms() {
        try {
            ArrayList<ProgramDTO> allPrograms = programBO.getAllPrograms();
            ObservableList<ProgramDTO> observableList = FXCollections.observableArrayList(allPrograms);
            tblProgram.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load programs to table: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtProgramId.getText();
        String name = txtProgramName.getText();

        if (id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!").show();
            return;
        }

        try {
            ProgramDTO programDTO = new ProgramDTO(id, name);
            boolean isSaved = programBO.saveProgram(programDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Program Saved Successfully!").show();
                clearFields();
                loadAllPrograms();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Program!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtProgramId.getText();
        String name = txtProgramName.getText();

        if (id.isEmpty() || name.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty for update!").show();
            return;
        }

        try {
            ProgramDTO programDTO = new ProgramDTO(id, name);
            boolean isUpdated = programBO.updateProgram(programDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Program Updated Successfully!").show();
                clearFields();
                loadAllPrograms();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Program!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtProgramId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter Program ID to delete!").show();
            return;
        }

        try {
            boolean isDeleted = programBO.deleteProgram(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Program Deleted Successfully!").show();
                clearFields();
                loadAllPrograms();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Program!").show();
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
        txtProgramId.clear();
        txtProgramName.clear();
        tblProgram.getSelectionModel().clearSelection();
    }
}