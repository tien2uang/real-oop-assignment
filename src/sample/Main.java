package sample;

import animatefx.animation.ZoomIn;
import cmd.Dictionary;
import cmd.DictionaryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.HistoryController;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {

        HistoryController.mStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        new ZoomIn(root).setSpeed(0.6).play();
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement(dictionary);
        DictionaryManagement.InsertFromFile("resource/dictionaries/dict.txt");
        launch(args);
    }
}
