package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

/*
 * Load personnel, add to unitinfo screen
 * Shows all attached members
 */

public class PersonnelData implements Serializable {
    private static final long serialVersionUID = 1L;

    String MAName;
    String MAFamilyname;
    String MADnr;
    String MAEmail;
    String MAPhnnbr;

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

    public String getMAPhnnbr() {
        return MAPhnnbr;
    }

    public void setMAPhnnbr(String MAPhnnbr) {
        this.MAPhnnbr = MAPhnnbr;
    }
}
