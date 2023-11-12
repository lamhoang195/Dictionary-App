package app.Controller;

import app.CommandLine.Translate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button translateButton;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        translateButton.setOnAction(this::handleTranslateButton);
    }

    @FXML
    private void handleTranslateButton(ActionEvent event) {
        String textToTranslate = inputTextArea.getText();
        try {
            String translatedText = Translate.translate("en", "vi", textToTranslate);
            outputTextArea.setText(translatedText);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., show an error message
        }
    }
}
