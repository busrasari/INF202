/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * @author busra
 */
public class Musteriler  {
    private IntegerProperty Mid;
    private StringProperty Firmaname;
    private StringProperty il;
    private StringProperty ilce;
    private StringProperty teklifno;
    private StringProperty isemrino;

    private static final DBConnection database = new DBConnection();
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Connection connect;



    public Musteriler(int id,String firmaname, String il, String ilce, String isemrino, String teklifno) {
        this.Mid=new SimpleIntegerProperty(id);
        this.Firmaname = new SimpleStringProperty(firmaname);
        this.il= new SimpleStringProperty(il);
        this.ilce = new SimpleStringProperty(ilce);
        this.isemrino = new SimpleStringProperty(isemrino);
        this.teklifno = new SimpleStringProperty(teklifno);

    }

    public Musteriler(String Firmaname) {
        this.Firmaname = new SimpleStringProperty(Firmaname);
    }

    public Musteriler(String adi,String il, String ilce,String isemri, String teno) {
        this.Firmaname = new SimpleStringProperty(adi);
        this.isemrino = new SimpleStringProperty(isemri);
        this.il= new SimpleStringProperty(il);
        this.ilce= new SimpleStringProperty(ilce);
        this.teklifno = new SimpleStringProperty(teno);
    }


    public IntegerProperty getMid() {
        return Mid;
    }

    public StringProperty getFirmaname() { return Firmaname;
    }

    public StringProperty getIl() {
        return il;
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


    @Override
    public String toString() {
        return this.getFirmaname().getValue() + this.getIl().getValue()+ this.getIlce().getValue()+ this.getIsemrino().getValue()+this.getTeklifno().getValue();
    }

    public static Musteriler musteriler(String firma){
        connect = database.getConnection();
        String query="select * from musteri where Firma_Adi=?";
        Musteriler musteriler=null;
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, firma);
            rs = pstmt.executeQuery();
            while(rs.next()){
                musteriler=new Musteriler(rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO_Ekipman.class.getName()).log(Level.SEVERE, null, ex);
        }


        return musteriler;
    }
}
    

