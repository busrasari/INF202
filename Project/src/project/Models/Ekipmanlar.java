package project.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import project.DataAccesObject.DAO_Ekipman;
import project.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ekipmanlar {
    private IntegerProperty e_id;
    private StringProperty Cihaz;
    private StringProperty KutupM;
    private StringProperty MpTAO;
    private StringProperty MTeknik;
    private StringProperty Uv;
    private StringProperty Isik;
    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;



    public Ekipmanlar(int e_id, String cihaz, String kutupM, String mpTAO, String MTeknik, String uv, String isik) {
        this.e_id=new SimpleIntegerProperty(e_id);
        this.Cihaz = new SimpleStringProperty(cihaz);
        this.KutupM= new SimpleStringProperty(kutupM);
        this.MpTAO = new SimpleStringProperty(mpTAO);
        this.MTeknik = new SimpleStringProperty(MTeknik);
        this.Uv = new SimpleStringProperty(uv);
        this.Isik= new SimpleStringProperty(isik);

    }

    public Ekipmanlar( String cihazs, String kutupMs, String mpTAOs, String MTekniks, String uvs, String isiks) {
        this.Cihaz = new SimpleStringProperty(cihazs);
        this.KutupM= new SimpleStringProperty(kutupMs);
        this.MpTAO = new SimpleStringProperty(mpTAOs);
        this.MTeknik = new SimpleStringProperty(MTekniks);
        this.Uv = new SimpleStringProperty(uvs);
        this.Isik= new SimpleStringProperty(isiks);
    }

    public Ekipmanlar(String Cihaz) {
        this.Cihaz = new SimpleStringProperty(Cihaz);
    }

  /*  public Ekipmanlar(int id) {
        this.e_id= new SimpleIntegerProperty(id);
    } */

    public Ekipmanlar(Ekipmanlar id) {
        this.e_id= new SimpleIntegerProperty();
    }



    public IntegerProperty getE_id(){ return e_id;}

    public StringProperty getCihaz() { return Cihaz;
    }

    public StringProperty getKutupM() {
        return KutupM;
    }


    public StringProperty getMpTAO() {
        return MpTAO;
    }

    public StringProperty getMTeknik() {
        return MTeknik;
    }

    public StringProperty getUv() {
        return Uv;
    }

    public StringProperty getIsik() { return Isik;
    }

    @Override
    public String toString() {
        return this.getCihaz().getValue() + " " + this.getKutupM().getValue() +this.getMpTAO().getValue() + this.getMTeknik().getValue()+this.getUv().getValue()+this.getIsik().getValue() ;
    }
   /* public ObservableList<String> showCihaz(String sql) throws SQLException {
        ObservableList<String> cihazList = FXCollections.observableArrayList();
        connect = db.getConnection();
        pstmt = connect.prepareStatement(sql);
        rs = pstmt.executeQuery("SELECT ekipman_cihaz from ekipmanlar");
        while(rs.next()){
            cihazList.addAll(rs.getString("ekipman_cihaz"));
        }
        return cihazList;
    } */

    public static Ekipmanlar ekipman(String cihaz){
        System.out.println("1111");
        connect = database.getConnection();
        String query="select * from ekipman where Cihaz=?";
        Ekipmanlar ekipmanlar=null;
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, cihaz);

            rs = pstmt.executeQuery();
            System.out.println("tryy");
            while(rs.next()){
                System.out.println("laylayyyy");
                ekipmanlar=new Ekipmanlar(rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Ekipman.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ekipmanlar;
    }

}








