package project.DataAccesObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.Models.Calisanlar;
import project.database.DBConnection;

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
    private static PreparedStatement ps;
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
                list.add(new Calisanlar(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public void deleteAccount(int p_id) {
        String query = "DELETE FROM personel WHERE id=" + p_id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        saveData(query);
    }


    public void update(String p_ad, String p_soyad, String p_level) {
        String query = "UPDATE personel SET Soyadı=?,Seviye=?  WHERE Adı=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, p_soyad);
            ps.setString(2, p_level);
            ps.setString(3, p_ad);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Calisan.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static String ekleme(String p_id, String p_ad, String p_soyad, String p_level) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO personel(Adı, Soyadı, Seviye) VALUES(?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, p_ad);
            ps.setString(2, p_soyad);
            ps.setString(3, p_level);
            ps.executeUpdate();
            String a = "işlem başarılı";
            return a;

        } catch (Exception e) {

           /* Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Personel Ekleme İşlemi Başarısız :(");
            alert.setContentText("Ama üzülmeyin, tekrar deneyebilirsiniz :) Herkes ikinci bir şansı hakeder...");
            alert.show();
            e.printStackTrace(); */
        }
        return null;
    }

    public ObservableList<Calisanlar> getNameComboBox() {
        ObservableList<Calisanlar> list = FXCollections.observableArrayList();
        String query = "SELECT * from personel";
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Calisanlar c = new Calisanlar(rs.getString(2), rs.getString(3));
                list.addAll(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public static String getLevel(String ad, String soyad){

        String l = null;

        try {
            String query = "SELECT Seviye from personel WHERE (Adı,Soyadı) =(?,?)";
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, ad);
            pstmt.setString(2, soyad);
            rs = pstmt.executeQuery();
            System.out.println("devamkee");
            while(rs.next()){
                System.out.println("devamke22");
                l = (rs.getString("Seviye"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Calisan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
}













