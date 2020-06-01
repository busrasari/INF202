package project.DataAccesObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.Models.Projeler;
import project.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Projeler {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static  PreparedStatement ps;

    public ObservableList<Projeler> getAccountsData(String query) {
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Projeler(rs.getInt(1),rs.getString(2)));
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

    public void deleteAccount(int proje_id) {
        String query = "DELETE FROM projeler WHERE id=" + proje_id + "";
        try {
            ps = DBConnection.connect.prepareStatement(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        saveData(query);
    }



    public static String ekleme(String proje) throws SQLException {
        ResultSet rs = null;
        String query = "INSERT INTO projeler(adi) VALUES(?)";


        try {
            ps = DBConnection.connect.prepareStatement(query);
            ps.setString(1, proje);
            ps.executeUpdate();
            String a= "işlem başarılı";
            return a;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Projeler> getProjeComboBox() {
        ObservableList<Projeler> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM projeler";
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Projeler projeler = new Projeler(
                        rs.getString(2)

                );
                list.add(projeler);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
