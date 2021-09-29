package cmd;

public class Word {
    private String wordTarget;
    private String wordExplain;

    public Word() { }

    public String getWord_target() {
        return  wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWord_explain(String wordExplain) {
        this.wordExplain=wordExplain;
    };

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

}
