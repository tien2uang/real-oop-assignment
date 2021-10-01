package cmd;


import com.sun.org.apache.xml.internal.utils.StringToStringTable;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DictionaryManagement {
    private Dictionary dictionaryData;


    public DictionaryManagement(Dictionary d) {
        this.dictionaryData = d;
    }

    public void insertFromFile(String directory) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(directory), "UTF8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(":");
                dictionaryData.addWord(new Word(words[0], words[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file database.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertFromFileAdvanced(String directory) {
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
        for (Word word : dictionaryData.getWordList()) {
            System.out.println(word.getWordTarget() + ": " + word.getWordExplain());
        }

    }

    public void exportToFile() {

    }



}
