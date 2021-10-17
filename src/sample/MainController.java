package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {
    private AnchorPane textAnchorPane = null;
    private AnchorPane wordAnchorPane = null;
    private AnchorPane advancedAnchorPane=null;
    private AnchorPane currentPane=null;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button text_button;
    @FXML
    private Button word_button;
    @FXML
    private Button advanced_button;
    @FXML
    private Button quit_button;


    private void showTextAnchorPane() {

        this.mainBorderPane.setCenter(textAnchorPane);

        //  new FadeIn(textAnchorPane).setSpeed(2).play();

    }
    private  void showAdvancedAnchorPane(){
        this.mainBorderPane.setCenter(advancedAnchorPane);
    }

    private void showWordAnchorPane() {
        this.mainBorderPane.setCenter(wordAnchorPane);
        // new FadeIn(wordAnchorPane).setSpeed(2).play();


    }

    @FXML
    private void click(ActionEvent actionEvent) {
        if (actionEvent.getSource() == word_button) {
            this.showWordAnchorPane();
        } else if (actionEvent.getSource() == text_button) {

            this.showTextAnchorPane();
        }
        else if (actionEvent.getSource() == advanced_button) {

            this.showAdvancedAnchorPane();
        }
        else if(actionEvent.getSource()==quit_button){
            Stage stage= (Stage) mainBorderPane.getScene().getWindow();
            stage.close();
        }


    }

    @FXML
    public void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TextFXML.fxml"));
            textAnchorPane = (AnchorPane) fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Khong tim thay file TextFXML");
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WordFXML.fxml"));
            wordAnchorPane = (AnchorPane) fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Khong tim thay file WordFXML");
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AdvancedFXML.fxml"));
            advancedAnchorPane = (AnchorPane) fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Khong tim thay file AdvancedFXML");
        }


        this.showWordAnchorPane();

    }

}