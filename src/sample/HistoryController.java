package sample;

import animatefx.animation.ZoomIn;
import cmd.DictionaryManagement;
import cmd.Word;
import cmd.WordProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class HistoryController {
    @FXML
    private Button detailButton;
    @FXML
    private HBox hBox;
    @FXML
    private Label targetLabel;
    @FXML
    private Label spellingLabel;
    @FXML
    private Label classLabel;
    @FXML
    private Button deleteHistoryButton;
    private WordProperty wordProperty;

    private Word word;
    public static Stage mStage;
    private double x, y;

    public void detail(ActionEvent actionEvent) {
        if (actionEvent.getSource() ==detailButton) {

            Parent root;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("HistoryWordDetail.fxml"));
                HistoryWordController historyWordController = new HistoryWordController();
                fxmlLoader.setController(historyWordController);
                root = fxmlLoader.load();
                historyWordController.show(word);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setResizable(false);
                stage.setX(mStage.getX() + 400);
                stage.setY(mStage.getY() + 150);

                new ZoomIn(root).setSpeed(1.5).play();
                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                root.setOnMouseDragged(event -> {

                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                });
                stage.show();
            } catch (IOException e) {
                System.out.println("Khong tim thay HistoryWordDetail");
                e.printStackTrace();
            }


        }
    }
    public void delete(ActionEvent actionEvent){
        if(actionEvent.getSource()==deleteHistoryButton){
            DictionaryManagement.historyList.remove(wordProperty);
        }
    }

    public void setHistory(WordProperty wordProperty) {
        this.wordProperty=wordProperty;
        this.word = wordProperty.getWord();
        targetLabel.setText(word.getWordTarget());
        spellingLabel.setText(word.getWordSpelling());
        classLabel.setText(word.getWordClass());
    }

    public Label getTargetLabel() {
        return targetLabel;
    }

    @FXML
    public void initialize() {
        targetLabel.setText("");
        spellingLabel.setText("");
    }
}
