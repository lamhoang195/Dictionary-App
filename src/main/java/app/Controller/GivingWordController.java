package app.Controller;

import app.CommandLine.Dictionary;


import app.CommandLine.DictionaryManagement;
import app.CommandLine.GivingWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GivingWordController extends GameController implements Initializable {
    @FXML
    public Label wordExplainLabel = new Label();
    public Button Exit;
    public Button playAgainButton;
    public Label ans = new Label();
    public Label turnsLeft = new Label();
    @FXML
    private AnchorPane main;
    @FXML
    public TextField englishWord = new TextField();
    GivingWord game;
    @FXML
    public Button Check;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo từ điển và trò chơi
        super.initialize1();
        game = new GivingWord(dictionary);
        wordExplainLabel.setText(game.getWord().getWordExplain());
        // System.out.println(game.getWord().getWordExplain());
        Check.setOnAction(event -> checkAns());
        Exit.setOnAction(event->setNotKeepPlaying());
        playAgainButton.setOnAction(event -> setKeepPlaying());
        turnsLeft.setText("Số lượt còn lại: " + Integer.toString(this.game.getTurns()));
        playAgainButton.setVisible(false);
        ans.setVisible(false);
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
            englishWord.setText("");
            englishWord.setStyle("-fx-border-color: red"); // Đặt màu chữ của TextField thành đỏ
            if (this.game.getTurns() == 1) {
                ans.setVisible(true);
                ans.setText(this.game.getWord().getWordTarget());
                playAgainButton.setVisible(true);
                ans.setVisible(true);
            }
            game.setTurns(this.game.getTurns()-1);

        }
        turnsLeft.setText("Số lượt còn lại: " + Integer.toString(this.game.getTurns()));

    }


    @FXML
    private void setChildren(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    public void setNotKeepPlaying() {
        try {
            // Tải GameGui.fxml
            Parent gameGui = FXMLLoader.load(getClass().getResource("/GUI/GameGui.fxml"));

            // Tạo một cảnh mới với GameGui.fxml
            Scene gameGuiScene = new Scene(gameGui);

            // Lấy stage hiện tại
            Stage window = (Stage) Exit.getScene().getWindow();

            // Đặt cảnh mới cho stage
            window.setScene(gameGuiScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setKeepPlaying() {
    }
}