package project.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.Calisanlar;
import project.Ilceler;
import project.Iller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_ilveilce {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static PreparedStatement ps;

    public ObservableList<Iller> getIller() {
        ObservableList<Iller> illerlist = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            String query="SELECT * FROM iller";
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Iller iller=new Iller(rs.getString(2));

                illerlist.add(iller);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return illerlist;
    }
    public ObservableList<Ilceler> getileGoreIlce(int ID) {
        ObservableList<Ilceler> ilcelerlist = FXCollections.observableArrayList();
        try{
            connect = database.getConnection();
            String query="SELECT * FROM ilceler where il_no=" +ID;
            pstmt = connect.prepareStatement(query);
            rs=pstmt.executeQuery();
            System.out.println("try blok");
            while(rs.next()) {
                Ilceler ilceler=new Ilceler( rs.getString(2));
                ilcelerlist.add(ilceler);
                System.out.println("while");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ilcelerlist;
    }
}
