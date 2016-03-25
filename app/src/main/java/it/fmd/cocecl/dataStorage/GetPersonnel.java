package it.fmd.cocecl.dataStorage;

import android.app.Application;

/**
 * Get personnel from DB, write into UnitInfo Screen
 */

public class GetPersonnel {

    private static GetPersonnel mInstance = null;

    String MAName;
    String MAFamilyname;
    String MADnr;
    String MAEmail;

    public static GetPersonnel getInstance() {
        if (mInstance == null) {
            mInstance = new GetPersonnel();
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
