package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import project.Models.Musteriler;
import project.DataAccesObject.DAO_Musteri;
import project.DataAccesObject.DAO_ilveilce;
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
public class MusterilerController implements Initializable {
    private static boolean EDIT = false, ADD = true;
    public String query;
    public int id;
    DAO_ilveilce dao = new DAO_ilveilce();
    CalisanlarController c = new CalisanlarController();
    DAO_Musteri daom;
    @FXML
    private Label basarili;

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
    void enter_anasayfa(MouseEvent event) {
        SayfaGecis.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));

    }

    @FXML
    void enter_ekipman(MouseEvent event) {

    }

    @FXML
    void enter_musteri(MouseEvent event) {

    }

    @FXML
    void enter_personell(MouseEvent event) {

    }

    @FXML
    void enter_projeler(MouseEvent event) {

    }

    @FXML
    void enter_yuzeydurumu(MouseEvent event) {

    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daom = new DAO_Musteri();


        yenibt.setOnAction(e -> {
          //  String id = musteri_id.getText();
            String name = firmaadi.getText();
            String ilce = ilxetxt.getText();
            String il = iltxt.getText();
            String isem = isemrino.getText();
            String tn = teklifno.getText();


            try {
                String b = DAO_Musteri.ekleme(name, il, ilce, isem, tn);
                if (b == "işlem başarılı") {
                    //basarili.setText("Ekleme İşlemi Başarılıyla Sonuçlandı");
                   // c.animasyon();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            refreshTable();
        });

        kaydetbt.setOnAction(e -> {
           // String id = musteri_id.getText();
            String name = firmaadi.getText();
            String il = iltxt.getText();
            String ilce = ilxetxt.getText();
            String isem = isemrino.getText();
            String tn = teklifno.getText();
            daom.update(name, il, ilce, isem, tn);
            refreshTable();
           // c.basarili.setText("Değişiklikler güncellendi");
            //c.animasyon();

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Uyarı");
            alert.setHeaderText("Personel Silme");
            alert.setContentText("Seçtiğiniz Personeli Silmek İstediğinize Emin Misiniz?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                daom.deleteAccount(id);
                refreshTable();
                ///  basarili.setText("Silme İşlemi Başarıyla Gerçekleşti");
                // animasyon();
            } else {
                return;
            }


        });
        initTable();
        refreshTable();


        // TODO

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


    private void editAccount() {
        Musteriler selected = musteri_tablo.getSelectionModel().getSelectedItem();
       // musteri_id.setText(selected.getMid().get());
        firmaadi.setText(selected.getFirmaname().get());
        iltxt.setText(selected.getIl().get());
        ilxetxt.setText(selected.getIlce().get());
        isemrino.setText(selected.getIsemrino().get());
        teklifno.setText(selected.getTeklifno().get());

    }




}
