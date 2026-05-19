package edu.ijse.fx.orm.controller;

import edu.ijse.fx.orm.bo.BOFactory;
import edu.ijse.fx.orm.bo.custom.PaymentBO;
import edu.ijse.fx.orm.dto.PaymentDTO;
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

public class PaymentController {

    @FXML
    private TextField txtPaymentId;
    @FXML
    private TextField txtSessionId;
    @FXML
    private TextField txtDate;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    @FXML
    private TableView<PaymentDTO> tblPayment;
    @FXML
    private TableColumn<PaymentDTO, String> colPaymentId;
    @FXML
    private TableColumn<PaymentDTO, String> colSessionId;
    @FXML
    private TableColumn<PaymentDTO, String> colDate;

    private PaymentBO paymentBO;

    public void initialize() {
        try {
            paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

            colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
            colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            loadAllPayments();

            tblPayment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtPaymentId.setText(newValue.getPaymentId());
                    txtSessionId.setText(newValue.getSessionId());
                    txtDate.setText(newValue.getDate());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to initialize Payment Business Logic!").show();
        }
    }

    private void loadAllPayments() {
        try {
            ArrayList<PaymentDTO> allPayments = paymentBO.getAllPayments();
            ObservableList<PaymentDTO> observableList = FXCollections.observableArrayList(allPayments);
            tblPayment.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payments to table: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        String sessionId = txtSessionId.getText();
        String date = txtDate.getText();

        if (paymentId.isEmpty() || sessionId.isEmpty() || date.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!").show();
            return;
        }

        try {
            PaymentDTO paymentDTO = new PaymentDTO(paymentId, sessionId, date);
            boolean isSaved = paymentBO.savePayment(paymentDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Saved Successfully!").show();
                clearFields();
                loadAllPayments();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Payment!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String paymentId = txtPaymentId.getText();
        String sessionId = txtSessionId.getText();
        String date = txtDate.getText();

        if (paymentId.isEmpty() || sessionId.isEmpty() || date.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty for update!").show();
            return;
        }

        try {
            PaymentDTO paymentDTO = new PaymentDTO(paymentId, sessionId, date);
            boolean isUpdated = paymentBO.updatePayment(paymentDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Updated Successfully!").show();
                clearFields();
                loadAllPayments();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update Payment!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPaymentId.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter Payment ID to delete!").show();
            return;
        }

        try {
            boolean isDeleted = paymentBO.deletePayment(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Deleted Successfully!").show();
                clearFields();
                loadAllPayments();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Delete Payment!").show();
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
        txtPaymentId.clear();
        txtSessionId.clear();
        txtDate.clear();
        tblPayment.getSelectionModel().clearSelection();
    }
}