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
	private int ID;
    @FXML
    private TableColumn<Calisanlar,Integer> idsutun;
    @FXML
    private TableColumn<Calisanlar, String> adsutun;
    @FXML
    private TableColumn<Calisanlar,String> soyadsutun;
    @FXML
    private TableColumn<Calisanlar,String> seviyesutun;
    @FXML
    private TableColumn<?, ?> idsutun1;
    
  
  public void initialize(URL url, ResourceBundle rb) {
     
    loadData();
    initTable();
    	dao = new DataAccesObject();
		//initGender();
	/*	btn_position.setOnAction(e->{
			showPosition();
		});
		*/
		/*combo_position.setOnMouseClicked(e->{
			initPosition();
		}); */
		
		
                kaydetb.setOnAction(e->{
			saveAccount();
		});
		/* btn_print_preview.setOnAction(e->{
			printReport();
		}); */
		duzenleb.setOnAction(e->{
			ADD = false;
			EDIT = true;
			editAccount();
		});
		yenib.setOnAction(e->{
			EDIT = false;
			ADD = true;
			insertNewAccount();
		});
		silb.setOnAction(e->{
			deleteAccount();
		});
		

		//initPosition();
		//combo_gender.getSelectionModel().select(0);
		//combo_position.getSelectionModel().select(0);
		
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
    
		adsutun.setCellValueFactory(cell->cell.getValue().getpname());
                idsutun.setCellValueFactory(cell->cell.getValue().getpID().asObject());
		soyadsutun.setCellValueFactory(cell->cell.getValue().getpLastname());
		seviyesutun.setCellValueFactory(cell->cell.getValue().getpPosition());
		}
	
	private void refreshTable() {
		initTable();
		query = "SELECT  a.account_ID, a.firstname, a.lastname, p.seviye FROM account as a " + 
				"JOIN positions as p ON a.position_ID=p.position_ID " + 
				"ORDER BY a.firstname";
                tablo_personel.setItems(dao.getAccountsData(query));
		}
	
	private void saveAccount() { // for saving
		
		id= txt_id.getText();
                firstname = txt_ad.getText();
		lastname = txt_soyad.getText();
                seviye= level.getValue();
		//position = combo_position.getSelectionModel().getSelectedIndex()+1+""; // plus 1 since index starts with 0 and primary key starts with 1
		
		if(EDIT) { // if edit button is pressed
			query = "UPDATE account SET id='"+id+"', firstname='"+firstname+"', lastname='"+lastname+"', level="+seviye+" WHERE account_ID="+id+"";   
		}else if(ADD){ // if add button is pressed
			query = "INSERT INTO account VALUES(null, '"+firstname+"', '"+lastname+"', '"+"', "+seviye+");";
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
	
	PreparedStatement ps;
        private String insertNewAccount() { // for adding new account
        String id=txt_id.getText();
        String name=txt_ad.getText();
        String nach=txt_soyad.getText();
        String lev=level.getValue();
        
        System.out.println("buraya 1");
        String sql="INSERT INTO CALISANLAR('ID','AD','SOYAD','SEVIYE') VALUES(?,?,?,?)";
        System.out.println("buraya 2");
        
        try {

            System.out.println("buraya 3"); 

            ps=database.connect.prepareStatement(sql);


            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, nach);
            ps.setString(4, lev);
            ps.executeUpdate();
            System.out.println("buraya 5"); 
            return "başarılı";

        } catch (Exception ex) {
            System.out.println("buraya 6"); 

            Logger.getLogger(CalisanlarController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("buraya 4");
            return "Ecception";
        }


    }
            
                
                
                /*txt_id.setText("");
                txt_ad.setText("");
		txt_soyad.setText("");
                level.setValue(query);
                dao.saveData(query);
                refreshTable(); */
              
		//combo_gender.getSelectionModel().select(0);
		//combo_position.getSelectionModel().select(0);

    @FXML
    private void setOnAction(MouseEvent event) {
           saveAccount();
    }

    @FXML
    private void SetonAction(MouseEvent event) {
        insertNewAccount();
    }
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



/*
@Override
    public void initialize(URL url, ResourceBundle rb) {
        database.baglan();
    }

    PreparedStatement ps;


    @FXML
    private String ekle(MouseEvent event){

        String name=ad.getText();
        String nach=soyad.getText();
        String lev=level.getText();
        String id=ıd.getText();
        System.out.println("buraya 1");
        String sql="INSERT INTO CALISANLAR(ID,AD,SOYAD,SEVIYE) VALUES(?,?,?,?)";
        System.out.println("buraya 2");



        try {

            System.out.println("buraya 3"); 

            ps=database.connection.prepareStatement(sql);


            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, nach);
            ps.setString(4, lev);
            ps.executeUpdate();
            System.out.println("buraya 5"); 
            return "başarılı";

        } catch (Exception ex) {
            System.out.println("buraya 6"); 

            Logger.getLogger(EkleController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("buraya 4");
            return "Ecception";
        }


    }
*/