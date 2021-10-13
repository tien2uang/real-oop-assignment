package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdvancedController {
    @FXML
    private VBox historyVBox=null;

    @FXML
    public void initialize() {
        //phần History
        Node[] nodes =new Node[15];
        for(int i=0;i<nodes.length;i++){
            try{
                nodes[i]= FXMLLoader.load(getClass().getResource("HistoryFXML.fxml"));
                historyVBox.getChildren().add(nodes[i]);
            } catch (IOException e) {
                System.out.println("Khong tim thay HistoryFXML");
                e.printStackTrace();
            }
        }
    }
}
