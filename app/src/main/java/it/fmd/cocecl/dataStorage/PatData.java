package it.fmd.cocecl.dataStorage;

import java.io.Serializable;

public class PatData implements Serializable {

    String patID;
    String patplsnr;

    String patfname; // Familyname
    String patname; // Name
    String patbdate;
    String patsvnr;
    String patgender;

    String patward;
    String patdiagnosis;

    public PatData() {
    }

    public String getPatID() {
        return patID;
    }

    public void setPatID(String patID) {
        this.patID = patID;
    }

    public String getPatplsnr() {
        return patplsnr;
    }

    public void setPatplsnr(String patplsnr) {
        this.patplsnr = patplsnr;
    }

    public String getPatfname() {
        return patfname;
    }

    public void setPatfname(String patfname) {
        this.patfname = patfname;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getPatbdate() {
        return patbdate;
    }

    public void setPatbdate(String patbdate) {
        this.patbdate = patbdate;
    }

    public String getPatgender() {
        return patgender;
    }

    public void setPatgender(String patgender) {
        this.patgender = patgender;
    }

    public String getPatsvnr() {
        return patsvnr;
    }

    public void setPatsvnr(String patsvnr) {
        this.patsvnr = patsvnr;
    }

    public String getPatward() {
        return patward;
    }

    public void setPatward(String patward) {
        this.patward = patward;
    }

    public String getPatdiagnosis() {
        return patdiagnosis;
    }

    public void setPatdiagnosis(String patdiagnosis) {
        this.patdiagnosis = patdiagnosis;
    }
}

