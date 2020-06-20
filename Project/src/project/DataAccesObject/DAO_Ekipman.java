package project.DataAccesObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.Ressource.Calisanlar;
import project.Ressource.Ekipmanlar;
import project.database.DBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO_Ekipman {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static PreparedStatement ps;
    DAO_Calisan dao_calisan = new DAO_Calisan();

    public ObservableList<Ekipmanlar> getAccountsData(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Ekipmanlar(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
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

    public void deleteAccount(int e_id) {
        String query = "DELETE FROM ekipman WHERE id=" + e_id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        saveData(query);
    }


    public void update(Ekipmanlar ekipman) {
        String query = "UPDATE ekipman SET Cihaz=?, Kutup_Mesafesi=?,MP_Tasiyici_Ortam=?,Miknatislama_teknigi=?,UV_isiksiddeti=?,isik_mesafesi=?  WHERE id=?";
        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, ekipman.getcihaz());
            ps.setString(2, ekipman.getKutup());
            ps.setString(3, ekipman.getMptao());
            ps.setString(4, ekipman.getMteknik());
            ps.setString(5, ekipman.getUvs());
            ps.setString(6, ekipman.getisik());
            ps.setInt(7, ekipman.getid());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Ekipman.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static String ekleme(Ekipmanlar ekipman) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO ekipman(Cihaz,Kutup_Mesafesi,MP_Tasiyici_Ortam,Miknatislama_teknigi,UV_isiksiddeti,isik_mesafesi) VALUES(?,?,?,?,?,?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, ekipman.getcihaz());
            ps.setString(2, ekipman.getKutup());
            ps.setString(3, ekipman.getMptao());
            ps.setString(4, ekipman.getMteknik());
            ps.setString(5, ekipman.getUvs());
            ps.setString(6, ekipman.getisik());
            ps.executeUpdate();
            String a = "işlem başarılı";
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

    /*public ObservableList<Calisanlar> getNameComboBox(){
        ObservableList<Calisanlar> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM personel";
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Calisanlar c = new Calisanlar(
                        rs.getString(2),
                        rs.getString(3)
                );
                list.add(c);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    } */

   /* public ObservableList<Ekipmanlar> getCihazComboBox() {
        ObservableList<Ekipmanlar> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM ekipman";
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Ekipmanlar ekipmanlar = new Ekipmanlar(
                        rs.getString(2)

                );
                list.add(ekipmanlar);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    } */

    public ObservableList<String> getCihazComboBox(String sql) throws SQLException {
        ObservableList<String> cihazList = FXCollections.observableArrayList();
        connect = database.getConnection();
        pstmt = connect.prepareStatement(sql);
        rs = pstmt.executeQuery("SELECT Cihaz from ekipman");
        while (rs.next()) {
            cihazList.addAll(rs.getString("Cihaz"));
        }
        return cihazList;


    }
    public int toplam() throws SQLException {
        connect = database.getConnection();
        Statement pstmt = connect.createStatement();
        String query = "select count(id) as count from ekipman";
        ResultSet rs = pstmt.executeQuery(query);
        rs.next();
        int count = rs.getInt("count");
        //System.out.println("Personel Sayısı: " + count);
        return count;
    }
    public static Ekipmanlar ekipman(String cihaz){
        connect = database.getConnection();
        String query="select * from ekipman where Cihaz=?";
        Ekipmanlar ekipmanlar=null;
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, cihaz);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ekipmanlar=new Ekipmanlar(rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Ekipman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ekipmanlar;
    }
}
