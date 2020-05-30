package project.Classlar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Projeler {
    private IntegerProperty proje_id;
    private StringProperty proje;

    public Projeler(int proje_id, String proje) {
        this.proje_id = new SimpleIntegerProperty(proje_id);
        this.proje = new SimpleStringProperty(proje);
    }

    public Projeler (String proje){
        this.proje=new SimpleStringProperty(proje);
    }

    @Override
    public String toString() {
        return this.getProje().getValue();
    }

    public IntegerProperty getProje_id(){ return proje_id;}

    public StringProperty getProje() { return proje;
    }

}
