package sample;

import cmd.Dictionary;
import cmd.DictionaryManagement;
import cmd.Word;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class AdvancedController {
    //history fields
    @FXML
    private VBox historyVBox;
    @FXML
    private HBox history = null;

    //history methods


    @FXML
    public void initialize() {
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

//                     HistoryController historyController =(HistoryController) fxmlLoader.getController();
            //            historyController.getTargetLabel().setText("abc");
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
