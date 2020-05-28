/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author busra
 */
public class Musteriler  {
    private StringProperty Mid;
    private StringProperty Firmaname;
    private StringProperty il;
    private StringProperty ilce;
    private StringProperty teklifno;
    private StringProperty isemrino;

    /*
    Firma Adı varchar(45)
    İl varchar(45)
    İlce varchar(45)
    İş Emri No varchar(45)
    Teklif No
     */

    public Musteriler(String id,String firmaname, String il, String ilce, String teklifno, String isemrino) {
        this.Mid=new SimpleStringProperty(id);
        this.Firmaname = new SimpleStringProperty(firmaname);
        this.il= new SimpleStringProperty(il);
        this.ilce = new SimpleStringProperty(ilce);
        this.teklifno = new SimpleStringProperty(teklifno);
        this.isemrino = new SimpleStringProperty(isemrino);


    }


    public StringProperty getMid() {
        return Mid;
    }

    public StringProperty getFirmaname() { return Firmaname;
    }

    public StringProperty getIl() {
        return ilce;
    }

    public StringProperty getIlce() {
        return ilce;
    }

    public StringProperty getTeklifno() {
        return teklifno;
    }

    public StringProperty getIsemrino() {
        return isemrino;
    }
}

    

