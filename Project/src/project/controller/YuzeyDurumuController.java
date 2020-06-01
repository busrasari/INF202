package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Models.YuzeyDurumu;
import project.DataAccesObject.DAO_YuzeyDurumu;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class YuzeyDurumuController implements Initializable {
    DAO_YuzeyDurumu dao_yuzeydurumu= new DAO_YuzeyDurumu();
    CalisanlarController calisan= new CalisanlarController();

    @FXML
    public TableView yuzeydtablo;

    @FXML
    public JFXButton anasayfa;

    @FXML
    public JFXButton ekleb;

    @FXML
    private JFXTextField yuzeydtxt;

    @FXML
    private TableColumn<YuzeyDurumu, Number> idst;

    @FXML
    private TableColumn<YuzeyDurumu, String> ydst;

    @FXML
    private Label basarili;



    public void initialize(URL url, ResourceBundle rb) {
        ekleb.setOnAction(e -> {
            String name = yuzeydtxt.getText();
            try {
                String b = dao_yuzeydurumu.ekleme(name);
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
    private void anasayfadon(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/FXMLDocument.fxml")));

        stage.setScene(scene);
        stage.show();
    }
    private void initTable() {
        idst.setCellValueFactory(cell -> cell.getValue().getYd_id());
        ydst.setCellValueFactory(cell -> cell.getValue().getYuzeydurumu());

    }

    public void refreshTable() {
        initTable();
        String query = "SELECT * FROM yuzeydurumu";
        yuzeydtablo.setItems(dao_yuzeydurumu.getAccountsData(query));
    }
}
