package sample;

import cmd.Dictionary;
import cmd.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import cmd.DictionaryManagement;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


public class WordController implements Initializable {
    private Dictionary dictionaryData;

    @FXML
    public TextField inputSearch;

    @FXML
    public ListView<String> wordListSearch;

    @FXML
    public TextArea wordMeaning;

    @FXML
    public Label wordTarget;

    @FXML
    public Label wordClass;

    @FXML
    public Label wordSpellings;

    @FXML
    public Label wordSecondSpellings;

    public void updateListView() {
        String word = inputSearch.getText();
        wordListSearch.setItems((new DictionaryManagement(this.dictionaryData)).listTarget(word));
    }

    public void wordLookUp(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String wordLook = inputSearch.getText();
            Word word = new DictionaryManagement(this.dictionaryData).searchWord(wordLook);
            if (word.getWordSpelling().contains(";")) {
                StringTokenizer wordText = new StringTokenizer(word.getWordSpelling(), ";");
                String firstSpelling = wordText.nextToken().trim();
                String secondSpelling = wordText.nextToken().trim();
                wordSpellings.setText(firstSpelling);
                wordSecondSpellings.setText(secondSpelling);
            } else {
                wordSpellings.setText(word.getWordSpelling());
                wordSecondSpellings.setText("");
            }
            wordTarget.setText(word.getWordTarget());
            wordClass.setText(word.getWordClass());
            wordMeaning.setText(word.getWordExplain());
        }
    }

    public void clickWord(MouseEvent event) {
        try {
            String inputMouseSetText = wordListSearch.getSelectionModel().getSelectedItem().toString();
            inputSearch.setText(inputMouseSetText);
            String wordMeaningMouseSetText = wordListSearch.getSelectionModel().getSelectedItem().toString();
            Word word = new DictionaryManagement(this.dictionaryData).searchWord(wordMeaningMouseSetText);
            if (word.getWordSpelling().contains(";")) {
                StringTokenizer wordText = new StringTokenizer(word.getWordSpelling(), ";");
                String firstSpelling = wordText.nextToken();
                String secondSpelling = wordText.nextToken();
                wordSpellings.setText(firstSpelling);
                wordSecondSpellings.setText(secondSpelling);
            } else {
                wordSpellings.setText(word.getWordSpelling());
                wordSecondSpellings.setText("");
            }
            wordTarget.setText(word.getWordTarget());
            wordClass.setText(word.getWordClass());
            wordMeaning.setText(word.getWordExplain());
        } catch (NullPointerException e) {
            System.out.println("Error!");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.InsertFromFile("resource/dictionaries/dict.txt");
    }
}
