package app.Controller;

import app.CommandLine.Hangman;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7;
    @FXML
    private Label label = new Label();

    @FXML
    public Button Check;
    private Hangman hangman;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize();
        hangman = new Hangman(dictionary); // Thay Dictionary bằng lớp từ điển thực tế của bạn
        wordExplainLabel.setText(hangman.getWord().getWordExplain());
        String ex = "Example";
        for (int i = 0; i < ex.length(); ++i) {
            char  c = ex.charAt(i);
            Label label = new Label(String.valueOf(c));
            label.setStyle("-fx-font-size: 20; -fx-font-family: 'Arial'; -fx-alignment: center;-fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 10;");
            label.setLayoutX(100 + i * 30);
            label.setLayoutY(50);
            label.setPrefWidth(30);
            guessLetters.getChildren().add(label);
            Check.setOnAction(event -> checkGuess());
        }
        wordTarget.setText(hangman.getWord().getWordTarget());
        updateUI();

    }

    private void updateUI() {
        // Cập nhật giao diện người dùng dựa trên trạng thái hiện tại của trò chơi
        label.setText(new String(hangman.getGuessedLetters()));
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
                break;
        }
    }

    public void makeGuess(char letter) {
        hangman.makeGuess(letter);
        updateUI();
    }

    public void checkGuess() {

    }
}
