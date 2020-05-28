package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Calisanlar;
import project.database.DAO_Calisan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RaporOlustur implements Initializable {
    DAO_Calisan dao= new DAO_Calisan();


    @FXML
    private JFXComboBox<Calisanlar> operator;

    @FXML
    private JFXComboBox<Calisanlar> degerlendiren;

    @FXML
    private JFXComboBox<Calisanlar> onaylayan;

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
        operator.setOnMouseClicked(e->{
            initName();
        });
        degerlendiren.setOnMouseClicked(e->{
            initsName();
        });
        onaylayan.setOnMouseClicked(e->{
            initName();
        });
        //initName();

      // operator.getSelectionModel().select(0);

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


    private void initName() {
        operator.setItems(dao.getNameComboBox());

        onaylayan.setItems(dao.getNameComboBox());
    }
    private void initsName() {
        degerlendiren.setItems(dao.getNameComboBox());
    }

}
