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
import project.DataAccesObject.DAO_Musteri;
import project.Helper.Messages;
import project.Helper.Asistan;
import project.Ressource.Ekipmanlar;
import project.Ressource.Musteriler;
import project.database.DBConnection;
import tray.notification.NotificationType;

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
public class MusterilerController implements Initializable {
    private static boolean EDIT = false, ADD = true;
    public String query;
    public int id;
    CalisanlarController c = new CalisanlarController();
    DAO_Musteri daom;
    @FXML
    private Label uyarilabel;

    @FXML
    private Label musterisum;

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
    private JFXTextField firmaadi;

    @FXML
    private JFXTextField iltxt;

    @FXML
    private JFXTextField ilxetxt;

    @FXML
    private JFXTextField isemrino;

    @FXML
    private JFXTextField teklifno;

    @FXML
    private TableView<Musteriler> musteri_tablo;

    @FXML
    private TableColumn<Musteriler, Number> idsutunn;
    @FXML
    private TableColumn<Musteriler, String> teklifn;

    @FXML
    private TableColumn<Musteriler, String> firmadi;

    @FXML
    private TableColumn<Musteriler, String> ilsu;

    @FXML
    private TableColumn<Musteriler, String> ilcesu;

    @FXML
    private TableColumn<Musteriler, String> isemri;

    @FXML
    private JFXButton yenibt;

    @FXML
    private JFXButton duzenlebt;

    @FXML
    private JFXButton silbt;

    @FXML
    private JFXButton kaydetbt;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane musteri;
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
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/Yuzeydurumu.fxml"));
    }

    @FXML
    void idveri(KeyEvent event) {
        uyarilabel.setText("ID Bilgisi Değiştirilemez");
        Messages.animasyon(uyarilabel);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daom = new DAO_Musteri();
        DBConnection data = new DBConnection();

        try {
            data.getConnection();
            String count = "" + daom.toplam();
            musterisum.setText(count);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        yenibt.setOnAction(e -> {
            if(firmaadi.getText().isEmpty() || ilxetxt.getText().isEmpty() || iltxt.getText().isEmpty() ||
                    isemrino.getText().isEmpty() || teklifno.getText().isEmpty()){
                JFXButton geriButton = new JFXButton("Geri Dön");
                Messages.showDialog(rootPane, musteri, Arrays.asList(geriButton), "Müşteri Ekleme İşlemi",
                        String.format("Lütfen Bütün Alanları Doldurunuz"));
                return;
            }
            String name = firmaadi.getText().substring(0, 1).toUpperCase() + firmaadi.getText().substring(1).toLowerCase();;
            String ilce = ilxetxt.getText().substring(0, 1).toUpperCase() + ilxetxt.getText().substring(1).toLowerCase();;
            String il = iltxt.getText().substring(0, 1).toUpperCase() + iltxt.getText().substring(1).toLowerCase();;
            String isem = isemrino.getText();
            String tn = teklifno.getText();


            try {
                Musteriler musteriekle=new Musteriler(name,il,ilce,isem,tn);
                String b = DAO_Musteri.ekleme(musteriekle);
                if (b == "işlem başarılı") {
                    Messages.TrayMessage("Müşteri Ekleme İşlemi", "Ekleme İşlemi Başarıyla Sonuçlandı", NotificationType.SUCCESS);
                } else {
                    Messages.TrayMessage("Müşteri Ekleme İşlemi", "Müşteri Ekleme İşlemi Başarısız", NotificationType.ERROR);


                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            refreshTable();
            ClearTextfield();
        });

        kaydetbt.setOnAction(e -> {
            int id = Integer.parseInt(idtxt.getText());
            String name = firmaadi.getText().substring(0, 1).toUpperCase() + firmaadi.getText().substring(1).toLowerCase();;
            String il = iltxt.getText().substring(0, 1).toUpperCase() + iltxt.getText().substring(1).toLowerCase();;
            String ilce = ilxetxt.getText().substring(0, 1).toUpperCase() + ilxetxt.getText().substring(1).toLowerCase();
            String isem = isemrino.getText();
            String tn = teklifno.getText();
            Musteriler musteriupdate=new Musteriler(id,name,il,ilce,isem,tn);
            daom.update(musteriupdate);
            refreshTable();
            Messages.TrayMessage("Müşteri Bilgisi Güncelleme", "Değişiklikler Başarıyla Kaydedildi", NotificationType.SUCCESS);
            ClearTextfield();

        });

        duzenlebt.setOnAction(e -> {
            ADD = false;
            EDIT = true;
            editAccount();
            refreshTable();
        });


        silbt.setOnAction(e -> {
            Musteriler selected = musteri_tablo.getSelectionModel().getSelectedItem();
            id = selected.getMid().get();
            JFXButton yesButton = new JFXButton("EVET");
            JFXButton noButton = new JFXButton("HAYIR");
            Messages.showDialog(rootPane, musteri, Arrays.asList(yesButton, noButton), "Müşteri Silme İşlemi",
                    String.format("%s ID'li  %s  Firmasını silmek istediğinize emin misiniz ?", selected.getMid().getValue(), selected.getFirmaname().getValue()));
            noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                Messages.TrayMessage("Müşteri Silme İşlemi", "Silme İşlemi İptal Edildi", NotificationType.INFORMATION);
            });
            yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                daom.deleteAccount(id);
                refreshTable();
                Messages.TrayMessage("Müşteri Silme İşlemi", "Silme İşlemi Başarıyla Gerçekleşti", NotificationType.SUCCESS);
            });

        });
        initTable();
        refreshTable();
    }

    private void initTable() {
        idsutunn.setCellValueFactory(cell -> cell.getValue().getMid());
        firmadi.setCellValueFactory(cell -> cell.getValue().getFirmaname());
        ilsu.setCellValueFactory(cell -> cell.getValue().getIl());
        ilcesu.setCellValueFactory(cell -> cell.getValue().getIlce());
        isemri.setCellValueFactory(cell -> cell.getValue().getIsemrino());
        teklifn.setCellValueFactory(cell -> cell.getValue().getTeklifno());
    }

    public void refreshTable() {
        initTable();
        String query = "SELECT * FROM musteri";
        musteri_tablo.setItems(daom.getAccountsData(query));
    }

    public void ClearTextfield() {
        idtxt.clear();
        firmaadi.clear();
        iltxt.clear();
        ilxetxt.clear();
        isemrino.clear();
        teklifno.clear();
    }


    private void editAccount() {
        Musteriler selected = musteri_tablo.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(selected.getMid().get()));
        firmaadi.setText(selected.getFirmaname().get());
        iltxt.setText(selected.getIl().get());
        ilxetxt.setText(selected.getIlce().get());
        isemrino.setText(selected.getIsemrino().get());
        teklifno.setText(selected.getTeklifno().get());

    }


}
