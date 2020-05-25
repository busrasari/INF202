package project.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import project.Calisanlar;
import project.database.DBConnection;
import project.database.DAO_Calisan;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class CalisanlarController implements Initializable {
    private static boolean EDIT = false, ADD = true;
    ObservableList<String> levelList = FXCollections.observableArrayList("Level 1", "Level 2", "Level 3");
    Connection conn;
    DAO_Calisan dao;
    PreparedStatement ps;
    @FXML
    public ChoiceBox<String> level;
    @FXML
    public AnchorPane personel;
    @FXML
    public TextField txt_id;
    @FXML
    public TextField txt_ad;
    @FXML
    public TextField txt_soyad;
    @FXML
    public Button kaydetb;
    @FXML
    public Button yenib;
    @FXML
    public Button duzenleb;
    @FXML
    public Button silb;
    @FXML
    public TableView<Calisanlar> tablo_personel;
    public FXMLLoader loader;
    public String query, id, firstname, lastname, seviye;
    public DBConnection database;
    public Connection connect;
    @FXML
    public TableColumn<Calisanlar, String> idsutun;
    @FXML
    public TableColumn<Calisanlar, String> adsutun;
    @FXML
    public TableColumn<Calisanlar, String> soyadsutun;
    @FXML
    public TableColumn<Calisanlar, String> seviyesutun;
    DBConnection dbc = new DBConnection();


    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAO_Calisan();
        loadData();

        yenib.setOnAction(e->{
            String name=txt_ad.getText();
            String nachn=txt_soyad.getText();
            String id=txt_id.getText();
            String seviye=level.getValue().toString();
            if (txt_id.getText().isEmpty() || txt_ad.getText().isEmpty() || txt_soyad.getText().isEmpty() || level.getValue().toString().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Lütfen tüm alanları doldurunuz");
                alert.showAndWait();
                return;
            }
            try {
                dao.ekleme(id,name,nachn,seviye);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            refreshTable();
        });
        kaydetb.setOnAction(e->{
            String id = txt_id.getText();
            String ad = txt_ad.getText();
            String soyadi = txt_soyad.getText();
            String seviyes = level.getValue();
            dao.update(id,ad,soyadi,seviyes);
            refreshTable();

        });

        duzenleb.setOnAction(e->{
            ADD = false;
            EDIT = true;
            editAccount();
            refreshTable();
        });




        silb.setOnAction(e->{
            Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
            id = selected.getpID().get();
            dao.deleteAccount(id);

            refreshTable();

        });
        initTable();
        refreshTable();


    }

    private void loadData() {
        this.levelList.removeAll(this.levelList);
        String a = "Level 1";
        String b = "Level 2";
        String c = "Level 3";
        this.levelList.addAll(a, b, c);
        this.level.getItems().addAll(this.levelList);
    }
    private void initTable() {
        idsutun.setCellValueFactory(cell -> cell.getValue().getpID());
        adsutun.setCellValueFactory(cell -> cell.getValue().getpname());
        soyadsutun.setCellValueFactory(cell -> cell.getValue().getpLastname());
        seviyesutun.setCellValueFactory(cell -> cell.getValue().getpSeviye());
    }
    public void refreshTable()  {
        initTable();
        String query = "SELECT * FROM personel";
        tablo_personel.setItems(dao.getAccountsData(query));
    }

    private void editAccount() {
        Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
        txt_id.setText(selected.getpID().get());
        txt_ad.setText(selected.getpname().get());
        txt_soyad.setText(selected.getpLastname().get());
        level.getSelectionModel().select(selected.getpSeviye().get());
    }

}


