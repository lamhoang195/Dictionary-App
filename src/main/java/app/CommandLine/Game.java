package app.CommandLine;

import java.util.Random;
import java.util.Scanner;

import java.util.Random;
import java.util.Scanner;

public class Game{
    protected Dictionary dictionary;
    protected Word word;
    protected int turns;
    protected int score;
    public Game(Dictionary dictionary) {
        this.score = 0;
        this.turns = 6;
        this.dictionary = dictionary;
    }
    public int getTurns() {
        return turns;
    }
    public void setTurns(int turns) {
        this.turns = turns;
    }
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * get random word index from 0 to 1000.
     *
     * @return Word.
     */
    public Word getRandomWord() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(150);
        return dictionary.getWord(randomIndex);
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        this.word = getRandomWord();
        System.out.println( "Give the word which mean: " + this.word.getWordExplain());
        while (this.turns > 0) {
            System.out.print("Your answer: ");
            String ans = sc.nextLine();
            if (ans.equals(this.word.getWordTarget())) {
                System.out.println("Correct!");
                break;
            }
            else {
                this.turns --;
                System.out.println("Try again!");
            }
        }
        if (this.turns == 0) System.out.println("You lose! The correct answer is: " + this.word.getWordTarget());
    }
}
