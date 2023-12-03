package app.Controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;



public class GameMenuController implements Initializable {
    public AnchorPane GameMenu;
    public ImageView HangMan;
    public ImageView GivingWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HangMan.setOnMouseClicked(mouseEvent -> {
            show("/GUI/GameGui.fxml");
        });
        GivingWord.setOnMouseClicked(mouseEvent -> {
            show("/GUI/GivingWordGui.fxml");
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
