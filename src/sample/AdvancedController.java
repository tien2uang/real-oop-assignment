package sample;

import cmd.Dictionary;
import cmd.DictionaryManagement;
import cmd.Word;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdvancedController {
    //history fields
    @FXML
    private VBox historyVBox;
    @FXML
    private HBox history = null;

    //history methods

    @FXML
    private AnchorPane addAndDeleteMain;

    @FXML
    private AnchorPane addAndDelete;



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
                        nodeArrayList.add((HBox) fxmlLoader.load());

                        historyVBox.getChildren().add(nodeArrayList.get(i));
                        if(historyController==null){
                            System.out.println("control null");
                        }
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
