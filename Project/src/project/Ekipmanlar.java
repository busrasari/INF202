package project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ekipmanlar {
    private StringProperty Cihaz;
    private StringProperty KutupM;
    private StringProperty MpTAO;
    private StringProperty MTeknik;
    private StringProperty Uv;
    private StringProperty Isik;


    public Ekipmanlar(String cihaz, String kutupM, String mpTAO, String MTeknik, String uv, String isik) {
        this.Cihaz = new SimpleStringProperty(cihaz);
        this.KutupM= new SimpleStringProperty(kutupM);
        this.MpTAO = new SimpleStringProperty(mpTAO);
        this.MTeknik = new SimpleStringProperty(MTeknik);
        this.Uv = new SimpleStringProperty(uv);
        this.Isik= new SimpleStringProperty(isik);

    }

    private String getCihaz() {
        return Cihaz.get();
    }

    public StringProperty cihazProperty() {
        return Cihaz;
    }

    private void setCihaz(String cihaz) {
        this.Cihaz.set(cihaz);
    }

    private String getKutupM() {
        return KutupM.get();
    }

    public StringProperty kutupMProperty() {
        return KutupM;
    }

    private void setKutupM(String kutupM) {
        this.KutupM.set(kutupM);
    }

    private String getMpTAO() {
        return MpTAO.get();
    }

    public StringProperty mpTAOProperty() {
        return MpTAO;
    }

    private void setMpTAO(String mpTAO) {
        this.MpTAO.set(mpTAO);
    }

    private String getMTeknik() {
        return MTeknik.get();
    }

    public StringProperty MTeknikProperty() {
        return MTeknik;
    }

    private void setMTeknik(String MTeknik) {
        this.MTeknik.set(MTeknik);
    }

    private String getUv() {
        return Uv.get();
    }

    public StringProperty uvProperty() {
        return Uv;
    }

    private void setUv(String uv) {
        this.Uv.set(uv);
    }

    private String getIsik() {
        return Isik.get();
    }

    public StringProperty isikProperty() {
        return Isik;
    }

    private void setIsik(String isik) {
        this.Isik.set(isik);
    }
}
