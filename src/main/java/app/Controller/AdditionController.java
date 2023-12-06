package app.Controller;

import app.CommandLine.Word;
import app.CommandLine.Dictionary;
import app.CommandLine.DictionaryManagement;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;
import java.io.*;


import javafx.concurrent.Worker;public class AdditionController implements Initializable {
    private final String PATH = "src/main/resources/data/dictionaries.txt";

    @FXML
    private TextField englishTextField;

    @FXML
    private TextField vietnameseTextField;

    @FXML
    private Button addButton;

    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement management = new DictionaryManagement(dictionary);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        management.insertFromFile(PATH);
        // Xử lý sự kiện khi nhấn nút "Thêm từ"
        addButton.setOnAction(event -> {
            addWordToFile();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thành công!");
            alert.showAndWait();
        });
    }

    // Thêm từ vào file dictionaries.txt
    private void addWordToFile() {
        String englishWord = englishTextField.getText();
        String vietnameseMeaning = vietnameseTextField.getText();

        management.addWord(englishWord, vietnameseMeaning);

        // Sau đó, cập nhật lại file
        exportToFileAdd(PATH, englishWord, vietnameseMeaning);
        System.out.println("Thêm từ: " + englishWord + " - " + vietnameseMeaning);
    }


    public void exportToFileAdd(String filePath, String englishWord, String vietnameseMeaning) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder content = new StringBuilder();
            String line;
            boolean wordExists = false;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2 && parts[0].equals(englishWord)) {
                    // Tìm thấy từ trong file, cập nhật nghĩa mới
                    content.append(englishWord).append("\t").append(vietnameseMeaning).append("\n");
                    wordExists = true;
                } else {
                    content.append(line).append("\n");
                }
            }

            bufferedReader.close();
            fileReader.close();

            // Nếu từ không tồn tại, thêm từ mới vào cuối file
            if (!wordExists) {
                content.append(englishWord).append("\t").append(vietnameseMeaning).append("\n");
            }

            // Ghi lại toàn bộ nội dung của file
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e);
        }
    }
}