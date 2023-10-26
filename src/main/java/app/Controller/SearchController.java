package app.Controller;

import app.CommandLine.Dictionary;
import app.CommandLine.DictionaryManagement;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    private final String PATH = "src/main/resources/data/dictionaries.txt";

    @FXML
    private AnchorPane main;

    @FXML
    public TextField searchZone;

    @FXML
    private Button returnButton;
    @FXML
    private Button saveButton;
    @FXML
    public Label englishWord;

    @FXML
    private TextArea explanation;

    @FXML
    public ListView<String> listWord;

    @FXML
    private int indexOfWord;

    @FXML
    private Dictionary dictionary = new Dictionary();

    @FXML
    private DictionaryManagement management = new DictionaryManagement(dictionary);

    @FXML
    ObservableList<String> results = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resources) {
        management.insertFromFile(PATH);
        searchZone.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTypedWord();
            }

        });
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

    @FXML
    private void handleTypedWord() {
        results.clear();
        management.getResult().clear();
        String prefix = searchZone.getText();
        management.insertFromFile(PATH);
        management.searchByPrefix(prefix);
        for(int i = 0;i<Math.min(management.getResult().size(),10);i++) {
            results.add(management.getResult().get(i).getWordTarget());
        }
        if(results.isEmpty()) {
            System.out.println("No problem");
        }
        else {
            listWord.setItems(results);
        }

    }

    @FXML
    private void handleChooseWord(MouseEvent mouseEvent) {
        String selectedWord = listWord.getSelectionModel().getSelectedItem();
        management.insertFromFile(PATH);
        if(selectedWord != null) {
            indexOfWord = dictionary.binarySearchWord(selectedWord);
            if(indexOfWord == -1) return;
            englishWord.setText(dictionary.getWord(indexOfWord).getWordTarget());
            explanation.setText(dictionary.getWord(indexOfWord).getWordExplain());

        }
    }

}
