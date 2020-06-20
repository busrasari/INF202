package project.Ressource;

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
    public int getid(){ return e_id.get();}
    public  String getcihaz(){ return Cihaz.get(); }
    public  String getKutup(){ return KutupM.get(); }
    public  String getMptao(){ return MpTAO.get(); }
    public  String getMteknik(){ return MTeknik.get(); }
    public  String getUvs(){ return Uv.get(); }
    public  String getisik(){ return Isik.get(); }



    @Override
    public String toString() {
        return this.getCihaz().getValue() + " " + this.getKutupM().getValue() +this.getMpTAO().getValue() + this.getMTeknik().getValue()+this.getUv().getValue()+this.getIsik().getValue() ;
    }




}








