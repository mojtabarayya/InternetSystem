package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.DatabaseStats;

public class StatisticsController {

    @FXML private Label totalSubscribersLabel;
    @FXML private Label activeSubscriptionsLabel;
    @FXML private Label totalPaymentsLabel;
    @FXML private Label expiredSubscriptionsLabel;
    @FXML private Label rejectedPaymentsLabel;

    @FXML
    public void initialize() {
        totalSubscribersLabel.setText(String.valueOf(DatabaseStats.getTotalSubscribers()));
        activeSubscriptionsLabel.setText(String.valueOf(DatabaseStats.getActiveSubscriptions()));
        totalPaymentsLabel.setText(String.format("%.2f ل.ل", DatabaseStats.getTotalPayments()));
        expiredSubscriptionsLabel.setText(String.valueOf(DatabaseStats.getExpiredSubscriptions()));
        rejectedPaymentsLabel.setText(String.valueOf(DatabaseStats.getRejectedPayments()));
    }
    @FXML
    private void refreshStatistics() {
        initialize(); // ببساطة نعيد استدعاء initialize لتحديث البيانات
    }
}