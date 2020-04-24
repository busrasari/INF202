import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
//import project.CalisanlarController;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
}
