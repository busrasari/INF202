package project.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.Calisanlar;
import project.Ekipmanlar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Ekipman {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static  PreparedStatement ps;
    DAO_Calisan dao_calisan=new DAO_Calisan();

    public ObservableList<Ekipmanlar> getAccountsData(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Ekipmanlar(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteAccount(String cihaz) {
        String query = "DELETE FROM ekipman WHERE Cihaz=" + cihaz + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dao_calisan.saveData(query);
    }



    public void update(String cihaz, String kutup, String mp, String mıknatıs,String uv,String isik) {
        String query = "UPDATE ekipman SET Cihaz=?,Kutup Mesafesi=?,MP Taşıyıcı Ortam=?,Mıknatıslama Tekniği=?,UV Işık Şiddeti=?,Işık Mesafesi=?  WHERE Cihaz=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, kutup);
            ps.setString(2, mp);
            ps.setString(3, mıknatıs);
            ps.setString(4, uv);
            ps.setString(5, isik);
            ps.setString(6, cihaz);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Ekipman.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
        /*
    Cihaz varchar(50)
Kutup Mesafesi varchar(45)
MP Taşıyıcı Ortam varchar(45)
Mıknatıslama Tekniği varchar(45)
UV Işık Şiddeti varchar(45)
Işık Mesafesi
     */

    public static String ekleme(String cihaz, String kutup, String mp, String mıknatıs,String uv,String isik) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO ekipman(Cihaz,Kutup Mesafesi,MP Taşıyıcı Ortam,Mıknatıslama Tekniği,UV Işık Şiddeti,Işık Mesafesi) VALUES(?,?,?,?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, kutup);
            ps.setString(2, mp);
            ps.setString(3, mıknatıs);
            ps.setString(4, uv);
            ps.setString(5, isik);
            ps.setString(6, cihaz);
            ps.executeUpdate();
            String a= "işlem başarılı";
            return a;

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ekipman Ekleme İşlemi Başarısız :(");
            alert.setContentText("Ama üzülmeyin, tekrar deneyebilirsiniz :) Herkes ikinci bir şansı hakeder...");
            alert.show();
            e.printStackTrace();
        }
        return null;
    }

}
