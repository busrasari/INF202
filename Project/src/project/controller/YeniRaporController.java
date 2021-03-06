/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import project.Export.Excel;
import project.Export.PDF;
import project.Helper.Asistan;
import project.Ressource.*;
import project.DataAccesObject.*;
import project.database.DBConnection;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author busra
 */
public class YeniRaporController implements Initializable {
    private static final DBConnection database = new DBConnection();
    private static String deneme;
    private static String deneme1;




    final ObservableList<Ekipmanlar> options = FXCollections.observableArrayList();

    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;

    ObservableList<String> sonuclist = FXCollections.observableArrayList();
    ObservableList<String> akimlist = FXCollections.observableArrayList();
    ObservableList<String> muayenelist = FXCollections.observableArrayList();
    DAO_Ekipman dao_ekipman = new DAO_Ekipman();
    DAO_Musteri dao_musteri = new DAO_Musteri();
    DAO_Projeler dao_projeler = new DAO_Projeler();
    DAO_Calisan dao_calisan = new DAO_Calisan();
    DAO_YuzeyDurumu dao_yuzeyDurumu = new DAO_YuzeyDurumu();
    ComboBox comboBox = new ComboBox(options);
    Ekipmanlar id;
    Ekipmanlar ekipman = new Ekipmanlar(id);
    @FXML
    private TextField kmtxt, mptxt, mttxt, uvtxt, isiktxt;
    @FXML
    private TextField testyeri,isemri,teklifno;
    @FXML
    private TextField optxt_adi, optxt_seviye;
    @FXML
    private TextField detxt_adi, detxt_seviye;
    @FXML
    private TextField ontxt_adi, ontxt_seviye;
    @FXML
    private TextField must,dest,mupro,mukap,resno,
            sfno,rapno,mubolg,lux,muor,migi,isilis,
            ysicaklik,muboalan,yuzey,isik,katest,stsp,
            kano,kontrolu,kaynaky,kalinlik,cap, hatatip,
            hatay, aciklamave;


    @FXML
    private TextField mustxt_adi;

    @FXML
    private VBox vbox;
    @FXML
    private JFXComboBox<String> cihazadi;
    @FXML
    private ComboBox<String> sonuc1, sonuc2, sonuc3, sonuc4, sonuc5, sonuc6, sonuc7, sonuc8, sonuc9, sonuc10, sonuc11, sonuc12, sonuc13, sonuc14;

    @FXML
    private JFXComboBox<String> mustericombo;
    @FXML
    private JFXComboBox<Projeler> projecombo;
    @FXML
    private JFXComboBox<YuzeyDurumu> yuzeydcombo;
    @FXML
    private JFXComboBox<String> muayenecombo,akimtipi;
    @FXML
    private TextField rapor_tarihi;

    @FXML
    private TextField muayene_tarihi;


    @FXML
    private TextField optarih;


    @FXML
    private TextField detarih;


    @FXML
    private TextField ontarih;

    @FXML
    private TextField mutarih;

    @FXML
    private JFXButton geridon;
    @FXML
    private JFXButton pdfbt;
    @FXML
    ScrollPane Deneme;
    @FXML AnchorPane raporekrani;
    @FXML AnchorPane deneme2;

    @FXML
    void geridon(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/RaporOlustur.fxml"));
    }

