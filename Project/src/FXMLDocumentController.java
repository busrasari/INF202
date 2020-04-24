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

public class FXMLDocumentController implements Initializable {
  private Label label;
  
  @FXML
  private Button newreport;
  
  @FXML
  private Button personel_buton;
  
  @FXML
  private BorderPane ekran;
  
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
}