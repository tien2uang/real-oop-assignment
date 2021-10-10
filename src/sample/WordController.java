package sample;

import cmd.Dictionary;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


public class WordController implements Initializable {
    private Dictionary dictionaryData;

    @FXML
    public TextField inputSearch;

    @FXML
    public ListView<String> wordListSearch;

    @FXML
    public TextArea wordMeaning;

    public void updateListView() {
        String word = inputSearch.getText();
        wordListSearch.setItems((new DictionaryManagement(this.dictionaryData)).listTarget(word));
    }

    public void wordLookUp(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String wordLook = inputSearch.getText();
            wordMeaning.setText(new DictionaryManagement(this.dictionaryData).lookWordMeaning(wordLook));
        }
    }

    public void clickWord(MouseEvent event) {
        try {
            String inputMouseSetText = wordListSearch.getSelectionModel().getSelectedItem().toString();
            inputSearch.setText(inputMouseSetText);
            String wordMeaningMouseSetText = new DictionaryManagement(this.dictionaryData).lookWordMeaning(wordListSearch.getSelectionModel().getSelectedItem().toString());
            wordMeaning.setText(wordMeaningMouseSetText);
        } catch (NullPointerException e) {
            System.out.println("Error!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            DictionaryManagement.InsertFromFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
