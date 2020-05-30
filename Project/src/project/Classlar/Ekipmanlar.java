package project.Classlar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ekipmanlar {
    private IntegerProperty e_id;
    private StringProperty Cihaz;
    private StringProperty KutupM;
    private StringProperty MpTAO;
    private StringProperty MTeknik;
    private StringProperty Uv;
    private StringProperty Isik;
    private String e_idS;
    private String Cihazs;
    private String KutupMs;
    private String MpTAOs;
    private String MTekniks;
    private String Uvs;
    private String Isiks;


    public Ekipmanlar(int e_id, String cihaz, String kutupM, String mpTAO, String MTeknik, String uv, String isik) {
        this.e_id=new SimpleIntegerProperty(e_id);
        this.Cihaz = new SimpleStringProperty(cihaz);
        this.KutupM= new SimpleStringProperty(kutupM);
        this.MpTAO = new SimpleStringProperty(mpTAO);
        this.MTeknik = new SimpleStringProperty(MTeknik);
        this.Uv = new SimpleStringProperty(uv);
        this.Isik= new SimpleStringProperty(isik);

    }

    private Ekipmanlar(String e_idS, String cihazs, String kutupMs, String mpTAOs, String MTekniks, String uvs, String isiks) {
        this.e_idS = e_idS;
        Cihazs = cihazs;
        KutupMs = kutupMs;
        MpTAOs = mpTAOs;
        this.MTekniks = MTekniks;
        Uvs = uvs;
        Isiks = isiks;
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
        return this.getCihaz().getValue();
    }

}






