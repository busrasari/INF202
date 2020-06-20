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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.DataAccesObject.DAO_YuzeyDurumu;
import project.Helper.Asistan;
import project.Helper.Messages;
import project.Ressource.YuzeyDurumu;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;


public class YuzeyDurumuController implements Initializable {
    @FXML
    public TableView yuzeydtablo;
    @FXML
    public JFXButton anasayfa;
    @FXML
    public JFXButton ekleb;
    DAO_YuzeyDurumu dao_yuzeydurumu = new DAO_YuzeyDurumu();
    CalisanlarController calisan = new CalisanlarController();
    @FXML
    private JFXTextField yuzeydtxt;

    @FXML
    private TableColumn<YuzeyDurumu, Number> idst;

    @FXML
    private TableColumn<YuzeyDurumu, String> ydst;

    @FXML
    private Label basarili;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane ydurumusf;


    public void initialize(URL url, ResourceBundle rb) {
        ekleb.setOnAction(e -> {
            if (yuzeydtxt.getText().isEmpty()) {
                JFXButton geriButton = new JFXButton("Geri Dön");
                Messages.showDialog(rootPane, ydurumusf, Arrays.asList(geriButton), "Yüzey Durumu Ekleme İşlemi",
                        String.format("Lütfen Bütün Alanları Doldurunuz"));
                return;
            }
            String name = yuzeydtxt.getText().substring(0, 1).toUpperCase() + yuzeydtxt.getText().substring(1).toLowerCase();
            try {
                YuzeyDurumu yuzeyDurumuekle=new YuzeyDurumu(name);
                String b = DAO_YuzeyDurumu.ekleme(yuzeyDurumuekle);
                ClearTextField();
                if (b == "işlem başarılı") {
                    Messages.TrayMessage("Yüzey Durumu Ekleme İşlemi", "Ekleme İşlemi Başarıyla Sonuçlandı", NotificationType.SUCCESS);
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
    void anasayfadon(MouseEvent event) throws IOException {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));
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

    public void ClearTextField() {
        yuzeydtxt.clear();
    }
}
