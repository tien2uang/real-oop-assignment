package cmd;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordList = new ArrayList<>();

    public boolean addWord(Word word) {
        try {
            int start = 0;
            int end = wordList.size();
            String target = word.getWordTarget();

            while (start < end) {
                int middle = (start + end) >> 1;
                String middleTarget = wordList.get(middle).getWordTarget();
                if (target.compareToIgnoreCase(middleTarget) == 0) {
                    return false;
                }
                if (target.compareToIgnoreCase(middleTarget) < 0) {
                    end = middle;
                } else {
                    start = middle + 1;
                }
            }

            if (wordList.size() == 0 || start == wordList.size()) {
                wordList.add(word);
            } else {
                wordList.add(start, word);
            }
            return true;
        } catch (NullPointerException e) {
            System.out.println("Error: NullPointerException");
            e.printStackTrace();
            return false;
        }
    }

    public Word getWord(String target) {
        try {
            int start = 0;
            int end = wordList.size();

            while (start < end) {
                int middle = (start + end) / 2;
                String middleTarget = wordList.get(middle).getWordTarget();
                if(target.compareToIgnoreCase(middleTarget) == 0){
                    return wordList.get(middle);
                }else if(target.compareToIgnoreCase(middleTarget)>0){
                    start=middle+1;
                }else {
                    end=middle-1;
                }
            }
            return null;


        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }
}
