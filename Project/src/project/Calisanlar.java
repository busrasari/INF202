package project;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    private final StringProperty pname;
    private final StringProperty pLastname;

	private final StringProperty pSeviye;
	private final StringProperty pID;
	
	public Calisanlar(String pID, String pname, String pLastname, String pSeviye ) {
                this.pID = new SimpleStringProperty(pID);
		this.pname = new SimpleStringProperty(pname);
		this.pLastname = new SimpleStringProperty(pLastname);
		this.pSeviye = new SimpleStringProperty(pSeviye);
		
	}
	
	public StringProperty getpname() {
		return pname;
	}
	
	public StringProperty getpLastname() {
		return pLastname;
	}
	
	
	public StringProperty getpSeviye() {
		return pSeviye;
	}
	public StringProperty getpID() {
		return pID;
	}
	   
    
}
