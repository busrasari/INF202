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
    private TableView<?> tablo_personel;
       
    
  
  public void initialize(URL url, ResourceBundle rb) {
      conn=DbaseConnection.BaglantiKur();
        if(conn==null)
        {
        	System.out.println("Bağlantı başarısız :(");
        }
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
    private void personelEkle(ActionEvent event) throws SQLException
    {
    	
    	PreparedStatement preparedStatement=null;
    	ResultSet rs=null;
    	
    	String PersonelId=txt_id.getText();
    	String Personelname=txt_ad.getText();
    	String Personelsoyad=txt_soyad.getText();
    	String seviye =level.getValue();
    	
    	if (PersonelId.isEmpty() || Personelname.isEmpty() || Personelname.isEmpty() || seviye.isEmpty()) 
    	{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tüm alanları doldurunuz");
            alert.showAndWait();
            return;
        }
    	
    	String query="INSERT INTO PERSONEL(ID,NAME,SURNAME,LEVEL) VALUES(?,?,?,?)";
		
		try 
		{
			preparedStatement=conn.prepareStatement(query);
			preparedStatement.setString(1, Personelname);
			preparedStatement.setString(2, Personelsoyad);
			preparedStatement.setString(3, seviye);
			rs=preparedStatement.executeQuery();
			
			if(rs!=null)
			{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Personel Ekleme İşlemleri");
            alert.setContentText("Ekleme Başarıyla Sonuçlandı");
            alert.showAndWait();
			}
			
			} 
		catch (Exception e) 
		{
			/*Burada ekleme iþleminde baþarýsýzlýk varsa o mesaj yazdýrýlýyor.Örneðin veritabanýnda
			 * id alaný primary key ve ayný id den eklemeye çalýþýrsanýz ekleme yapmaz ve rs null döner
			 * ama try catch çalýþtýracaðý için try içinde rs=preparedStatement.executeQuery(); bu kod çalýþmadýðý an
			 * direk olarak catch bloðuna atlar o yüzden baþarýsýzlýk hatasýný yukarýda rs==null diye if oluþturmadan
			 * burada kullandýk
			*/
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Personel Ekleme işlemleri");
            alert.setContentText("BAŞARISIZ OLUNDU");
            alert.showAndWait();
			e.printStackTrace();
		}
		finally
		{
			
			preparedStatement.close();
			
			txt_id.setText("");
			txt_ad.setText("");
			txt_soyad.setText("");
			level.setValue(query);
			
                }
    }
}
