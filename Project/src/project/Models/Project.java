package project.Models;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
//import project.Classlar.Project;

public class Project extends Application {
    private double xOffset = 0.0D;

    private double yOffset = 0.0D;

    @FXML
    private Button newreport;

    public static void main(String[] args) throws SQLException {
        launch(args);
        //DBConnection a=new DBConnection();
        //a.toplam();

    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/project/fxml/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
       // stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(event -> {
            this.xOffset = event.getSceneX();
            this.yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - this.xOffset);
            stage.setY(event.getScreenY() - this.yOffset);
        });
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}