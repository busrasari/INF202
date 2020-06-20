package project.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import project.Helper.Messages;
import project.Ressource.Calisanlar;
import project.DataAccesObject.DAO_Calisan;
import project.Helper.Asistan;
import project.database.DBConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CalisanlarController implements Initializable {

    private static boolean EDIT = false, ADD = true;
    @FXML
    public AnchorPane personel;
    public FXMLLoader loader;
    public String query;
    public int id;
    public String firstname;
    public String lastname;
    public String seviye;
    public DBConnection database;
    public Connection connect;
    ObservableList<String> levelList = FXCollections.observableArrayList("Level 1", "Level 2", "Level 3");
    Connection conn;
    DAO_Calisan dao;
    PreparedStatement ps;
    DBConnection dbc = new DBConnection();
    @FXML
    private TableView<Calisanlar> tablo_personel;
    @FXML
    private TableColumn<Calisanlar,Number> idsutun;
    @FXML
    private TableColumn<Calisanlar, String> adsutun;
    @FXML
    private TableColumn<Calisanlar, String> soyadsutun;
    @FXML
    private TableColumn<Calisanlar, String> seviyesutun;
    @FXML
    private Label uyarilabel;
    @FXML
    private Label personelsum;
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
    private JFXTextField txt_id;
    @FXML
    private JFXTextField txt_ad;
    @FXML
    private JFXTextField txt_soyad;
    @FXML
    private JFXComboBox<String> level;
    @FXML
    private JFXButton yenib;
    @FXML
    private JFXButton silb;
    @FXML
    private JFXButton duzenleb;
    @FXML
    private JFXButton kaydetb;
    @FXML
    private StackPane rootPane;

    @FXML
    void enter_anasayfa(MouseEvent event) {  Asistan.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml")); }

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
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Yuzeydurumu.fxml"));

    }
    @FXML
    void idchangeblock(KeyEvent event) {
        uyarilabel.setText("ID Bilgisi Değiştirilemez");
        Messages.animasyon(uyarilabel);

    }

    public void initialize(URL url, ResourceBundle rb) {
        dao = new DAO_Calisan();
        try {
            String count = "" + dao.ptoplam();
            personelsum.setText(count);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();

        yenib.setOnAction(e -> {
            if (txt_ad.getText().isEmpty() || txt_soyad.getText().isEmpty() || level.getValue() == null) {
                JFXButton geriButton = new JFXButton("Geri Dön");
                Messages.showDialog(rootPane, personel, Arrays.asList(geriButton), "Personel Ekleme İşlemi",
                        String.format("Lütfen Bütün Alanları Doldurunuz"));
                return;
            }
            String name = txt_ad.getText().substring(0, 1).toUpperCase() + txt_ad.getText().substring(1).toLowerCase();
            String nachn = txt_soyad.getText().toUpperCase();
            String seviye = level.getValue();
            try {
                Calisanlar calisanlar= new Calisanlar(name,nachn,seviye);
                String b = DAO_Calisan.ekleme(calisanlar);
                ClearTextfield();
                if (b == "işlem başarılı") {
                    Messages.TrayMessage("Personel Ekleme İşlemi", "İşlem Başarıyla Sonuçlandı", NotificationType.SUCCESS);

                } else {

                    Messages.TrayMessage("Personel Ekleme İşlemi", "İşlem Başarısız", NotificationType.ERROR);

                }
            } catch (SQLException throwables) {


                throwables.printStackTrace();
            }

            refreshTable();
        });
        kaydetb.setOnAction(e -> {
            int id = Integer.parseInt(txt_id.getText());
            String ad = txt_ad.getText().substring(0, 1).toUpperCase() + txt_ad.getText().substring(1).toLowerCase();;
            String soyadi = txt_soyad.getText().toUpperCase();
            String seviyes = level.getValue();
            Calisanlar calisanupdate= new Calisanlar(id,ad,soyadi,seviyes);
            dao .update(calisanupdate);
            refreshTable();
            ClearTextfield();
            Messages.TrayMessage("Personel Bilgisi Güncelleme İşlemi", "Değişiklikler Kaydedildi", NotificationType.SUCCESS);
        });

        duzenleb.setOnAction(e -> {
            ADD = false;
            EDIT = true;
            editAccount();
            refreshTable();
        });


        silb.setOnAction(e -> {
            Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
            int id = selected.getpID().get();
            JFXButton yesButton = new JFXButton("EVET");
            JFXButton noButton = new JFXButton("HAYIR");
            Messages.showDialog(rootPane, personel, Arrays.asList(yesButton, noButton), "Personel Silme İşlemi",
                    String.format("%s ID'li  %s %s  isimli personeli silmek istediğinize emin misiniz ?", selected.getpID().getValue(), selected.getpname().getValue(), selected.getpLastname().getValue()));
            noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                Messages.TrayMessage("Personel Silme İşlemi", "Silme İşlemi İptal Edildi", NotificationType.INFORMATION);

            });
            yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                dao.deleteAccount(id);
                refreshTable();
                Messages.TrayMessage("Personel Silme İşlemi", "Silme İşlemi Başarıyla Sonuçlandı", NotificationType.SUCCESS);
            });

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

    public void refreshTable() {
        initTable();
        String query = "SELECT * FROM personel";
        tablo_personel.setItems(dao.getAccountsData(query));
    }


    private void editAccount() {
        Calisanlar selected = tablo_personel.getSelectionModel().getSelectedItem();
        txt_id.setText(String.valueOf(selected.getpID().get()));
        txt_ad.setText(selected.getpname().get());
        txt_soyad.setText(selected.getpLastname().get());
        level.getSelectionModel().select(selected.getpSeviye().get());
    }
 public void ClearTextfield(){
     txt_id.clear();
     txt_ad.clear();
     txt_soyad.clear();
     level.getSelectionModel().clearSelection();
    }


}


