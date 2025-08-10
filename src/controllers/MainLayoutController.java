package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainLayoutController {

    @FXML private StackPane contentArea;

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource("/views/" + fxmlFile));
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void goToDashboard()     { loadView("Dashboard.fxml"); }
    @FXML private void goToSubscribers()  { loadView("SubscriberList.fxml"); }
    @FXML private void goToSubscriptions(){ loadView("AddSubscriptions.fxml"); }
    @FXML private void goToPayments()     { loadView("PaymentHistory.fxml"); }
    @FXML private void goToStatistics()   { loadView("Statistics.fxml"); }
}