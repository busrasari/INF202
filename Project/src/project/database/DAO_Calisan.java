package project.database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.Calisanlar;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author busra
 */
public class DAO_Calisan {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    PreparedStatement ps;

    public DAO_Calisan() {
    }

    public void saveData(String query) {
        try {
            connect = database.getConnection(); // get connection
            pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close(connect, pstmt, null);
        }
    }

    public ObservableList<Calisanlar> getAccountsData(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Calisanlar(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




    public void deleteAccount( String p_id)  {
        String query = "DELETE FROM personel WHERE id=" + p_id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Calisanlar selected = calisan.tablo_personel.getSelectionModel().getSelectedItem();
        //id = selected.getpID().get();

        saveData(query);
    }


    public void update(String p_id, String p_ad, String p_soyad, String p_level) {
        String query = "UPDATE personel SET Adı=?,Soyadı=?,Seviye=?  WHERE id=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1,  p_id);
            ps.setString(2, p_ad);
            ps.setString(3, p_soyad);
            ps.setString(4, p_level);
            ps.executeUpdate();

        }
        catch (SQLException ex) {
            Logger.getLogger(DAO_Calisan.class.getName()).log(Level.SEVERE, null, ex);
        }


       /* Statement stmt = connect.createStatement();
        String ad = calisan.txt_ad.getText();
        String id = calisan.txt_id.getText();
        String soyad = calisan.txt_soyad.getText();
        String seviye = calisan.level.getValue(); */


    }

    public void ekleme(String p_id, String p_ad, String p_soyad, String p_level ) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO personel(id, Adı, Soyadı, Seviye) VALUES(?,?,?,?)";

        if (p_id.isEmpty() || p_ad.isEmpty() || p_soyad.isEmpty() || p_level.isEmpty()) {
            return;
        }

        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1,p_id);
            ps.setString(2,p_ad);
            ps.setString(3,p_soyad);
            ps.setString(4,p_level);
            ps.executeUpdate();
            //rs=ps.executeQuery();

            if (rs != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Personel Ekleme İşlemi");
                alert.setContentText("Ekleme Başarıyla sonuçlandı :)");
                alert.show();
            }
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Personel Ekleme İşlemi Başarısız :(");
            alert.setContentText("Ama üzülmeyin, tekrar deneyebilirsiniz :) Herkes ikinci bir şansı hakeder...");
            alert.show();
            e.printStackTrace();
        }
    }
}




