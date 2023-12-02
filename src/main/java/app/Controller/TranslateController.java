package app.Controller;

import app.CommandLine.Translate;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends GeneralController implements Initializable {

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button translateButton;

    @FXML
    private Label vieLabel;

    @FXML
    private Label engLabel;

    @FXML
    private Button switchButton;

    @FXML
    private Boolean ok = false;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        outputTextArea.setEditable(false);
        translateButton.setOnAction(this::handleTranslateButton);
    }

    @FXML
    private void handleTranslateButton(ActionEvent event) {
        String textToTranslate = inputTextArea.getText();
        try {
            if(engLabel.getLayoutX() == 121) {
                String translatedText = Translate.translate("en", "vi", textToTranslate);
                outputTextArea.setText(translatedText);
            }
            else {
                String translatedText = Translate.translate("vi", "en", textToTranslate);
                outputTextArea.setText(translatedText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClickSwitchButton() {
        inputTextArea.clear();
        outputTextArea.clear();
        if(engLabel.getLayoutX() == 121) {
            engLabel.setLayoutX(463);
            vieLabel.setLayoutX(121);
            ok = true;
            return;
        }
        if(vieLabel.getLayoutX() == 121) {
            vieLabel.setLayoutX(463);
            engLabel.setLayoutX(121);
        }
    }

    @FXML
    private void handleClickSoundButton1() {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if(voice != null) {
            voice.allocate();
            voice.speak(inputTextArea.getText());
        } else throw new IllegalStateException("Can't find");
    }

    @FXML
    private void handleClickSoundButton2() {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if(voice != null) {
            voice.allocate();
            voice.speak(outputTextArea.getText());
        } else throw new IllegalStateException("Can't find");
    }
    //
}
