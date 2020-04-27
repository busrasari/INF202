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
import project.DBConnection;
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
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;




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
	private String query,id, firstname, lastname, seviye;
	DataAccesObject dao;
	private DBConnection database;
	private Connection connect;
	private Map<String, Object> map;
	private boolean EDIT=false, ADD=true;

    @FXML
    private TableColumn<Calisanlar,String> idsutun;
    @FXML
    private TableColumn<Calisanlar, String> adsutun;
    @FXML
    private TableColumn<Calisanlar,String> soyadsutun;
    @FXML
    private TableColumn<Calisanlar,String> seviyesutun;
   
  
  
  public void initialize(URL url, ResourceBundle rb) {
   
   
    	dao = new DataAccesObject(); 
    loadData();
     kaydetb.setOnAction(e->{
			saveAccount();
		});
		
		duzenleb.setOnAction(e->{
			ADD = false;
			EDIT = true;
			editAccount();
                         refreshTable();
		});
		/*yenib.setOnAction(e->{
			EDIT = false;
			ADD = true;
                        insertNewAccount();
			
                        
		}); */
                
		silb.setOnAction(e->{
			deleteAccount();
		});
                initTable();
   refreshTable();
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
                idsutun.setCellValueFactory(cell->cell.getValue().getpID());
		adsutun.setCellValueFactory(cell->cell.getValue().getpname());
             // idsutun.setCellValueFactory(cell->cell.getValue().getpID().asObject());
		soyadsutun.setCellValueFactory(cell->cell.getValue().getpLastname());
		seviyesutun.setCellValueFactory(cell->cell.getValue().getpSeviye());
		} 
private void refreshTable() {
		initTable();
		query = "SELECT * FROM personel";
				//"JOIN Seviye as p ON a.Seviye=p.Seviye " + 
				//"ORDER BY a.Adı";
                tablo_personel.setItems(dao.getAccountsData(query));
		}
private void saveAccount() { // for saving
		
		id= txt_id.getText();
                firstname = txt_ad.getText();
		lastname = txt_soyad.getText();
                seviye= level.getValue();
		//position = combo_position.getSelectionModel().getSelectedIndex()+1+""; // plus 1 since index starts with 0 and primary key starts with 1
		
		if(EDIT) { // if edit button is pressed
			query = "UPDATE personel SET id='"+id+"', Adı='"+firstname+"', Soyadı='"+lastname+"', Seviye="+seviye;   
		}else if(ADD){ // if add button is pressed
			query = "INSERT INTO personel(id, Adı, Soyadı, Seviye) VALUES(?,?,?,?)";
		}
		
		dao.saveData(query);
		txt_id.setText("");
		txt_ad.setText("");
		txt_soyad.setText("");
                level.setValue(query);
		
		refreshTable();
		
		ADD = true;
	}

private void deleteAccount() {
		Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
                id = selected.getpID().get();
		//ID = selected.getpID().get();
		query = "DELETE FROM personel WHERE id="+id+"";
		dao.saveData(query);
		refreshTable();
	}
	
	private void editAccount() { // for updating existing account
		Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
		id = selected.getpID().get();
               txt_id.setText(selected.getpID().get());
		txt_ad.setText(selected.getpname().get());
		txt_soyad.setText(selected.getpLastname().get());
		//combo_gender.getSelectionModel().select(selected.getpGender().get());
		level.getSelectionModel().select(selected.getpSeviye().get());
	}
	
private void insertNewAccount() { // for adding new account
		txt_id.setText("");
                txt_ad.setText("");
		txt_soyad.setText("");
                level.setValue("");
             
              //  String lev=level.getValue();
		//combo_gender.getSelectionModel().select(0);
		//combo_position.getSelectionModel().select(0);
	} 
        
        PreparedStatement ps;
    @FXML
    private void setOnAction(MouseEvent event) throws SQLException {
            String name=txt_ad.getText();
        String nach=txt_soyad.getText();
    String id=txt_id.getText();
        String seviye=level.getValue().toString();

        String sql="INSERT INTO personel(id, Adı, Soyadı, Seviye) VALUES(?,?,?,?)";
        ps=database.connect.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, nach);
        ps.setString(4, seviye);

        System.out.println("geldi");
        ps.executeUpdate();
        System.out.println("geldi2");
        refreshTable();
}

//


    
	}
 
	
	