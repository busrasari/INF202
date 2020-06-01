package project.DataAccesObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.Models.Musteriler;
import project.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Musteri {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static  PreparedStatement ps;
    DAO_Calisan dao_calisan=new DAO_Calisan();

    public ObservableList<Musteriler> getAccountsData(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Musteriler(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    public void deleteAccount(int id) {
        String query = "DELETE FROM musteri WHERE id=" + id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        saveData(query);
    }
/*
    id
    Firma Adı varchar(45)
    İl varchar(45)
    İlce varchar(45)
    İş Emri No varchar(45)
    Teklif No
     */

    public void update(String firmaAdi, String il, String ilce,String isemri,String teklif) {
        String query = "UPDATE musteri SET il=?,ilce=?,is_emrino =?,teklif_no =?  WHERE Firma_Adi=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, il);
            ps.setString(2, ilce);
            ps.setString(3, isemri);
            ps.setString(4, teklif);
            ps.setString(5, firmaAdi);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


    public static String ekleme( String firmaAdi, String il, String ilce,String isemri,String teklif) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO musteri(Firma_Adi,il,ilce,is_emrino,teklif_no) VALUES(?,?,?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, firmaAdi);
            ps.setString(2, il);
            ps.setString(3, ilce);
            ps.setString(4, isemri);
            ps.setString(5, teklif);
            ps.executeUpdate();
            String a= "işlem başarılı";
            return a;

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Müşteri Ekleme İşlemi Başarısız :(");
            alert.setContentText("Ama üzülmeyin, tekrar deneyebilirsiniz :) Herkes ikinci bir şansı hakeder...");
            alert.show();
            e.printStackTrace();
        }
        return null;
    }


    public ObservableList<String> getMusteriComboBox(String sql) throws SQLException  {
        ObservableList<String> list = FXCollections.observableArrayList();
        connect = database.getConnection();
        pstmt = connect.prepareStatement(sql);
        rs = pstmt.executeQuery("SELECT Firma_Adi from musteri ");
        while(rs.next()){
           list.addAll(rs.getString("Firma_Adi"));
        }
        return list;
    }

}
