package app.CommandLine;


import java.util.*;
import java.io.*;

public class DictionaryManagement extends Dictionary{

    protected Dictionary dictionary;

    private List<Word> result = new ArrayList<>();

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<Word> getResult() {
        return result;
    }

    /**
     * check valid word.
     *
     * @param word - word
     * @return true if valid, false if not valid
     */
    public boolean validWord(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (!Character.isLetter(word.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Import words from file
     *
     * @param path path-dictionary.txt
     */
    public void insertFromFile(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String englishWord = bufferedReader.readLine();
            englishWord = englishWord.replace("@", "");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Word word = new Word();
                word.setWordTarget(englishWord.trim());
                String meaning = line + "\n";
                while ((line = bufferedReader.readLine()) != null)
                    if (!line.startsWith("@")) meaning += line + "\n";
                    else {
                        englishWord = line.replace("@", "");
                        break;
                    }
                word.setWordExplain(meaning.trim());
                dictionary.addWord(word);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An error occur with file: " + e);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Export to file.
     *
     */
    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
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

    public void addWordToHistoryFile(Dictionary dictionary, String englishWord, String explanation) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/data/bookmark.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("@" + englishWord); // Thêm dấu "|"
            bufferedWriter.newLine();
            bufferedWriter.write(explanation); // Viết giải thích
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    /**
     * Insert word from commandline.
     *
     */
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
            String wordMeaning = sc.nextLine();
            wordMeaning = wordMeaning.toLowerCase();
            Word word = new Word(wordTarget, wordMeaning);
            dictionary.addWord(word);
            exportToFile(dictionary,"src/main/resources/data/dictionaries.txt");
        }
    }

    /**
     * Remove word.
     *
     * @param English word in English
     */
    public void removeWord(String English) {
        dictionary.removeWord(English);
        exportToFile(dictionary,"src/main/resources/data/anhviet109K.txt");
    }

    public void removeWordInHistory(String English) {
        dictionary.removeWord(English);
        exportToFile(dictionary,"src/main/resources/data/bookmark.txt");
    }

    /**
     * Update word meaning
     *
     * @param wordTarget    word in English
     * @param wordMeaning   word meaning
     */
    public void updateWord(String wordTarget, String wordMeaning) {
        wordTarget = wordTarget.toLowerCase();
        wordMeaning = wordMeaning.toLowerCase();
        dictionary.updateWord(wordTarget, wordMeaning);
        exportToFile(dictionary,"src/main/resources/data/anhviet109K.txt");
    }

    /**
     * Look up.
     *
     * @param wordTarget word in English
     * @return mean in Vietnamese
     */
    public String dictionaryLookup(String wordTarget) {
        wordTarget = wordTarget.toLowerCase();
        return dictionary.lookupWord(wordTarget);
    }

    /**
     * search by prefix
     *
     * @param prefixWord prefix
     */
    public void searchByPrefix(String prefixWord) {
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
