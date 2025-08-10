package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.PackageModel;
import utils.DatabaseUtils;

public class AddSubscriberController {

    @FXML
    private TextField fullNameField, phoneField, addressField;

    @FXML
    private ComboBox<String> packageComboBox;

    @FXML
    public void initialize() {
        // تحميل الباقات من قاعدة البيانات
        packageComboBox.getItems().addAll(PackageModel.getAllPackageNames());
    }

    @FXML
    private void handleAddSubscriber() {
        String name = fullNameField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String selectedPackage = packageComboBox.getValue();

        if (name.isEmpty() || phone.isEmpty() || selectedPackage == null) {
            showAlert("يرجى تعبئة كافة الحقول المطلوبة.");
            return;
        }

        boolean success = DatabaseUtils.insertSubscriber(name, phone, address, selectedPackage);
        if (success) {
            showAlert("تم إضافة المشترك بنجاح!");
            clearFields();
        } else {
            showAlert("حدث خطأ أثناء الإضافة.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }

    private void clearFields() {
        fullNameField.clear();
        phoneField.clear();
        addressField.clear();
        packageComboBox.getSelectionModel().clearSelection();
    }
}