package app.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    @FXML
    private AnchorPane main;
    @FXML
    public Button returnButton;
    @FXML
    private Button soundButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/DictionaryGui.fxml");
            }
        });
    }

    @FXML
    private void setChildren(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    @FXML
    private void show(String path) {
        try {
            AnchorPane children = FXMLLoader.load(getClass().getResource(path));
            setChildren(children);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
