package sample;

import Translate.GoogleTTS;
import cmd.Dictionary;
import cmd.Word;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public JFXTextArea wordMeaning;

    @FXML
    public Label wordTarget;

    @FXML
    public Label wordClass;

    @FXML
    public Label wordSpellings;

    @FXML
    public Label wordSecondSpellings;
    @FXML
    private Label definitions;
    @FXML
    private Button speakingButton;

    public void updateListView() {
        String word = inputSearch.getText();
        if (word.length() > 0) {
            wordListSearch.setVisible(true);
        } else {
            wordListSearch.setVisible(false);
        }
        wordListSearch.setItems((new DictionaryManagement(this.dictionaryData)).listTarget(word));
    }

    public void wordLookUp(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String wordLook = inputSearch.getText();
            Word word = new DictionaryManagement(this.dictionaryData).getWord(wordLook);
//            if (word.getWordSpelling().contains(";")) {
//                StringTokenizer wordText = new StringTokenizer(word.getWordSpelling(), ";");
//                String firstSpelling = wordText.nextToken().trim();
//                String secondSpelling = wordText.nextToken().trim();
//                wordSpellings.setText(firstSpelling);
//                wordSecondSpellings.setText(secondSpelling);
//                wordSpellings.setText(word.getWordSpelling());
//            } else {
//                wordSpellings.setText(word.getWordSpelling());
//                wordSecondSpellings.setText("");
//            }
            wordSpellings.setText(word.getWordSpelling());
            wordTarget.setText(word.getWordTarget());
            wordClass.setText(word.getWordClass());
            wordMeaning.setText(word.getWordExplain());
        }
    }
    
    public void clickWord(MouseEvent event) {
        try {
            String inputMouseSetText = wordListSearch.getSelectionModel().getSelectedItem().toString();
            if(inputMouseSetText!="No result") {
                inputSearch.setText(inputMouseSetText);
                String wordMeaningMouseSetText = wordListSearch.getSelectionModel().getSelectedItem().toString();
                Word word = new DictionaryManagement(this.dictionaryData).getWord(wordMeaningMouseSetText);
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
                speakingButton.setVisible(true);
                definitions.setVisible(true);
                speakingButton.setDisable(false);
                wordTarget.setText(word.getWordTarget());
                wordClass.setText(word.getWordClass());
                wordMeaning.setText(word.getWordExplain());
            }
        } catch (NullPointerException e) {
            System.out.println("Error!");
        }
    }
    public void speak(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource()==speakingButton){
            GoogleTTS.speak(wordTarget.getText());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordListSearch.setVisible(false);
        speakingButton.setVisible(false);
        speakingButton.setDisable(true);
        definitions.setVisible(false);
        DictionaryManagement.InsertFromFile("resource/dictionaries/dict.txt");
    }
}
