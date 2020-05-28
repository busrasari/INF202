package project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/*
  @author busra
 */
public class Calisanlar {
    private StringProperty pname;
    private StringProperty pLastname;
    private StringProperty pSeviye;
    private StringProperty pID;



    public Calisanlar(String pID, String pname, String pLastname, String pSeviye) {
        this.pID = new SimpleStringProperty(pID);
        this.pname = new SimpleStringProperty(pname);
        this.pLastname = new SimpleStringProperty(pLastname);
        this.pSeviye = new SimpleStringProperty(pSeviye);

    }

    public Calisanlar(String pname, String pLastname) {
        this.pname = new SimpleStringProperty(pname);
        this.pLastname = new SimpleStringProperty(pLastname);
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

    public StringProperty getpID() {
        return pID;
    }

    @Override
    public String toString() {
        return this.getpname().getValue() + "  " + this.getpLastname().getValue();
    }
}
