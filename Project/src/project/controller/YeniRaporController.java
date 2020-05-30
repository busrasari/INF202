/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.Classlar.Ekipmanlar;
import project.Classlar.Musteriler;
import project.Classlar.Projeler;
import project.Classlar.YuzeyDurumu;
import project.DAO.*;
import project.database.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author busra
 */
public class YeniRaporController implements Initializable {
    private static final DBConnection database = new DBConnection();

    final ObservableList<Ekipmanlar> options = FXCollections.observableArrayList();

    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ObservableList<String> sonuclist = FXCollections.observableArrayList();
    ObservableList<String> akimlist = FXCollections.observableArrayList();
    ObservableList<String> muayenelist = FXCollections.observableArrayList();
    DAO_Ekipman dao_ekipman = new DAO_Ekipman();
    DAO_Musteri dao_musteri = new DAO_Musteri();
    DAO_Projeler dao_projeler = new DAO_Projeler();
    DAO_Calisan dao_calisan = new DAO_Calisan();
    DAO_YuzeyDurumu dao_yuzeyDurumu = new DAO_YuzeyDurumu();
    ComboBox comboBox = new ComboBox(options);
    Ekipmanlar id;
    Ekipmanlar ekipman = new Ekipmanlar(id);
    @FXML
    private TextField kmtxt, mptxt, mttxt, uvtxt, isiktxt;

    @FXML
    private TextField optxt_adi;

    @FXML
    private VBox vbox;
    @FXML
    private JFXComboBox<Ekipmanlar> cihazadi;
    @FXML
    private ComboBox<String> sonuc1, sonuc2, sonuc3, sonuc4, sonuc5, sonuc6, sonuc7, sonuc8, sonuc9, sonuc10, sonuc11, sonuc12, sonuc13, sonuc14;

    @FXML
    private JFXComboBox<Musteriler> mustericombo;
    @FXML
    private JFXComboBox<Projeler> projecombo;
    @FXML
    private JFXComboBox<YuzeyDurumu> yuzeydcombo;
    @FXML
    private JFXComboBox<String> muayenecombo,akimtipi;
    @FXML
    private JFXButton geridon;

    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        this.optxt_adi.setText(String.valueOf(RaporOlustur.s_operator));
        cihazadi.setOnMouseClicked(e -> {
            init_Cihaz();
            try {
                String query = "SELECT * FROM ekipman WHERE id= " + ekipman.getE_id();
                conn = database.getConnection();
                pst = conn.prepareStatement(query);
                System.out.println("plsss");
                pst.setString(1, String.valueOf(cihazadi.getSelectionModel().getSelectedItem()));
                System.out.println("bçalışş");
                rs = pst.executeQuery();
                System.out.println("TRY");
                while (rs.next()) {
                    kmtxt.setText(rs.getString(3));
                    mptxt.setText(rs.getString(4));
                    mttxt.setText(rs.getString(5));
                    uvtxt.setText(rs.getString(6));
                    isiktxt.setText(rs.getString(7));
                    System.out.println("aleyküm");
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(YeniRaporController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mustericombo.setOnMouseClicked(e -> {
            init_Musteri();
        });

        yuzeydcombo.setOnMouseClicked(e -> {
            init_YuzeyDurumu();
        });
        projecombo.setOnMouseClicked(e -> {
            init_Projeler();
        });
    }


    @FXML
    private void geridon(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/FXMLDocument.fxml")));

        stage.setScene(scene);
        stage.show();
    }

    private void loadData() {
        sonuclist.removeAll(sonuclist);
        akimlist.removeAll(akimlist);
        muayenelist.removeAll(muayenelist);
        String ok = "OK";
        String red = "RED";
        String ac = "AC";
        String dc = "DC";
        String unt = "Untreated";
        String t = "Treated";
        sonuclist.addAll(ok, red);
        akimlist.addAll(ac, dc);
        muayenelist.addAll(unt, t);
        sonuc1.getItems().addAll(ok, red);
        sonuc2.getItems().addAll(ok, red);
        sonuc3.getItems().addAll(ok, red);
        sonuc4.getItems().addAll(ok, red);
        sonuc5.getItems().addAll(ok, red);
        sonuc6.getItems().addAll(ok, red);
        sonuc7.getItems().addAll(ok, red);
        sonuc8.getItems().addAll(ok, red);
        sonuc9.getItems().addAll(ok, red);
        sonuc10.getItems().addAll(ok, red);
        sonuc11.getItems().addAll(ok, red);
        sonuc12.getItems().addAll(ok, red);
        sonuc13.getItems().addAll(ok, red);
        sonuc14.getItems().addAll(ok, red);
        akimtipi.getItems().addAll(ac, dc);
        muayenecombo.getItems().addAll(unt, t);

    }

    private void init_Cihaz() {
        cihazadi.setItems(dao_ekipman.getCihazComboBox());
    }

    private void init_Musteri() {
        mustericombo.setItems((dao_musteri.getMusteriComboBox()));
    }

    private void init_YuzeyDurumu() {
        yuzeydcombo.setItems(dao_yuzeyDurumu.getYuzeyDComboBox());
    }

    private void init_Projeler() {
        projecombo.setItems(dao_projeler.getProjeComboBox());
    }
}
