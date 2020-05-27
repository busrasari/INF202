/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

/**
 * @author busra
 */
public class YeniRaporController implements Initializable {

    ObservableList<String> sonuclist = FXCollections.observableArrayList();
    ObservableList<String> akimlist = FXCollections.observableArrayList();
    @FXML
    private TextField cihazadi;
    @FXML
    private ComboBox<String> sonuc1, sonuc2, sonuc3, sonuc4, sonuc5, sonuc6, sonuc7, sonuc8, sonuc9, sonuc10, sonuc11, sonuc12, sonuc13, sonuc14;
    @FXML
    private ComboBox<String> akimtipi;
    @FXML
    private JFXButton geridon;

    public void initialize(URL url, ResourceBundle rb) {
        loadData();

    }


    @FXML
    private void geridon(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/FXMLDocument.fxml")));

        stage.setScene(scene);
        stage.show();
    }

    private void loadData() {
        sonuclist.removeAll(sonuclist);
        akimlist.removeAll(akimlist);
        String ok = "OK";
        String red = "RED";
        String ac = "AC";
        String dc = "DC";
        sonuclist.addAll(ok, red);
        akimlist.addAll(ac, dc);
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


    }

}
