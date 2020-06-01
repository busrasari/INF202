/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import project.DataAccesObject.DAO_Ekipman;
import project.Models.Ekipmanlar;
import project.Models.SayfaGecis;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author busra
 */
public class EkipmanController implements Initializable {
    private static boolean EDIT = false, ADD = true;
    public String query;
    public int id;

    DAO_Ekipman dao_ekipman= new DAO_Ekipman();

    @FXML
    private Label basarili;

    @FXML
    private Label ekipmansum;

    @FXML
    private Button anasayfa;

    @FXML
    private Button personel_buton;

    @FXML
    private Button musteri_buton;

    @FXML
    private Button ekipman_buton;

    @FXML
    private Button proje_buton;

    @FXML
    private Button yd_buton;

    @FXML
    private JFXTextField cihaztxt;

    @FXML
    private JFXTextField kmtxt;

    @FXML
    private JFXTextField mptxt;

    @FXML
    private JFXTextField mttxt;

    @FXML
    private JFXTextField uvtxt;

    @FXML
    private JFXTextField isiktxt;

    @FXML
    private JFXButton eklebt;

    @FXML
    private JFXButton editbt;

    @FXML
    private JFXButton silbt;

    @FXML
    private JFXButton kaydetbt;


    @FXML
    private TableView<Ekipmanlar> ekipman_tablo;

    @FXML
    private TableColumn<Ekipmanlar, String> cihazst;

    @FXML
    private TableColumn<Ekipmanlar, String> kutupst;

    @FXML
    private TableColumn<Ekipmanlar, String> mpst;

    @FXML
    private TableColumn<Ekipmanlar, String> miknatisst;

    @FXML
    private TableColumn<Ekipmanlar, String> uvst;

    @FXML
    private TableColumn<Ekipmanlar, String> isikst;

    @FXML
    private TableColumn<Ekipmanlar, Number> idst;


    @FXML
    void enter_anasayfa(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));

    }

    @FXML
    void enter_ekipman(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Ekipman.fxml"));

    }

    @FXML
    void enter_musteri(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Musteriler.fxml"));

    }

    @FXML
    void enter_personell(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Calisanlar.fxml"));

    }

    @FXML
    void enter_projeler(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/Projeler.fxml"));

    }

    @FXML
    void enter_yuzeydurumu(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/YuzeyDurumu.fxml"));

    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao_ekipman = new DAO_Ekipman();
        eklebt.setOnAction(e -> {
            String cihaz = cihaztxt.getText();
            String kutup = kmtxt.getText();
            String mp = mptxt.getText();
            String miknatis = mttxt.getText();
            String uv = uvtxt.getText();
            String isik = isiktxt.getText();
            try {
                String b = dao_ekipman.ekleme(cihaz, kutup, mp, miknatis,uv,isik);
                if (b == "işlem başarılı") {
                    basarili.setText("Ekleme İşlemi Başarılıyla Sonuçlandı");
                    animasyon();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            refreshTable();
        });
       kaydetbt.setOnAction(e -> {
           String cihaz = cihaztxt.getText();
           String kutup = kmtxt.getText();
           String mp = mptxt.getText();
           String miknatis = mttxt.getText();
           String uv = uvtxt.getText();
           String isik = isiktxt.getText();
            dao_ekipman.update(cihaz, kutup, mp, miknatis,uv,isik);
            refreshTable();
            basarili.setText("Bilgiler güncellendi");
            animasyon();

        });

        editbt.setOnAction(e -> {
            ADD = false;
            EDIT = true;
            editAccount();
            refreshTable();
        });


        silbt.setOnAction(e -> {
            Ekipmanlar selected = ekipman_tablo.getSelectionModel().getSelectedItem();
            id = selected.getE_id().get();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uyarı");
            alert.setHeaderText("Ekipman Silme");
            alert.setContentText("Seçtiğiniz Ekipmanı Silmek İstediğinize Emin Misiniz?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao_ekipman.deleteAccount(id);
                refreshTable();
                basarili.setText("Silme İşlemi Başarıyla Gerçekleşti");
                animasyon();
            } else {
                return;
            }


        });
       initTable();
        refreshTable();

    }

    private void initTable() {
        idst.setCellValueFactory(cell -> cell.getValue().getE_id());
        cihazst.setCellValueFactory(cell -> cell.getValue().getCihaz());
        kutupst.setCellValueFactory(cell -> cell.getValue().getKutupM());
        mpst.setCellValueFactory(cell -> cell.getValue().getMpTAO());
        miknatisst.setCellValueFactory(cell -> cell.getValue().getMTeknik());
        uvst.setCellValueFactory(cell -> cell.getValue().getUv());
        isikst.setCellValueFactory(cell -> cell.getValue().getIsik());
    }

    public void refreshTable() {
        initTable();
        String query = "SELECT * FROM ekipman";
        ekipman_tablo.setItems(dao_ekipman.getAccountsData(query));
    }


    private void editAccount() {
        Ekipmanlar selected = ekipman_tablo.getSelectionModel().getSelectedItem();
        cihaztxt.setText(selected.getCihaz().get());
        kmtxt.setText(selected.getKutupM().get());
        mptxt.setText(selected.getMpTAO().get());
        mttxt.setText(selected.getMTeknik().get());
        uvtxt.setText(selected.getUv().get());
        isiktxt.setText(selected.getIsik().get());

    }


    public void animasyon() {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), basarili);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.play();

    }

}
