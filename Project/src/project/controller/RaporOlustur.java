package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.database.DAO_Calisan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RaporOlustur {

    @FXML
    private JFXComboBox<String> operator;

    @FXML
    private JFXComboBox<?> degerlendiren;

    @FXML
    private JFXComboBox<?> onaylayan;

    @FXML
    private JFXButton radyografikr;

    @FXML
    private JFXButton manyetikr;

    @FXML
    private void rapor_olustur(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/YeniRapor.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void anasayfadon(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/FXMLDocument.fxml")));

        stage.setScene(scene);
        stage.show();
    }
}
