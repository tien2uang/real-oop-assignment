package sample;

import cmd.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HistoryController {
    @FXML
    private Button btn1;
    @FXML
    private HBox hBox;
    @FXML
    private Label targetLabel;
    @FXML
    private Label spellingLabel;
    private Word word;

    public void click(ActionEvent actionEvent){
//        if(actionEvent.getSource()==btn1){
//           hBox.getChildren().remove(btn1);
//        }
    }

    public void setHistory(Word word)
    {
        this.word=word;
        targetLabel.setText(word.getWordTarget());
        spellingLabel.setText(word.getWordSpelling());
    }

    public Label getTargetLabel() {
        return targetLabel;
    }
    @FXML
    public void initialize(){
        targetLabel.setText("");
        spellingLabel.setText("");
    }
}
