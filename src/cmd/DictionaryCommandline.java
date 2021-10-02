package cmd;

public class DictionaryCommandline {
    private Dictionary dictionary = new Dictionary();

    public void dictionaryBasic() {
        (new DictionaryManagement(dictionary)).insertFromCommandLine();
        (new DictionaryManagement(dictionary)).showAllWords();
    }

    public void dictionaryAdvanced() {

    }

}
