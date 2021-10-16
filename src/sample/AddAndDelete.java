package sample;
import cmd.Dictionary;
import cmd.DictionaryManagement;
import cmd.Word;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import java.util.ResourceBundle;


public class AddAndDelete  implements Initializable {
    Dictionary dictionary;
    @FXML
    private JFXTextField addWordTarget;

    @FXML
    private JFXTextField addWordClass;

    @FXML
    private JFXTextField addWordSpelling;

    @FXML
    private JFXTextArea addWordMeaning;

    @FXML
    private Button saveAddWord;

    @FXML
    private JFXTextField deleteWord;

    @FXML
    private ListView listDeleteWord;

    @FXML
    private Button delete;

    @FXML
    private JFXCheckBox check;

    @FXML
    private Label message;

    public boolean isExisting(String wordTarget) {
        Word word = (new Dictionary()).getWord2(wordTarget);
        return (word != null) && (word.getWordTarget().compareToIgnoreCase(wordTarget) == 0);
    }

    public boolean isEmpty() {
        String textTarget = addWordTarget.getText();
        String textSpelling = addWordSpelling.getText();
        String textClass = addWordClass.getText();
        String textExplain = addWordMeaning.getText();
        return ((textTarget.isEmpty()) || (textSpelling.isEmpty()) || (textClass.isEmpty()) || (textExplain.isEmpty()));
    }
    public void addWordToList(MouseEvent event) {
        if (event.getSource() ==  saveAddWord && check.isSelected() && !isEmpty()) {
            if (!isExisting(addWordTarget.getText())) {
                String target = addWordTarget.getText();
                String wordNewClass = addWordClass.getText();
                String spellings = addWordSpelling.getText();
                String explain = addWordMeaning.getText();
                (new Dictionary()).addWord(new Word(target, explain, spellings, wordNewClass));//can than
                message.setText("Successfully! You have just added '" + addWordTarget.getText() + "' to the list.");
                addWordTarget.clear();
                addWordSpelling.clear();
                addWordClass.clear();
                addWordMeaning.clear();
                check.setSelected(false);
            } else {
                message.setText("Failed to add '" + addWordTarget.getText() + "' because the word is existing.");
                addWordClass.clear();
                addWordSpelling.clear();
                addWordMeaning.clear();
            }
        } else if (isEmpty()) {
            message.setText("Any empty space must be filled.");
        } else {
            message.setText("You need to click agreement checkbox for adding word.");
        }
    }

    public void enterDelete(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && (!deleteWord.getText().isEmpty())) {
            if (isExisting(deleteWord.getText())) {
                new DictionaryManagement(this.dictionary).removeWord(deleteWord.getText());
                message.setText("Successfully! You have just deleted '" + deleteWord.getText() + "' from the list.");
                deleteWord.clear();
            } else {
                message.setText("Failed to delete '" + deleteWord.getText() + "' because that word doesn't exist.");
            }
        } else {
            message.setText("You need to type in word you want to delete.");
        }
    }
    public void listViewDeleteWord() {
        String word = deleteWord.getText();
        listDeleteWord.setItems((new DictionaryManagement(this.dictionary)).listTarget(word));
    }

    public void clickToChooseDeleteWord(MouseEvent event) {
        try {
            String inputMouseToDelete = listDeleteWord.getSelectionModel().getSelectedItem().toString();
            if(!inputMouseToDelete.equals("No result")) {
                deleteWord.setText(inputMouseToDelete);
            }
        } catch (NullPointerException e) {
            System.out.println("Error!");
        }
    }

    public void clickToDelete(MouseEvent event) {
        if (event.getSource() == delete && (!deleteWord.getText().isEmpty())) {
            if (isExisting(deleteWord.getText())) {
                String deleteText = deleteWord.getText();
                new DictionaryManagement(this.dictionary).removeWord(deleteText);
                listDeleteWord.setItems((new DictionaryManagement(this.dictionary)).listTarget(deleteText));
                message.setText("Successfully! You have just deleted '" + deleteText + "' from the list.");
                deleteWord.clear();
            } else {
                message.setText("Failed to delete '" + deleteWord.getText() +  "' because that word doesn't exist.");
            }
        } else if (event.getSource() == delete && (deleteWord.getText().isEmpty())) {
            message.setText("You need to type in word you want to delete.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RequiredFieldValidator targetValidator = new RequiredFieldValidator();
        RequiredFieldValidator classValidator = new RequiredFieldValidator();
        RequiredFieldValidator spellingValidator = new RequiredFieldValidator();
        RequiredFieldValidator explainValidator = new RequiredFieldValidator();
        addWordTarget.getValidators().add(targetValidator);
        addWordSpelling.getValidators().add(spellingValidator);
        addWordClass.getValidators().add(classValidator);
        addWordMeaning.getValidators().add(explainValidator);
        targetValidator.setMessage("No input given");
        classValidator.setMessage("No input given");
        spellingValidator.setMessage("No input given");
        explainValidator.setMessage("No input given");
        addWordTarget.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                addWordTarget.validate();
            }
        });
        addWordSpelling.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                addWordSpelling.validate();
            }
        });
        addWordClass.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                addWordClass.validate();
            }
        });
        addWordMeaning.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                addWordMeaning.validate();
            }
        });
        try {
            Image alert = new Image(new FileInputStream("resource/img/alert.png"));
            targetValidator.setIcon(new ImageView(alert));
            spellingValidator.setIcon(new ImageView(alert));
            classValidator.setIcon(new ImageView(alert));
            explainValidator.setIcon(new ImageView(alert));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

