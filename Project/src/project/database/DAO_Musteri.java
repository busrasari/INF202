package project.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.Ekipmanlar;
import project.Musteriler;

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
                list.add(new Musteriler(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteAccount(String id) {
        String query = "DELETE FROM musteri WHERE =" + id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dao_calisan.saveData(query);
    }
/*
    id
    Firma Adı varchar(45)
    İl varchar(45)
    İlce varchar(45)
    İş Emri No varchar(45)
    Teklif No
     */

    public void update(String id, String firmaAdi, String il, String ilce,String isemri,String teklif) {
        String query = "UPDATE musteri SET id=?,Firma Adı=?,İl=?,İlce=?,İş Emri No =?,Teklif No =?  WHERE id=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, firmaAdi);
            ps.setString(2, il);
            ps.setString(3, ilce);
            ps.setString(4, isemri);
            ps.setString(5, teklif);
            ps.setString(6, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    public static String ekleme(String id, String firmaAdi, String il, String ilce,String isemri,String teklif) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO musteri(id,Firma Adı,İl,İlce,İş Emri No,Teklif No) VALUES(?,?,?,?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, firmaAdi);
            ps.setString(2, il);
            ps.setString(3, ilce);
            ps.setString(4, isemri);
            ps.setString(5, teklif);
            ps.setString(6, id);
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


}
