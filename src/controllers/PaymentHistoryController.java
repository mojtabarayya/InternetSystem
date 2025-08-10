package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import models.PaymentModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.ReportExporter;

import java.io.File;
import java.util.List;

public class PaymentHistoryController {

    @FXML private TextField subscriberIdField;
    @FXML private TableView<PaymentModel> paymentTable;
    @FXML private TableColumn<PaymentModel, String> dateCol, methodCol, statusCol;
    @FXML private TableColumn<PaymentModel, Double> amountCol;

    @FXML
    public void initialize() {
        dateCol.setCellValueFactory(data -> data.getValue().paymentDateProperty());
        amountCol.setCellValueFactory(data -> data.getValue().amountProperty().asObject());
        methodCol.setCellValueFactory(data -> data.getValue().methodProperty());
        statusCol.setCellValueFactory(data -> data.getValue().statusProperty());
    }

    @FXML
    private void handleLoadPayments() {
        String idText = subscriberIdField.getText();
        try {
            int subscriberId = Integer.parseInt(idText);
            ObservableList<PaymentModel> list = PaymentModel.getPaymentsBySubscriber(subscriberId);
            paymentTable.setItems(list);
        } catch (NumberFormatException e) {
            showAlert("رقم المشترك غير صالح.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.show();
    }
    @FXML
    private void handleExportReport() {
        List<PaymentModel> payments = paymentTable.getItems();
        if (payments.isEmpty()) {
            showAlert("لا توجد بيانات لتصديرها.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("اختر مكان حفظ التقرير");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setInitialFileName("payments_report.csv");

        // الحصول على stage من عنصر داخل النافذة
        Stage stage = (Stage) paymentTable.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            ReportExporter.exportPayments(payments, file.getAbsolutePath());
            showAlert("تم حفظ التقرير بنجاح في:\n" + file.getAbsolutePath());
        }
    }
}