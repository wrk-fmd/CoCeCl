package it.fmd.cocecl.dataStorage;


public class MainData {

    private static MainData mInstance = null;


    public String amb;
    public String ambtype; // AMBULANZ, ÜBUNG, EINSATZ

    public static MainData getInstance() {
        if (mInstance == null) {
            mInstance = new MainData();
        }
        return mInstance;
    }

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
