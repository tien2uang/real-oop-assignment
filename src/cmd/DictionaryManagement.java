package cmd;

public class DictionaryManagement {
    private Dictionary dictionaryData;

    public DictionaryManagement(Dictionary d) {
        this.dictionaryData = d;
    }

    public void insertFromFile(String directory) {

    }

    /**
     *
     * @param wordTarget
     * @return trả về null nếu không tìm thấy,còn không trả về Word
     */
    public Word dictionaryLookUp(String wordTarget) {

        return this.dictionaryData.getWord(wordTarget);
    }

    public void showAllWords() {
        for(Word word:dictionaryData.getDictionary()){
            System.out.println(word.getWordTarget()+": "+word.getWordExplain());
        }

    }

    public void exportToFile() {

    }
}
