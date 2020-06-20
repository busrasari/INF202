package project.Ressource;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class YuzeyDurumu {
    private IntegerProperty yd_id;
    private StringProperty yuzeydurumu;

    public YuzeyDurumu(int yd_id, String yuzeydurumu) {
        this.yd_id = new SimpleIntegerProperty(yd_id);
        this.yuzeydurumu = new SimpleStringProperty(yuzeydurumu);
    }
    public  YuzeyDurumu(String yd){
        this.yuzeydurumu= new SimpleStringProperty(yd);

    }
    @Override
    public String toString() {
        return this.getYuzeydurumu().getValue();
    }

    public IntegerProperty getYd_id(){ return yd_id;}

    public StringProperty getYuzeydurumu() { return yuzeydurumu;
    }
    public int getYDid(){ return yd_id.get();}
    public  String getYDurumu(){ return yuzeydurumu.get(); }
}
