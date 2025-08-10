package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.DatabaseUtils;
import java.time.LocalDate;

public class AddPaymentController {

    @FXML private TextField subscriptionIdField, amountField;
    @FXML private DatePicker paymentDatePicker;

    @FXML
    private void handleAddPayment() {
        String subscriptionId = subscriptionIdField.getText();
        String amountStr = amountField.getText();
        LocalDate paymentDate = paymentDatePicker.getValue();

        if (subscriptionId.isEmpty() || amountStr.isEmpty() || paymentDate == null) {
            showAlert("يرجى تعبئة كافة الحقول المطلوبة.");
            return;
        }

        try {
            int subId = Integer.parseInt(subscriptionId);
            double amount = Double.parseDouble(amountStr);

            boolean success = DatabaseUtils.insertPayment(subId, amount, paymentDate);
            if (success) {
                showAlert("تم تسجيل الدفع بنجاح.");
                clearFields();
            } else {
                showAlert("فشل في تسجيل الدفع.");
            }
        } catch (NumberFormatException e) {
            showAlert("تأكد من صحة رقم الاشتراك والمبلغ.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }

    private void clearFields() {
        subscriptionIdField.clear();
        amountField.clear();
        paymentDatePicker.setValue(null);
    }
}