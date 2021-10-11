package sample;

import Translate.Translate;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class TextController {

    @FXML
    private JFXTextArea inputText;
    @FXML
    private JFXTextArea outputText;
    @FXML
    private Label firstLanguage;
    @FXML
    private Label secondLanguage;
    @FXML
    private Button changeLanguageButton;
    @FXML
    private Label translatingLabel;
    private StringProperty firstLanguageProp, secondLanguageProp;


    @FXML
    public void handleInput(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getSource() == inputText) {

            String text = inputText.getText();

            if (text.length() >= 1) {

                translatingLabel.setVisible(true);
                if (text.charAt(text.length() - 1) == ' ') {
                    //translatingLabel.setText("Translating...");
                    outputText.setText(Translate.translate(firstLanguageProp.getValue().substring(0, 2).toLowerCase(),
                            secondLanguageProp.getValue().substring(0, 2).toLowerCase(),
                            text.trim()));
                    if(Translate.requestStatus==true){
                       translatingLabel.setVisible(false);

                    }


                }
            } else {
                outputText.setText("");
                translatingLabel.setVisible(false);
                //translatingLabel.setText("");

            }

        }
    }

    @FXML
    private void handleLanguageChange(ActionEvent actionEvent) {
        String temp = firstLanguageProp.getValue();
        firstLanguageProp.setValue(secondLanguageProp.getValue());
        firstLanguage.setText(secondLanguageProp.getValue());
        secondLanguageProp.setValue(temp);
        secondLanguage.setText(temp);
        String tempText = inputText.getText();
        inputText.setText(outputText.getText());
        outputText.setText(tempText);

    }


    @FXML
    public void initialize() {


        translatingLabel.setVisible(false);
        firstLanguageProp = new SimpleStringProperty("English");
        secondLanguageProp = new SimpleStringProperty("Vietnamese");
        firstLanguage.setText("English");
        secondLanguage.setText("Vietnamese");



    }
}
