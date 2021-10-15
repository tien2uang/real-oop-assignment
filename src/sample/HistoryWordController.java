package sample;

import Translate.GoogleTTS;
import cmd.Word;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HistoryWordController {
    @FXML
    private Label wordClass;

    @FXML
    private Label wordSpellings;

    @FXML
    private Label wordTarget;

    @FXML
    private JFXTextArea wordMeaning;

    @FXML
    private Button speakButton;

    @FXML
    private Button closeButton;


    public void show(Word word){
        wordTarget.setText(word.getWordTarget());
        wordClass.setText(word.getWordClass());
        wordMeaning.setText(word.getWordExplain());
        wordSpellings.setText(word.getWordSpelling());
    }
    public void close(ActionEvent actionEvent){
        if(actionEvent.getSource()==closeButton){
            Stage stage= (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }
    public void speak(ActionEvent actionEvent){
        if(actionEvent.getSource()==speakButton){
            try {
                GoogleTTS.speak(wordTarget.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wordClass != null : "fx:id=\"wordClass\" was not injected: check your FXML file 'HistoryWordDetail.fxml'.";
        assert wordSpellings != null : "fx:id=\"wordSpellings\" was not injected: check your FXML file 'HistoryWordDetail.fxml'.";
        assert wordTarget != null : "fx:id=\"wordTarget\" was not injected: check your FXML file 'HistoryWordDetail.fxml'.";
        assert wordMeaning != null : "fx:id=\"wordMeaning\" was not injected: check your FXML file 'HistoryWordDetail.fxml'.";
        assert speakButton != null : "fx:id=\"speakButton\" was not injected: check your FXML file 'HistoryWordDetail.fxml'.";
        assert closeButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'HistoryWordDetail.fxml'.";


    }
}
