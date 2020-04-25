package project;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import project.CalisanlarController;
import javafx.scene.Node;
import java.sql.*;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import project.DbaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;




public class CalisanlarController implements Initializable {
ObservableList<String> levelList = FXCollections.observableArrayList("Level 1", "Level 2", "Level 3" );
  
  @FXML
  private ChoiceBox<String> level;
  
  @FXML
  private AnchorPane personel;
  
 

   
        Connection conn;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_ad;
    @FXML
    private TextField txt_soyad;
    @FXML
    private Button kaydetb;
    @FXML
    private Button yenib;
    @FXML
    private Button duzenleb;
    @FXML
    private Button silb;
    @FXML
    private TableView<Calisanlar> tablo_personel;
    
  
	private FXMLLoader loader;
	private String query, firstname, lastname, seviye;
	DataAccesObject dao;
	private DBConnection database;
	private Connection connect;
	private Map<String, Object> map;
	private boolean EDIT=false, ADD=true;
	private int ID;
    @FXML
    private TableColumn<Calisanlar,Integer> idsutun;
    @FXML
    private TableColumn<Calisanlar, String> adsutun;
    @FXML
    private TableColumn<Calisanlar,String> soyadsutun;
    @FXML
    private TableColumn<Calisanlar,String> seviyesutun;
    
  
  public void initialize(URL url, ResourceBundle rb) {
     
    loadData();
  }
  
  private void loadData() {
    this.levelList.removeAll((Collection)this.levelList);
    String a = "Level 1";
    String b = "Level 2";
    String c = "Level 3";
    this.levelList.addAll( a, b, c );
    this.level.getItems().addAll((Collection)this.levelList);
  }
  

	
	private void initTable() {
            CalisanlarController a;
		//adsutun.setCellValueFactory(cell->cell.getValue().getpname();
		soyadsutun.setCellValueFactory(cell->cell.getValue().getpLastname());
		seviyesutun.setCellValueFactory(cell->cell.getValue().getpPosition());
		idsutun.setCellValueFactory(cell->cell.getValue().getpID().asObject());
	}
	
	private void refreshTable() {
		initTable();
		query = "SELECT  a.account_ID, a.firstname, a.lastname, p.seviye FROM account as a " + 
				"JOIN positions as p ON a.position_ID=p.position_ID " + 
				"ORDER BY a.firstname";
                tablo_personel.setItems(dao.getAccountsData(query));
		
	}
	
	private void saveAccount() { // for saving
		
		firstname = txt_ad.getText();
		lastname = txt_soyad.getText();
                seviye= level.getValue();
		//position = combo_position.getSelectionModel().getSelectedIndex()+1+""; // plus 1 since index starts with 0 and primary key starts with 1
		
		if(EDIT) { // if edit button is pressed
			query = "UPDATE account SET firstname='"+firstname+"', lastname='"+lastname+"', level="+seviye+" WHERE account_ID="+ID+"";   
		}else if(ADD){ // if add button is pressed
			query = "INSERT INTO account VALUES(null, '"+firstname+"', '"+lastname+"', '"+"', "+seviye+");";
		}
		
		dao.saveData(query);
		
		txt_ad.setText("");
		txt_soyad.setText("");
	//	combo_gender.getSelectionModel().select(0);
	//	combo_position.getSelectionModel().select(0);
                level.setValue(query);
		
		refreshTable();
		
		ADD = true;
	}
	
	private void deleteAccount() {
		Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		query = "DELETE FROM account WHERE account_ID="+ID+"";
		dao.saveData(query);
		refreshTable();
	}
	
	private void editAccount() { // for updating existing account
		Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
		ID = selected.getpID().get();
		txt_ad.setText(selected.getpname().get());
		txt_soyad.setText(selected.getpLastname().get());
		//combo_gender.getSelectionModel().select(selected.getpGender().get());
		level.getSelectionModel().select(selected.getpPosition().get());
	}
	
	private void insertNewAccount() { // for adding new account
		txt_ad.setText("");
		txt_soyad.setText("");
                level.setValue(query);
		//combo_gender.getSelectionModel().select(0);
		//combo_position.getSelectionModel().select(0);
	}
	
	/* private void initGender() {
		List<String> list = new ArrayList<String>();

		// foreach loop
		for(String content:agender) {
			list.add(content);
		}
		
		// convert list to observable list
		ObservableList obList = FXCollections.observableArrayList(list);
		combo_gender.setItems(obList);

	}
	*/
        
	/*private void initPosition() {
		combo_position.getSelectionModel().clearSelection();
		query = "SELECT position FROM positions";
		combo_position.setItems(dao.getPositionComboBox(query));
	}
*/
	
	/* private void showPosition() {
		try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Position.fxml"));
			PositionController controller = new PositionController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("Position.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
*/
}

