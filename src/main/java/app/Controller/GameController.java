package app.Controller;


import app.CommandLine.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    @FXML
    public Button playButton;
    public Button exitButton;
    public AnchorPane Game = new AnchorPane();
    public ImageView HangMan;
    public ImageView GivingWord;
    @FXML
    private AnchorPane pane;
    protected Dictionary dictionary = new Dictionary();
    protected DictionaryManagement management = new DictionaryManagement(dictionary);
    protected Word word = new Word();
    private final  String HISTORY_PATH = "src/main/resources/data/bookmark.txt";
    public void initialize1() {
        management.insertFromFile(HISTORY_PATH);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initialize1();
        HangMan.setOnMouseClicked(mouseEvent -> {
            show("/GUI/HangmanGUI.fxml");
        });
        GivingWord.setOnMouseClicked(mouseEvent -> {
            show("/GUI/GivingWordGui.fxml");
        });
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exit();
            }
        });
    }
    @FXML
    private void setChildren(Node node) {
        Game.getChildren().clear();
        Game.getChildren().add(node);
    }
    public void exit() {
        try {
            // Tải GameGui.fxml
            Parent gameGui = FXMLLoader.load(getClass().getResource("/GUI/DictionaryGui.fxml"));

            // Tạo một cảnh mới với GameGui.fxml
            Scene gameGuiScene = new Scene(gameGui);

            // Lấy stage hiện tại
            Stage window = (Stage) exitButton.getScene().getWindow();

            // Đặt cảnh mới cho stage
            window.setScene(gameGuiScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void show(String path) {
        try {
            AnchorPane children = FXMLLoader.load(getClass().getResource(path));
            setChildren(children);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
