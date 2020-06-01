/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.Models.SayfaGecis;
import project.database.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author busra
 */

public class FXMLDocumentController {
    public Button yenirapor;
    public Button proje_buton;
    public Button yd_buton;
    private Label label;


    @FXML
    private Button personel_buton;

    @FXML
    private BorderPane ekran;
    @FXML
    private Button anasayfa;
    @FXML
    private Button musteri_buton;
    @FXML
    private Button ekipman_buton;
    @FXML
    private Button raporlar_buton;
    @FXML
    private Label personelsum;


    @FXML
    public void initialize() throws SQLException {

    }


    @FXML
    private void enter_personell(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Calisanlar.fxml"));
    }



    @FXML
    private void enter_musteri(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Musteriler.fxml"));
    }


    @FXML
    private void enter_ekipman(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Ekipman.fxml"));
    }

    @FXML
    private void enter_raporlar(MouseEvent event) throws IOException {
        Parent Raporlar = FXMLLoader.load(getClass().getResource("/project/fxml/Raporlar.fxml"));
        this.ekran.setCenter(Raporlar);
    }

    @FXML
    private void enter_anasayfa(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));
    }

    @FXML
    private void enter_projeler(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Projeler.fxml"));

    }

    @FXML
    private void enter_yuzeydurumu(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Yuzeydurumu.fxml"));
    }

    @FXML
    private void yenirapor(MouseEvent event) throws IOException {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/RaporOlustur.fxml"));

    }
}


    

