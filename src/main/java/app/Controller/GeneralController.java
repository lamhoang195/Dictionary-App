package app.Controller;

import app.CommandLine.GivingWord;
import app.CommandLine.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GeneralController implements Initializable {
    @FXML
    private AnchorPane main;
    @FXML
    private Button bookmarkButton;
    @FXML
    private Button transButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button addButton;
    @FXML
    private Button GivingWordButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show("/GUI/SearcherGui.fxml");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/SearcherGui.fxml");
            }
        });


        bookmarkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/BookmarkGui.fxml");
            }
        });

        transButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/TranslationGui.fxml");
            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/AdditionGui.fxml");
            }
        });

        GivingWordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                show("/GUI/GivingWordGui.fxml");
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