package sample;

import cmd.Dictionary;
import cmd.DictionaryManagement;
import cmd.Word;
import cmd.WordProperty;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class EditWord {
    private final Dictionary dictionary = DictionaryManagement.getDictionary();
    private final ObservableList<WordProperty> historyList = DictionaryManagement.getHistoryList();
    @FXML
    private JFXTextField wordTarget;
    @FXML
    private JFXTextField wordClass;
    @FXML
    private JFXTextField wordSpelling;
    @FXML
    private JFXTextArea wordExplain;
    @FXML
    private TextField inputSearch;
    @FXML
    private Label notification;
    @FXML
    private Button submit;
    @FXML
    private JFXCheckBox checkBox;
    @FXML
    private ListView<String> listEditWord;

    public String tempTarget = "";

    public boolean isEmpty() {
        String textTarget = wordTarget.getText();
        String textSpelling = wordSpelling.getText();
        String textClass = wordClass.getText();
        String textExplain = wordExplain.getText();
        return ((textTarget.isEmpty()) || (textSpelling.isEmpty()) || (textClass.isEmpty()) || (textExplain.isEmpty()));
    }

    private void updateHistoryList(Word word, int index) {
        historyList.get(index).setWordTarget(word.getWordTarget());
        historyList.get(index).setWordSpelling(word.getWordSpelling());
        historyList.get(index).setWordExplain(word.getWordExplain());
        historyList.get(index).setWordClass(word.getWordClass());
    }

    private int isInHistoryList(Word word) {
        WordProperty wordProperty = new WordProperty(word);
        return historyList.indexOf(wordProperty);
    }

    public void editWord(MouseEvent event) {

        if (event.getSource() == submit) {
            notification.setVisible(true);
            if (checkBox.isSelected() && !isEmpty()) {
                Word tempWord = this.dictionary.getWord(tempTarget);
                if (!tempWord.equals(null)) {
                    int index = isInHistoryList(tempWord);
                    tempWord.setWordClass(wordClass.getText());
                    tempWord.setWordExplain(wordExplain.getText());
                    tempWord.setWordSpelling(wordSpelling.getText());
                    tempWord.setWordTarget(wordTarget.getText());
                    if (index != -1) {
                        updateHistoryList(tempWord, index);
                    } else {
                        System.out.println("khong tim thay");
                    }
                    notification.setTextFill(Color.web("#52C41A"));
                    notification.setText("Successful!");
                    reset();
                    inputSearch.clear();
                }
            } else if (isEmpty()) {
                notification.setTextFill(Color.web("#FF4D4F"));
                notification.setText("Any empty space must be filled.");

            } else {
                notification.setTextFill(Color.web("#FF4D4F"));
                notification.setText("You need to fill the agreement checkbox.");
            }
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
            reset();
        }
        listEditWord.setItems((new DictionaryManagement(this.dictionary)).listTarget(word));
    }

    public void clickWord(MouseEvent event) {
        try {
            String inputMouseSetText = listEditWord.getSelectionModel().getSelectedItem();
            if (!inputMouseSetText.equals("No result")) {
                notification.setVisible(true);
                inputSearch.setText(inputMouseSetText);
                listEditWord.setVisible(false);
                int index = this.dictionary.searchIndexWord(0, this.dictionary.getWordList().size(), inputMouseSetText);
                wordTarget.setText(this.dictionary.getWordList().get(index).getWordTarget());
                wordClass.setText(this.dictionary.getWordList().get(index).getWordClass());
                wordSpelling.setText(this.dictionary.getWordList().get(index).getWordSpelling());
                wordExplain.setText(this.dictionary.getWordList().get(index).getWordExplain());
                tempTarget = inputMouseSetText;
                notification.setTextFill(Color.web("#1890FF"));
                notification.setText("Sửa đê bạn ey!");
            }
        } catch (NullPointerException e) {
            System.out.println("Error!");
        }
    }

    public void checkAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == wordClass
                || mouseEvent.getSource() == wordExplain
                || mouseEvent.getSource() == wordSpelling
                || mouseEvent.getSource() == wordTarget
                || mouseEvent.getSource() == checkBox
                || mouseEvent.getSource() == inputSearch) {
            notification.setVisible(false);

        }
    }

    private void reset() {
        wordTarget.clear();
        wordClass.clear();
        wordSpelling.clear();
        wordExplain.clear();
        checkBox.setSelected(false);
    }

    @FXML
    public void initialize() {
        listEditWord.setVisible(false);
        notification.setVisible(false);

    }
}
