package sample;

import cmd.Dictionary;
import cmd.DictionaryManagement;
import cmd.Word;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class EditWord  {
    Dictionary dictionary = DictionaryManagement.getDictionary();

    @FXML
    private  JFXTextField wordTarget;
    @FXML
    private  JFXTextField wordClass;
    @FXML
    private  JFXTextField wordSpelling;
    @FXML
    private  JFXTextArea wordExplain;
    @FXML
    private  TextField inputSearch;
    @FXML
    private Label notification;
    @FXML
    private  Button submit;
    @FXML
    private  JFXCheckBox checkBox;
    @FXML
    private ListView<String> listEditWord;
    @FXML
    private MouseEvent event;

    public boolean isEmpty() {
        String textTarget = wordTarget.getText();
        String textSpelling = wordSpelling.getText();
        String textClass = wordClass.getText();
        String textExplain = wordExplain.getText();
        return ((textTarget.isEmpty()) || (textSpelling.isEmpty()) || (textClass.isEmpty()) || (textExplain.isEmpty()));
    }


    public void editWord(MouseEvent event ) {

        if (event.getSource() == submit && checkBox.isSelected() && !isEmpty()) {

            String wordLookup = inputSearch.getText();
            int index = this.dictionary.searchIndexWord(0, this.dictionary.getWordList().size(), wordLookup);

            String target = wordTarget.getText();
            String wordNewClass = wordClass.getText();
            String spellings = wordSpelling.getText();
            String explain = wordExplain.getText();

            this.dictionary.getWordList().remove(index);
            this.dictionary.addWord(new Word(target, explain, spellings, wordNewClass));


            notification.setText("thanh cong");

            wordTarget.clear();
            wordClass.clear();
            wordSpelling.clear();
            wordExplain.clear();
            checkBox.setSelected(false);
        } else if (isEmpty()) {

            notification.setText("check Box or word your input");
        }

    }


    public void listViewWord() {
        String word = inputSearch.getText();
        listEditWord.setItems((new DictionaryManagement(this.dictionary)).listTarget(word));
    }

    public void updateListView() {
        String word = inputSearch.getText();
        if (word.length() > 0) {
            listEditWord.setVisible(true);
        } else {
            listEditWord.setVisible(false);
        }
        listEditWord.setItems((new DictionaryManagement(this.dictionary)).listTarget(word));
    }

    public void clickWord(MouseEvent event) {
        try {
            String inputMouseSetText = listEditWord.getSelectionModel().getSelectedItem().toString();
            if(inputMouseSetText!="No result") {
                inputSearch.setText(inputMouseSetText);
                listEditWord.setVisible(false);
                int index = this.dictionary.searchIndexWord(0, this.dictionary.getWordList().size(), inputMouseSetText);
                wordTarget.setText(this.dictionary.getWordList().get(index).getWordTarget());
                wordClass.setText(this.dictionary.getWordList().get(index).getWordClass());
                wordSpelling.setText(this.dictionary.getWordList().get(index).getWordSpelling());
                wordExplain.setText(this.dictionary.getWordList().get(index).getWordExplain());


                notification.setText("sua ben nay ==>");
            }
        } catch (NullPointerException e) {
            System.out.println("Error!");
        }
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }
}
