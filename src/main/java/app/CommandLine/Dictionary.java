package app.CommandLine;

import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {
    private ArrayList<Word> words = new ArrayList<>();

    public Dictionary(){
        this.words = new ArrayList<>();
    }

    //Hiển thị từ
    public void displayAllWord() {
        System.out.println("No   |   English         |   Vietnamese");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < words.size(); ++i) {
            System.out.printf("%-4d |   %-15s |   %-15s%n", (i + 1), words.get(i).getWordTarget(), words.get(i).getWordExplain());
        }
    }

    //Sắp xếp từ
    public void addWord(Word word) {
        int length = words.size();
        int index = searchIndexInsert(0, length - 1, word.getWordTarget());
        if (index <= length && index >= 0) {
            words.add(index, word);
        } else {
            System.out.println("The word already exists");
        }
    }

    //Xóa từ
    public void removeWord(String English) {
        int index = binarySearchWord(English);
        if (index >= 0) {
            words.remove(index);
            System.out.println("Remove successfully!");
        } else {
            System.out.println("Word not found, no word removed.");
        }
    }

    //Cập nhật từ
    public void updateWord(String wordTarget, String updateWordExplain) {
        for (Word word : words) {
            if (word.getWordTarget().equals(wordTarget)) {
                word.setWordExplain(updateWordExplain);
                System.out.println("Update successfully!");
                return;
            }
        }
        System.out.println("Word not found, update word failed!");
    }

    public String lookupWord(String wordTarget) {
        int index = binarySearchWord(wordTarget);
        if (index >= 0) {
            return "Work Explain: " + words.get(index).getWordExplain();
        }
        return "Work not found, lookup word failed!";
    }

    //Ti kiếm index từ
    private int searchIndexInsert(int start, int end, String wordTarget) {
        if (end < start) return start;
        int mid = start + (end - start) / 2;
        if (mid == words.size()) return mid;
        Word word = words.get(mid);
        int compare = word.getWordTarget().compareTo(wordTarget);
        if (compare == 0) return -1;
        if (compare > 0) return searchIndexInsert(start, mid - 1, wordTarget);
        return searchIndexInsert(mid + 1, end, wordTarget);
    }

    //Tìm kiếm
    private int binarySearchWord(String wordTarget) {
        words.sort(new SortDictionaryByWord());
        int left = 0;
        int right = words.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = words.get(mid).getWordTarget().compareTo(wordTarget);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //Xắp xếp từ theo alphabet
    public void sortWord(){
        ArrayList<Word> wordList = new ArrayList<>(words);
        Collections.sort(wordList, (word1, word2) -> word1.getWordTarget().compareToIgnoreCase(word2.getWordTarget()));
        words = new ArrayList<>(wordList);
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}

