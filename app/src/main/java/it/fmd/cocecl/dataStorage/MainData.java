package it.fmd.cocecl.dataStorage;


import java.io.Serializable;

public class MainData implements Serializable {
    private static final long serialVersionUID = 1L;

    public String amb;
    public String ambtype; // AMBULANZ, ÜBUNG, EINSATZ

    public MainData() {

    }

    public String getAmb() {
        return amb;
    }

    public void setAmb(String amb) {
        this.amb = amb;
    }

    public String getAmbtype() {
        return ambtype;
    }

    public void setAmbtype(String ambtype) {
        this.ambtype = ambtype;
    }
}
