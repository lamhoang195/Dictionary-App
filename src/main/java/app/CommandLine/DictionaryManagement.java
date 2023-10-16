package app.CommandLine;


import java.util.*;
import java.io.*;

public class DictionaryManagement extends Dictionary{

    protected Dictionary dictionary;

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    //Nhập dữ liệu từ dòng lệnh
    public void insertFromCommandLine() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number words you need: ");
        int word_size = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < word_size; ++i) {
            System.out.print((i + 1) + ". English: ");
            String wordTarget = sc.nextLine();
            wordTarget = wordTarget.toLowerCase();
            System.out.print("   Vietnamese: ");
            String wordExplain = sc.nextLine();
            wordExplain = wordExplain.toLowerCase();
            Word word = new Word(wordTarget, wordExplain);
            dictionary.addWord(word);
            exportToFile(dictionary);
        }
    }

    //Nhập dữ liệu từ dictionaries.txt
    public void insertFromFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String lineWord = bufferedReader.readLine();
                String[] parts = lineWord.split("\t");
                if (parts.length == 2) {
                    if (!validWord(parts[0])) {
                        System.out.println(parts[0] + " is not English Word" + ". Import word to dictionary");
                    } else {
                        dictionary.addWord(new Word(parts[0], parts[1]));
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    //Xuất file ra
    public void exportToFile(Dictionary dictionary) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/data/dictionaries_out.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary.getWords()) {
                bufferedWriter.write(word.getWordTarget() + "\t" + word.getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public boolean validWord(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isLetter(s.charAt(i))) return false;
        }
        return true;
    }

    //Xóa từ trong dictionaries_out.txt
    public void removeWord(String English) {
        dictionary.removeWord(English);
        exportToFile(dictionary);
    }

    //Sửa từ trong dictionaries_out.txt
    public void updateWord(String wordTarget, String wordExplain) {
        wordTarget = wordTarget.toLowerCase();
        wordExplain = wordExplain.toLowerCase();
        dictionary.updateWord(wordTarget, wordExplain);
        exportToFile(dictionary);
    }

    public String dictionaryLookup(String wordTarget) {
        wordTarget = wordTarget.toLowerCase();
        return dictionary.lookupWord(wordTarget);
    }

    public void searcher(String prefixWord) {
        List<Word> result = new ArrayList<>();
        for (Word word : dictionary.getWords()) {
            if (word.getWordTarget().startsWith(prefixWord)) {
                result.add(word);
            }
        }
        if (!result.isEmpty()) {
            for (Word word : result) {
                System.out.println(word.getWordTarget() + ": " + word.getWordExplain());
            }
        } else {
            System.out.println("No found prefix word!");
        }
    }
}
