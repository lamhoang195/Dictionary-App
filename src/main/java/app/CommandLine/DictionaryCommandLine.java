package app.CommandLine;

import java.util.Scanner;

public class DictionaryCommandLine {
    private Dictionary dictionary;

    public DictionaryCommandLine(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void showAllWords(){
        dictionary.displayAllWord();
    }

    public void dictionaryBasic(){
        DictionaryManagement management = new DictionaryManagement(dictionary);
        management.insertFromCommandLine();
        showAllWords();
    }

    public void displayAdvance() {
        DictionaryManagement management = new DictionaryManagement(dictionary);
        Scanner sc = new Scanner(System.in);
        management.insertFromFile("src/main/resources/data/dictionaries.txt");
        System.out.println("Welcome to My Application!");
        while (true) {
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.print("Your action: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Goodbye!");
                    return;
                case 1:
                    management.insertFromCommandLine();
                    break;
                case 2:
                    System.out.print("Enter a word to remove: ");
                    String wordToRemove = sc.nextLine();
                    wordToRemove = wordToRemove.trim().toLowerCase();
                    management.removeWord(wordToRemove);
                    break;
                case 3:
                    System.out.print("Enter a word to update: ");
                    sc.nextLine();
                    System.out.print("English: ");
                    String wordTarget = sc.nextLine();
                    System.out.print("Vietnamese: ");
                    String updatedWordExplain = sc.nextLine();
                    management.updateWord(wordTarget, updatedWordExplain);
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    System.out.print("Enter a word to lookup: ");
                    management.dictionaryLookup();
                    break;
                case 6:
                    System.out.print("Enter a prefix to search: ");
                    String searchPrefix = sc.nextLine();
                    break;
                case 7:
                    break;
                case 8:
                    System.out.print("Enter the file name to import from: ");
                    break;
                case 9:
                    System.out.print("Enter the file name to export to: ");
                    break;
                default:
                    System.out.println("Action not supported.");
                    break;
            }

        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        DictionaryCommandLine test = new DictionaryCommandLine(dic);
        test.displayAdvance();;
    }
}