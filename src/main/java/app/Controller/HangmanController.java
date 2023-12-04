package app.Controller;

import app.CommandLine.Hangman;
import javafx.collections.ObservableList;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HangmanController extends GameController implements Initializable {
    public Label wordExplainLabel = new Label();
    public HBox guessLetters = new HBox();
    public TextField guessLetter;
    public Label wordTarget = new Label();
    public Label checkWin = new Label();
    public Label checkLose = new Label();
    public Button playAgain = new Button();
    public Button Exit = new Button();
    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7;
    @FXML
    public Button Check;
    private Hangman hangman;
    private boolean keepPlaying;
    private boolean isExit;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize1();
        keepPlaying = false;
        isExit = false;
        hangman = new Hangman(dictionary);
        wordExplainLabel.setText(hangman.getWord().getWordExplain());
        int l = this.hangman.getWord().getWordTarget().length();
        for (int i = 0; i < l; i++) {
            char c = hangman.getGuessedLetters()[i];
            Label label = new Label(String.valueOf(c));
            label.setStyle("-fx-font-size: 20; -fx-font-family: 'Arial'; -fx-alignment: center;-fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 10;");
            label.setLayoutX(100 + i * 30);
            label.setLayoutY(50);
            label.setPrefWidth(30);
            guessLetters.getChildren().add(label);
        }
        Check.setOnAction(event -> checkGuess());
        playAgain.setVisible(false);
        playAgain.setOnAction(event->setKeepPlaying());
        Exit.setOnAction(event->setNotKeepPlaying());
        updateUI();

    }
    public void displayGuessLetters() {
        ObservableList<Node> children = guessLetters.getChildren();
        int i = 0;
        for (Node child : children) {
            if (child instanceof Label) {
                ((Label) child).setText(""+hangman.getGuessedLetters()[i]);
                ++i;
            }
        }
    }
    private void updateUI() {
        // Cập nhật giao diện người dùng dựa trên trạng thái hiện tại của trò chơi
        if (this.hangman.checkCorrect()) {
            checkWin.setVisible(true);
            checkWin.setStyle("-fx-text-fill :green; -fx-alignment: center;");
            wordTarget.setText(hangman.getWord().getWordTarget());
            wordTarget.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-alignment: center; -fx-border-radius: 10;");
            playAgain.setText("Next Word");
            playAgain.setVisible(true);
            return;
        }
        displayGuessLetters();
        switch (hangman.getTurns() + 1) { // Giả sử getTurns() trả về số lượt còn lại
            case 7:
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                break;
            case 6:
                img1.setVisible(true);
                break;
            case 5:
                img2.setVisible(true);
                break;
            case 4:
                img3.setVisible(true);
                break;
            case 3:
                img4.setVisible(true);
                break;
            case 2:
                img5.setVisible(true);
                break;
            case 1:
                img6.setVisible(true);
                break;
            case 0:
                img7.setVisible(true);
                checkLose.setVisible(true);
                checkLose.setStyle("-fx-text-fill: red; -fx-alignment: center");
                wordTarget.setText(hangman.getWord().getWordTarget());
                wordTarget.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-alignment: center; -fx-border-radius: 10;");
                playAgain.setText("Play Again");
                playAgain.setVisible(true);
                break;
        }
    }

    public void checkGuess() {
        char c = guessLetter.getText().charAt(0);
        hangman.makeGuess(c);
        updateUI();
        guessLetter.setText("");
    }

    public void setKeepPlaying() {
        keepPlaying = true;
        reset();
    }

    public void reset() {
        playAgain.setVisible(false);
        checkLose.setVisible(false);
        checkWin.setVisible(false);
        img1.setVisible(false);
        img2.setVisible(false);
        img3.setVisible(false);
        img4.setVisible(false);
        img5.setVisible(false);
        img6.setVisible(false);
        this.show("/GUI/HangmanGui.fxml");
    }

    public void setNotKeepPlaying() {
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

}