    @FXML
    void hometusu(MouseEvent event) {
        Asistan.loadWindow(event, getClass().getResource("/project/fxml/FXMLDocument.fxml"));

    }
   public int rno=100;
    PDF pdf =new PDF();
    Excel excel=new Excel();
    @FXML
    void xlsyap(ActionEvent event) {
       String musteriname=mustericombo.getValue();
       String proje= String.valueOf(projecombo.getValue());
       String testy = testyeri.getText();
       String musta= must.getText();
       String degst= dest.getText();
       String mupros=mupro.getText();
       String muakaps=mukap.getText();
       String resimno= resno.getText();
       String yuzeyd= String.valueOf(yuzeydcombo.getValue());
       String masamasi= muayenecombo.getValue();
       String sayfano= sfno.getText();
       String raporn= String.valueOf(rno);
       String tarihr= rapor_tarihi.getText();
       String iseno= isemri.getText();
       String teklfno= teklifno.getText();
       String chazadi= cihazadi.getValue();
       String km= kmtxt.getText();
       String to= mptxt.getText();
       String mikt= mttxt.getText();
       String isiks= uvtxt.getText();
       String mesafeis= isiktxt.getText();
        String muayeneB = mubolg.getText();
        String akimT = akimtipi.getValue();
        String luxM = lux.getText();
        String muayeneO = muor.getText();
        String miknatisG = migi.getText();
        String isil = isilis.getText();
        String ySicaklik = ysicaklik.getText();
        String mubalan = muboalan.getText();
        String yzey = yuzey.getText();
        String isikcihaz = isik.getText();
        String kaldirmaT = katest.getText();
        String standartS = stsp.getText();
        String muTarih = muayene_tarihi.getText();
        String aciklamalar = aciklamave.getText();
        String kpN= kano.getText();
        String kuN= kontrolu.getText();
        String ky= kaynaky.getText();
        String kalin =kalinlik.getText();
        String cp = cap.getText();
        String ht = hatatip.getText();
        String hy = hatay.getText();
        String s = sonuc1.getValue();
        String opN = optxt_adi.getText();
        String deN = detxt_adi.getText();
        String onN = ontxt_adi.getText();
        String opS = optxt_seviye.getText();
        String deS = detxt_seviye.getText();
        String onS= ontxt_seviye.getText();
        String opT= optarih.getText();
        String deT = detarih.getText();
        String onT = ontarih.getText();
        ++rno;
        this.rapno.setText(String.valueOf(rno));
        try {
            excel.Export_Excel(musteriname, proje, testy,musta,degst,mupros,muakaps,resimno,yuzeyd,masamasi,sayfano,
                    raporn,tarihr,iseno,teklfno,km,chazadi,to,mikt,isiks,mesafeis,muayeneB,akimT,luxM,muayeneO,miknatisG,
                    isil,ySicaklik,mubalan,yzey,isikcihaz,kaldirmaT,standartS,muTarih,aciklamalar,kpN,kuN,cp,ht,hy,s,ky,
                    kalin,opN,deN,onN,opS,deS,onS,opT,deT,onT);
        } catch (IOException ex1) {
            Logger.getLogger(YeniRaporController.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }





    @FXML
    void pdfyap(ActionEvent event) {
       pdf.Export_PDF(raporekrani);
    }



    public void initialize(URL url, ResourceBundle rb) {
        init_Cihaz();
        init_Musteri();
        loadData();
  this.rapno.setText(String.valueOf(rno));
        this.optxt_adi.setText(String.valueOf(RaporOlusturController.s_operator));
        this.detxt_adi.setText(String.valueOf(RaporOlusturController.s_degerlendiren));
        this.ontxt_adi.setText(String.valueOf(RaporOlusturController.s_onaylayan));
        this.optxt_seviye.setText(RaporOlusturController.sOpSeviye);
        this.detxt_seviye.setText(RaporOlusturController.sDeSeviye);
        this.ontxt_seviye.setText(RaporOlusturController.sOnSeviye);

        this.rapor_tarihi.setText(String.valueOf(RaporOlusturController.secilentarih));
        this.optarih.setText(String.valueOf(RaporOlusturController.secilentarih));
        this.detarih.setText(String.valueOf(RaporOlusturController.secilentarih));
        this.ontarih .setText(String.valueOf(RaporOlusturController.secilentarih));
        this.muayene_tarihi.setText(String.valueOf(RaporOlusturController.secilentarih));

       /* cihazadi.setOnMouseClicked(e -> {
            init_Cihaz();
            deneme= String.valueOf(cihazadi.getSelectionModel().getSelectedItem());
            System.out.println(deneme.toUpperCase());
            Ekipmanlar a;
            a=Ekipmanlar.ekipman(deneme);
            kmtxt.setText(a.getKutupM().get());
            mptxt.setText(a.getMpTAO().get());
            mttxt.setText(a.getMTeknik().get());
            uvtxt.setText(a.getUv().get());
            isiktxt.setText(a.getIsik().get());





            /*try {
                String query = "SELECT * FROM ekipman WHERE id= " + ekipman.getE_id();
                conn = database.getConnection();
                pst = conn.prepareStatement(query);
                System.out.println("plsss");
                pst.setString(1, String.valueOf(cihazadi.getSelectionModel().getSelectedItem()));
                System.out.println("bçalışş");
                rs = pst.executeQuery();
                System.out.println("TRY");
                while (rs.next()) {
                    kmtxt.setText(rs.getString(3));
                    mptxt.setText(rs.getString(4));
                    mttxt.setText(rs.getString(5));
                    uvtxt.setText(rs.getString(6));
                    isiktxt.setText(rs.getString(7));
                    System.out.println("aleyküm");
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(YeniRaporController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); */


        yuzeydcombo.setOnMouseClicked(e -> {
            init_YuzeyDurumu();
        });
        projecombo.setOnMouseClicked(e -> {
            init_Projeler();
        });
    }



    private void loadData() {
        sonuclist.removeAll(sonuclist);
        akimlist.removeAll(akimlist);
        muayenelist.removeAll(muayenelist);
        String ok = "OK";
        String red = "RED";
        String ac = "AC";
        String dc = "DC";
        String unt = "Untreated";
        String t = "Treated";
        sonuclist.addAll(ok, red);
        akimlist.addAll(ac, dc);
        muayenelist.addAll(unt, t);
        sonuc1.getItems().addAll(ok, red);
        sonuc2.getItems().addAll(ok, red);
        sonuc3.getItems().addAll(ok, red);
        sonuc4.getItems().addAll(ok, red);
        sonuc5.getItems().addAll(ok, red);
        sonuc6.getItems().addAll(ok, red);
        sonuc7.getItems().addAll(ok, red);
        sonuc8.getItems().addAll(ok, red);
        sonuc9.getItems().addAll(ok, red);
        sonuc10.getItems().addAll(ok, red);
        sonuc11.getItems().addAll(ok, red);
        sonuc12.getItems().addAll(ok, red);
        sonuc13.getItems().addAll(ok, red);
        sonuc14.getItems().addAll(ok, red);
        akimtipi.getItems().addAll(ac, dc);
        muayenecombo.getItems().addAll(unt, t);

    }

    private void init_Cihaz()  {
        String sql="SELECT Cihaz FROM ekipman";
        try {
            cihazadi.setItems(dao_ekipman.getCihazComboBox(sql));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void init_Musteri() {
        String sql = "SELECT Firma_Adi from musteri ";
        try {
            mustericombo.setItems((dao_musteri.getMusteriComboBox(sql)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void init_YuzeyDurumu() {
        yuzeydcombo.setItems(dao_yuzeyDurumu.getYuzeyDComboBox());
    }

    private void init_Projeler() {
        projecombo.setItems(dao_projeler.getProjeComboBox());
    }

    @FXML
    void cihazgir(ActionEvent event) {
        init_Cihaz();
        deneme= String.valueOf(cihazadi.getSelectionModel().getSelectedItem());
        Ekipmanlar a;
        a=DAO_Ekipman.ekipman(deneme);
        kmtxt.setText(a.getKutupM().get());
        mptxt.setText(a.getMpTAO().get());
        mttxt.setText(a.getMTeknik().get());
        uvtxt.setText(a.getUv().get());
        isiktxt.setText(a.getIsik().get());
    }

    @FXML
    void CustomersotherInfo(ActionEvent event) {
        deneme1= String.valueOf(mustericombo.getSelectionModel().getSelectedItem());
        Musteriler b;
        b=DAO_Musteri.musteriler(deneme1);
        testyeri.setText(b.getIl().get() + "/"+ b.getIlce().get());
        isemri.setText(b.getIsemrino().get());
        teklifno.setText(b.getTeklifno().get());


    }

}
