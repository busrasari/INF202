package project.DataAccesObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.Ressource.Musteriler;
import project.database.DBConnection;

import java.sql.*;
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


    public void update(Musteriler musteri) {
        String query = "UPDATE musteri SET Firma_Adi=?, il=?,ilce=?,is_emrino =?,teklif_no =?  WHERE id=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, musteri.getfirmaname());
            ps.setString(2, musteri.getil());
            ps.setString(3, musteri.getilce());
            ps.setString(4, musteri.getisemrino());
            ps.setString(5, musteri.getteklifno());
            ps.setInt(6,musteri.getmid());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Musteri.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


    public static String ekleme( Musteriler musteri) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO musteri(Firma_Adi,il,ilce,is_emrino,teklif_no) VALUES(?,?,?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, musteri.getfirmaname());
            ps.setString(2, musteri.getil());
            ps.setString(3, musteri.getilce());
            ps.setString(4, musteri.getisemrino());
            ps.setString(5, musteri.getteklifno());
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
    public int toplam() throws SQLException {
        connect = database.getConnection();
        Statement pstmt = connect.createStatement();
        String query = "select count(id) as count from musteri";
        ResultSet rs = pstmt.executeQuery(query);
        rs.next();
        int count = rs.getInt("count");
        //System.out.println("Personel Sayısı: " + count);
        return count;
    }

    public static Musteriler musteriler(String firma){
        connect = database.getConnection();
        String query="select * from musteri where Firma_Adi=?";
        Musteriler musteriler=null;
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, firma);
            rs = pstmt.executeQuery();
            while(rs.next()){
                musteriler=new Musteriler(rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Ekipman.class.getName()).log(Level.SEVERE, null, ex);
        }


        return musteriler;
    }

}
