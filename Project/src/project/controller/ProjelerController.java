package project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import project.Helper.Asistan;
import project.Helper.Messages;
import project.Ressource.Projeler;
import project.DataAccesObject.DAO_Projeler;
import tray.notification.NotificationType;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
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

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane projesf;


    public void initialize(URL url, ResourceBundle rb) {
        ekleb.setOnAction(e -> {
            if(projeaditxt.getText().isEmpty()){
                JFXButton geriButton = new JFXButton("Geri Dön");
                Messages.showDialog(rootPane, projesf, Arrays.asList(geriButton), "Proje Ekleme İşlemi",
                        String.format("Lütfen Bütün Alanları Doldurunuz"));
                return;
            }
            String name = projeaditxt.getText().substring(0, 1).toUpperCase() +projeaditxt.getText().substring(1).toLowerCase();
            try {
                Projeler projeekle= new Projeler(name);
                String b = dao_projeler.ekleme(projeekle);
                ClearTextField();
                if (b == "işlem başarılı") {
                    Messages.TrayMessage("Proje Ekleme İşlemi", "Ekleme İşlemi Başarıyla Sonuçlandı", NotificationType.SUCCESS);
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
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));
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
    public void ClearTextField(){
        projeaditxt.clear();
    }

}
