package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.DatabaseUtils;

import java.util.Arrays;

public class AddSubscriptionController {

    @FXML private TextField subscriberIdField, yearField;
    @FXML private ComboBox<String> monthComboBox;

    @FXML
    public void initialize() {
        monthComboBox.getItems().addAll(Arrays.asList(
                "يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو",
                "يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"));
    }

    @FXML
    private void handleAddSubscription() {
        String subscriberId = subscriberIdField.getText();
        String month = monthComboBox.getValue();
        String year = yearField.getText();

        if (subscriberId.isEmpty() || month == null || year.isEmpty()) {
            showAlert("يرجى تعبئة جميع الحقول!");
            return;
        }

        boolean success = DatabaseUtils.insertSubscription(subscriberId, month, year);
        if (success) {
            showAlert("تم تسجيل الاشتراك بنجاح.");
            clearFields();
        } else {
            showAlert("فشل في تسجيل الاشتراك.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }

    private void clearFields() {
        subscriberIdField.clear();
        monthComboBox.getSelectionModel().clearSelection();
        yearField.clear();
    }
}