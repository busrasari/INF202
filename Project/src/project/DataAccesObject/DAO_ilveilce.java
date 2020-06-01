package project.DataAccesObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.Models.Ilceler;
import project.Models.Iller;
import project.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_ilveilce {
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;
    private static PreparedStatement ps;

    public ArrayList<Iller> getIller(){
        ArrayList<Iller> illerlist=new ArrayList<>();
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
    public ObservableList<Ilceler> getileGoreIlce() {
        ObservableList<Ilceler> ilcelerlist = FXCollections.observableArrayList();
        try{
            connect = database.getConnection();
            String query="SELECT * FROM ilceler";
            pstmt = connect.prepareStatement(query);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                Ilceler ilceler=new Ilceler( rs.getString(2));
                ilcelerlist.add(ilceler);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ilcelerlist;
    }
}
