package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneNavigator {

    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void goTo(String fxmlFile) {
        try {
            // Try to get the resource
            URL fxmlUrl = SceneNavigator.class.getResource("/views/" + fxmlFile);
            if (fxmlUrl == null) {
                System.err.println("❌ FXML file not found: /views/" + fxmlFile);
                return;
            }

            // Load and set the scene
            Parent root = FXMLLoader.load(fxmlUrl);
            mainStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
