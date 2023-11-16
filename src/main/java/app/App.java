package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL resource = getClass().getResource("/GUI/DictionaryGui.fxml");
            if (resource == null) {
                System.err.println("Không thể tìm thấy tệp FXML.");
                System.exit(1); // hoặc xử lý lỗi khác ở đây
            }
            primaryStage.setTitle("Dictionary");
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root, 870, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
