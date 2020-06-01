package project.Models;

public class Ilceler {
    private int Ilceid;
    private int ilId;
    private String IlceAdi;

    public Ilceler(int ilceid, int ilId, String ilceAdi) {
        super();
        Ilceid = ilceid;
        this.ilId = ilId;
        IlceAdi = ilceAdi;
    }

    public Ilceler(String ilceAdi) {
        IlceAdi = ilceAdi;
    }

    public Ilceler() {

    }

    public int getIlceid() {
        return Ilceid;
    }

    public void setIlceid(int ilceid) {
        Ilceid = ilceid;
    }

    public int getIlId() {
        return ilId;
    }

    public void setIlId(int ilId) {
        this.ilId = ilId;
    }

    public String getIlceAdi() {
        return IlceAdi;
    }

    public void setIlceAdi(String ilceAdi) {
        IlceAdi = ilceAdi;
    }

    @Override
    public String toString() {
        return this.IlceAdi;
    }


}
