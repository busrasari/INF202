import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class HomeController implements Initializable {
  @FXML
  private Label label;
  
  @FXML
  private VBox vbFiles;
  
  @FXML
  private void closeWindow(MouseEvent event) {
    System.exit(0);
  }
  
  @FXML
  private void hideWindow(MouseEvent event) {
    Node node = (Node)event.getSource();
    Stage stage = (Stage)node.getScene().getWindow();
    stage.setIconified(true);
  }
  
  @FXML
  private void uploadFile(MouseEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload File to Google Drive");
    Node node = (Node)event.getSource();
    Stage stage = (Stage)node.getScene().getWindow();
    File archivo = fileChooser.showOpenDialog((Window)stage);
    System.out.println(archivo.getName());
  }
  
  public void initialize(URL url, ResourceBundle rb) {}
}
