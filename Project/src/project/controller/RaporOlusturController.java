package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import project.Models.Calisanlar;
import project.DataAccesObject.DAO_Calisan;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;


public class RaporOlusturController implements Initializable {
    DAO_Calisan dao= new DAO_Calisan();

    static String secilentarih;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXComboBox<Calisanlar> operator;

    @FXML
    private JFXComboBox<Calisanlar> degerlendiren;

    @FXML
    private JFXComboBox<Calisanlar> onaylayan;


    @FXML
    private JFXButton radyografikr;

    @FXML
    private JFXButton manyetikr;

    static Calisanlar s_operator;
    static Calisanlar s_degerlendiren;
    static Calisanlar s_onaylayan;
    static String a,b;
    static String sOpSeviye;
    static String sDeSeviye;
    static String sOnSeviye;

    @FXML
    private void rapor_olustur(MouseEvent event) throws IOException {
       choosenPersonel();
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/YeniRapor.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    public void initialize(URL url, ResourceBundle rb) {

        operator.setOnMouseClicked(e->{
            init_OpName();
        });
        degerlendiren.setOnMouseClicked(e->{
            init_DName();
        });
        onaylayan.setOnMouseClicked(e->{
         init_OnName();
        });

    }

    @FXML
    private void anasayfadon(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/project/fxml/FXMLDocument.fxml")));

        stage.setScene(scene);
        stage.show();
    }


    private void init_OpName() {
            operator.setItems(dao.getNameComboBox()); }


    private void init_OnName() {
        onaylayan.setItems(dao.getNameComboBox());
    }
    private void init_DName() {
        degerlendiren.setItems(dao.getNameComboBox());
    }

    public void choosenPersonel(){
        s_operator=operator.getSelectionModel().getSelectedItem();
        System.out.println(s_operator);
        a = String.valueOf(s_operator.getpname().get());
        System.out.println(a);
        b = String.valueOf(s_operator.getpLastname().get());
        System.out.println(b);
        DAO_Calisan dc = new DAO_Calisan();
        sOpSeviye=DAO_Calisan.getLevel(a,b);
        System.out.println("hadibakim3");

        s_degerlendiren=degerlendiren.getSelectionModel().getSelectedItem();
        System.out.println(s_degerlendiren);
        a = String.valueOf(s_degerlendiren.getpname().get());
        System.out.println(a);
        b = String.valueOf(s_degerlendiren.getpLastname().get());
        System.out.println(b);
        sDeSeviye=DAO_Calisan.getLevel(a,b);

        s_onaylayan=onaylayan.getSelectionModel().getSelectedItem();
        System.out.println(s_onaylayan);
        a = String.valueOf(s_onaylayan.getpname().get());
        System.out.println(a);
        b = String.valueOf(s_onaylayan.getpLastname().get());
        System.out.println(b);
        sOnSeviye=DAO_Calisan.getLevel(a,b);

    }
    @FXML
    private void tarihal(ActionEvent event) {
        LocalDate tarih = date.getValue();
        secilentarih = tarih.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

    }


}
