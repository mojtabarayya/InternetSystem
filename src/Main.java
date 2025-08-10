import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import utils.SceneNavigator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // تحميل المشهد الرئيسي للتنقل
            Parent root = FXMLLoader.load(getClass().getResource("/views/MainLayout.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("نظام إدارة الاشتراكات");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();

            // تمرير الـ Stage إلى SceneNavigator (في حال احتجناه لاحقاً)
            SceneNavigator.setMainStage(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}