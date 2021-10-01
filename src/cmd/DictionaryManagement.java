package cmd;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionaryData;
    int wordListSize = this.dictionaryData.getWordListSize();

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

    /**
     * sửa từ trong từ điển.
     */
    public void editWordInDectionary(String wordToEdit) {
        Scanner scanner = new Scanner(System.in);
        String newWordTarget, newWordExplain;

        int index = this.dictionaryData.searchIndexWord(0, wordListSize, wordToEdit);
        if (index == -1) {
            System.out.println("không tìm thấy từ muốn thay thế");
        } else {
            newWordTarget = scanner.nextLine();
            newWordExplain = scanner.nextLine();

            this.dictionaryData.getWordList().get(index).setWordTarget(newWordTarget);
            this.dictionaryData.getWordList().get(index).setWordExplain(newWordExplain);
        }
    }

    /**
     * xóa một từ trong từ điển.
     */
    public void removeWordInDectionary(String wordToRemove) {

        int index = this.dictionaryData.searchIndexWord(0, wordListSize, wordToRemove);
        if (index == -1) {
            System.out.println("không tìm thấy từ muốn xóa");
        } else {
            this.dictionaryData.getWordList().remove(index);
        }

    }

    public void dictionarySearcher(String wordSearch) {

        ArrayList<Word> words = this.dictionaryData.searcher(wordSearch);

        for (Word word : words) {
            System.out.print(word.getWordTarget() + ", ");
        }
    }

    /**
     * xuất dữ liệu từ điển hiện tại ra file.
     */
    public static void dictionaryExportToFile(ArrayList<Word> words) {
        try {
            FileWriter fw = new FileWriter("dictionaries.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Word word : words) {
                bw.write(word.getWordTarget() + "\t" + word.getWordExplain());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}



