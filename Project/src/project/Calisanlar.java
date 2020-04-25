package project;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author busra
 */
public class Calisanlar {
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("Calisanlar.fxml"));
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
	        stage.setScene(scene);
	        stage.setTitle("Personel Ekleme Bölümü");
	        stage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
    
}
