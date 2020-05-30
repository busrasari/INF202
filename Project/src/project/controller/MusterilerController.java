package project.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import project.Classlar.Ilceler;
import project.Classlar.Iller;
import project.Classlar.Musteriler;
import project.DAO.DAO_Musteri;
import project.DAO.DAO_ilveilce;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author busra
 */
public class MusterilerController implements Initializable {
    private static boolean EDIT = false, ADD = true;
    public String query, id;
    DAO_ilveilce dao = new DAO_ilveilce();
    CalisanlarController c = new CalisanlarController();
    DAO_Musteri daom;
    @FXML
    private TableView<Musteriler> musteri_tablo;
    @FXML
    private TextField musteri_id;
    @FXML
    private TextField firmaadı;
    @FXML
    private TextField isemrino;

    @FXML
    private TextField teklifno;

    @FXML
    private Button kaydetbt;
    @FXML
    private Button yenibt;
    @FXML
    private Button duzenlebt;
    @FXML
    private Button silbt;


    @FXML
    private TextField iltxt;

    @FXML
    private TextField ilxetxt;

    @FXML
    private TableColumn<Musteriler, String> idsutunn;
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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daom = new DAO_Musteri();


        yenibt.setOnAction(e -> {
            String id = musteri_id.getText();
            String name = firmaadı.getText();
            String ilce = ilxetxt.getText();
            String il = iltxt.getText();
            String isem = isemrino.getText();
            String tn = teklifno.getText();


            try {
                String b = DAO_Musteri.ekleme(id, name, il, ilce, isem, tn);
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
            String id = musteri_id.getText();
            String name = firmaadı.getText();
            String il = iltxt.getText();
            String ilce = ilxetxt.getText();
            String isem = isemrino.getText();
            String tn = teklifno.getText();
            daom.update(id, name, il, ilce, isem, tn);
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
        musteri_id.setText(selected.getMid().get());
        firmaadı.setText(selected.getFirmaname().get());
        iltxt.setText(selected.getIl().get());
        ilxetxt.setText(selected.getIlce().get());
        isemrino.setText(selected.getIsemrino().get());
        teklifno.setText(selected.getTeklifno().get());

    }




}
