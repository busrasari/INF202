package project.Models;


public class Iller {
    private int ilId;
    private String ilAdi;

    public Iller(int ilId, String ilAdi) {
        super();
        this.ilId = ilId;
        this.ilAdi = ilAdi;
    }

    private Iller(int ilId) {
        this.ilId = ilId;
    }

    public Iller() {

    }


    public Iller(String ilAdi) {
        this.ilAdi = ilAdi;
    }

    public int getIlId() {
        return ilId;
    }

    public void setIlId(int ilId) {
        this.ilId = ilId;
    }

    public String getIlAdi() {
        return ilAdi;
    }

    public void setIlAdi(String ilAdi) {
        this.ilAdi = ilAdi;
    }


    @Override
    public String toString() {
        return this.getIlAdi();
    }

}