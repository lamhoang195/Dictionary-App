package app.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class GameMenuController extends GeneralController implements Initializable {
    @FXML
    public AnchorPane GameMenu;
    @FXML
    public ImageView HangMan;
    @FXML
    public ImageView GivingWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HangMan.setOnMouseClicked(mouseEvent -> {
            Parent hangmanGame = null;
            try {
                hangmanGame = FXMLLoader.load(getClass().getResource("/GUI/HangmanGui.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene currentScene = HangMan.getScene();
            currentScene.setRoot(hangmanGame);
        });
        GivingWord.setOnMouseClicked(mouseEvent -> {
            Parent hangmanGame = null;
            try {
                hangmanGame = FXMLLoader.load(getClass().getResource("/GUI/GivingWordGui.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene currentScene = HangMan.getScene();
            currentScene.setRoot(hangmanGame);
        });
    }

    private void show(String path) {
        try {
            AnchorPane children = FXMLLoader.load(getClass().getResource(path));
            setChildren(children);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setChildren(Node node) {
        GameMenu.getChildren().clear();
        GameMenu.getChildren().add(node);
    }
}