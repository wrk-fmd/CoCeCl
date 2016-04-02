package it.fmd.cocecl.dataStorage;

/**
 * Get personnel from DB, write into UnitInfo Screen
 */

public class PersonnelData {

    private static PersonnelData mInstance = null;

    String MAName;
    String MAFamilyname;
    String MADnr;
    String MAEmail;

    public static PersonnelData getInstance() {
        if (mInstance == null) {
            mInstance = new PersonnelData();
        }
        return mInstance;
    }

    public String getMAFamilyname() {
        return MAFamilyname;
    }

    public void setMAFamilyname(String MAFamilyname) {
        this.MAFamilyname = MAFamilyname;
    }

    public String getMAName() {
        return MAName;
    }

    public void setMAName(String MAName) {
        this.MAName = MAName;
    }

    public String getMADnr() {
        return MADnr;
    }

    public void setMADnr(String MADnr) {
        this.MADnr = MADnr;
    }

    public String getMAEmail() {
        return MAEmail;
    }

    public void setMAEmail(String MAEmail) {
        this.MAEmail = MAEmail;
    }
}
