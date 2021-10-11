package cmd;


import com.sun.org.apache.xml.internal.utils.StringToStringTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

import java.io.*;

public class DictionaryManagement extends Dictionary {
    private Dictionary dictionaryData;
    private static int SINGLE_SPELLINGS = 2;
    private static int MULTIPLE_SPELLINGS = 4;
    public static List<String> listWordTarget = new ArrayList<>();
    public static ArrayList<Word> listWord = new ArrayList<>();


    public DictionaryManagement(Dictionary d) {
        this.dictionaryData = d;
    }

    public void insertFromCommandLine() {
        Scanner input = new Scanner(System.in);
        int wordCount = input.nextInt();
        for (int i = 0; i < wordCount; i++) {
            String target = input.nextLine();
            String explain = input.nextLine();
            this.dictionaryData.addWord(new Word(target, explain));
        }
    }

    public void insertFromFile(String directory) {
        try {
            Scanner scanner = new Scanner(new File(directory));
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                String[] words = line.split(":");
                dictionaryData.addWord(new Word(words[0], words[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertFromFileAdvanced(String directory) {
        try {
            Scanner scanner = new Scanner(new File(directory));

            for (int i=0;i<20;i++){
                String line =scanner.nextLine();
                StringTokenizer stringTokenizer= new StringTokenizer(line,"/");
                System.out.println("Number: "+i);
                while (stringTokenizer.hasMoreTokens()){
                    System.out.println(stringTokenizer.nextToken().replaceAll("^\\s+",""));

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database.");
        }

    }

    /**
     * insert mới.
     * @param directory
     */
    public void insertFromFilenew(String directory) {
        try {
            Scanner scanner = new Scanner(new File(directory));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("/", 2);
                dictionaryData.addWord(new Word(words[0], words[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database.");
        } catch (Exception e) {
            System.out.println(e);
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
        /**
        for (Word word : dictionaryData.getWordList()) {
            System.out.println(word.getWordTarget() + ": " + word.getWordExplain());
        }
         */
        System.out.printf("%-5s|%-20s|%s%n", "No", "English", "Vietnamese");
        int size = this.dictionaryData.getWordListSize();
        for (int i = 0; i < size; i++) {
            Word word = this.dictionaryData.getWord(i);
            String target = word.getWordTarget();
            String explain = word.getWordExplain();
            System.out.printf("%-5d|%-20s|%s%n", (i + 1), target, explain);
        }
    }

    /**
     * tách xâu.
     * @param explain
     * @return
     */
    public ArrayList tachxau(String explain) {
        ArrayList<String> word = new ArrayList<>();
        String[] explain_ = explain.split("/");
        String[] new_explain = explain_[explain_.length - 1].split("-");

        for (int i = 0; i < explain_.length - 1; i++) {
            word.add(explain_[i].replace("*", ""));
        }

        for (int i = 0; i < new_explain.length; i++) {
            word.add(new_explain[i].replace("*", ""));
        }

        for (int i = 0; i < word.size(); i++) {
            word.get(i).replace("*", "");
        }
        return word;

    }

    public void showAllWordsnew() {
        System.out.printf("%-5s|%-20s|%s%n", "No", "English", "Vietnamese");
        int size = this.dictionaryData.getWordListSize();
        for (int i = 0; i < size; i++) {
            Word word = this.dictionaryData.getWord(i);
            String target = word.getWordTarget();
            String explain = word.getWordExplain();

            ArrayList<String> new_explain = tachxau(explain);

            System.out.println(target);
            for(int j = 0; j < new_explain.size(); j++) {
                System.out.println(new_explain.get(j));
            }
            System.out.println("===================");

        }

    }


    /**
     * sửa từ trong từ điển.
     */
    public void editWordInDictionary(String wordToEdit) {
        Scanner scanner = new Scanner(System.in);
        String newWordTarget, newWordExplain;

        int index = this.dictionaryData.searchIndexWord(0, this.dictionaryData.getWordList().size()-1, wordToEdit);
        if (index == -1) {
            System.out.println("không tìm thấy từ muốn thay thế");
        } else {

            System.out.println("Enter new target: ");
            newWordTarget = scanner.nextLine();
            System.out.println("Enter new meaning: ");
            newWordExplain = scanner.nextLine();

            this.dictionaryData.getWordList().get(index).setWordTarget(newWordTarget);
            this.dictionaryData.getWordList().get(index).setWordExplain(newWordExplain);
        }
        scanner.close();
    }

    /**
     * xóa một từ trong từ điển.
     */
    public void removeWordInDictionary(String wordToRemove) {

        int index = this.dictionaryData.searchIndexWord(0, this.dictionaryData.getWordList().size()-1, wordToRemove);
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
    public void dictionaryExportToFile(ArrayList<Word> words) {
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

    public ObservableList<String> listTarget(String target) {
        int size = listWord.size();
        ObservableList<String> listTarget = FXCollections.observableArrayList();

        if (target.isEmpty()) {
            for (int i=0; i< size; i++) {
                listTarget.add(listWord.get(i).getWordTarget());
            }
        } else {
            for (int i = 0;i < size;i++) {
                if (listWord.get(i).getWordTarget().toLowerCase().startsWith(target.toLowerCase())) {
                    listTarget.add(listWord.get(i).getWordTarget());
                }
            }
        }
        return listTarget;
    }

    public static void InsertFromFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (checkTypeOfLine(line) == SINGLE_SPELLINGS) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
                    String target = stringTokenizer.nextToken().trim();
                    String spellings = "/" + stringTokenizer.nextToken() + "/";
                    String temp = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp, "*");
                    String temp1 = stringTokenizer.nextToken();
                    stringTokenizer = new StringTokenizer(temp1, "-");
                    String wordClass = "*  " + stringTokenizer.nextToken().trim();
                    String explain = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        String text = stringTokenizer.nextToken();
                        if (text.contains("=")) {
                            StringTokenizer tempStringTokenizer = new StringTokenizer(text, "=");
                            explain += "-" + tempStringTokenizer.nextToken() + "\n";
                            while (tempStringTokenizer.hasMoreTokens()) {
                                String similar = tempStringTokenizer.nextToken().trim();
                                explain += "  = " + similar + "\n";

                            }
                        } else {
                            explain += "-" + text + "\n";
                        }
                    }
                    listWord.add(new Word(target, explain, spellings, wordClass));


                } else if (checkTypeOfLine(line) == MULTIPLE_SPELLINGS) {

                    StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
                    String target = stringTokenizer.nextToken().trim();
                    String firstSpellings = "/" + stringTokenizer.nextToken() + "/";
                    String tmp=stringTokenizer.nextToken().trim();
                    String secondSpellings ="/" + stringTokenizer.nextToken() + "/";
                    String spellings = firstSpellings + " ; " + secondSpellings;
                    String temp = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp, "*");
                    String temp1 = stringTokenizer.nextToken();
                    stringTokenizer = new StringTokenizer(temp1.trim(), "\t");
                    String wordClass = "* "+  stringTokenizer.nextToken().trim();
                    String explain = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        String text = stringTokenizer.nextToken();
                        if (text.contains("=")) {
                            explain+="  "+text+"\n";
                        } else {
                            explain +=  text + "\n";
                        }
                    }
                    listWord.add(new Word(target, explain, spellings, wordClass));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database");
            e.printStackTrace();
        }
    }

    public Word searchWord(String word) {
        for (int i = 0; i < listWord.size(); i++) {
            if (word.toLowerCase().equals(listWord.get(i).getWordTarget().toLowerCase())) {
                return listWord.get(i);
            }
        }
        return null;
    }
}



