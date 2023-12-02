package app.Controller;

import app.CommandLine.Hangman;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7;
    @FXML
    public Button Check;
    private Hangman hangman;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize();
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
            checkWin.setStyle("-fx-text-fill = green; -fx-alignment: center;");
            wordTarget.setText(hangman.getWord().getWordTarget());
            wordTarget.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-alignment: center; -fx-border-radius: 10;");
            return;
        }
        displayGuessLetters();
        switch (hangman.getTurns()) { // Giả sử getTurns() trả về số lượt còn lại
            case 6:
                img1.setVisible(false);
                img2.setVisible(false);
                img3.setVisible(false);
                img4.setVisible(false);
                img5.setVisible(false);
                img6.setVisible(false);
                break;
            case 5:
                img1.setVisible(true);
                break;
            case 4:
                img2.setVisible(true);
                break;
            case 3:
                img3.setVisible(true);
                break;
            case 2:
                img4.setVisible(true);
                break;
            case 1:
                img5.setVisible(true);
                break;
            case 0:
                img6.setVisible(true);
                checkLose.setVisible(true);
                checkLose.setStyle("-fx-text-fill: red; -fx-alignment: center");
                wordTarget.setText(hangman.getWord().getWordTarget());
                wordTarget.setStyle("-fx-border-color: green; -fx-border-width: 2; -fx-alignment: center; -fx-border-radius: 10;");
                break;
        }
    }

    public void checkGuess() {
        char c = guessLetter.getText().charAt(0);
        hangman.makeGuess(c);
        updateUI();
        guessLetter.setText("");
    }

}
