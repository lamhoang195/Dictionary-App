package app.Controller;

import app.CommandLine.Dictionary;
import app.CommandLine.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.*;

public class SearchController extends GeneralController implements Initializable {
    private final String PATH = "src/main/resources/data/dictionaries.txt";
    private final String HISTORY_PATH = "src/main/resources/data/bookmark.txt";

    @FXML
    private AnchorPane main;

    @FXML
    public TextField searchZone;

    @FXML
    public Button editButton;
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
    private Dictionary dictionary1 = new Dictionary();

    @FXML
    private DictionaryManagement management = new DictionaryManagement(dictionary);

    @FXML
    public DictionaryManagement management1 = new DictionaryManagement(dictionary1);

    @FXML
    ObservableList<String> results = FXCollections.observableArrayList();

    @FXML
    public TreeMap<String, Integer> map = new TreeMap<>();

    @FXML
    private WebView wExplanation;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        explanation.setEditable(false);
        management.insertFromFile(PATH);
        management1.insertFromFile(HISTORY_PATH);
        setDefaultListWord();
        searchZone.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                handleTypedWord();
            }
        });
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

    @FXML
    private void handleTypedWord() {
        String s = searchZone.getText();
        ArrayList<String> suggestions = dictionary.searcher(s, false);
        if (suggestions.isEmpty()) {
            results.clear();
        } else {
            results = FXCollections.observableArrayList();
            results.addAll(suggestions);
            listWord.setItems(results);
        }
    }

    @FXML
    private void handleChooseWord(MouseEvent mouseEvent) {
        try {
            if (!results.isEmpty()) {
                String s = listWord.getSelectionModel().getSelectedItem();
                indexOfWord = dictionary.binarySearchWord(s);
                englishWord.setText(s);
                explanation.setText(dictionary.lookupWord(s));
                loadWebView(wExplanation, dictionary.lookupWord(s));
                if(!map.containsKey(englishWord.getText())) {
                    map.put(englishWord.getText(), 1);
                    management1.addWordToHistoryFile(dictionary, englishWord.getText(), explanation.getText());
                }
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    @FXML
    private void handleClickSoundButton() {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if(voice != null) {
            voice.allocate();
            voice.speak(englishWord.getText());
        } else throw new IllegalStateException("Can't find");
    }

    @FXML
    private void handleClickEditButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to edit this word?");
        alert.setContentText("Choose your option");

        ButtonType buttonTypeYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        alert.showAndWait().ifPresent(response ->{
            if(response == buttonTypeYes) {
                explanation.setEditable(true);
                TextInputDialog tiDialog = new TextInputDialog();
                tiDialog.setTitle("Edit");
                tiDialog.setHeaderText("Enter new meaning for " + englishWord.getText() + ":");
                Optional<String> result = tiDialog.showAndWait();
                result.ifPresent(s -> management.updateWord(englishWord.getText(), s));
                loadWebView(wExplanation, dictionary.lookupWord(englishWord.getText()));
            }
        });
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
                loadWebView(wExplanation, dictionary.getWord(indexOfWord+1).getWordExplain());
                refreshListWord();
            }
        });
        management.removeWord(dictionary.getWord(indexOfWord).getWordTarget());
    }

    private void loadWebView(WebView webView, String text) {
        webView.getEngine().loadContent(text);
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
        ArrayList<String> keys = dictionary.searcher("", true);
        results.addAll(keys);
        listWord.setItems(results);
    }
}