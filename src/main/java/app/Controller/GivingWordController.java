package app.Controller;


import app.CommandLine.GivingWord;
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
    public Label wordTargetLabel = new Label();
    public Button Exit;
    public Button playAgainButton;
    public Label ans = new Label();
    public Label turnsLeft = new Label();
    @FXML
    private AnchorPane main;
    @FXML
    public TextField wordExplainField = new TextField();
    GivingWord game;
    @FXML
    public Button Check;

    private boolean correctAnswer = false;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khởi tạo từ điển và trò chơi
        super.initialize1();
        game = new GivingWord(dictionary);
        wordTargetLabel.setText(game.getWord().getWordTarget());
        // System.out.println(game.getWord().getWordExplain());
        Check.setOnAction(event -> checkAns());
        Exit.setOnAction(event->setNotKeepPlaying());
        playAgainButton.setOnAction(event -> setKeepPlaying());
        turnsLeft.setText("Số lượt còn lại: " + Integer.toString(this.game.getTurns()));
        playAgainButton.setVisible(false);
        ans.setVisible(false);
    }

    public void checkAns() {
        String answer = wordExplainField.getText();
        if (game.getWord().getWordExplain().equals(answer)) {
            handleCorrectAnswer();
        } else {
            handleIncorrectAnswer();
        }
    }

    @FXML
    private void setChildren(Node node) {
        main.getChildren().clear();
        main.getChildren().add(node);
    }

    public void setNotKeepPlaying() {
        try {
            // Tải GameGui.fxml
            Parent gameGui = FXMLLoader.load(getClass().getResource("/GUI/DictionaryGui.fxml"));

            // Tạo một cảnh mới với DictionaryGui.fxml
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

    private void handleIncorrectAnswer() {
        System.out.println("Incorrect. Try again.");
        wordExplainField.setText("");
        wordExplainField.setStyle("-fx-border-color: red");

        if (this.game.getTurns() == 1) {
            ans.setVisible(true);
            ans.setText("The correct answer is: " + this.game.getWord().getWordExplain());
            playAgainButton.setVisible(true);
            playAgainButton.setText("Next Word!");
        }
        game.setTurns(game.getTurns() - 1);
        turnsLeft.setText("Số lượt còn lại: " + game.getTurns());
    }

    private void handleCorrectAnswer() {
        // Correct guess handling
        System.out.println("Correct!");
        wordExplainField.setText(wordExplainField.getText() + " ✓ (Correct)");
        wordExplainField.setStyle("-fx-text-fill: green;");
        correctAnswer = true;

        playAgainButton.setVisible(true);
        playAgainButton.setText("Next Word!");
        ans.setVisible(false);
    }

    public void setKeepPlaying() {
        game = new GivingWord(dictionary);
        turnsLeft.setText("Số lượt còn lại: " + game.getTurns());
        wordTargetLabel.setText(game.getWord().getWordTarget());
        ans.setVisible(false);
        playAgainButton.setVisible(false);
        wordExplainField.setStyle("-fx-text-fill: black;");
        if (correctAnswer) {
            wordExplainField.setText("");
            wordExplainField.setStyle("");
            correctAnswer = false;
        }
    }
}