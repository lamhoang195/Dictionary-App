package app.Controller;


import app.CommandLine.*;

import java.net.URL;
import java.util.ResourceBundle;


public class GameController  {
    protected Dictionary dictionary = new Dictionary();
    protected DictionaryManagement management = new DictionaryManagement(dictionary);
    protected Word word = new Word();
    private final  String HISTORY_PATH = "src/main/resources/data/bookmark.txt";
    public void initialize() {
        management.insertFromFile(HISTORY_PATH);
    }

}
