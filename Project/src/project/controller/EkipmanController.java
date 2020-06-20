/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import project.DataAccesObject.DAO_Ekipman;
import project.Helper.Messages;
import project.Helper.Asistan;
import project.Ressource.Ekipmanlar;
import project.database.DBConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author busra
 */
public class EkipmanController implements Initializable {
    TrayNotification tray = new TrayNotification();

    private static boolean EDIT = false, ADD = true;
    public String query;
    public int id;

    DAO_Ekipman dao_ekipman = new DAO_Ekipman();

    @FXML
    private Label uyarilabel;

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
    private JFXTextField idtxt;

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
    private StackPane rootPane;

    @FXML
    private AnchorPane ekipman;
    private DBConnection database;


    @FXML
    void enter_anasayfa(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));

    }

    @FXML
    void enter_ekipman(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Ekipman.fxml"));

    }

    @FXML
    void enter_musteri(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Musteriler.fxml"));

    }

    @FXML
    void enter_personell(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Calisanlar.fxml"));

    }

    @FXML
    void enter_projeler(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Projeler.fxml"));

    }

    @FXML
    void enter_yuzeydurumu(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/YuzeyDurumu.fxml"));

    }


    @FXML
    void idveri(KeyEvent event) {
        uyarilabel.setText("ID Bilgisi Değiştirilemez");
        Messages.animasyon(uyarilabel);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(3));

        dao_ekipman = new DAO_Ekipman();
        try {
            String count = "" + dao_ekipman.toplam();
            ekipmansum.setText(count);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao_ekipman = new DAO_Ekipman();

        eklebt.setOnAction(e -> {
            if (cihaztxt.getText().isEmpty() || kmtxt.getText().isEmpty() || mptxt.getText().isEmpty() ||
                    mttxt.getText().isEmpty() || uvtxt.getText().isEmpty() || isiktxt.getText().isEmpty()) {
                JFXButton geriButton = new JFXButton("Geri Dön");
                Messages.showDialog(rootPane, ekipman, Arrays.asList(geriButton), "Ekipman Ekleme İşlemi",
                        String.format("Lütfen Bütün Alanları Doldurunuz"));
                return;
            }
            String cihaz = cihaztxt.getText().substring(0, 1).toUpperCase() + cihaztxt.getText().substring(1).toLowerCase();
            String kutup = kmtxt.getText();
            String mp = mptxt.getText();
            String miknatis = mttxt.getText();
            String uv = uvtxt.getText();
            String isik = isiktxt.getText();
            try {
                Ekipmanlar ekipmanekle = new Ekipmanlar(cihaz,kutup,mp,miknatis,uv,isik);
                String b = DAO_Ekipman.ekleme(ekipmanekle);
                ClearTextField();
                if (b == "işlem başarılı") {
                    Messages.TrayMessage("Ekipman Ekleme İşlemi", "Ekleme İşlemi Başarıyla Sonuçlandı", NotificationType.SUCCESS);

                } else {
                    Messages.TrayMessage("Ekipman Ekleme İşlemi", "Ekleme İşlemi Başarısız", NotificationType.ERROR);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            refreshTable();
        });

        kaydetbt.setOnAction(e -> {
            int id = Integer.parseInt(idtxt.getText());
            String cihaz = cihaztxt.getText().substring(0, 1).toUpperCase() + cihaztxt.getText().substring(1).toLowerCase();
            String kutup = kmtxt.getText();
            String mp = mptxt.getText();
            String miknatis = mttxt.getText();
            String uv = uvtxt.getText();
            String isik = isiktxt.getText();
            Ekipmanlar ekipmanupdate= new Ekipmanlar(id,cihaz,kutup,mp,miknatis,uv,isik);
            dao_ekipman.update(ekipmanupdate);
            refreshTable();
            ClearTextField();
            Messages.TrayMessage("Ekipman Bilgisi Güncelleme İşlemi", "Değişikliklikler Kaydedildi", NotificationType.SUCCESS);

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
            JFXButton yesButton = new JFXButton("EVET");
            JFXButton noButton = new JFXButton("HAYIR");
            Messages.showDialog(rootPane, ekipman, Arrays.asList(yesButton, noButton), "Ekipman Silme İşlemi",
                    String.format("%s ID'li  %s  isimli ekipmanı silmek istediğinize emin misiniz ?", selected.getE_id().getValue(), selected.getCihaz().getValue()));
            noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                Messages.TrayMessage("Ekipman Silme İşlemi", "Silme İşlemi İptal Edildi", NotificationType.INFORMATION);
            });
            yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                dao_ekipman.deleteAccount(id);
                refreshTable();
                Messages.TrayMessage("Ekipman Silme İşlemi", "Silme İşlemi İptal Edildi", NotificationType.SUCCESS);
            });

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
        idtxt.setText(String.valueOf(selected.getE_id().get()));
        cihaztxt.setText(selected.getCihaz().get());
        kmtxt.setText(selected.getKutupM().get());
        mptxt.setText(selected.getMpTAO().get());
        mttxt.setText(selected.getMTeknik().get());
        uvtxt.setText(selected.getUv().get());
        isiktxt.setText(selected.getIsik().get());

    }
    public void ClearTextField(){
        idtxt.clear();
        cihaztxt.clear();
        kmtxt.clear();
        mptxt.clear();
        mttxt.clear();
        mttxt.clear();
        uvtxt.clear();
        isiktxt.clear();
    }


}
