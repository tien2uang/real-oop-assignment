package cmd;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionaryData;

    public DictionaryManagement(Dictionary d) {
        this.dictionaryData = d;
    }

    public void insertFromFile(String directory) {
        try {
            Scanner scanner = new Scanner(new File(directory));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String wordTarget = line.split("\t")[0];
                String wordExplain = line.split("\t")[1];
                dictionaryData.addWord(new Word(wordTarget, wordExplain));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database.");
        }
    }

    public Dictionary getDictionaryData() {
        return dictionaryData;
    }

    /**
     * @param wordTarget
     * @return trả về null nếu không tìm thấy,còn không trả về Word
     */
    public Word dictionaryLookUp(String wordTarget) {

        return this.dictionaryData.getWord(wordTarget);
    }

    public void showAllWords() {
        for(Word word:dictionaryData.getWordList()){
            System.out.println(word.getWordTarget()+": "+word.getWordExplain());
        }

    }
    public void exportToFile() {

    }

}
