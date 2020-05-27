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
    private static  PreparedStatement ps;
    public final ObservableList options = FXCollections.observableArrayList();

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


    public void deleteAccount(String p_id) {
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
            ps.setString(1, p_ad);
            ps.setString(2, p_soyad);
            ps.setString(3, p_level);
            ps.setString(4, p_id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Calisan.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static String ekleme(String p_id, String p_ad, String p_soyad, String p_level) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO personel(id, Adı, Soyadı, Seviye) VALUES(?,?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, p_id);
            ps.setString(2, p_ad);
            ps.setString(3, p_soyad);
            ps.setString(4, p_level);
            ps.executeUpdate();
            String a= "işlem başarılı";
            return a;

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Personel Ekleme İşlemi Başarısız :(");
            alert.setContentText("Ama üzülmeyin, tekrar deneyebilirsiniz :) Herkes ikinci bir şansı hakeder...");
            alert.show();
            e.printStackTrace();
        }
        return null;
    }


   /* ComboBox comboBox = new ComboBox(options);
    public void fillComboBox(){
        options.clear();
        try {
            String query = "SELECT Adı FROM personel";
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();

            while(rs.next()){
                options.add(rs.getString("Adı"));
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Calisan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}




