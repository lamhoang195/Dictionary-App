package app.Controller;


import app.CommandLine.Dictionary;
import app.CommandLine.DictionaryManagement;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.*;

public class AdditionController implements Initializable {
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
        addButton.setOnAction(event -> showAlert());
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

    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you want to add this word?");
        alert.setContentText("Choose your option");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No",ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        alert.showAndWait().ifPresent(response ->{
            if(response == buttonTypeYes) {
                addWordToFile();
            }
        });
    }
}