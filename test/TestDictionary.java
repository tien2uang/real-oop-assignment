

import org.junit.Assert;
import org.junit.Test;
import cmd.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestDictionary {
    @Test
    public void addWordTest() {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord(new Word("cat", "mèo"));
        dictionary.addWord(new Word("explain", "giải thích"));
        dictionary.addWord(new Word("dog", "chó"));
        dictionary.addWord(new Word("apple", "táo"));
        dictionary.addWord(new Word("air", "kk"));
        dictionary.addWord(new Word("ear", "tai"));
        dictionary.addWord(new Word("ear", "tai"));
        for (Word word : dictionary.getWordList()) {
            System.out.println(word.getWordTarget());
        }
    }

    @Test
    public void testGetWord() {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord(new Word("cat", "mèo"));
        dictionary.addWord(new Word("explain", "giải thích"));
        dictionary.addWord(new Word("dog", "chó"));
        dictionary.addWord(new Word("apple", "táo"));
        dictionary.addWord(new Word("air", "kk"));
        dictionary.addWord(new Word("ear", "tai"));
        dictionary.addWord(new Word("ear", "tai"));
        dictionary.addWord(new Word("zoo", "tai"));

        Word word = dictionary.getWord("air");
        System.out.println(word.getWordTarget());

    }
    @Test
    public void testSearchIndexWord(){
        Dictionary dictionary = new Dictionary();
        dictionary.addWord(new Word("cat", "mèo"));
        dictionary.addWord(new Word("explain", "giải thích"));
        dictionary.addWord(new Word("dog", "chó"));
        dictionary.addWord(new Word("apple", "táo"));
        dictionary.addWord(new Word("air", "kk"));
        dictionary.addWord(new Word("ear", "tai"));
        dictionary.addWord(new Word("ear", "tai"));
        dictionary.addWord(new Word("zoo", "tai"));
        for (Word word : dictionary.getWordList()) {
            System.out.println(word.getWordTarget());
        }
        //Assert.assertEquals(4,dictionary.searchIndexWord(0,dictionary.getWordList().size()-1,"ear"));
        int result = dictionary.searchIndexWord(0,dictionary.getWordList().size()-1,"ear");
        System.out.println(result);
    }



}
