/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import project.DAO.DAO_Ekipman;
import project.Classlar.Ekipmanlar;

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
    private Button editbt;

    @FXML
    private Button silbt;

    @FXML
    private TextField cihaztxt;

    @FXML
    private TextField kmtxt;

    @FXML
    private TextField mptxt;

    @FXML
    private TextField mttxt;

    @FXML
    private TextField uvtxt;

    @FXML
    private TextField isiktxt;

    @FXML
    private Button kaydetbt;

    @FXML
    private Button eklebt;

    @FXML
    private Label basarili;

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
