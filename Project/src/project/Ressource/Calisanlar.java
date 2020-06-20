package project.Ressource;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/*
  @author busra
 */
public class Calisanlar {
    private StringProperty pname;
    private StringProperty pLastname;
    private StringProperty pSeviye;
    private IntegerProperty pID;



    public Calisanlar(int pID, String pname, String pLastname, String pSeviye) {
        this.pID = new SimpleIntegerProperty(pID);
        this.pname = new SimpleStringProperty(pname);
        this.pLastname = new SimpleStringProperty(pLastname);
        this.pSeviye = new SimpleStringProperty(pSeviye);

    }

    public Calisanlar(String pname, String pLastname) {
        this.pname = new SimpleStringProperty(pname);
        this.pLastname = new SimpleStringProperty(pLastname);
    }

    public Calisanlar(String name, String nachn, String seviye) {
        this.pname = new SimpleStringProperty(name);
        this.pLastname = new SimpleStringProperty(nachn);
        this.pSeviye = new SimpleStringProperty(seviye);
    }

    public String getppName() {
        return pname.get();
    }
    public String getLastname() {
        return pLastname.get();
    }
    public String getSeviye() {
        return pSeviye.get();
    }
    public int getID(){
        return pID.get();
    }
    public StringProperty getpname() {
        return pname;
    }

    public StringProperty getpLastname() {
        return pLastname;
    }


    public StringProperty getpSeviye() {
        return pSeviye;
    }

    public IntegerProperty getpID() {
        return pID;
    }

    @Override
    public String toString() {
        return this.getpname().getValue() + "  " + this.getpLastname().getValue();
    }
}
