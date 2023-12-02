package app.Controller;

import app.CommandLine.Dictionary;


import app.CommandLine.DictionaryManagement;
import app.CommandLine.GivingWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GivingWordController extends GameController implements Initializable {
    @FXML
    public Label wordExplainLabel = new Label();
    @FXML
    private AnchorPane main;
    @FXML
    public TextField englishWord = new TextField();
    GivingWord game;
    @FXML
    public Button Check;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo từ điển và trò chơi
        super.initialize();
        game = new GivingWord(dictionary);
        wordExplainLabel.setText(game.getWord().getWordExplain());
       // System.out.println(game.getWord().getWordExplain());
        Check.setOnAction(event -> checkAns());
    }


    public void checkAns() {
        String answer = englishWord.getText();
        if (game.getWord().getWordTarget().equals(answer)) {
            // Câu trả lời đúng
            System.out.println("Correct!");
            englishWord.setText(englishWord.getText() + " ✓ (Correct");
            englishWord.setStyle("-fx-text-fill: green;"); // Đặt màu chữ của TextField thành xanh
        } else {
            // Câu trả lời sai
            System.out.println("Incorrect. Try again.");
            englishWord.setText(englishWord.getText() + " X (Try again)");
            englishWord.setStyle("-fx-text-fill: red;"); // Đặt màu chữ của TextField thành đỏ

        }
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
