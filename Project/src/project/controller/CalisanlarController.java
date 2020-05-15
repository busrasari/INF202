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


