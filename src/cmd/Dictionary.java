package cmd;

import java.util.ArrayList;

public class Dictionary {
    private static final ArrayList<Word> wordList = new ArrayList<>();

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
            int end = wordList.size() - 1;
            while (start <= end) {
                int middle = start + (end - start) / 2;
                String middleTarget = wordList.get(middle).getWordTarget();
                if (target.compareToIgnoreCase(middleTarget) == 0) {
                    return wordList.get(middle);
                } else if (target.compareToIgnoreCase(middleTarget) > 0) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
            return null;


        } catch (NullPointerException e) {
            System.out.println("Error!");
            e.printStackTrace();
            return null;
        }
    }



    public Word getWord(int i) {
        return wordList.get(i);
    }

    /**
     * t??m v??? tr?? c???a t??? ti???ng Anh trong wordList.
     *
     * @param start .
     * @param end .
     * @param target .
     * @return .
     */
    public int searchIndexWord(int start, int end, String target) {
        try {

            while (start <= end) {
                int middle = (start + end) / 2;
                String middleTarget = wordList.get(middle).getWordTarget();

                if (target.equals(middleTarget)) {
                    return middle;
                } else if (target.compareToIgnoreCase(middleTarget) > 0) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
            return -1;


        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * t??m v??? tr?? c???a t??? b???t ?????u b???ng wordSearch.
     *
     * @param start .
     * @param end .
     * @param target .
     * @return .
     */
    public int startWithWordSearch(int start, int end, String target) {
        try {
            while (start <= end) {
                int middle = start + (end - start) / 2;
                String middleTarget = wordList.get(middle).getWordTarget();

                if (middleTarget.startsWith(target)) {
                    return middle;
                } else if (target.compareToIgnoreCase(middleTarget) > 0) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
            return -1;


        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * danh s??ch c??c t??? b???t ?????u b???ng wordSearch.
     * @param wordSearch
     * @return
     */
    public ArrayList<Word> searcher(String wordSearch) {
        ArrayList<Word> result = new ArrayList<>();
        int index = startWithWordSearch(0, wordList.size() - 1, wordSearch);

        if (index >= 0) {
            result.add(wordList.get(index));
            int left = index - 1, right = index + 1;

            while (left >= 0) {
                Word leftWord = wordList.get(left--);
                if (leftWord.getWordTarget().startsWith(wordSearch))
                    result.add(leftWord);
                else
                    break;
            }

            while (right < this.getWordListSize()) {
                Word leftWord = wordList.get(right++);
                if (leftWord.getWordTarget().startsWith(wordSearch))
                    result.add(leftWord);
                else
                    break;
            }
        }
        return result;
    }


    public  ArrayList<Word> getWordList() {
        return wordList;
    }

    public int getWordListSize() {
        return wordList.size();
    }


}
