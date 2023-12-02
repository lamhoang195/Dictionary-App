package app.Controller;

import app.CommandLine.Dictionary;


import app.CommandLine.DictionaryManagement;
import app.CommandLine.GivingWord;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GivingWordController extends GameController {
    public Label wordExplainLabel;
    public TextField englishWord;
    GivingWord game;
    public Button Check;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo từ điển và trò chơi
        super.initialize();
        game = new GivingWord(dictionary);
        wordExplainLabel.setText(game.getWord().getWordExplain());
        Check.setOnAction(event -> checkAns());
    }


    public void checkAns() {
        String answer = englishWord.getText();
        if (game.getWord().getWordTarget().equals(answer)) {
            // Câu trả lời đúng
            System.out.println("Correct!");
        } else {
            // Câu trả lời sai
            System.out.println("Incorrect. Try again.");
        }
    }
}
