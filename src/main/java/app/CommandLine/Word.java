package app.CommandLine;

public class Word {
    private String wordTarget, wordExplain;

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget.trim().toLowerCase();
        this.wordExplain = wordExplain.trim().toLowerCase();
    }

    public Word() {
        this("", "");
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }
}

