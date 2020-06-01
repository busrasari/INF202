package project.DataAccesObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.Models.YuzeyDurumu;
import project.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_YuzeyDurumu {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static  PreparedStatement ps;

    public ObservableList<YuzeyDurumu> getAccountsData(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new YuzeyDurumu(rs.getInt(1),rs.getString(2)));
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

    public void deleteAccount(int yd_id) {
        String query = "DELETE FROM yuzeydurumu WHERE id=" + yd_id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        saveData(query);
    }



    public static String ekleme(String yuzeydurumu) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO yuzeydurumu(adi) VALUES(?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, yuzeydurumu);
            ps.executeUpdate();
            String a= "işlem başarılı";

            return a;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<YuzeyDurumu> getYuzeyDComboBox() {
        ObservableList<YuzeyDurumu> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM yuzeydurumu";
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                YuzeyDurumu yuzeyDurumu = new YuzeyDurumu(
                        rs.getString(2)

                );
                list.add(yuzeyDurumu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
