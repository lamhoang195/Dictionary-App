package app.Controller;

import app.CommandLine.Dictionary;
import app.CommandLine.DictionaryManagement;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
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
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkController extends GeneralController implements Initializable {
    private final String PATH = "src/main/resources/data/bookmark.txt";

    @FXML
    private AnchorPane main;
    @FXML
    public TextField searchZone;
    @FXML
    public Button deleteButton;

    @FXML
    public Label englishWord;

    @FXML
    private TextArea explanation;

    @FXML
    public ListView<String> listWord;

    @FXML
    public Button soundButton;

    @FXML
    private int indexOfWord;

    @FXML
    private int indexOfWordInList;

    @FXML
    private Dictionary dictionary = new Dictionary();

    @FXML
    private DictionaryManagement management = new DictionaryManagement(dictionary);

    @FXML
    ObservableList<String> results = FXCollections.observableArrayList();
    @FXML
    private WebView wExplanation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        management.insertFromFile(PATH);
        setDefaultListWord();
        searchZone.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTypedWord();
            }
        });
    }

    @FXML
    private void handleTypedWord() {
        results.clear();
        management.getResult().clear();
        String prefix = searchZone.getText();
        management.insertFromFile(PATH);
        management.searchByPrefix(prefix);
        for (int i = 0; i < Math.min(management.getResult().size(), 30); i++) {
            results.add(management.getResult().get(i).getWordTarget());
        }
        if (results.isEmpty()) {
            System.out.println("No problem");
        } else {
            listWord.setItems(results);
        }
    }

    @FXML
    private void handleChooseWord(MouseEvent mouseEvent) {
        String selectedWord = listWord.getSelectionModel().getSelectedItem();
        management.insertFromFile(PATH);
        if (selectedWord != null) {
            indexOfWord = dictionary.binarySearchWord(selectedWord);
            if (indexOfWord == -1) return;
            englishWord.setText(dictionary.getWord(indexOfWord).getWordTarget());
            explanation.setText(dictionary.getWord(indexOfWord).getWordExplain());
            wExplanation.getEngine().loadContent(dictionary.getWord(indexOfWord).getWordExplain());
            explanation.setEditable(false);
        }
    }

    @FXML
    private void handleClickSoundButton() {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if(voice != null) {
            voice.allocate();
            voice.speak(dictionary.getWord(indexOfWord).getWordTarget());
        } else throw new IllegalStateException("Can't find");
    }

    @FXML
    private void handleClickDeleteButton() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to delete this word?");
        alert.setContentText("Choose your option");

        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        alert.showAndWait().ifPresent(response ->{
            if(response == buttonTypeYes) {
                englishWord.setText(dictionary.getWord(indexOfWord+1).getWordTarget());
                explanation.setText(dictionary.getWord(indexOfWord+1).getWordExplain());
                wExplanation.getEngine().loadContent(dictionary.getWord(indexOfWord+1).getWordExplain());
                refreshListWord();
            }
        });
        management.removeWordInHistory(dictionary.getWord(indexOfWord).getWordTarget());
    }

    private void refreshListWord() {
        for (int i = 0; i < results.size(); i++)
            if (results.get(i).equals(dictionary.getWord(indexOfWord).getWordTarget())) {
                results.remove(i);
                break;
            }
        listWord.setItems(results);
    }

    private void setDefaultListWord() {
        for(int i=0; i < dictionary.getWords().size(); i++) {
            results.add(dictionary.getWord(i).getWordTarget());
        }
        listWord.setItems(results);
    }

}


