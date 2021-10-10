package test;

import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class AdvWord {
    private String wordTarget;
    private String wordExplain;
    private ArrayList<String> spellings;
    private String wordClass;

    public AdvWord() {
    }

    public AdvWord(String wordTarget, ArrayList<String> spellings, String wordClass, String wordExplain) {
        this(wordTarget, wordExplain);
        this.spellings = spellings;
        this.wordClass = wordClass;

    }

    public AdvWord(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
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

    public ArrayList<String> getSpellings() {
        return spellings;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }




}
