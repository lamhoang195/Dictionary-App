package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Hiiii");
        primaryStage.setTitle("Hellooooo");
        Button button  = new Button();
        button.setText("say Le Ba Hoang");
        Button button1 = new Button();
        button1.setText("He");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(button, button1);
        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}