package cmd;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> wordList = new ArrayList<>();

    public boolean addWord(Word word) {
        try {
            int start = 0;
            int end = wordList.size();
            String target = word.getWord_target();

            while (start < end) {
                int middle = (start + end) >> 1;
                String middleTarget = wordList.get(middle).getWord_target();
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
        }
        catch (NullPointerException e) {
            System.out.println("Error: NullPointerException");
            e.printStackTrace();
            return false;
        }
    }

    public int getSize() {
        return wordList.size();
    }
}
