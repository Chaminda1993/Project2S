import GUI.HomePageController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class Starter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    double x,y;

    @Override
    public void start(Stage primaryStage) {
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource("GUI/HomePage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Scene scene = new Scene(root,screenSize.getWidth(),screenSize.getHeight());
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll("GUI/style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("BookLab ICS");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
