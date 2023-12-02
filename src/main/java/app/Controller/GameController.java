package app.Controller;


import app.CommandLine.*;

import java.net.URL;
import java.util.ResourceBundle;


public class GameController  {
    protected Dictionary dictionary = new Dictionary();
    protected DictionaryManagement management = new DictionaryManagement(dictionary);
    protected Word word;
    public void initialize() {
        // Khởi tạo từ điển và trò chơi
        String HISTORY_PATH = "src/main/resources/data/bookmark.txt";
        management.insertFromFile(HISTORY_PATH);
    }

}
