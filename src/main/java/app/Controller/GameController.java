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
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {
    @FXML
    public Button playButton;
    public Button exitButton;
    public Button Exit = new Button();
    @FXML
    private AnchorPane pane;
    protected Dictionary dictionary = new Dictionary();
    protected DictionaryManagement management = new DictionaryManagement(dictionary);
    protected Word word = new Word();
    private final String HISTORY_PATH = "src/main/resources/data/dictionaries_target_tab_explain.txt";
    public void initialize1() {
        management.insertFromFile(HISTORY_PATH);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initialize1();
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/HangmanGui.fxml");
            }
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
        pane.getChildren().clear();
        pane.getChildren().add(node);
    }
    public void exit() {
        try {
            // Tải MenuGame.fxml
            Parent menuGame = FXMLLoader.load(getClass().getResource("/GUI/MenuGame.fxml"));

            // Lấy scene hiện tại từ một control (ở đây, Exit là một ví dụ)
            Scene currentScene = Exit.getScene();

            // Thay đổi gốc của Parent trong Scene thành MenuGame.fxml
            currentScene.setRoot(menuGame);
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