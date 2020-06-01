package project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import project.Models.Projeler;
import project.DataAccesObject.DAO_Projeler;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProjelerController implements Initializable {

    DAO_Projeler dao_projeler= new DAO_Projeler();
    CalisanlarController calisan= new CalisanlarController();

    @FXML
    private JFXTextField projeaditxt;

    @FXML
    private JFXButton ekleb;

    @FXML
    private TableView<Projeler> projetablo;

    @FXML
    private TableColumn<Projeler, Number> idst;

    @FXML
    private TableColumn<Projeler, String> projest;

    @FXML
    private JFXButton anasayfa;

    @FXML
    private Label basarili;

    public void initialize(URL url, ResourceBundle rb) {
        ekleb.setOnAction(e -> {
            String name = projeaditxt.getText();
            try {
                String b = dao_projeler.ekleme(name);
                if (b == "işlem başarılı") {
                    basarili.setText("Ekleme İşlemi Başarılıyla Sonuçlandı");
                    calisan.animasyon();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            refreshTable();
        });
        initTable();

        refreshTable();
    }

    @FXML
    private void anasayfa_don(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/FXMLDocument.fxml")));

        stage.setScene(scene);
        stage.show();
    }



    private void initTable() {
        idst.setCellValueFactory(cell -> cell.getValue().getProje_id());
        projest.setCellValueFactory(cell -> cell.getValue().getProje());

    }

    public void refreshTable() {
        initTable();
        String query = "SELECT * FROM projeler";
        projetablo.setItems(dao_projeler.getAccountsData(query));
    }

}
