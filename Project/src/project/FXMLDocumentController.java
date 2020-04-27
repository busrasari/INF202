/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import project.FXMLDocumentController;

/**
 *
 * @author busra
 */

  public class FXMLDocumentController implements Initializable {
  private Label label;
  
  
  @FXML
  private Button personel_buton;
  
  @FXML
  private BorderPane ekran;
    @FXML
    private Button anasayfa;
    @FXML
    private Button musteri_buton;
    @FXML
    private Button ekipman_buton;
    @FXML
    private Button raporlar_buton;
  
  private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked me!");
    this.label.setText("Hello World!");
  }
  
  public void initialize(URL url, ResourceBundle rb) {}
  
  @FXML
  private void enter_personell(MouseEvent event) throws IOException {
    Parent Calisanlar = (Parent)FXMLLoader.load(getClass().getResource("/project/Calisanlar.fxml"));
    this.ekran.setCenter((Node)Calisanlar);
  }

    @FXML
    private void enter_musteri(MouseEvent event) throws IOException {
        Parent Musteriler = (Parent)FXMLLoader.load(getClass().getResource("/project/Musteriler.fxml"));
    this.ekran.setCenter((Node)Musteriler);
    }



   @FXML
    private void enter_ekipman(MouseEvent event) throws IOException {
        Parent Ekipman = (Parent)FXMLLoader.load(getClass().getResource("/project/Ekipman.fxml"));
    this.ekran.setCenter((Node)Ekipman);
    }

    @FXML
    private void enter_raporlar(MouseEvent event) throws IOException {
        Parent Raporlar = (Parent)FXMLLoader.load(getClass().getResource("/project/Raporlar.fxml"));
    this.ekran.setCenter((Node)Raporlar);
    }
}

    

