package cmd;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;


public class DictionaryManagement {
    private static Dictionary dictionaryData;
    private final static int SINGLE_SPELLINGS = 2;
    private final static int MULTIPLE_SPELLINGS = 4;
    private static final List<String> listWordTarget = new ArrayList<>();
    private static final ObservableList<Word> historyList = FXCollections.observableArrayList();


    public DictionaryManagement(Dictionary d) {
        dictionaryData = d;
    }

    public static Dictionary getDictionary() {
        return dictionaryData;
    }

    public static ObservableList<Word> getHistoryList() {
        return historyList;
    }

    public void insertHistory(Word word) {
        if (historyList.size() == 0) {
            historyList.add(word);
        } else {
            historyList.add(0, word);
        }
    }

    public void removeHistory(String target) {
        Word word = dictionaryLookUp(target);
        if (historyList.size() > 0) {
            historyList.remove(word);
        }
    }

    public void insertFromCommandLine() {
        Scanner input = new Scanner(System.in);
        int wordCount = input.nextInt();
        for (int i = 0; i < wordCount; i++) {
            String target = input.nextLine();
            String explain = input.nextLine();
            dictionaryData.addWord(new Word(target, explain));
        }
    }

    public void insertFromFile(String directory) {
        try {
            Scanner scanner = new Scanner(new File(directory));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(":");
                dictionaryData.addWord(new Word(words[0], words[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database.");
        }
    }


    public Dictionary getDictionaryData() {
        return dictionaryData;
    }

    /**
     * @param wordTarget target
     * @return trả về null nếu không tìm thấy,còn không trả về Word
     */
    public Word dictionaryLookUp(String wordTarget) {
        return dictionaryData.getWord2(wordTarget);
    }


    public void showAllWords() {

//         for (Word word : dictionaryData.getWordList()) {
//         System.out.println(word.getWordTarget() + ": " + word.getWordExplain());
//         }

        System.out.printf("%-5s|%-20s|%s%n", "No", "English", "Vietnamese");
        int size = dictionaryData.getWordListSize();
        for (int i = 0; i < size; i++) {
            Word word = dictionaryData.getWord(i);
            String target = word.getWordTarget();
            String explain = word.getWordExplain();
            System.out.printf("%-5d|%-20s|%s%n", (i + 1), target, explain);
        }
    }


    /**
     * sửa từ trong từ điển.
     */
    public void editWordInDictionary(String wordToEdit) {
        Scanner scanner = new Scanner(System.in);
        String newWordTarget, newWordExplain;

        int index = dictionaryData.searchIndexWord(0, dictionaryData.getWordList().size() - 1, wordToEdit);
        if (index == -1) {
            System.out.println("không tìm thấy từ muốn thay thế");
        } else {

            System.out.println("Enter new target: ");
            newWordTarget = scanner.nextLine();
            System.out.println("Enter new meaning: ");
            newWordExplain = scanner.nextLine();

            dictionaryData.getWordList().get(index).setWordTarget(newWordTarget);
            dictionaryData.getWordList().get(index).setWordExplain(newWordExplain);
        }
        scanner.close();
    }

    /**
     * xóa một từ trong từ điển.
     */
    public void removeWordInDictionary(String wordToRemove) {

        int index = dictionaryData.searchIndexWord(0, dictionaryData.getWordList().size() - 1, wordToRemove);
        if (index == -1) {
            System.out.println("không tìm thấy từ muốn xóa");
        } else {
            dictionaryData.getWordList().remove(index);
        }

    }

    public void removeWord(String wordTarget) {
        for (int i = 0; i < dictionaryData.getWordList().size(); i++) {
            if (dictionaryData.getWordList().get(i).getWordTarget().compareToIgnoreCase(wordTarget) == 0) {
                dictionaryData.getWordList().remove(i);
            }
        }

    }

    public Word getWord2(String wordTarget) {
        try {
            for (int i = 0; i < this.getDictionaryData().getWordList().size(); i++) {
                if (this.getDictionaryData().getWordList().get(i).getWordTarget().compareToIgnoreCase(wordTarget) == 0) {
                    return this.getDictionaryData().getWordList().get(i);
                }
            }
            return null;
        } catch (NullPointerException e) {
            System.out.println("Error!");
            e.printStackTrace();
            return null;
        }
    }

    public void dictionarySearcher(String wordSearch) {

        ArrayList<Word> words = dictionaryData.searcher(wordSearch);

        for (Word word : words) {
            System.out.print(word.getWordTarget() + ", ");
        }
    }

    /**
     * xuất dữ liệu từ điển hiện tại ra file.
     */
    public void dictionaryExportToFile(ArrayList<Word> words) {
        try {
            FileWriter fw = new FileWriter("dictionaries.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Word word : words) {
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
        int size = dictionaryData.getWordListSize();
        ObservableList<String> listTarget = FXCollections.observableArrayList();

        if (target.isEmpty()) {
            for (int i = 0; i < size; i++) {
                listTarget.add(dictionaryData.getWordList().get(i).getWordTarget());
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (dictionaryData.getWordList().get(i).getWordTarget().toLowerCase().startsWith(target.toLowerCase())) {
                    listTarget.add(dictionaryData.getWordList().get(i).getWordTarget());
                }
            }
        }
        if (listTarget.size() == 0) {
            listTarget.add("No result");
        }
        return listTarget;
    }

    public static int checkTypeOfLine(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '/') {
                count++;
            }
        }
        return count;
    }

    public static void InsertFromFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.equals("THE-END")) {
                    break;
                }
                if (checkTypeOfLine(line) == SINGLE_SPELLINGS) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
                    String target = stringTokenizer.nextToken().trim();
                    String spellings = "/" + stringTokenizer.nextToken().trim() + "/";
                    String temp = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp, "*");
                    String temp1 = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp1, "-");
                    String wordClass = "*  " + stringTokenizer.nextToken().trim();
                    StringBuilder explain = new StringBuilder();
                    while (stringTokenizer.hasMoreTokens()) {
                        String text = stringTokenizer.nextToken();
                        if (text.contains("=")) {
                            StringTokenizer tempStringTokenizer = new StringTokenizer(text, "=");
                            explain.append("-").append(tempStringTokenizer.nextToken()).append("\n");
                            while (tempStringTokenizer.hasMoreTokens()) {
                                String similar = tempStringTokenizer.nextToken().trim();
                                explain.append("  = ").append(similar).append("\n");

                            }
                        } else {
                            explain.append("-").append(text).append("\n");
                        }
                    }
                    //  dictionaryData.getWordList().add(new Word(target, explain, spellings, wordClass));
                    dictionaryData.addWord(new Word(target, explain.toString(), spellings, wordClass));


                } else if (checkTypeOfLine(line) == MULTIPLE_SPELLINGS) {

                    StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
                    String target = stringTokenizer.nextToken().trim();
                    String firstSpellings = "/" + stringTokenizer.nextToken() + "/";
                    String tmp = stringTokenizer.nextToken().trim();
                    String secondSpellings = "/" + stringTokenizer.nextToken() + "/";
                    String spellings = firstSpellings + " , " + secondSpellings;
                    String temp = stringTokenizer.nextToken().trim();
                    stringTokenizer = new StringTokenizer(temp, "*");
                    String temp1 = stringTokenizer.nextToken();
                    stringTokenizer = new StringTokenizer(temp1.trim(), "\t");
                    String wordClass = "* " + stringTokenizer.nextToken().trim();
                    StringBuilder explain = new StringBuilder();
                    while (stringTokenizer.hasMoreTokens()) {
                        String text = stringTokenizer.nextToken();
                        if (text.contains("=")) {
                            explain.append("  ").append(text).append("\n");
                        } else {
                            explain.append(text).append("\n");
                        }
                    }
                    //dictionaryData.getWordList().add(new Word(target, explain, spellings, wordClass));
                    dictionaryData.addWord(new Word(target, explain.toString(), spellings, wordClass));
                } else {
                    continue;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database");
            e.printStackTrace();
        }
    }
}



