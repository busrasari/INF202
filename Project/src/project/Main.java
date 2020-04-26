/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author busra
 */
public class Main extends Application {
	
	private FXMLLoader loader;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Test.fxml"));
			CalisanlarController controller = new CalisanlarController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("JavaFX + Database + Jasper Report Basic CRUD");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
