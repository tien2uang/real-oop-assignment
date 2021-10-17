package sample;

import cmd.DictionaryManagement;
import cmd.Word;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class AdvancedController {
    //history fields
    @FXML
    private VBox historyVBox;


    //history methods

    @FXML
    private AnchorPane addAndDeleteMain;

    @FXML
    private AnchorPane addAndDelete;

    @FXML
    private AnchorPane editWord;

    @FXML
    private AnchorPane editWordMain;



    @FXML
    public void initialize() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddAndDelete.fxml"));
            addAndDelete = fxmlLoader.load();
            AddAndDelete addDelete = new AddAndDelete();
            fxmlLoader.setController(addDelete);
            this.addAndDeleteMain.getChildren().setAll(addAndDelete);
        } catch (IOException e) {
            System.out.println("Không tim thay file AddAndDelete.");
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EditWord.fxml"));
            editWord = fxmlLoader.load();
            EditWord editWord_ = new EditWord();
            fxmlLoader.setController(editWord_);
            this.editWordMain.getChildren().setAll(editWord);
        } catch (IOException e) {
            System.out.println("Không tim thay file EditWord.fxml");
        }

        // History
        DictionaryManagement.getHistoryList().addListener(new ListChangeListener<Word>() {
            @Override
            public void onChanged(Change<? extends Word> c) {
                historyVBox.getChildren().clear();
                ArrayList<HBox> nodeArrayList = new ArrayList<>();
                for (int i = 0; i < DictionaryManagement.getHistoryList().size(); i++) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("HistoryFXML.fxml"));
                        HistoryController historyController = new HistoryController();
                        fxmlLoader.setController(historyController);
                        nodeArrayList.add(fxmlLoader.load());
                        historyVBox.getChildren().add(nodeArrayList.get(i));
                        historyController.setHistory(DictionaryManagement.getHistoryList().get(i));
                        System.out.println(DictionaryManagement.getHistoryList().get(i).getWordTarget());
                    } catch (Exception e) {
                        System.out.println("Khong tim thay HistoryFXML");
                        e.printStackTrace();
                    }
                }
            }
        });

    }


//    public static VBox getHistoryVBox() {
//        return historyVBox;
//    }
}
