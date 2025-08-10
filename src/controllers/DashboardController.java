package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.DatabaseStats;
import utils.SceneNavigator;

public class DashboardController {

    @FXML private Label summaryLabel;

    @FXML
    public void initialize() {
        String summary = generateSystemSummary();
        summaryLabel.setText(summary);
    }

    private String generateSystemSummary() {
        int totalSubscribers = DatabaseStats.getTotalSubscribers();
        int activeSubscriptions = DatabaseStats.getActiveSubscriptions();
        double totalPayments = DatabaseStats.getTotalPayments();

        return String.format("""
            إجمالي المشتركين: %d
            الاشتراكات الفعالة: %d
            مجموع المدفوعات: %.2f ل.ل
        """, totalSubscribers, activeSubscriptions, totalPayments);
    }

    @FXML private void navigateSubscribers()  { SceneNavigator.goTo("Subscribers.fxml"); }
    @FXML private void navigateSubscriptions(){ SceneNavigator.goTo("Subscriptions.fxml"); }
    @FXML private void navigatePayments()     { SceneNavigator.goTo("PaymentHistory.fxml"); }
    @FXML private void navigateStats()        { SceneNavigator.goTo("Statistics.fxml"); }
}