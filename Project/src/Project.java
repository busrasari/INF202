import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import project.Project;

public class Project extends Application {
  private double xOffset = 0.0D;
  
  private double yOffset = 0.0D;
  
  @FXML
  private Button newreport;
  
  public void start(Stage stage) throws Exception {
    Parent root = (Parent)FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    Scene scene = new Scene(root);
    stage.initStyle(StageStyle.TRANSPARENT);
    root.setOnMousePressed(event -> {
          this.xOffset = event.getSceneX();
          this.yOffset = event.getSceneY();
        });
    root.setOnMouseDragged(event -> {
          stage.setX(event.getScreenX() - this.xOffset);
          stage.setY(event.getScreenY() - this.yOffset);
        });
    stage.setScene(scene);
    stage.show();
  }
  
  public void initialize(URL location, ResourceBundle resources) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}