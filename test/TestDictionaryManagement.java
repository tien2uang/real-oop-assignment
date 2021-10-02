import cmd.Dictionary;
import cmd.DictionaryManagement;
import org.junit.Test;

public class TestDictionaryManagement {
    @Test
    public void testInsertFromFile(){
        Dictionary dictionary= new Dictionary();
        DictionaryManagement dictionaryManagement= new DictionaryManagement(dictionary);
        dictionaryManagement.insertFromFile("src/resource/dictionaries/dictionaries.txt");
        dictionaryManagement.showAllWords();
    }
}
