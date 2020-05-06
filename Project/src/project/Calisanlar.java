package project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/*
  @author busra
 */
public class Calisanlar {
    private final StringProperty pname;
    private final StringProperty pLastname;
    private final StringProperty pSeviye;
    private final StringProperty pID;

    public Calisanlar(String pID, String pname, String pLastname, String pSeviye) {
        this.pID = new SimpleStringProperty(pID);
        this.pname = new SimpleStringProperty(pname);
        this.pLastname = new SimpleStringProperty(pLastname);
        this.pSeviye = new SimpleStringProperty(pSeviye);

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


}
