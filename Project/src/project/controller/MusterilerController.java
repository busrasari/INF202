package project.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import project.Calisanlar;
import project.Ilceler;
import project.Iller;
import project.database.DAO_Calisan;
import project.database.DAO_Musteri;
import project.database.DAO_ilveilce;
import project.Musteriler;
import project.database.DBConnection;

import java.net.URL;
import java.sql.PreparedStatement;
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

    DAO_ilveilce dao= new DAO_ilveilce();
    DAO_Musteri daom;
    public String query, id;


    @FXML
    private TableView<Musteriler> musteri_tablo;
    @FXML
    private TextField musteri_id;
    @FXML
    private TextField firmaadı;

    @FXML
    private Button kaydetbt;
    @FXML
    private Button yenibt;
    @FXML
    private Button duzenlebt;
    @FXML
    private Button silbt;
    @FXML
    private JFXComboBox<Iller> ilcombo;

    @FXML
    private JFXComboBox<Ilceler> ilcecombo;


    @FXML
    private TableColumn<Musteriler,String > idsutunn;
    @FXML
    private TableColumn<Musteriler, String> teklifn;

    @FXML
    private TableColumn<Musteriler,String> firmadi;

    @FXML
    private TableColumn<Musteriler ,String > ilsu;

    @FXML
    private TableColumn<Musteriler, String> ilcesu;

    @FXML
    private TableColumn<Musteriler, String> isemri;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daom = new DAO_Musteri();
       ilcombo.setOnMouseClicked(e->{
            comboDoldur();
        });
       ilcecombo.setOnMouseClicked(e->{
           comboIlceDoldur();
       });

         /* yenibt.setOnAction(e -> {
          if (musteri_id.getText().isEmpty() || firmaadı.getText().isEmpty() || txt_soyad.getText().isEmpty() || level.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Lütfen tüm alanları doldurunuz");
                alert.showAndWait();
                return;
            }
            String name = txt_ad.getText();
            String nachn = txt_soyad.getText().toUpperCase();
            String id = txt_id.getText();
            String seviye = level.getValue().toString();
            try {
                String b = dao.ekleme(id, name, nachn, seviye);
                if (b == "işlem başarılı") {
                    basarili.setText("Ekleme İşlemi Başarılıyla Sonuçlandı");
                    animasyon();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            refreshTable();
        });
        kaydetb.setOnAction(e -> {
            String id = txt_id.getText();
            String ad = txt_ad.getText();
            String soyadi = txt_soyad.getText().toUpperCase();
            String seviyes = level.getValue();
            dao.update(id, ad, soyadi, seviyes);
            refreshTable();
            basarili.setText("Değişiklikler güncellendi");
            animasyon();

        }); */

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
        musteri_id.setText(selected.getMid().get());
        firmadi.setText(selected.getFirmaname().get());
        ilcombo.getSelectionModel().select(Integer.parseInt(selected.getIl().get()));
        ilcecombo.getSelectionModel().select(Integer.parseInt(selected.getIlce().get()));
        isemri.setText(selected.getIsemrino().get());
        teklifn.setText(selected.getTeklifno().get());
    }

    private void comboDoldur() {
       ilcombo.setItems(dao.getIller());



    }
    private void comboIlceDoldur() {
       int a= ilcombo.getSelectionModel().getSelectedItem().getIlId();
        ilcecombo.setItems(dao.getileGoreIlce(a));
        System.out.println("lalaalala");

    }


}
