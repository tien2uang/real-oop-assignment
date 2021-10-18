package cmd;

import java.util.Objects;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String wordSpelling;
    private String wordClass;

    public Word() {
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

    public String getWordSpelling() {
        return wordSpelling;
    }

    public void setWordSpelling(String wordSpelling) {
        this.wordSpelling = wordSpelling;
    }

    public String getWordClass() {
        return wordClass;
    }

    public void setWordClass(String wordClass) {
        this.wordClass = wordClass;
    }

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public Word(String wordTarget, String wordExplain, String wordSpelling, String wordClass) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordSpelling = wordSpelling;
        this.wordClass = wordClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return wordTarget.equals(word.wordTarget) &&
                wordExplain.equals(word.wordExplain) &&
                wordSpelling.equals(word.wordSpelling) &&
                wordClass.equals(word.wordClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordTarget, wordExplain, wordSpelling, wordClass);
    }


}
