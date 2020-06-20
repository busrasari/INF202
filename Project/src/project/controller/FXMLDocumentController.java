/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import project.Helper.Asistan;

import java.io.IOException;
import java.sql.SQLException;

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
    private void enter_personell(MouseEvent event)  {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Calisanlar.fxml"));
    }



    @FXML
    private void enter_musteri(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Musteriler.fxml"));
    }


    @FXML
    private void enter_ekipman(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Ekipman.fxml"));
    }


    @FXML
    private void enter_anasayfa(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));
    }

    @FXML
    private void enter_projeler(MouseEvent event)  {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Projeler.fxml"));

    }

    @FXML
    private void enter_yuzeydurumu(MouseEvent event) throws IOException {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Yuzeydurumu.fxml"));
    }

    @FXML
    private void yenirapor(MouseEvent event) throws IOException {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/RaporOlustur.fxml"));

    }
}


    

