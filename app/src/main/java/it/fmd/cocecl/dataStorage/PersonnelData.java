package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

/**
 * Get personnel from DB, write into UnitInfo Screen
 * LogIn Request
 */

public class PersonnelData implements Serializable {
    private static final long serialVersionUID = 1L;

    String MAName;
    String MAFamilyname;
    String MADnr;
    String MAEmail;

    String MAPassword;
    String gcmID;

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

    public String getMAPassword() {
        return MAPassword;
    }

    public void setMAPassword(String MAPassword) {
        this.MAPassword = MAPassword;
    }

    public String getGcmID() {
        return gcmID;
    }

    public void setGcmID(String gcmID) {
        this.gcmID = gcmID;
    }
}
