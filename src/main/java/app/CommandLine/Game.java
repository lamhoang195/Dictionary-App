package app.CommandLine;

import java.util.Random;
import java.util.Scanner;

import java.util.Random;
import java.util.Scanner;

abstract class Game {
    protected Dictionary dictionary;
    protected Word word;
    protected int turns;
    protected int score;

    public Game(Dictionary dictionary) {
        this.score = 0;
        this.turns = 6;
        this.dictionary = dictionary;
    }
    public Word getWord() {
        return this.word;
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
        int randomIndex = rand.nextInt(15);
        return dictionary.getWord(randomIndex);
    }
    protected void play(){};
}